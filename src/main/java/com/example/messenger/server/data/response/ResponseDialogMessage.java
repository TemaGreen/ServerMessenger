package com.example.messenger.server.data.response;

public class ResponseDialogMessage {

    int id;

    int dialogs;

    int author;

    int icon;

    String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public ResponseDialogMessage(int id, int dialogs, int author, int icon, String name, String text, long time) {
        this.id = id;
        this.dialogs = dialogs;
        this.author = author;
        this.name = name;
        this.icon = icon;
        this.text = text;
        this.time = time;
    }

    public ResponseDialogMessage() {
    }
}
