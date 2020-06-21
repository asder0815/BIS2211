package teamB.BIS2211.TankApp.WebController;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamB.BIS2211.TankApp.ApiData.ApiData;
import teamB.BIS2211.TankApp.ApiData.ApiKey;
import teamB.BIS2211.TankApp.Model.GasStation;

@Controller
public class MainController 
{
  @GetMapping("/main")
  public String main(
    @RequestParam(name="name", required=false, defaultValue="lizer") final String name,
    @RequestParam(name="lat", required=false, defaultValue="") final String lat,
    @RequestParam(name="lon", required=false, defaultValue="") final String lon,
    @RequestParam(name="rad", required=false, defaultValue="") final String rad,
    @RequestParam(name="fuelType", required=false, defaultValue="all") final String fuelType,
    @CookieValue(value = "cfavourites", defaultValue = "") String favs,
    final Model model) 
  {
    model.addAttribute("name", name);

    final ArrayList<GasStation> gsList = ApiData.getJSON(convertReqestparameter(lat), convertReqestparameter(lon), convertReqestparameter(rad));
    for(GasStation gs: gsList)
    {
      gs.setPredictionDiesel(gs.getCurrentPrediction("diesel"));
      gs.setPredictionE5(gs.getCurrentPrediction("e5"));
      gs.setPredictionE10(gs.getCurrentPrediction("e10"));
    }
    model.addAttribute("gsList", gsList);

    final String[] favourites = splitFavString(favs); 
    final ArrayList<GasStation> favList = ApiData.getJSON(favourites);
    for(GasStation gs: favList)
    {
      gs.setPredictionDiesel(gs.getCurrentPrediction("diesel"));
      gs.setPredictionDiesel(gs.getCurrentPrediction("e5"));
      gs.setPredictionDiesel(gs.getCurrentPrediction("e10"));
    }
    model.addAttribute("favList", favList);

    model.addAttribute("favString", favs);

    model.addAttribute("rad", rad);

    model.addAttribute("lat", lat);
    model.addAttribute("lon", lon);

    model.addAttribute("jsList", Arrays.asList(gsList));

    model.addAttribute("fuelType", fuelType);

    String key_opencage = new ApiKey().getApiKey("opencage"); 
    String key_leaflet = new ApiKey().getApiKey("leaflet");
    model.addAttribute("key_opencage", key_opencage); 

    model.addAttribute("key_leaflet", key_leaflet);
		return "main";
  }

  private static float convertReqestparameter(final String s)
  {
    if(s == null) return 0;
		try
		{
			return Float.parseFloat(s);
		}
		catch(final NumberFormatException ex)
		{
			return 0;
		}
  }

  private static String[] splitFavString(final String s)
  {
    return s.split("/"); 
  }
}