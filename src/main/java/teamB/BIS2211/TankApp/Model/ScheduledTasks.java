package teamB.BIS2211.TankApp.Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks 
{
    @Autowired
    LeaderboardDataService leaderboardDataService;

    //@Scheduled(fixedRate = 86400000)
    @Scheduled(fixedRate = 30000)
    //@Scheduled(cron = "0 30 3 1/1 * ?")
	public void databaseMaintenance() {
        System.out.println("Starting database maintenance");
        System.out.println("Getting new price data...");
        updatePriceDataRepo();

        ArrayList<LeaderboardData> ldData = leaderboardDataService.getAllData(); 
        for(LeaderboardData data: ldData)
        {
            calculateAveragePrice(data);
            System.out.println("------------------------------");
        }
    }

    private void calculateAveragePrice(LeaderboardData data)
    {
        System.out.println("Checking entry with ID: " + data.getId()); 
        if(data.isChecked())
        {
            System.out.println("Entry already was updated.");
            return; 
        }
        else
        {
            System.out.println("Entry isn't updated.");
            data.setPriceDeviation(getPriceDeviationByHour(data, getHourOfEntry(data)));
            leaderboardDataService.updateData(data); 
        }
    }

    private static float getPriceDeviationByHour(LeaderboardData data, int hour)
    {
        try 
        {
            String path = "gasStationData/data/" + data.getGsID().replace("-", "/") + "/" +  data.getType().toLowerCase() + ".json"; 
            Reader reader = Files.newBufferedReader(Paths.get(path));
            JsonElement element = JsonParser.parseReader(reader); 
            JsonArray arr = element.getAsJsonArray();
            float priceDev = arr.get(hour).getAsJsonObject().get("price").getAsFloat();        
            reader.close();
            return priceDev; 
        
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        return 0; 
    }

    private static int getHourOfEntry(LeaderboardData data)
    {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(data.getTimestamp()); 
        return cal.get(Calendar.HOUR_OF_DAY); 
    }

    private static boolean repoReady = false; 

    private static void updatePriceDataRepo()
    {
        if (!repoReady) 
        {
            System.out.print("ERROR! Repository doesn't exist!"); 
            return; 
        }
        Repository localRepo = null;
        Git git = null;
        try 
        {
            localRepo = new FileRepository(new File("gasStationData/.git"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();  
        }
        git = new Git(localRepo);
        
        PullCommand pullCmd = git.pull();
        try 
        {
            pullCmd.setProgressMonitor(new TextProgressMonitor(new PrintWriter(System.out))).call();

        } 
        catch (GitAPIException e) 
        {
            e.printStackTrace();  
        }
        git.close(); 
    }

    @PostConstruct
    private static void getInitialPriceDataRepo()
    {
        if (!Files.isDirectory(Paths.get("gasStationData")))
        {
            System.out.println("Couldn't find price data. Cloning repository..."); 
            try 
            {
                Git result = Git.cloneRepository().setProgressMonitor(new TextProgressMonitor(new PrintWriter(System.out))).setURI("https://github.com/volzinnovation/fuel_price_variations_germany.git").setDirectory(new File("gasStationData/")).call();
                result.getRepository().close(); 
            } 
            catch (GitAPIException e) 
            {
                e.printStackTrace();
            }
            System.out.println("Finished cloning the repository...");
            repoReady = true; 
            updatePriceDataRepo();
        }
        else
        {
            System.out.println("Price data found.");
            repoReady = true; 
            updatePriceDataRepo();
        }
    }
}