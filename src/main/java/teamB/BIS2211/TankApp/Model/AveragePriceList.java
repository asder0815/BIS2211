package teamB.BIS2211.TankApp.Model;

public class AveragePriceList 
{
    private int hour; 
    private float priceDeviation;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public float getPriceDeviation() {
        return priceDeviation;
    }

    public void setPriceDeviation(float priceDeviation) {
        this.priceDeviation = priceDeviation;
    }
}