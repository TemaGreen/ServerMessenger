package com.example.messenger.server.controller;

import com.example.messenger.server.data.dao.DialogsDao;
import com.example.messenger.server.data.dao.MessagesDao;
import com.example.messenger.server.data.dao.UserDao;
import com.example.messenger.server.data.model.Dialog;
import com.example.messenger.server.data.model.Message;
import com.example.messenger.server.data.model.User;
import com.example.messenger.server.data.request.*;
import com.example.messenger.server.data.response.ResponseDialogMessage;
import com.example.messenger.server.data.response.ResponseUserDialog;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class MainController {

    private UserDao userDao;

    private MessagesDao messagesDao;

    private DialogsDao dialogsDao;

    // Все сообщения
    @GetMapping("/message/all")
    public @ResponseBody LinkedList<Message> getMessages() {
        return messagesDao.selectAllMessages();
    }

    //Конкретное сообщение
    @GetMapping("/message/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messagesDao.selectMessageById(id);
    }

    //Все Сообщения в диалоге
    @PostMapping("/messages-dialog")
    public LinkedList<ResponseDialogMessage> selectMessagesByDialog(@RequestBody RequestDialog requestDialog) {
        return messagesDao.selectDialogMessages(requestDialog);
    }

    //Сообщения в диалоге от определенного времени
    @PostMapping("/messages-dialog-time")
    public @ResponseBody LinkedList<ResponseDialogMessage> selectMessagesByDialogOverTime(@RequestBody RequestDialogTime requestDialogTime) {
        return messagesDao.selectDialogMessages(requestDialogTime);
    }

    //Все сообщения пользователя в диалоге
    @PostMapping("/messages-dialog-user")
    public LinkedList<ResponseDialogMessage> selectMessagesUserByDialog(@RequestBody RequestDialogUser requestDialogUser) {
        return messagesDao.selectDialogMessages(requestDialogUser);
    }

    //Получение сообщений пользователя в диалоге от определенного времени
    @PostMapping("/messages-dialog-time-user")
    public LinkedList<ResponseDialogMessage> selectMessagesUserByDialogOverTime(@RequestBody RequestDialogTimeUser requestDialogTimeUser) {
        return messagesDao.selectDialogMessages(requestDialogTimeUser);
    }

    //Получение сообщения от клиента
    @PostMapping("/send-message")
    public ResponseDialogMessage sendMessage(@RequestBody RequestSendMessage message) {
        return messagesDao.sendMessage(message);
    }

    //Изменение сообщения
    @PutMapping("/edit-message")
    public Message editMessage(@RequestBody RequestEditMessage requestEditMessage) {
        return messagesDao.editMessage(requestEditMessage);
    }

    //Добавление Нового пользователя
    @PostMapping("/user-new-add")
    public User addUser(@RequestBody RequestNewUser requestNewUser) {
        return userDao.addNewUser(requestNewUser);
    }

    //Изменение пользователя
    @PutMapping("/user-change")
    public User editUser(@RequestBody RequestEditUser user) {
        return userDao.editUser(user);
    }

    //Удаление пользователя
    @DeleteMapping("/user-delete")
    public void deleteUser(@RequestBody RequestDeleteUser requestDeleteUser) {
        userDao.deleteUser(requestDeleteUser);
    }

    //Получение списка всех пользователей
    @GetMapping("/user/all")
    public LinkedList<User> getAllUser() {
        return userDao.selectAllUser();
    }

    //Получение конкретного пользователя по id-шнику
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") int id) {
        return userDao.selectUserById(id);
    }

    //Получение пользователя по имени
    @PostMapping("/user-name")
    public User getUserByName(@RequestBody RequestGetUserByName requestGetUserByName) {
        return userDao.selectUserByName(requestGetUserByName.getName());
    }

    //Получение списка участников диалога по id-шнику диалога
    @GetMapping("/participant/{id}")
    public LinkedList<User> getParticipant(@PathVariable int id) {
        return userDao.selectParticipantByDialog(id);
    }

    //Получение списка участников диалога по имени создателя диалога
    @PostMapping("/participant")
    public LinkedList<User> getParticipantByAuthor(@RequestBody RequestParticipantByAuthor requestParticipantByAuthor) {
        return userDao.selectParticipantByAuthor(requestParticipantByAuthor.getAuthor());
    }

    @GetMapping("/participant/can-added")
    public LinkedList<User> getUserCanAdded(@RequestBody RequestUserCanAdded requestUserCanAdded){
        return userDao.getUsersCanAdded(requestUserCanAdded.getDialog());
    }

    //Получение списка диалогов
    @GetMapping("/dialog/all")
    public LinkedList<Dialog> getAllDialog() {
        return dialogsDao.selectAllDialogs();
    }

    //Получение диалога по id-шнику
    @GetMapping("/dialog/{id}")
    public ResponseUserDialog getDialogById(@PathVariable int id) {
        return dialogsDao.selectDialogsById(id);
    }

    //Удаление диалога по id-шнику
    @DeleteMapping("/dialog-delete")
    public void deleteDialog(@RequestBody RequestDeleteDialog requestDeleteDialog) {
        dialogsDao.deleteDialogs(requestDeleteDialog.getId_dialog());
    }

    //Получение диалогов пользователя по его id-шнику
    @GetMapping("/dialogs-user/{id}")
    public LinkedList<ResponseUserDialog> getDialogsByUserId(@PathVariable int id){
        return dialogsDao.selectUserDialogsByUserId(id);
    }

    //Получение диалогов пользователя по его имени
    @PostMapping("/dialogs-user")
    public LinkedList<ResponseUserDialog> getDialogsByUserName(@RequestBody RequestDialogsByName requestDialogsByName){
        return dialogsDao.selectUserDialogsByUserName(requestDialogsByName.getName());
    }

    //Создать диалог
    @PostMapping("/dialog-new")
    public LinkedList<ResponseUserDialog> createDialogUser(@RequestBody RequestCreateDialogUser requestCreateDialogUser){
        dialogsDao.createDialog(requestCreateDialogUser);
        return dialogsDao.selectUserDialogsByUserId(requestCreateDialogUser.getAuthor());
    }
/*
    @GetMapping("/select")
    public LinkedList<ResponseUserDialog> createDialogUser(){
        List<Integer> users = Stream.of(5,7,10).toList();
        RequestCreateDialogUser requestCreateDialogUser = new RequestCreateDialogUser(1,"Какой-то диалог", users);
        dialogsDao.createDialog(requestCreateDialogUser);
        return dialogsDao.selectUserDialogsByUserId(1);
    }
*/
    public MainController() {
        this.userDao = new UserDao();
        this.messagesDao = new MessagesDao();
        this.dialogsDao = new DialogsDao();
    }
}
/*
@GetMapping("/test")
    public void getTest() {
    }
 */