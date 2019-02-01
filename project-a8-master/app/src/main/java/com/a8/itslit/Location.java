package com.a8.itslit;

import java.util.ArrayList;

class Location //stores information about the club/location
{
   // stores all events at location
	private final ArrayList<Event> events = new ArrayList<>();


	private String placeID;
	private Hours hours;
	private int age;
	private double rating;
	private double lat;
	private double lng;
	private int busyness;
	private boolean favorite = false;
	private boolean setBColor = false;

	private String name;
	private String description;
	private String address;
	private String price;
	private String agelimit;
	private String url;
	private String getHours;

	//Updates with information about location given lots of information
	public Location(String name, String description, String address, String price, String agelimit, String url, String getHours, boolean favorite)
	{
		this.name = name;
		this.description = description;
		this.address = address;
		this.agelimit = agelimit;
		this.price = price;
		this.url = url;
		this.getHours = getHours;
		this.favorite = false;
	}

	//Updates with information about location given some information
	public Location(String name, String description, String address, String agelimit)
    {
		this.name = name;
		this.description = description;
		this.address = address;
		this.agelimit = agelimit;
	}

    public String getURL() {
	    return url;
    }

    public void setURL(String url){
	    this.url = url;
    }
	public String getPlaceID() { return placeID; }

	public void setPlaceID(String id){
		this.placeID = id;
	}
	public ArrayList<Event> getEvents() {
		return events;
	}

	public String getDiscription() {
		return description;
	}

	public String getAddress() {
		return address;
	}

	public double getRating() {
		return rating;
	}

	public String getPrice() {
		return price;
	}

	public int getBusyness() {
		return busyness;
	}

	public String getAgeLimit() {
		return agelimit;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAgeLimit(String newValue){
		this.agelimit = newValue;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setBusyness(int busyness) {
		this.busyness = busyness;
	}

	public Hours getHours() {
		return hours;
	}

	public void setLat(double lat){ this.lat = lat;}

	public double getLat(){ return this.lat;}

	public void setLng(double lng) {this.lng = lng;}

	public double getLng(){ return this.lng;}

	public int getAge(){
		return age;
	}

	public void setAge(int ageIn){
		age = ageIn;
	}

	public void setFavorite(boolean bool) { this.favorite = bool; }

	
	public boolean getFavorite() { return this.favorite; }

	//Sets hours for days of the week based on location
	public void setHours(String mon, String tue, String wed, String thr, String fri, String sat, String sun)
    {
		hours.setMonHours(mon);
		hours.setTueHours(tue);
		hours.setWedHours(wed);
		hours.setThrHours(thr);
		hours.setFriHours(fri);
		hours.setSatHours(sat);
		hours.setSunHours(sun);
	}

	public String getName() {
		return name;
	}

	public Location(String name)
    {
		this.name = name;
		this.hours = new Hours();
	}

	public void addEvent(Event e) {
		events.add(e);
	}

	public void removeEvent(Event e) // remove event, when cancelled or is over
	{
		events.remove(e);
	}

	@Override
	public String toString()
    {
		return "Location [events=" + events + ", address=" + address + ", name=" + name + ", description=" + description
				+ ", hours=" + hours + ", rating=" + rating + ", price=" + price + ", busyness=" + busyness + "]";
	}

	    //Set todaysHours to the open hours of the location for the current day
	    public String getDisplay()
        {
        //adds hours
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//        String todaysHours=null;
//
//        switch (day) {
//            case Calendar.SUNDAY:
//                todaysHours = hours.getSunHours();
//                break;
//            case Calendar.MONDAY:
//                todaysHours = hours.getMonHours();
//                break;
//            case Calendar.TUESDAY:
//                todaysHours = hours.getTueHours();
//                break;
//            case Calendar.WEDNESDAY:
//                todaysHours = hours.getWedHours();
//                break;
//            case Calendar.THURSDAY:
//                todaysHours = hours.getThrHours();
//                break;
//            case Calendar.FRIDAY:
//                todaysHours = hours.getFriHours();
//                break;
//        }
			String display;
			if(price.equals("N/A"))
				display = name + "\n" + description + "\nAge Limit: " + agelimit + "\nVisit: " + url + "\nToday's Hours: " + getHours;
			else
				display = name + "\n" + description + "\nPrice: " + price + "\nAge Limit: " + agelimit + "\nVisit: " + url + "\nToday's Hours: " + getHours;
        return display;
    }
}