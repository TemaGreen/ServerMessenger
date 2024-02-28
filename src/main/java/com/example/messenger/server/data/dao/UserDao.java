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
