package teamB.BIS2211.TankApp.Model;

import java.util.ArrayList;

public class LeaderboardEntry 
{
    private String name; 
    private ArrayList<LeaderboardData> bookings; 
    private int amountBookings; 
    private float amountSaved;

    public LeaderboardEntry(String name, ArrayList<LeaderboardData> bookings) 
    {
        this.name = name;
        this.bookings = bookings;
        this.amountBookings = calculateNumberEntries(bookings); 
        this.amountSaved = calculateAmountSaved(bookings);
    }

    public String getName() {
        return name;
    }

    public ArrayList<LeaderboardData> getBookings() {
        return bookings;
    }

    public int getAmountBookings() {
        return amountBookings;
    }

    public float getAmountSaved() {
        return amountSaved;
    }

    private static float calculateAmountSaved(ArrayList<LeaderboardData> bookings)
    {
        float amountSaved = 0; 
        for(LeaderboardData entry : bookings)
        {
            if(entry.isChecked()) amountSaved += (entry.getAmount() * entry.getPriceDeviation()); 
        }
        return amountSaved;
    }

    private static int calculateNumberEntries(ArrayList<LeaderboardData> bookings)
    {
        int i = 0; 
        for(LeaderboardData entry : bookings)
        {
            if(entry.isChecked()) i++; 
        }
        return i; 
    }

    public String getSavedString()
    {
        String sign = ""; 
        if(amountSaved < 0) sign = "- "; 
        else sign = "+ ";
        if(amountSaved == 0) sign = "";
        return sign + String.format("%.2f", Math.abs(amountSaved)) + "€"; 
    }

    public int didSave()
    {
        if(amountSaved == 0) return 2; 
        if(amountSaved < 0) return 1; 
        else return 0; 
    }
}