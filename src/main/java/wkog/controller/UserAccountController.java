package wkog.controller;

import wkog.database.Connect;
import wkog.model.UserAccount;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class UserAccountController {
    private PreparedStatement preparedStatement;
    private Connection connection;

    public String checkUserLogin(UserAccount userAccount) {
        connection = Connect.getConnection();
        String selectUserAccount;
        if (userAccount.getUsername().equals("admin")) {
            selectUserAccount = "SELECT Password, LevelAccountId FROM AdminAccount WHERE Username = ? ";
        } else {
            selectUserAccount = "SELECT Password, LevelAccountId FROM UserAccount WHERE Username = ? ";
        }
        try {
            preparedStatement = connection.prepareStatement(selectUserAccount);
            preparedStatement.setString(1, userAccount.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(userAccount.getPassword())) {
                    userAccount.setLevelAccount(resultSet.getInt(2));
                    return "success";
                }
            }
            preparedStatement.close();
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "fail";
    }

    public void writeUserToFile(UserAccount userAccount) {
        Date getDate = new Date();
        getDate.getTime();
        TreeMap<Date, UserAccount> dateUserAccountTreeSet = new TreeMap<Date, UserAccount>();
        dateUserAccountTreeSet.put(getDate, userAccount);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/java/wkog/file/user.ser"))) {
            objectOutputStream.writeObject(dateUserAccountTreeSet);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearUserToFile() {
        try (RandomAccessFile raf = new RandomAccessFile(new File("src/main/java/wkog/file/user.ser"), "rw")) {
            raf.setLength(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserAccount readUserToFile() {
        Date getDate = new Date();
        UserAccount userAccount = new UserAccount();
        TreeMap<Date, UserAccount> dateUserAccountTreeSet;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/java/wkog/file/user.ser"))) {
            dateUserAccountTreeSet = (TreeMap<Date, UserAccount>) objectInputStream.readObject();
            if (!dateUserAccountTreeSet.isEmpty()) {
                for (Map.Entry<Date, UserAccount> entry : dateUserAccountTreeSet.entrySet()) {
                    getDate = entry.getKey();
                    userAccount = entry.getValue();
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        259200000
        if ((new Date().getTime() - getDate.getTime()) < 2) {
            return userAccount;
        } else {
            this.clearUserToFile();
        }
        return null;
    }
}
