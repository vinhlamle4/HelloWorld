package com.example.lamlv.sample1.Model;

public class Message {
    private static Message message;
    private String strtext;

    private Message() {
    }

    public static Message getInstance() {
        if (message == null) {
            message = new Message();
        }
        return message;
    }

    public String getStrtext() {
        return strtext;
    }

    public void setStrtext(String strtext) {
        this.strtext = strtext;
    }

    @Override
    public String toString() {
        return "Message{" +
                "strtext='" + strtext + '\'' +
                '}';
    }
}
