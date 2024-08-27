package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();
    public static Connection connection = null;

    public static ConnectDB getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        String user = "root";
        String password = "root";
        String url = "jdbc:mariadb://localhost:3306/huynhkimthanh_lab01";

        connection = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
