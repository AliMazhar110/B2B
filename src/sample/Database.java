package sample;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/airport";
    private static final String username = "root";
    private static final String password = "AbcD123#";
    private static final String className = "com.mysql.cj.jdbc.Driver";
    private static String table;
    private static String query;

    public static boolean insertUser(String name, String id, String passwd,
                              String email, String mobile) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            query = "insert into " + table + " values(" + "'" + name + "','" + id + "','" + passwd
                    + "','" + email + "','" + mobile + "');";
            PreparedStatement prep = con.prepareStatement(query);
            if (!loginUser(id, passwd)) {
                prep.execute();
                return true;
            } else
                return false;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean loginUser(String id, String passwd) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            query = "select * from " + table + ";";//" where (User_ID="+
                    //id +" AND User_Password = "+ passwd +");";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                if (rs.getString(2).equals(id) &&
                        rs.getString(3).equals(passwd))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean bookFlight(String source, String destination, String date, String airline,
                              String flightNo, String PNR, String id, String seats, String departure,
                                     String arrival) {
        table = "flights_booked";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            query = "insert into " + table + " values('"+ id + "', '"+ airline +"', '"+ flightNo +"', '"+
                    PNR + "', '" + source + "', '" + destination + "', '"+ departure +"', '"+ arrival +"', '"+
                    date +"', '"+ seats +"');";
            PreparedStatement prep = con.prepareStatement(query);
            prep.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean cancelFlight(String userid, String PNR) {
        table = "flights_booked";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            query = "delete from " + table + " where ('User_ID' = " + userid + " AND 'PNR' = "
                    + PNR + ");";
            st.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean editProfile(String id, String name, String passwd,
                        String mobile) {
        table = "users";
        try {
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            query = "update " + table + " set " + "Full_Name='" + name + "',"+
            "User_Password='" + passwd + "',User_Mobile='" + mobile + "' where User_ID='"+ id +"';";
            PreparedStatement prep = con.prepareStatement(query);
            prep.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList showFlights(String source, String destination) {
        table = "flights";
        String s = "";
        String d = "";
        try {
            ArrayList<Flights> f = new ArrayList<>();
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            if (source.equals("Delhi"))
                s = "DEL";
            else if (source.equals("Mumbai"))
                s = "BOM";
            else if (source.equals("Kolkata"))
                s = "CCU";
            else if (source.equals("Chennai"))
                s = "MAA";
            if (destination.equals("Delhi"))
                d = "DEL";
            else if (destination.equals("Mumbai"))
                d = "BOM";
            else if (destination.equals("Kolkata"))
                d = "CCU";
            else if (destination.equals("Chennai"))
                d = "MAA";
            query = "select * from " + table +";"; //" where ('source' = " + s +
                    //" AND 'destination' = " + d + ");";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(3).equals(s) && rs.getString(4).equals(d)) {
                    f.add(new Flights(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8)));
                }
            }
            return f;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList showUser(String id) {
        table = "users";
        try {
            ArrayList<Users> u = new ArrayList<>();
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            query = "select * from " + table + " where ('User_ID' = "+ id +");";
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

    public static ArrayList showBookedFlight(String id) {
        table = "flights_booked";
        try {
            ArrayList<BookedFlights> b = new ArrayList<>();
            Class.forName(className);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            query = "select * from " + table + " where ('User_ID' = "+ id + ");";
            ResultSet rs = st.executeQuery(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
                // ID, Airline, FlightNo, PNR, source, destination, departure, arrival, date, seats
                b.add(new BookedFlights(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10)));
            }
            return b;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}