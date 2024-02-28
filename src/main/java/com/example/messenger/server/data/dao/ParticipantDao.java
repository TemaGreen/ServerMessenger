package com.example.messenger.server.data.dao;

import com.example.messenger.server.data.ConnectManager;
import com.example.messenger.server.data.model.User;
import com.example.messenger.server.data.request.RequestAddNewParticipantDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ParticipantDao {

    /**
     * Добавить новых участников диалога
     * @param request запрос, содержащий информация о диалоге и новых участниках
     */
    public void addNewParticipantDialog(RequestAddNewParticipantDialog request){
        List<Integer> ids = request.getParticipants().stream().map(User::getId).toList();
        insertParticipant(request.getDialog(), ids);
    }

    public void insertParticipant(int dialog, int participant){
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            String query = "INSERT INTO public.user_dialogs(id_dialogs, id_participant) values (?,?, (SELECT id FROM public.role WHERE name = 'User'))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, participant);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void insertParticipant(int dialog, int participant, int role){
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            String query = "INSERT INTO public.user_dialogs(id_dialogs, id_participant, role) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, participant);
            preparedStatement.setInt(3, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void insertParticipant(int dialog, int participant, String role){
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            String query = "INSERT INTO public.user_dialogs(id_dialogs, id_participant, role) values (?,?, (SELECT id FROM public.role WHERE name = ?))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, participant);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    public void insertParticipant(int dialog, User participant){
        insertParticipant(dialog, participant.getId());
    }

    public void insertParticipant(int dialog, List<Integer> participants){
        ResultSet resultSet = null;
        LinkedList<User> list = new LinkedList<>();
        try {
            Connection connection = ConnectManager.getConnect();
            String query = "INSERT INTO public.user_dialogs(id_dialogs, id_participant, role) values \n";
            query += participants.stream()
                    .sorted()
                    .map(participant -> "(" + dialog + ", " + participant.toString() + ", (SELECT id FROM public.role WHERE name = 'User'))")
                    .reduce((x, y) -> x + ",\n" + y)
                    .get();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    /**
     * Удалить участника диалога
      * @param dialog id диалога
     * @param id_participant id участника диалога
     */
    public void deleteParticipant(int dialog, int id_participant) {
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectManager.getConnect();
            String query = "DELETE FROM public.user_dialogs WHERE id_dialogs = ? AND id_participant = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dialog);
            preparedStatement.setInt(2, id_participant);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        }
    }

    /**
     * Удалить участника диалога
     * @param dialog id диалога
     * @param participant участник диалога
     */
    public void deleteParticipant(int dialog, User participant) {
        deleteParticipant(dialog, participant.getId());
    }

    /**
     * Удалить несколько участников диалога
     * @param dialog id диалога
     * @param participants список участников диалога
     */
    public void deleteParticipant(int dialog, List<User> participants) {
        participants.stream().forEach(participant -> {
            deleteParticipant(dialog, participant);
        });
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
}
