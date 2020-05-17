package web;

import java.util.ArrayList;
import API.ApiData;
import model.GasStation;

public class WebController
{	
	private static ArrayList<GasStation> gsList = null; 
	public static ArrayList<GasStation> getGslist() {
		return gsList;
	}	

	public static String printGSTable(float lat, float lon)
	{
		String output = "";
		getGSData(lat, lon);  
		for(GasStation gs : gsList)
		{
			output += ("<tr> <td>" + gs.getName() + "</td>"); 
			output += ("<td>" + gs.getStreet() + "</td>");
			output += ("<td>" + gs.getOpenStatus() + "</td> ");
			output += ("<td> <button type=\"submit\" onclick= \" addfavorite()\"  id=\"favorite_\" + gs.getId() +\"\">zu Favoriten hinzufügen</button> </td> </tr>");
		}		
		System.out.println(output);
		
		return output;		
	}
	
	private static void getGSData(float lat, float lon)
	{
		gsList = ApiData.getJSON(lat, lon); 
		System.out.println("Successfully got Gas Station data."); 
	}
	
	public static float convertReqestParameter(String s)
	{
		System.out.println("Converting: " + s);
		if(s == null) return 0; 
		try
		{
			return Float.parseFloat(s); 
		}
		catch(NumberFormatException ex)
		{
			return 0; 
		}
	}
}
