package sample;

import java.util.ArrayList;

public class Notification {
    public static void sendNotification(String id) {
        ArrayList<BookedFlights> b = Database.showBookedFlight(id);
        ArrayList<Users> u = Database.showUser(id);
    }
}
