package com.example.messenger.server.data.model;

public class Dialog {

    int id;

    int author;

    String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dialog(int id, int author, String name) {
        this.id = id;
        this.author = author;
        this.name = name;
    }

    public Dialog(int author, String name) {
        this.id = -1;
        this.author = author;
        this.name = name;
    }

    public Dialog(int author) {
        this.author = author;
    }

    public Dialog() {
    }
}
