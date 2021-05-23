package sample;

import com.email.durgesh.Email;
import java.util.ArrayList;

public class Notification {
    public static void sendNotification(String id) {
        ArrayList<BookedFlights> b = Database.showBookedFlight(id);
        ArrayList<Users> u = Database.showUser(id);
        try{
            Email email = new Email("luqmani.mazhar.ali@gmail.com","FATIMA@92");
            //From
            email.setFrom("luqmani.mazhar.ali@gmail.com","B2B");
            email.setSubject("Your Ticket");
            email.setContent("<table><tr><td>Name = "+u.get(u.size()-1).getName()+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PNR = "+b.get(b.size()-1).getPNR()+"</td></tr>" +
                    "<tr><td>AirLine = "+b.get(b.size()-1).getAirline()+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Flight Number = "+b.get(b.size()-1).getFlightNo()+"</td></tr>"+
                    "<tr><td>Source = "+b.get(b.size()-1).getSource()+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Destination = "+b.get(b.size()-1).getDestination()+"</td></tr>"+
                    "<tr><td>Departure Time = "+b.get(b.size()-1).getDepartureTime()+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Arrival Time = "+b.get(b.size()-1).getArrivalTime()+"</td></tr>"+
                    "</table><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date = "+b.get(b.size()-1).getDate()+"<h3>","text/html");
            email.addRecipient(u.get(u.size()-1).getEmail());
            email.send();
        }
        catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
}
