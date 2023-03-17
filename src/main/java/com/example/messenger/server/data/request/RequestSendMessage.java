package com.example.messenger.server.data.request;

public class RequestSendMessage {

    int id_dialogs;

    int id_author;

    String text_message;

    public int getId_dialogs() {
        return id_dialogs;
    }

    public void setId_dialogs(int id_dialogs) {
        this.id_dialogs = id_dialogs;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getText_message() {
        return text_message;
    }

    public void setText_message(String text_message) {
        this.text_message = text_message;
    }

    public RequestSendMessage(int id_dialogs, int id_author, String text_message) {
        this.id_dialogs = id_dialogs;
        this.id_author = id_author;
        this.text_message = text_message;
    }

    public RequestSendMessage() {
    }
}
