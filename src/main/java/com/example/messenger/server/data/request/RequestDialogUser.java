package com.example.messenger.server.data.request;

public class RequestDialogUser {

    int id_author;

    int id_dialogs;

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public int getId_dialogs() {
        return id_dialogs;
    }

    public void setId_dialogs(int id_dialogs) {
        this.id_dialogs = id_dialogs;
    }

    public RequestDialogUser(int id_author, int id_dialogs) {
        this.id_author = id_author;
        this.id_dialogs = id_dialogs;
    }

    public RequestDialogUser() {
    }
}
