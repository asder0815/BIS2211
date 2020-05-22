package web;

import java.util.ArrayList;
import API.ApiData;
import model.GasStation;

public class WebController {
	private static ArrayList<GasStation> gsList = null;

	public static ArrayList<GasStation> getGslist() {
		return gsList;
	}

	public static String printGSTable(float lat, float lon, float rad) {
		String output = "";
		getGSData(lat, lon, rad);
		for (GasStation gs : gsList) {
			output += ("<tr> <td>" + gs.getName() + "</td>");
			output += ("<td>" + gs.getDieselPrice() + "</td> ");
			output += ("<td>" + gs.getE5Price() + "</td> ");
			output += ("<td>" + gs.getE10Price() + "</td> ");
			output += ("<td>" + gs.getStreet() + "</td>");
			output += ("<td>" + gs.getOpenStatus() + "</td> ");
			output += ("<td><button class=\"btn btn-warning\" title=\"zu Favoriten hinzufügen\" onclick=\"addFavorite(this.id)\"  id=\"favorite_"
					+ gs.getId() + "\"> <i class=\"material-icons\">grade</i></button> </td> </tr>");
		}
		System.out.println(output);

		return output;
	}

	public static String printGSTable(String[]favList) {
		String output = "";
		getGSData(favList);
		for (GasStation gs : gsList) {
			output += ("<tr> <td>" + gs.getName() + "</td>");
			output += ("<td>" + gs.getDieselPrice() + "</td> ");
			output += ("<td>" + gs.getE5Price() + "</td> ");
			output += ("<td>" + gs.getE10Price() + "</td> ");
			output += ("<td>" + gs.getStreet() + "</td>");
			output += ("<td>" + gs.getOpenStatus() + "</td> ");
			output += ("<td><button class=\"btn btn-warning\" title=\"zu Favoriten hinzufügen\" onclick=\"addFavorite(this.id)\"  id=\"favorite_"
					+ gs.getId() + "\"> <i class=\"material-icons\">remove</i></button> </td> </tr>");
		}
		System.out.println(output);

		return output;
	}

	private static void getGSData(float lat, float lon, float rad) {
		gsList = ApiData.getJSON(lat, lon, rad);
		System.out.println("Successfully got Gas Station data.");
	}
	private static void getGSData(String[] favList) {
		gsList = ApiData.getJSON(favList);
		System.out.println("Successfully got Gas Station data.");
	}


	public static float convertReqestParameter(String s) {
		System.out.println("Converting: " + s);
		if (s == null)
			return 0;
		try {
			return Float.parseFloat(s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
}
