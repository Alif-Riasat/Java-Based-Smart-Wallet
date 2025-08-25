package axia;

import java.sql.*;

public class DBConnection {
    //Connect To Pre-Made SQL DatBase
    private static final String URL = "jdbc:mysql://localhost:3306/smart_wallet";
    private static final String USER = "root"; // update with your MySQL username
    private static final String PASSWORD = "pass1234@#Alif"; // update with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
