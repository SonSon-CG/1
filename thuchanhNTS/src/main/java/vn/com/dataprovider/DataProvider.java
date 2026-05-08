package vn.com.dataprovider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataProvider {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_LINK = 
        "jdbc:mysql://localhost:3306/monan?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";

    public static Connection ketNoi() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DATABASE_LINK, "root", "Svd2024@");
        } catch (ClassNotFoundException ex) {
            System.err.println("Lỗi không tìm thấy driver. Chi tiết: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Không kết nối được với Database. Chi tiết: " + ex.getMessage());
        }
        return conn;
    }
}