package web;

import java.util.ArrayList;
import API.ApiData;
import model.GasStation;

public class WebController
{	
	private ArrayList<GasStation> gsList = new ArrayList<GasStation>(); 
	public ArrayList<GasStation> getGslist() {
		return gsList;
	}	
	private ArrayList<GasStation> favouriteList = new ArrayList<GasStation>(); 
	public ArrayList<GasStation> getFavouriteList() {
		return gsList;
	}
	
	public String printGSTable(float lat, float lon, float rad)
	{
		String output = "";
		getGSData(lat, lon, rad);  
		for(GasStation gs : gsList)
		{
			output += ("<tr> <td>" + gs.getName() + "</td>");
			output += ("<td>" + gs.getDieselPrice() + "</td> ");
			output += ("<td>" + gs.getE5Price() + "</td> ");
			output += ("<td>" + gs.getE10Price() + "</td> ");
			output += ("<td>" + gs.getStreet() + "</td>");
			output += ("<td>" + gs.getDistance()  + "</td>"); 
			output += ("<td>" + gs.getOpenStatus() + "</td> ");
			output += ("<td> "
						+ "<button class=\"btn btn-primary\" "
						+ "id=\"favorite_" + gs.getId() + "\" "
						+ "onclick=\"toggleFav(this.id)\"> "
						+ "<i class=\"material-icons\"> " + checkFavButton(gs.getId()) + " </i> "
						+ "</button> </td> </tr>");
		}		
		return output;		
	}
	
	public String printGSTable(String[] favs)
	{
		String output = "";
		getGSData(favs);  
		for(GasStation gs : favouriteList)
		{
			output += ("<tr> <td>" + gs.getName() + "</td>");
			output += ("<td>" + gs.getDieselPrice() + "</td> ");
			output += ("<td>" + gs.getE5Price() + "</td> ");
			output += ("<td>" + gs.getE10Price() + "</td> ");
			output += ("<td>" + gs.getStreet() + "</td>");
			output += ("<td>" + gs.getOpenStatus() + "</td> ");
			output += ("<td> "
						+ "<button class=\"btn btn-primary\" "
						+ "id=\"favorite_" + gs.getId() + "\" "
						+ "onclick=\"toggleFav(this.id)\"> "
						+ "<i class=\"material-icons\"> remove_circle </i> "
						+ "</button> </td> </tr>");
		}				
		return output;		
	}
	
	private void getGSData(float lat, float lon, float rad)
	{
		gsList = ApiData.getJSON(lat, lon, rad); 
	}
	
	private void getGSData(String[] favs)
	{
		favouriteList = ApiData.getJSON(favs); 
	}
	
	public static float convertReqestParameter(String s)
	{
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
	
	private String checkFavButton(String id)
	{
		for(GasStation gs: favouriteList)
		{
			if(gs.getId().equals(id))
			{
				return "remove_circle"; 
			}
		}
		return "add_circle"; 
	}
}
