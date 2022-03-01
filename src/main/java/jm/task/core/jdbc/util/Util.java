package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// реализуйте настройку соеденения с БД

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/my_schema";
    private static final String USERNAME = "zabava";
    private static final String PASSWORD = "12345678";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
