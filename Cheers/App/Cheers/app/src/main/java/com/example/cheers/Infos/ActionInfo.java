package com.example.cheers.Infos;

import java.io.Serializable;

public class ActionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int avatarId;
    private String username;
    private String date;
    private int actionImageId;
    private String actionDescripation;
    private String position;
    private int age;
    private int sex;


    public ActionInfo(int avatarId, String username, String date, int actionImageId, String actionDescripation, String position, int age, int sex) {
        this.avatarId = avatarId;
        this.username = username;
        this.date = date;
        this.actionImageId = actionImageId;
        this.actionDescripation = actionDescripation;
        this.position = position;
        this.age = age;
        this.sex = sex;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public int getActionImageId() {
        return actionImageId;
    }

    public String getActionDescripation() {
        return actionDescripation;

    }


    public String getPosition() {
        return position;
    }

    public int getAge () { return age;}

    public int getSex () { return sex;}

}

