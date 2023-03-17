package com.example.messenger.server.data.request;

public class RequestGetUserByName {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestGetUserByName(String name) {
        this.name = name;
    }

    public RequestGetUserByName() {
    }
}
