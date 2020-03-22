package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    private static ConnectionClass instance = new ConnectionClass();

    ConnectionClass() {

    }

    public static ConnectionClass getInstance() {
        return instance;
    }

    public Connection getConnection() {

        //Prihlasovacie informácie do databázy a jej názov
        Connection connection;
        String dbName = "macrohard";
        String user = "root";
        String pass = "";

        //Nadviazanie spojenia s databázou
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/" + dbName + "?useLegacyDatetimeCode=false&serverTimezone=America/New_York", user, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
