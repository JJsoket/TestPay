package com.example.demo.to;

public class Response {
    private String id;
    private String dateTime;
    private String state;

    public Response() {
    }

    public Response(String id, String dateTime, String state) {
        this.id = id;
        this.dateTime = dateTime;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
