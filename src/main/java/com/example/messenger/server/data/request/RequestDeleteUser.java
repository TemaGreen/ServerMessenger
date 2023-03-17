package com.example.messenger.server.data.request;

public class RequestDeleteUser {

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RequestDeleteUser(int id) {
        this.id = id;
    }

    public RequestDeleteUser() {
    }
}
