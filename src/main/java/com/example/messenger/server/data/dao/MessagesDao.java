package com.example.messenger.server.data.dao;

import com.example.messenger.server.data.ConnectManager;
import com.example.messenger.server.data.model.Message;
import com.example.messenger.server.data.request.*;
import com.example.messenger.server.data.response.ResponseDialogMessage;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessagesDao {

    /**
     * Добавить сообщение
     * @param dialog id диалога
     * @param author id автора сообщения
     * @param text  текст сообщения
     */
    public void insertMessage(int dialog, int author, String text) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.messages (id_dialogs, id_author, text_message, time_message) values (?,?,?, NOW());"
            );
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, author);
            preparedStatement.setString(3, text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    /**
     * Удалить сообщение
     * @param id id сообщения
     */
    public void deleteMessage(int id) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM public.messages WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    /**
     * Изменить сообщение
     * @param message id сообщения
     * @param dialog новое значение id диалога
     * @param author новое id автора сообщения
     * @param text новый текст сообщения
     */
    public void updateMessage(int message, int dialog, int author, String text) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.messages SET id = ?, id_dialogs = ?, id_author = ?, text_message = ? WHERE id = ?"
            );
            preparedStatement.setInt(1, message);
            preparedStatement.setInt(2, dialog);
            preparedStatement.setInt(3, author);
            preparedStatement.setString(4, text);
            preparedStatement.setInt(5, message);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    /**
     * Удалить список сообщений
     * @param list список удаляемых сообщений
     */
    public void deleteSelectedMessages(List<Integer> list) {
        if (!list.isEmpty()) {
            try {
                Connection connection = ConnectManager.getConnect();
                String query = "DELETE FROM public.messages WHERE";
                for (Integer id : list) {
                    query += " id = " + id.intValue() + " OR";
                }
                query = query.substring(0, query.length() - 3);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getSQLState());
                e.printStackTrace();
            }
        }
    }

    /**
     * Изменить сообщение
     * @param newMessage новое сообщение
     * @param dialog новое значение id диалога
     * @param author новое значение id автора
     * @param text измененный текст сообщения
     * @param oldMessage старое сообщение
     */
    public void updateMessage(int newMessage, int dialog, int author, String text, int oldMessage) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.messages SET id = ?, id_dialogs = ?, id_author = ?, text_message = ? WHERE id = ?"
            );
            preparedStatement.setInt(1, newMessage);
            preparedStatement.setInt(2, dialog);
            preparedStatement.setInt(3, author);
            preparedStatement.setString(4, text);
            preparedStatement.setInt(5, oldMessage);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    /**
     * Получить все сообщения
     * @return список сообщений
     */
    public LinkedList<Message> selectAllMessages() {
        ResultSet resultSet = null;
        LinkedList<Message> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, id_dialogs, id_author, text_message, time_message FROM public.messages",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new Message(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getTimestamp(5).getTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    /**
     * Изменить id сообщения
     * @param newMessage новый id сообщения
     * @param oldMessage старый id сообщения
     */
    public void updateMessageId(int newMessage, int oldMessage) {
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE public.messages SET id = ? WHERE id = ?"
            );
            preparedStatement.setInt(1, newMessage);
            preparedStatement.setInt(2, oldMessage);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    /**
     * Получить сообщение
     * @param id id нужного сообщения
     * @return
     */
    public Message selectMessageById(int id) {
        ResultSet resultSet = null;
        Message message = null;
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, id_dialogs, id_author, text_message, time_message FROM public.messages WHERE id = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            message = new Message(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getTimestamp(5).getTime()
            );
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return message;
        }
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(int dialog) {
        ResultSet resultSet = null;
        LinkedList<ResponseDialogMessage> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT m.id, m.id_dialogs, m.id_author, u.icon, u.name, m.text_message, m.time_message FROM public.messages AS m INNER JOIN public.user AS u ON m.id_author = u.id WHERE id_dialogs = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, dialog);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new ResponseDialogMessage(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(int dialog, long time) {
        ResultSet resultSet = null;
        LinkedList<ResponseDialogMessage> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT m.id, m.id_dialogs, m.id_author, u.icon, u.name, m.text_message, m.time_message " +
                            "FROM public.messages AS m INNER JOIN public.user AS u ON m.id_author = u.id " +
                            "WHERE id_dialogs = ? AND time_message >= ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, dialog);
            preparedStatement.setTimestamp(2, new Timestamp(time));
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new ResponseDialogMessage(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(int user, int dialog) {
        ResultSet resultSet = null;
        LinkedList<ResponseDialogMessage> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT m.id, m.id_dialogs, m.id_author, u.icon, u.name, m.text_message, m.time_message " +
                            "FROM public.messages AS m INNER JOIN public.user AS u ON m.id_author = u.id " +
                            "WHERE id_dialogs = ? AND id_author = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, user);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new ResponseDialogMessage(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(int user, int dialog, long time) {
        ResultSet resultSet = null;
        LinkedList<ResponseDialogMessage> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT m.id, m.id_dialogs, m.id_author, u.icon, u.name, m.text_message, m.time_message " +
                            "FROM public.messages AS m INNER JOIN public.user AS u ON m.id_author = u.id " +
                            "WHERE id_dialogs = ? AND id_author = ? AND time_message >= ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, user);
            preparedStatement.setTimestamp(3, new Timestamp(time));
            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 1; i <= count; i++) {
                resultSet.next();
                list.add(new ResponseDialogMessage(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public ResponseDialogMessage selectLastMessage(int dialog, int author) {
        ResultSet resultSet = null;
        ResponseDialogMessage message = null;
        try {
            Connection connection = ConnectManager.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT m.id, m.id_dialogs, m.id_author, u.icon, u.name, m.text_message, m.time_message " +
                            "FROM public.messages AS m INNER JOIN public.user AS u ON m.id_author = u.id " +
                            "WHERE time_message = (Select MAX(time_message) From public.messages Where id_dialogs = ? And id_author = ?)"
            );
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, author);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                message = new ResponseDialogMessage(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getTimestamp(7).getTime()
                );
            } else {
                message = new ResponseDialogMessage();
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            return message;
        }
    }

    public ResponseDialogMessage sendMessage(int dialogs, int author, String text) {
        insertMessage(dialogs, author, text);
        return selectLastMessage(dialogs, author);
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(RequestDialog requestDialog){
        return selectDialogMessages(requestDialog.getId_dialog());
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(RequestDialogTime requestDialogTime){
        return selectDialogMessages(requestDialogTime.getId_dialogs(), requestDialogTime.getTime());
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(RequestDialogUser requestDialogUser){
        return selectDialogMessages(requestDialogUser.getId_author(), requestDialogUser.getId_dialogs());
    }

    public LinkedList<ResponseDialogMessage> selectDialogMessages(RequestDialogTimeUser requestDialogTimeUser){
        return selectDialogMessages(requestDialogTimeUser.getId_author(), requestDialogTimeUser.getId_dialogs(), requestDialogTimeUser.getTime());
    }

    public ResponseDialogMessage sendMessage(RequestSendMessage response) {
        return sendMessage(
                response.getId_dialogs(),
                response.getId_author(),
                response.getText_message());
    }

    public Message editMessage(RequestEditMessage message) {
        updateMessage(
                message.getId(),
                message.getId_dialogs(),
                message.getId_author(),
                message.getText_message(),
                message.getChangeable_message()
        );
        return selectMessageById(message.getId());
    }

    public MessagesDao() {
    }
}
