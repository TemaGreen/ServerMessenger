package com.example.messenger.server.data.request;

public class RequestDialogsByName {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestDialogsByName(String name) {
        this.name = name;
    }

    public RequestDialogsByName() {
    }
}
