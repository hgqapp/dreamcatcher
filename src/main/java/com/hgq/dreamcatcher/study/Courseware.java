package com.hgq.dreamcatcher.study;

public class Courseware {

    private String id;
    private String name;
    private int time;
    private String speaker;

    public Courseware(String id, String name, int time, String speaker) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.speaker = speaker;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
