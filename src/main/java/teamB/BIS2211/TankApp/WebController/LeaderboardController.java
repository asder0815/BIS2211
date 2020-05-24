package teamB.BIS2211.TankApp.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController 
{
    @GetMapping("/leaderboard")
    public String leaderboard(@CookieValue(value = "username", required=false, defaultValue = "") String username, final Model model)
    {
        model.addAttribute("username", username);
        return "leaderboard";
    }
}