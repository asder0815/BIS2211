package teamB.BIS2211.TankApp.Model;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
    LeaderboardDataService leaderboardDataService;
    
    //@Scheduled(fixedRate = 86400000)
    @Scheduled(fixedRate = 30000000)
	public void databaseMaintenance() {
        System.out.println("Starting database maintenance");
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
        URL url;
        HttpURLConnection request;
        try 
        {
            System.out.println("Trying to get prices from: " + buildRequestString(data.getGsID(), data.getType()));
            url = new URL(buildRequestString(data.getGsID(), data.getType())); 
            request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("GET");
            request.connect();
            final JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
            final JsonArray arr = element.getAsJsonArray();
            System.out.println("Trying to parse JSON array...");
            float priceDev = arr.get(hour).getAsJsonObject().get("price").getAsFloat();
            System.out.println("Price deviation: " + priceDev);
            return priceDev; 
        }
        catch (final Exception e) 
        {
            System.out.println("There was an error parsing the JSON data");
        }
        return 0; 
    }

    private static String buildRequestString(String id, String type)
    {
        String newID = id.replace("-", "/");
        return "https://www.volzinnovation.com/fuel_price_variations_germany/data/" + newID + "/" + type.toLowerCase() + ".json"; 
    }

    private static int getHourOfEntry(LeaderboardData data)
    {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(data.getTimestamp()); 
        return cal.get(Calendar.HOUR_OF_DAY); 
    }
}