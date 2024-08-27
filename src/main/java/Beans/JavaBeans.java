package Beans;

import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JavaBeans {
    private ConnectDB connectDB;

    public int checkEmpty(Object un, Object pw) {
        if (!un.equals("")) {
            if (!pw.equals("")) {
                return 0;
            } else {
                return -1;
            }
        }
        return 1;
    }

    public int checkLogin(String un, String pw) {
        if (un.equals("admin")) {
            if (pw.equals("admin")) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public int checkLoginWithConnectDB(String un, String pw) {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con = ConnectDB.getConnection();
        String sql = "Select * from Users where userName = ? and passWord = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, un);
            stmt.setString(2, pw);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (!rs.getString(1).isEmpty()) {
                    if (!rs.getString(2).isEmpty()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
