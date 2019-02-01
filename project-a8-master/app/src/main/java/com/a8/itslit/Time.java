package com.a8.itslit;

public class Time
{
    private int hour, min;

    public Time (int hourIn, int minIn)
    {
        hour = hourIn;
        min = minIn;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    /*
        If timeIn is later than current time then return: 1
        If timeIn is current time then return: 0
        If timeIn is earlier than current time then return: -1
     */

    //Makes sure people are checking in at the real time and not a pre-programmed time on their phone
    public int compareTime(Time timeIn)
    {
        if(timeIn.getHour() > getHour())
        {
            return 1;
        }
        else if (timeIn.getHour() < getHour())
        {
            return -1;
        }
        else
            {
                return Integer.compare(timeIn.getMin(), getMin());
        }
    }

      //Sets the hours for the day at a specific location
//    public String getDisplay() {
//
//        //adds hours
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
//        String display = name + "\n" + description + "\n" + address + "\nAge Limit: " + agelimit + "\nToday's hours " + todaysHours;
//        return display;
//    }
}