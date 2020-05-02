package API;

import model.GasStation;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ApiData
{		
	public static String test() 
	{
        return printStationNames(getTestJSON());
    }

    public static ArrayList<GasStation> getTestJSON() 
    {    	
        URL url;
        HttpURLConnection request;
        try 
        {
            url = new URL(buildRequestString("48.878550", "8.717204", 5));
            request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("GET");
            request.connect();
            
            JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
            JsonObject obj = element.getAsJsonObject();
            
            System.out.println("Checking JSON status"); 
            if(obj.get("status").getAsString().equals("ok"))
            {	  
            	System.out.println("Status: OK"); 
	            String jsonString = obj.toString().substring(obj.toString().indexOf("["), (obj.toString().indexOf("]")+1)); 
	            
	            System.out.println("JSON file: " + jsonString);
	            System.out.println("Trying to parse the JSON file...");
	            
	            Type listType = new TypeToken<ArrayList<GasStation>>(){}.getType();
	            ArrayList<GasStation> gsList = new Gson().fromJson(jsonString, listType);
	
	            return gsList;  
            }
            System.out.println("Status: NOT OK");
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        	System.out.println("There was an error parsing the JSON file.");
        }
        return new ArrayList<GasStation>();
    }

    //returns a https request string with the specified latitude, longitude and radius
    public static String buildRequestString(String lat, String lon, double radius) 
    {
        return "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + lat + "&lng=" + lon + "&rad=" + radius + "&sort=dist&type=all&apikey=" + Api_Key.API_KEY;
    }

    public static String printStationNames(ArrayList<GasStation> list) 
    {    	    	
        String result = ""; 
        for(GasStation gs: list)
        {
        	result = result + gs + "<br/>"; 
        }
        System.out.println(result); 
        return result; 
    }
}
