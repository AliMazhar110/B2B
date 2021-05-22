package sample;

import javafx.scene.image.Image;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
//import javafx.application.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ticket {//extends Application {

    /*@Override
    public void start(Stage stage) throws Exception {
        showTicket(stage);
    }*/

    public static void showTicket(Stage window, String id) {
        SignedInUser s = new SignedInUser();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("media/B2B-Background.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputStream);
        grid.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        window.setTitle("Boarding Pass");
        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Font font = Font.font("Century", FontWeight.SEMI_BOLD, 16);

        HBox details = showDetails(id);

        /*Label name = new Label("Name: ");
        name.setFont(font);
        Label airline = new Label("Airline: ");
        airline.setFont(font);
        Label flight = new Label("Flight No: ");
        flight.setFont(font);
        Label seatNo = new Label("Seat No: ");
        seatNo.setFont(font);
        Label gateNo = new Label("Gate No: ");
        gateNo.setFont(font);
        Label time = new Label("Departure: ");
        time.setFont(font);

        VBox vertical = new VBox(10);
        VBox verticalSecondColumn = new VBox(10);
        vertical.setAlignment(Pos.CENTER);
        vertical.getChildren().addAll(airline, name, seatNo, gateNo);
        verticalSecondColumn.getChildren().addAll(flight, time);
        */
        details.setAlignment(Pos.CENTER);

        Button back = new Button("Back");
        back.setOnAction(e -> {
            try {
                s.display("Welcome", window, id);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        back.setStyle("-fx-background-color: #FFA500;");
        back.setFont(Font.font("Century", FontWeight.NORMAL, 16));

        grid.add(details, 0, 2);
        //grid.add(verticalSecondColumn, 2, 2);
        grid.add(back, 1, 4);

        window.show();
    }

    private static HBox showDetails(String id) {
        Font font = Font.font("Century", FontWeight.SEMI_BOLD, 16);
        ArrayList<BookedFlights> b = Database.showBookedFlight(id);
        HBox v = new HBox(10);
        for (int i = 0; i < b.size(); ++i) {
            Label airline = new Label(b.get(i).getAirline());
            airline.setFont(font);
            Label flight = new Label(b.get(i).getFlightNo());
            flight.setFont(font);
            Label source = new Label(b.get(i).getSource());
            source.setFont(font);
            Label destination = new Label(b.get(i).getDestination());
            destination.setFont(font);
            Label departureTime = new Label(b.get(i).getDepartureTime());
            departureTime.setFont(font);
            Label arrivalTime = new Label(b.get(i).getArrivalTime());
            arrivalTime.setFont(font);
            Label seats = new Label(b.get(i).getSeats());
            arrivalTime.setFont(font);
            v.getChildren().addAll(airline, flight, source, destination,
                            departureTime, arrivalTime, seats);
            return v;
        }
        return v;
    }
}
