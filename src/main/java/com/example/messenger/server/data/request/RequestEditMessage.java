package com.example.messenger.server.data.request;

public class RequestEditMessage {

    int id;

    int id_dialogs;

    String text_message;

    int id_author;

    int changeable_message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dialogs() {
        return id_dialogs;
    }

    public void setId_dialogs(int id_dialogs) {
        this.id_dialogs = id_dialogs;
    }

    public String getText_message() {
        return text_message;
    }

    public void setText_message(String text_message) {
        this.text_message = text_message;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public int getChangeable_message() {
        return changeable_message;
    }

    public void setChangeable_message(int changeable_message) {
        this.changeable_message = changeable_message;
    }

    public RequestEditMessage(int id, int id_dialogs, String text_message, int id_author, int changeable_message) {
        this.id = id;
        this.id_dialogs = id_dialogs;
        this.text_message = text_message;
        this.id_author = id_author;
        this.changeable_message = changeable_message;
    }

    public RequestEditMessage() {
    }
}
