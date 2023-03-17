package com.example.messenger.server.data.request;

public class RequestUserCanAdded {

    int dialog;

    public int getDialog() {
        return dialog;
    }

    public void setDialog(int dialog) {
        this.dialog = dialog;
    }

    public RequestUserCanAdded(int dialog) {
        this.dialog = dialog;
    }

    public RequestUserCanAdded() {
    }
}
