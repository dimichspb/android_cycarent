package com.example.dimichspb.cypruscarrentals;

import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Request implements Serializable {
    private Type type;
    private String dateStart;
    private String timeStart;
    private String dateEnd;
    private String timeEnd;
    private String email;

    /*
    public Request(String type, String dateStart, String timeStart, String dateEnd, String timeEnd, String email) {
        this.type = type;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.dateEnd = dateEnd;
        this.timeEnd = timeEnd;
        this.email = email;
    }*/

    public Request() {

    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
