package com.a8.itslit;

public class Event
{
    private String eventName;
    private int startTime;
    private int endTime;
    private int age;
    private int price;
    private int date;

    public Event (String eventName, int startTime, int endTime, int date)
    {
        this.eventName=eventName;
        this.startTime=startTime;
        this.endTime = endTime;
        this.date =date;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) { //set price if different from normal club price
        this.price = price;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) { //set age if different from normal club age restriction
        this.age = age;
    }
}
