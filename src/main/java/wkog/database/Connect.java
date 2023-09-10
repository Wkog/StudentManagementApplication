package wkog.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String URL = "jdbc:sqlserver://localhost:1433;databaseName=StudentManagement;integratedSecurity=false;trustServerCertificate=true";
            String USERNAME = "AdminStudentManagementApplication";
            String PASSWORD = "AdminStudentManagementApplication";

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return connection;
    }
}
