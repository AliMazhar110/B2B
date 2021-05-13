package sample;

import java.sql.*;

public class Database {
    public static void main(String[] args) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world",
                        "root", "AbcD123#");
                System.out.println("Connection Successful");
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
}
