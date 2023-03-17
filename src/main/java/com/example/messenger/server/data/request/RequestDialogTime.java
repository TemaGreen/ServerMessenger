package com.example.messenger.server.data.request;

public class RequestDialogTime {

    int id_dialogs;

    long time;

    public int getId_dialogs() {
        return id_dialogs;
    }

    public void setId_dialogs(int id_dialogs) {
        this.id_dialogs = id_dialogs;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public RequestDialogTime(int id_dialogs, long time) {
        this.id_dialogs = id_dialogs;
        this.time = time;
    }

    public RequestDialogTime() {
    }
}
