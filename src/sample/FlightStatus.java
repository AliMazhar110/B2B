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

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Flight status");

        Scene scene = new Scene(grid, 300, 300);
        window.setScene(scene);

        Label name = new Label(nameOfUser);
        Label airline = new Label(airlineName);
        Label flight = new Label(flightNumber);
        Label departure = new Label(time);

        VBox vertical = new VBox(10);
        vertical.setAlignment(Pos.CENTER_LEFT);
        vertical.getChildren().addAll(name, airline, flight, departure);

        Button exitWindow = new Button("Back");
        exitWindow.setOnAction(e -> window.close());

        grid.add(exitWindow, 0, 4);
        grid.add(vertical, 0, 2);

        window.showAndWait();
    }
}