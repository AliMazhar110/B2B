package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String username = "root";
    private static final String password = "AbcD123#";
    private static final String className = "com.mysql.cj.jdbc.Driver";
    private static String table;
    private static String query;

    private static void showMenu() {
        System.out.println("\n1. Insert User"+
                        "\n2. Show flights"+
                        "\n3. Insert Booked flight"+
                        "\n4. Cancel Booked Flight"+
                        "\n5. Edit User's details"+
                        "\n6. Show flight status"+
                        "\n7. Timings of all the flights");
    }

    public void access(int choice) {
        table = "world";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url+table,
                    username, password);
            System.out.println("Connection Successful");

            // Use of database
            // 1. Input personal details and register and store the data
            // 2. Login
            // 3. Show flights
            // 4. Input user's booked flight and data related to it
            // 5. Cancel user's booked flight
            // 6. Edit user's details
            // 7. Show flight status
            // 8. Timings of flights going from one place to another

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Name from country where Population < 2000");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertUser(String name, String id, String passwd, String email, String mobile) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url+table, username, password);
            Statement st = con.createStatement();
            query = "insert into "+ table +" values("+"'"+ name +"','"+ id +"','"+ passwd
                    +"','"+ email +"','"+ mobile +"');";
            ResultSet rs = st.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loginUser(String id, String passwd) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url+table, username, password);
            Statement st = con.createStatement();
            query = "select * from "+ table +";";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void bookFlight() {

    }

    public ArrayList showFlights(String source, String destination) {
        return null;
    }
}
















