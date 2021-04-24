package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class FlightStatus {
    
    public static void display(String nameOfUser, String airlineName,
                               String flightNumber, String time) {
        Stage window = new Stage();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));

        // to take input in this window only and not other open windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Flight status");
        window.setMinWidth(850);

        Label name = new Label(nameOfUser);
        Label airline = new Label(airlineName);
        Label flight = new Label(flightNumber);
        Label departure = new Label(time);

        VBox vertical = new VBox(10);
        HBox horizontal = new HBox(10);
        vertical.setAlignment(Pos.CENTER_LEFT);
        horizontal.setAlignment(Pos.BOTTOM_CENTER);
        vertical.getChildren().add(name);
        horizontal.getChildren().addAll(airline, flight, departure);

        grid.add(vertical, 0, 2);
        grid.add(horizontal, 1, 1);

        window.showAndWait();
    }
}