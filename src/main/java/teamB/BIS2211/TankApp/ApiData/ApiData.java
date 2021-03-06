package teamB.BIS2211.TankApp.ApiData;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import teamB.BIS2211.TankApp.Model.GasStation;

public class ApiData 
{
    public static ArrayList<GasStation> getJSON(final float lat, final float lon, final float rad) {
        URL url;
        HttpURLConnection request;
        try 
        {
            url = new URL(buildRequestString(lat, lon, rad));
            request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("GET");
            request.connect();
            final JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
            final JsonObject obj = element.getAsJsonObject();
            if (obj.get("status").getAsString().equals("ok")) {
                final String jsonString = obj.toString().substring(obj.toString().indexOf("["),(obj.toString().indexOf("]") + 1));
                final Type listType = new TypeToken<ArrayList<GasStation>>(){}.getType();
                final ArrayList<GasStation> gsList = new Gson().fromJson(jsonString, listType);
                return gsList;
            }
        } 
        catch (final Exception e) 
        {
            System.out.println("There was an error parsing the JSON data");
        }
        return new ArrayList<GasStation>();
    }

    public static ArrayList<GasStation> getJSON(String[] favList) 
    {   
    	ArrayList<GasStation> gsList = new ArrayList<GasStation>(); 
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
                System.out.println("Creating test data...");
	        }
    	}
        return gsList; 
    }

    private static String buildRequestString(final float lat, final float lon, final float radius) 
    {
        return "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + lat + "&lng=" + lon + "&rad=" + radius + "&sort=dist&type=all&apikey=" + "97200d44-73e2-51ae-f60b-8df9e06c0b0e";
    }

    private static String buildRequestString(String id)
    {
    	return "https://creativecommons.tankerkoenig.de/json/detail.php?id=" + id + "&apikey=" + "97200d44-73e2-51ae-f60b-8df9e06c0b0e"; 
    }

    public static ArrayList<GasStation> createTestData()
    {
        ArrayList<GasStation> testList = new ArrayList<>(); 
        testList.add(new GasStation("1","2","3","4","5", 0, 0, 0, 1, 2, 3, false, null, null));
        testList.add(new GasStation("1","2","3","4","5", 0, 0, 0, 1, 2, 3, false, null, null));
        testList.add(new GasStation("1","2","3","4","5", 0, 0, 0, 1, 2, 3, false, null, null));
        testList.add(new GasStation("1","2","3","4","5", 0, 0, 0, 1, 2, 3, false, null, null));
        testList.add(new GasStation("1","2","3","4","5", 0, 0, 0, 1, 2, 3, false, null, null));
        return testList; 
    }
}
