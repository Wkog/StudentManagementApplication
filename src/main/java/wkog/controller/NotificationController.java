package wkog.controller;

import wkog.database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationController {
    private PreparedStatement preparedStatement;
    private Connection connection;

    public List<String> getNotification() {
        List<String> notificationList = new ArrayList<>();
        connection = Connect.getConnection();
        String selectNotification ="SELECT NotificationId, Content, InitializationDate, ExpirationDate, Author, ModifiedDate FROM Notification";
        try {
            preparedStatement = connection.prepareStatement(selectNotification);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if ((resultSet.getDate(4).getDate() - resultSet.getDate(3).getDate() < 10)) {
                    notificationList.add(resultSet.getString(2));
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return notificationList;
    }
}
