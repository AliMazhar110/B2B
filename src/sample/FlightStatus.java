package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class FlightStatus {

    private static SignedInUser s = new SignedInUser();

    public static void display(Stage window, String nameOfUser, String airlineName,
                               String flightNumber, String time) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));

        window.setTitle("Flight status");

        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Label name = new Label(nameOfUser);
        Label airline = new Label(airlineName);
        Label flight = new Label(flightNumber);
        Label departure = new Label(time);

        VBox vertical = new VBox(10);
        vertical.setAlignment(Pos.CENTER_LEFT);
        vertical.getChildren().addAll(name, airline, flight, departure);

        Button exitWindow = new Button("Back");
        exitWindow.setOnAction(e -> s.start(window));

        grid.add(exitWindow, 0, 4);
        grid.add(vertical, 0, 2);

        window.show();
    }
}