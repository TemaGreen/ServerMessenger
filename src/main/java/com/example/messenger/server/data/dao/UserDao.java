package com.example.messenger.server.data.dao;

import com.example.messenger.server.data.ConnectManager;
import com.example.messenger.server.data.model.User;
import com.example.messenger.server.data.request.RequestDeleteUser;
import com.example.messenger.server.data.request.RequestEditUser;
import com.example.messenger.server.data.request.RequestNewUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserDao {

    public void insertUser(String name, int icon) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.user(name) values (?);"
            );
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void insertUser(User user) {
        insertUser(user.getName(), user.getIcon());
    }

    public void deleteUser(int id) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM public.user WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void deleteUser(RequestDeleteUser requestDeleteUser){
        deleteUser(requestDeleteUser.getId());
    }

    public void deleteUser(User user) {
        deleteUser(user.getId());
    }

    public void updateUser(int newUser, String name, int icon, int oldUser) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.user SET id = ?, name = ?, icon = ? WHERE id = ?"
            );
            preparedStatement.setInt(1, newUser);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, icon);
            preparedStatement.setInt(4, oldUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void updateUser(User oldUser, User newUser) {
        updateUser(newUser.getId(), newUser.getName(), newUser.getIcon(), oldUser.getId());
    }

    public User selectUserById(int id) {
        ResultSet resultSet = null;
        User user = null;
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, icon FROM public.user WHERE id = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return user;
        }
    }

    public User selectUserByName(String name) {
        ResultSet resultSet = null;
        User user = null;
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                            "SELECT id, name, icon FROM public.user WHERE name = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return user;
        }
    }

    public LinkedList<User> selectAllUser() {
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, icon FROM public.user",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<User> selectParticipantByDialog(int dialog) {
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT u.id, u.name, u.icon FROM public.user_dialogs AS d INNER JOIN public.user AS u ON d.id_participant = u.id WHERE d.id_dialogs = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, dialog);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<User> selectParticipantByAuthor(String author) {
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "WITH t1 AS (select d1.id AS dialog, u1.name AS author FROM public.dialogs AS d1 INNER JOIN public.user AS u1 ON d1.author = u1.id), " +
                            "t2 AS (SELECT d2.id_dialogs, u2.id, u2.name, u2.icon FROM public.user AS u2 INNER JOIN public.user_dialogs AS d2 ON u2.id = d2.id_participant) " +
                            "SELECT t2.id, t2.name, t2.icon AS participant FROM t1 INNER JOIN t2 ON t1.dialog = t2.id_dialogs WHERE t1.author = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, author);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<User> getUsersCanAdded(int dialog){
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try{
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT u.id, u.name, u.icon FROM public.user AS u LEFT JOIN public.user_dialogs AS d ON u.id = d.id_participant AND d.id_dialogs = ? WHERE d.id_participant IS NULL",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, dialog);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public User addNewUser(RequestNewUser requestNewUser){
        String name = requestNewUser.getName();
        insertUser(name, requestNewUser.getIcon());
        return selectUserByName(name);
    }

    public User editUser(RequestEditUser requestEditUser){
        int id = requestEditUser.getId();
        updateUser(id, requestEditUser.getName(), requestEditUser.getIcon(), requestEditUser.getChangeable_user());
        return selectUserById(id);
    }

    public UserDao() {
    }
}
