package teamB.BIS2211.TankApp.WebController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import teamB.BIS2211.TankApp.Model.LeaderboardData;
import teamB.BIS2211.TankApp.Model.LeaderboardDataService;
import teamB.BIS2211.TankApp.Model.LeaderboardEntry;

@Controller
public class LeaderboardController 
{

    @Autowired
    LeaderboardDataService leaderboardDataService;

    @GetMapping("/leaderboard")
    public String leaderboard(@CookieValue(value = "username", required=false, defaultValue = "") String username, final Model model)
    {
        model.addAttribute("username", username);
        ArrayList<LeaderboardEntry> lbEntries = new ArrayList<LeaderboardEntry>(); 
        for(String name : getNameList(leaderboardDataService.getAllData()))
        { 
            lbEntries.add(new LeaderboardEntry(name, leaderboardDataService.getDataByUser(name))); 
        }
        model.addAttribute("leaderboardEntries", lbEntries); 
        return "leaderboard";
    }

    private static ArrayList<String> getNameList(ArrayList<LeaderboardData> lbData)
    {
        ArrayList<String> nameList = new ArrayList<String>();
        for(LeaderboardData entry : lbData)
        {
            if(!nameList.contains(entry.getUser()))
            {
                nameList.add(entry.getUser()); 
            }
        }
        return nameList; 
    }
}