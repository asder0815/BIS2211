package teamB.BIS2211.TankApp.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import teamB.BIS2211.TankApp.ApiData.ApiData;

@Controller
public class TestController 
{
    @GetMapping("/test")
    public String test(final Model model)
    {
        model.addAttribute("apiStringGoogle", ApiData.getApiStringGoogle());
        System.out.println("hallo Welt");
        System.out.println("hallo Welt");
        System.out.println("hallo Welt");
        return "test";
    }

}