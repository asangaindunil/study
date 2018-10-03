package com.example.asangaindunil.study.model;

public class Session {
    private String name;
    private String description;
    private String from;
    private String to;
    private int Complete;

    public Session(String name, String description, String from, String to, int complete){
        this.name = name;
        this.description =description;
        this.from=from;
        this.to=to;
        this.Complete =complete;
    }
    public Session(String name, String from, String to, int complete){
        this.name = name;
        this.from=from;
        this.to=to;
        this.Complete =complete;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }


    public int getComplete() {
        return Complete;
    }




}


