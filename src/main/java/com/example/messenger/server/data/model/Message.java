package com.example.messenger.server.data.model;

public class Message {

    int id;

    int dialogs;

    int author;

    String text;

    long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDialogs() {
        return dialogs;
    }

    public void setDialogs(int dialogs) {
        this.dialogs = dialogs;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Message(int id, int dialogs, int author, String text, long time) {
        this.id = id;
        this.dialogs = dialogs;
        this.author = author;
        this.text = text;
        this.time = time;
    }

    public Message(int id, int dialogs, int author, String text) {
        this.id = id;
        this.dialogs = dialogs;
        this.author = author;
        this.text = text;
    }

    public Message() {
    }
}
