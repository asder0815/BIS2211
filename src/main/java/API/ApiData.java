package API;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiData
{		
	public static String test() 
	{
        return printStationNames(getTestJSON());
    }

    public static JsonObject getTestJSON() 
    {
        URL url;
        HttpURLConnection request;
        try {
            url = new URL(buildRequestString("48.878550", "8.717204", 5));
            request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("GET");
            request.connect();
            JsonElement element = JsonParser.parseReader(new InputStreamReader(request.getInputStream()));
            JsonObject obj = element.getAsJsonObject();
            System.out.println(obj);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //returns a https request string with the specified latitude, longitude and radius
    public static String buildRequestString(String lat, String lon, double radius) 
    {
        //https://creativecommons.tankerkoenig.de/json/list.php?lat=52.521&lng=13.438&rad=1.5&sort=dist&type=all&apikey=00000000-0000-0000-0000-000000000002
        return "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + lat + "&lng=" + lon + "&rad=" + radius + "&sort=dist&type=all&apikey=" + Api_Key.API_KEY;
    }

    public static String printStationNames(JsonObject obj) 
    {
        return obj.toString();
    }
}
