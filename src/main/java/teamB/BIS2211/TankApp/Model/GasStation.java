package teamB.BIS2211.TankApp.Model;

public class GasStation 
{
	private String id; 
	private String name; 
	private String brand; 
	private String street; 
	private String place; 
	private float lat; 
	private float lng; 
	private float dist; 
	private float diesel; 
	private float e5; 
	private float e10;
	private boolean isOpen; 
	private String houseNumber; 
	private String postCode; 
	
	public String getOpenStatus() {
		if (this.isOpen == true)  return "geöffnet"; 
		else	return "geschlossen";
	}
	
	public String getDieselPrice(){
		if(this.diesel == 0.0f) return "X";
		else return String.valueOf(this.diesel) + "€";
	}
	public String getE5Price(){
		if(this.e5 == 0.0f)	 return "X";
		else 	return String.valueOf(this.e5) + "€";
	}
	public String getE10Price(){
		if(this.e10 == 0.0f)	 return "X";
		else 	return String.valueOf(this.e10) + "€";
	}
	public String getDistance(){
		return this.dist + " km"; 
	}
	
	public String getId() {
		return id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(final String brand) {
		this.brand = brand;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(final String street) {
		this.street = street;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(final String place) {
		this.place = place;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(final float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(final float lng) {
		this.lng = lng;
	}
	public float getDist() {
		return dist;
	}
	public void setDist(final float dist) {
		this.dist = dist;
	}
	public float getDiesel() {
		return diesel;
	}
	public void setDiesel(final float diesel) {
		this.diesel = diesel;
	}
	public float getE5() {
		return e5;
	}
	public void setE5(final float e5) {
		this.e5 = e5;
	}
	public float getE10() {
		return e10;
	}
	public void setE10(final float e10) {
		this.e10 = e10;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(final boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(final String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(final String postCode) {
		this.postCode = postCode;
	}
	
	public GasStation(){}
	
	public GasStation(final String id, final String name, final String brand, final String street, final String place, final float lat, final float lng,
			final float dist, final float diesel, final float e5, final float e10, final boolean isOpen, final String houseNumber, final String postCode) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.street = street;
		this.place = place;
		this.lat = lat;
		this.lng = lng;
		this.dist = dist;
		this.diesel = diesel;
		this.e5 = e5;
		this.e10 = e10;
		this.isOpen = isOpen;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
	}
	
	@Override
	public String toString()
	{
		return name + " in: " + street + " " + houseNumber + " " + postCode + " " + place + " " + diesel + " " + e5 + " " + e10; 
	}
}
