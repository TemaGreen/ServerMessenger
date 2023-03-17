package com.example.messenger.server.data.dao;

import com.example.messenger.server.data.ConnectManager;
import com.example.messenger.server.data.model.Dialog;
import com.example.messenger.server.data.model.User;
import com.example.messenger.server.data.request.RequestCreateDialogUser;
import com.example.messenger.server.data.response.ResponseUserDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DialogsDao {
    public void insertDialogs(int id) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.dialogs (author, name, time_create) values (?,?,NOW());"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void insertDialogs(Dialog dialog) {
        insertDialogs(dialog.getId());
    }

    public void deleteDialogs(int id) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM public.dialogs WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void deleteDialogs(Dialog dialog) {
        deleteDialogs(dialog.getId());
    }

    public void updateDialogs(int newDialog, int author, int oldDialog) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.dialogs SET id = ?, author = ? WHERE id = ?"
            );
            preparedStatement.setInt(1, newDialog);
            preparedStatement.setInt(2, author);
            preparedStatement.setInt(3, oldDialog);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void updateDialogs(Dialog newDialog, Dialog oldDialog) {
        updateDialogs(newDialog.getId(), newDialog.getAuthor(), oldDialog.getId());
    }

    public ResponseUserDialog selectDialogsById(int id) {
        ResultSet resultSet = null;
        ResponseUserDialog dialog = null;
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT d.id, d.name, u.id, u.name FROM public.dialogs AS d INNER JOIN public.user AS u ON d.author = u.id WHERE d.id = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            dialog = new ResponseUserDialog(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            );
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return dialog;
        }
    }

    public LinkedList<Dialog> selectAllDialogs() {
        ResultSet resultSet = null;
        LinkedList<Dialog> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, author FROM public.dialogs",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new Dialog(
                        resultSet.getInt(1),
                        resultSet.getInt(2))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<Dialog> selectAllDialogsByUser(int id) {
        ResultSet resultSet = null;
        LinkedList<Dialog> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, author FROM public.dialogs WHERE author = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new Dialog(
                        resultSet.getInt(1),
                        resultSet.getInt(2))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<Dialog> selectAllDialogsByUser(User user) {
        return selectAllDialogsByUser(user.getId());
    }

    public LinkedList<ResponseUserDialog> selectUserDialogsByUserId(int id) {
        ResultSet resultSet = null;
        LinkedList<ResponseUserDialog> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT d.id, d.name, u.id, u.name FROM public.dialogs AS d INNER JOIN public.user AS u ON d.author = u.id WHERE d.author = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new ResponseUserDialog(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<ResponseUserDialog> selectUserDialogsByUserName(String name) {
        ResultSet resultSet = null;
        LinkedList<ResponseUserDialog> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT d.id, d.name, u.id, u.name FROM public.dialogs AS d INNER JOIN public.user AS u ON d.author = u.id WHERE u.name = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new ResponseUserDialog(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public void createDialog(RequestCreateDialogUser requestCreateDialogUser) {
        try {
            Connection connection = ConnectManager.getConnect();
            int author = requestCreateDialogUser.getAuthor();
            String query = "INSERT INTO public.dialogs (author, name, time_create) values (" + author + ", ?, NOW());" +
                    "\nINSERT INTO public.user_dialogs (id_dialogs, id_participant) values";
            for (int id : requestCreateDialogUser.getUsers()) {
                query += "\n((SELECT id FROM public.dialogs WHERE time_create = (SELECT MAX(time_create) FROM public.dialogs WHERE author = " + author + ")), " + id + "), ";
            }
            query += "\n((SELECT id FROM public.dialogs WHERE time_create = (SELECT MAX(time_create) FROM public.dialogs WHERE author = " + author + ")), " + author + ");";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, requestCreateDialogUser.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public DialogsDao() {
    }
}
