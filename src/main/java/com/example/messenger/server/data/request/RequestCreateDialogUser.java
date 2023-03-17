package com.example.messenger.server.data.request;

import java.util.List;

public class RequestCreateDialogUser {

    int author;

    String name;

    List<Integer> users;

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

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public RequestCreateDialogUser(int author, String name, List<Integer> users) {
        this.author = author;
        this.name = name;
        this.users = users;
    }

    public RequestCreateDialogUser() {
    }
}
