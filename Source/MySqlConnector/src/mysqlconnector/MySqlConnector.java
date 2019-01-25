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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kensho703
 */
public class MySqlConnector {

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

            conn = getMySqlConnection(_dbHost, _dbPort, _dbSid, _dbUsername, _dbPassword);
            st = conn.createStatement();

            scanner = new Scanner(System.in);
            System.out.println("Enter number to choose option: ");
            System.out.println("0. Exit ");
            System.out.println("1. Select");
            System.out.println("2. Insert");
            System.out.println("3. Delete");
            int option = scanner.nextInt();

            while (option != 0) {
                switch (option) {
                    case 1: {
                        scanner = new Scanner(System.in);
                        System.out.println("enter SQL query:");
                        String query = scanner.nextLine();
                        pstmt = conn.prepareStatement(query);
                        long startTime = System.currentTimeMillis();
                        ResultSet rs = pstmt.executeQuery();
                        ResultSetMetaData metaData = rs.getMetaData();
                        rs.next();
                        while (rs.next()) {
                        }
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        System.out.println("Executed time of query : " + duration + " ms");

                        System.out.println("Enter number to choose option: ");
                        System.out.println("0. Exit ");
                        System.out.println("1. Select");
                        System.out.println("2. Insert");
                        System.out.println("3. Delete");
                        option = scanner.nextInt();
                    }

                    break;
                    case 2: {
                        scanner = new Scanner(System.in);
                        System.out.println("enter SQL query:");
                        String query = scanner.nextLine();
                        long startTime = System.currentTimeMillis();
                        pstmt = conn.prepareStatement(query);
                        pstmt.executeUpdate();
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        System.out.println("Executed time of query : " + duration + " ms");

                        System.out.println("Enter number to choose option: ");
                        System.out.println("0. Exit ");
                        System.out.println("1. Select");
                        System.out.println("2. Insert");
                        System.out.println("3. Delete");
                        option = scanner.nextInt();
                    }
                    break;
                    case 3: {
                        scanner = new Scanner(System.in);
                        System.out.println("enter SQL query:");
                        String query = scanner.nextLine();
                        long startTime = System.currentTimeMillis();
                        pstmt = conn.prepareStatement(query);
                        pstmt.executeUpdate();
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        System.out.println("Executed time of query : " + duration + " ms");

                        System.out.println("Enter number to choose option: ");
                        System.out.println("0. Exit ");
                        System.out.println("1. Select");
                        System.out.println("2. Insert");
                        System.out.println("3. Delete");
                        option = scanner.nextInt();
                    }
                    break;
                    default:
                        throw new AssertionError();
                }
            }

////            String query = "select * from tbl_test where 1 = 1 limit 1000";
//            st = conn.createStatement();
//            pstmt = conn.prepareStatement(query);
//            long startTime = System.currentTimeMillis();
//            ResultSet rs = pstmt.executeQuery();
//            ResultSetMetaData metaData = rs.getMetaData();
////            int count = metaData.getColumnCount(); // get column count
//            rs.next();
//            while (rs.next()) {
////                for (int i = 1; i <= count; i++) {
////                    System.out.print(metaData.getColumnLabel(i) + " = " + rs.getString(i) + " | ");
////                }
////                rs.getString(i);
////                System.out.print("\n");
//            }
//            long endTime = System.currentTimeMillis();
//            long duration = endTime - startTime;
//            System.out.println("Executed time of query : " + duration + " ms");
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
        String url = "jdbc:mysql://" + host + ":" + port + "/" + sid + "?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

}
