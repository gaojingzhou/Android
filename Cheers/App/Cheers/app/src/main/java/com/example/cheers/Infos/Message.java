package com.example.cheers.Infos;

public class Message {
    private String name; //chat name
    private int img_id; //chat image
    private String info; //chat info
    private String time; // chat time


    public Message(String name, int img_id, String info, String time) {
        this.name = name;
        this.img_id = img_id;
        this.info = info;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getImg_id() {
        return img_id;
    }

    public String getInfo() {
        return info;
    }

    public String getTime() {
        return time;
    }



}
