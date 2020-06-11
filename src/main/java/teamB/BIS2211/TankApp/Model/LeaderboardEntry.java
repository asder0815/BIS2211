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
        this.amountBookings = bookings.size();
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
            amountSaved += (entry.getAmount() * entry.getPriceDeviation() * -1); 
        }
        return amountSaved;
    }
}