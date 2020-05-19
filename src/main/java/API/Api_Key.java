package API;

import java.io.InputStream;
import java.util.Properties;

public class Api_Key 
{	
	public String getApiKey()
	{
		try 
		{
			Properties prop = new Properties();
			InputStream in = getClass().getResourceAsStream("api.properties");
			prop.load(in);
			in.close();
			System.out.println("Successfully got the API-Key."); 
			return prop.getProperty("apiKey");
		}
		catch(Exception ex)
		{
			System.out.println("Could not grt the API-Key."); 
         	return "00000000-0000-0000-0000-000000000002"; 
		}
	}
}
