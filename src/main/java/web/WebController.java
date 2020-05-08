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

	public static String printGSTable()
	{
		String output = "<table> <tbody> <tr> <th>Name:</th> <th>Straße:</th> <th>Geöffnet:</th> </tr>"; 
		getGSData();  
		for(GasStation gs : gsList)
		{
			output += ("<tr> <td>" + gs.getName() + "</td>"); 
			output += ("<td>" + gs.getStreet() + "</td>");
			output += ("<td>" + gs.isOpen() + "</td> </tr>");
		}
		output += "</tbody> </table>";
		
		System.out.println(output); 
		
		return output; 		
	}
	
	private static void getGSData()
	{
		gsList = ApiData.getJSON(); 
		System.out.println("Successfully got Gas Station data."); 
	}
}
