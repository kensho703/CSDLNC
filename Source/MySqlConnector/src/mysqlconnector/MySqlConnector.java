/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class MySqlConnector {

//    private static String _dbHost = "10.240.194.44";
//    private static String _dbPort = "33060";
//    private static String _dbSid = "test";
//    private static String _dbUsername = "root";
//    private static String _dbPassword = "Viettel@123";
    private static String _dbHost = null;
    private static String _dbPort = null;
    private static String _dbSid = null;
    private static String _dbUsername = null;
    private static String _dbPassword = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        try {
            // TODO code application logic here
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter Db_Host:");
            _dbHost = scanner.nextLine();
            System.out.println("enter Db_Port:");
            _dbPort = scanner.nextLine();
            System.out.println("enter Db_Name:");
            _dbSid = scanner.nextLine();
            System.out.println("enter Db_User:");
            _dbUsername = scanner.nextLine();
            System.out.println("enter Db_Pass:");
            _dbPassword = scanner.nextLine();
             System.out.println("enter SQL query:");
             String query = scanner.nextLine();

            conn = getMySqlConnection(_dbHost, _dbPort, _dbSid, _dbUsername, _dbPassword);
            st = conn.createStatement();
//            String query = "select * from tbl_test where 1 = 1 limit 1000";
            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount(); // get column count
            rs.next();
            while (rs.next()) {
                for (int i = 1; i <= count; i++) {
                    System.out.print(metaData.getColumnLabel(i) + " = " + rs.getString(i) + " | ");
                }
                System.out.print("\n");
            }

        } catch (Exception ex) {
            Logger.getLogger(MySqlConnector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(MySqlConnector.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static Connection getMySqlConnection(String host, String port, String sid, String username, String password) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + sid + "?autoReconnect=true&useSSL=false";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

}
