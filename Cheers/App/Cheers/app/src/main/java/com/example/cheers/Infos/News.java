package com.example.cheers.Infos;

public class News {
    private String title; //news title
    private String time; // news time
    private String department; //news department



    public News(String title, String time, String department) {
        this.title = title;
        this.time = time;
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDepartment() {
        return department;
    }



}

