package com.a8.itslit;

import android.icu.util.Calendar;

public class Hours
{
    private String monHours, tueHours, wedHours, thrHours, friHours, satHours, sunHours;


    public Hours(){

    }

    public Hours(String monHours, String tueHours, String wedHours, String thrHours, String friHours, String satHours, String sunHours)
    {
        this.monHours = monHours;
        this.tueHours = tueHours;
        this.wedHours = wedHours;
        this.thrHours = thrHours;
        this.friHours = friHours;
        this.satHours = satHours;
        this.sunHours = sunHours;
    }

    public String getMonHours() {
        return monHours;
    }

    public String getTueHours() {
        return tueHours;
    }

    public String getWedHours() {
        return wedHours;
    }

    public String getThrHours() {
        return thrHours;
    }

    public String getFriHours() {
        return friHours;
    }

    public String getSatHours() {
        return satHours;
    }

    public String getSunHours() {
        return sunHours;
    }

    public void setMonHours(String monHours) {
        this.monHours = monHours;
    }

    public void setTueHours(String tueHours) {
        this.tueHours = tueHours;
    }

    public void setWedHours(String wedHours) {
        this.wedHours = wedHours;
    }

    public void setThrHours(String thuHours) {
        this.thrHours = thuHours;
    }

    public void setFriHours(String friHours) {
        this.friHours = friHours;
    }

    public void setSatHours(String satHours) {
        this.satHours = satHours;
    }

    public void setSunHours(String sunHours) {
        this.sunHours = sunHours;
    }

    //Tells user the times that the nightclubs are open
    public Time getOpenTime()
    {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        String h, m, fullTime;
        switch (day)
        {
            case 1:
                fullTime = getSunHours().substring(0, getMonHours().indexOf('-'));
                h = fullTime.substring(0, fullTime.indexOf(':'));
                m = fullTime.substring(fullTime.indexOf(':')+1, fullTime.length());
                return new Time(Integer.parseInt(h),Integer.parseInt(m));
            case 2:
                fullTime = getMonHours().substring(0, getMonHours().indexOf('-'));
                h = fullTime.substring(0, fullTime.indexOf(':'));
                m = fullTime.substring(fullTime.indexOf(':')+1, fullTime.length());
                return new Time(Integer.parseInt(h),Integer.parseInt(m));
            case 3:
                fullTime = getTueHours().substring(0, getMonHours().indexOf('-'));
                h = fullTime.substring(0, fullTime.indexOf(':'));
                m = fullTime.substring(fullTime.indexOf(':')+1, fullTime.length());
                return new Time(Integer.parseInt(h),Integer.parseInt(m));
            case 4:
                fullTime = getWedHours().substring(0, getMonHours().indexOf('-'));
                h = fullTime.substring(0, fullTime.indexOf(':'));
                m = fullTime.substring(fullTime.indexOf(':')+1, fullTime.length());
                return new Time(Integer.parseInt(h),Integer.parseInt(m));
            case 5:
                fullTime = getThrHours().substring(0, getMonHours().indexOf('-'));
                h = fullTime.substring(0, fullTime.indexOf(':'));
                m = fullTime.substring(fullTime.indexOf(':')+1, fullTime.length());
                return new Time(Integer.parseInt(h),Integer.parseInt(m));
            case 6:
                fullTime = getFriHours().substring(0, getMonHours().indexOf('-'));
                h = fullTime.substring(0, fullTime.indexOf(':'));
                m = fullTime.substring(fullTime.indexOf(':')+1, fullTime.length());
                return new Time(Integer.parseInt(h),Integer.parseInt(m));
            case 7:
                fullTime = getSatHours().substring(0, getMonHours().indexOf('-'));
                h = fullTime.substring(0, fullTime.indexOf(':'));
                m = fullTime.substring(fullTime.indexOf(':')+1, fullTime.length());
                return new Time(Integer.parseInt(h),Integer.parseInt(m));
        }
        return new Time(0,0);
    }


    //Gets hours of the day for any location
    public String getTodaysHours()
    {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day)
        {
            case 1:
                return getSunHours();
            case 2:
                return getMonHours();
            case 3:
                return getTueHours();
            case 4:
                return getWedHours();
            case 5:
                return getThrHours();
            case 6:
                return getFriHours();
            case 7:
                return getSatHours();
        }
        return "Closed Today";
    }

//    public int getTodaysHoursT(){
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//        switch (day){
//            case 1:
//                return 1;
//            case 2:
//                return 2;
//            case 3:
//                return 3;
//            case 4:
//                return 4;
//            case 5:
//                return 5;
//            case 6:
//                return 6;
//            case 7:
//                return 7;
//        }
//        return 0;
//    }

    //Gets time when the nightclub closes
    public Time getCloseTime(int day){
        return new Time(0,0);
    }
}
