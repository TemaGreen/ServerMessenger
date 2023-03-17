package com.example.messenger.server.data.request;

public class RequestEditUser {

    int id;

    String name;

    int icon;

    int changeable_user;

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

    public int getChangeable_user() {
        return changeable_user;
    }

    public void setChangeable_user(int changeable_user) {
        this.changeable_user = changeable_user;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public RequestEditUser(int id, String name, int icon, int changeable_user) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.changeable_user = changeable_user;
    }

    public RequestEditUser() {
    }
}
