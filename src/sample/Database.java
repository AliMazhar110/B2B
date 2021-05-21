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

    public boolean insertUser(String name, String id, String passwd,
                              String email, String mobile) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            query = "insert into " + table + " values(" + "'" + name + "','" + id + "','" + passwd
                    + "','" + email + "','" + mobile + "');";
            PreparedStatement prep = con.prepareStatement(query);
            if (!loginUser(id, passwd)) {
                prep.execute();
                return true;
            } else
                return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean loginUser(String id, String passwd) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            Statement st = con.createStatement();
            query = "select * from " + table + " where ('User_ID'="+
                    id +" AND 'User_Password' = "+ passwd +");";
            ResultSet rs = st.executeQuery(query);
            if (rs.getString(2).equals(id) &&
                    rs.getString(3).equals(passwd))
                return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean bookFlight(String source, String destination, String date,
                              String flightNo, String PNR, String id) {
        table = "flights_booked";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            query = "insert into " + table + " values(" + "'" + id + "', '" + flightNo + "', '" +
                    PNR + "', '" + source + "', '" + destination + "', '"+ date +"');";
            PreparedStatement prep = con.prepareStatement(query);
            prep.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insertSeats(String id, int[] seats) {
        table = "seats_booked";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            for (int i = 0; i < seats.length; ++i) {
                query = "insert into " + table + " values(" + "'" + id + "', " + seats[i] +");";
                PreparedStatement prep = con.prepareStatement(query);
                prep.execute();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean cancelFlight(String userid, String PNR) {
        table = "booked";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            Statement st = con.createStatement();
            query = "delete from " + table + " where ('userid' = " + userid + " AND 'PNR' = "
                    + PNR + ");";
            st.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean editProfile(String name, String id, String passwd,
                              String email, String mobile) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            query = "insert into " + table + " values(" + "'" + name + "','" + id + "','" + passwd
                    + "','" + email + "','" + mobile + "');";
            PreparedStatement prep = con.prepareStatement(query);
            if (!loginUser(id, passwd)) {
                prep.execute();
                return true;
            } else
                return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList showFlights(String source, String destination) {
        table = "flights";
        try {
            ArrayList<Flights> f = new ArrayList<>();
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            Statement st = con.createStatement();
            query = "select * from " + table + " where ('source' = " + source +
                    " AND 'destination' = " + destination + ");";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                f.add(new Flights(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8)));
            }
            return f;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList showUser(String id) {
        table = "users";
        try {
            ArrayList<Users> u = new ArrayList<>();
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            Statement st = con.createStatement();
            query = "select * from " + table + " where ('' = "+");";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                u.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)));
            }
            return u;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*public ArrayList showBookedFlight(String id) {
        table = "flights";
        try {
            ArrayList<Flights> f = new ArrayList<>();
            Class.forName(className);
            Connection con = DriverManager.getConnection(url + table, username, password);
            Statement st = con.createStatement();
            query = "select * from " + table + " where ('source' = " + source +
                    " AND 'destination' = " + destination + ");";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                f.add(new Flights(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8)));
            }
            return f;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }*/
}