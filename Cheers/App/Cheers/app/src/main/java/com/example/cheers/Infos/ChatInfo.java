package com.example.cheers.Infos;

public class ChatInfo {

    private int id; //chat id
    private String name; //name
    private String chatMessage; //send message
    private boolean isSend; //id send by user

    public ChatInfo(int id, String name, String chatMessage, boolean isSend) {
        super();
        this.id = id;
        this.name = name;
        this.chatMessage = chatMessage;
        this.isSend = isSend;
    }
    public ChatInfo() {
        super();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChatMessage() {
        return chatMessage;
    }
    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
    public boolean isSend() {
        return isSend;
    }
    public void setSend(boolean isMeSend) {
        this.isSend = isMeSend;
    }


}
