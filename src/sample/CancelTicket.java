package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.application.Application;

public class CancelTicket extends Application {

    private static SignedInUser s = new SignedInUser();

    @Override
    public void start(Stage primaryStage) {
        // SQL code to get name, airline, flight number, departure time,
        // gate number, number of passengers and seat number from the database
        // to be added soon

        display(primaryStage, "Name of User", "Airline", "Flight Number", "Departure",
                "Number Of Passengers", "Seat Number");
    }

    private static void display(Stage window, String name, String airline,
                                String flightNumber, String departure,
                                String numberOfPassengers, String seatNumber) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));

        window.setTitle("Cancel Ticket");
        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Label nameOfUser = new Label(name);
        Label flightProvider = new Label(airline);
        Label flight = new Label(flightNumber);
        Label seatNo = new Label(seatNumber);
        Label time = new Label(departure);
        Label noOfPassengers = new Label(numberOfPassengers);

        VBox v = new VBox(10);
        VBox secondColumn = new VBox(10);
        v.setAlignment(Pos.CENTER);
        secondColumn.setAlignment(Pos.CENTER_LEFT);
        v.getChildren().addAll(nameOfUser, flightProvider,
                flight, time);
        secondColumn.getChildren().addAll(noOfPassengers, seatNo);

        Button back = new Button("Back");
        back.setOnAction(e -> s.start(window));

        grid.add(v, 0, 2);
        grid.add(secondColumn, 2, 2);
        grid.add(back, 0, 4);

        window.show();
    }
}