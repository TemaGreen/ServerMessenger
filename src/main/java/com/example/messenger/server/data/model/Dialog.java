package com.example.messenger.server.data.model;

public class Dialog {

    int id;

    int author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public Dialog(int id, int author) {
        this.id = id;
        this.author = author;
    }

    public Dialog(int author) {
        this.author = author;
    }

    public Dialog() {
    }
}
