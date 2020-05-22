package API;

import model.GasStation;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ApiData
{		
    public static ArrayList<GasStation> getJSON(float lat, float lon, float rad) 
    {    	
        URL url;
        HttpURLConnection request;
        try 
        {
            url = new URL(buildRequestString(lat, lon, rad));
            request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("GET");
            request.connect();
            
            JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
            JsonObject obj = element.getAsJsonObject();
            if(obj.get("status").getAsString().equals("ok"))
            {	  
	            String jsonString = obj.toString().substring(obj.toString().indexOf("["), (obj.toString().indexOf("]")+1)); 
	            Type listType = new TypeToken<ArrayList<GasStation>>(){}.getType();
	            ArrayList<GasStation> gsList = new Gson().fromJson(jsonString, listType);
	
	            return gsList;  
            }            
        } 
        catch (Exception e) 
        {
        	System.out.println("There was an error parsing the JSON file.");
        }
        return new ArrayList<GasStation>();
    }
    
    public static ArrayList<GasStation> getJSON(String[] favList) 
    {   
    	ArrayList<GasStation> gsList = new ArrayList<GasStation>(); 
    	System.out.println(Arrays.toString(favList)); 
    	for(String s: favList)
    	{
	        URL url;
	        HttpURLConnection request;
	        try 
	        {
	            url = new URL(buildRequestString(s));
	            request = (HttpURLConnection) url.openConnection();
	            request.setDoOutput(true);
	            request.setRequestMethod("GET");
	            request.connect();
	            
	            JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
	            JsonObject obj = element.getAsJsonObject();
	            if(obj.get("status").getAsString().equals("ok"))
	            {	  
	            	int startIndex = obj.toString().indexOf("station") + 9; 
		            String jsonString = obj.toString().substring(startIndex, obj.toString().length()-1);             	
		            GasStation gs = new Gson().fromJson(jsonString, GasStation.class);
		            gsList.add(gs); 		
	            }	            
	        } 
	        catch (Exception e) 
	        {
	        	System.out.println("There was an error parsing the JSON file.");
	        }
    	}
        return gsList; 
    }

    public static String buildRequestString(float lat, float lon, float radius) 
    {
        return "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + lat + "&lng=" + lon + "&rad=" + radius + "&sort=dist&type=all&apikey=" + new Api_Key().getApiKey();
    }
    
    public static String buildRequestString(String id)
    {
    	return "https://creativecommons.tankerkoenig.de/json/detail.php?id=" + id + "&apikey=" + new Api_Key().getApiKey(); 
    }
}
