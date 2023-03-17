package com.example.messenger.server.data.request;

public class RequestParticipantByAuthor {

    String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public RequestParticipantByAuthor(String author) {
        this.author = author;
    }

    public RequestParticipantByAuthor() {
    }
}
