package com.example.cheers.Infos;

public class StudentInfo {
    private String name; //name
    private int img_id; //portrait
    private int age; // age
    private int sex_id; // gender
    private int distance; //diastance


    public StudentInfo(String name, int img_id, int age, int sex, int distance) {
        this.name = name;
        this.img_id = img_id;
        this.age = age;
        this.sex_id = sex;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getImg_id() {
        return img_id;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex_id;
    }

    public int getDistance() { return distance;}


}
