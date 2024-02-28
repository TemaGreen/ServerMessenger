package com.example.messenger.server.data.request;

import com.example.messenger.server.data.model.User;

import java.util.LinkedList;
import java.util.List;

public class RequestAddNewParticipantDialog {

    int dialog;

    List<User> participants;

    public int getDialog() {
        return dialog;
    }

    public void setDialog(int dialog) {
        this.dialog = dialog;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public RequestAddNewParticipantDialog(int dialog, List<User> participants) {
        this.dialog = dialog;
        this.participants = participants;
    }

    public RequestAddNewParticipantDialog(int dialog) {
        this.dialog = dialog;
        this.participants = new LinkedList<>();
    }

    public RequestAddNewParticipantDialog() {
    }
}
