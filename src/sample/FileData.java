package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class FileData {
    private final SimpleStringProperty airplane;
    private final SimpleStringProperty flightNumber;
    private final SimpleStringProperty PNR;
    private final SimpleStringProperty departure;
    private final SimpleStringProperty arrival;
    private final SimpleStringProperty source;
    private final SimpleStringProperty destination;
    private final CheckBox checkBox;

    FileData(String airplane, String flightNumber, String pnr, String source, String destination, String departure,
             String arrival) {
        this.airplane = new SimpleStringProperty(airplane);
        this.flightNumber = new SimpleStringProperty(flightNumber);
        this.PNR = new SimpleStringProperty(pnr);
        this.source = new SimpleStringProperty(source);
        this.destination = new SimpleStringProperty(destination);
        this.departure = new SimpleStringProperty(departure);
        this.arrival = new SimpleStringProperty(arrival);
        this.checkBox = new CheckBox();
    }

    public String getAirplane() { return airplane.get(); }
    public String getFlightNumber() { return flightNumber.get(); }
    public String getPNR() { return PNR.get(); }
    public String getDeparture() { return departure.get(); }
    public String getArrival() { return arrival.get(); }
    public String getDestination() { return destination.get(); }
    public String getSource() { return source.get(); }
    public CheckBox getCheckBox() { return checkBox; }
}