package teamB.BIS2211.TankApp.ApiData;

import java.io.InputStream;
import java.util.Properties;

public class ApiKey 
{	
	public String getApiKey(String keyString)
	{
		try 
		{
			Properties prop = new Properties();
			InputStream in = getClass().getResourceAsStream("api.properties");
			prop.load(in);
			in.close();
			return prop.getProperty(keyString);
		}
		catch(Exception ex)
		{
         	return null; 
		}
	}
}