package com.example.time_parallel;

public class Schedul {

    private   String ID  ;
    private   String Title  ;
    private   String Discription    ;
    private   String Weekly  ;
    private   String StartTime  ;
    private   String Endtime  ;
    private   String Type ;
    private   String SDate  ;

    public Schedul(String ID, String title, String discription, String weekly, String startTime, String endtime, String type, String SDate) {
        this.ID = ID;
        Title = title;
        Discription = discription;
        Weekly = weekly;
        StartTime = startTime;
        Endtime = endtime;
        Type = type;
        this.SDate = SDate;
    }

    public  String getStartTimeEndTime()
    {
        return  StartTime+" to "+Endtime;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getWeekly() {
        return Weekly;
    }

    public void setWeekly(String weekly) {
        Weekly = weekly;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndtime() {
        return Endtime;
    }

    public void setEndtime(String endtime) {
        Endtime = endtime;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSDate() {
        return SDate;
    }

    public void setSDate(String SDate) {
        this.SDate = SDate;
    }


}
