package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.application.Application;

public class Ticket extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        showTicket(stage);
    }

    private static void showTicket(Stage window) {
        SignedInUser s = new SignedInUser();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));

        window.setTitle("Boarding Pass");
        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Label name = new Label("Name: ");
        Label airline = new Label("Airline: ");
        Label flight = new Label("Flight No: ");
        Label seatNo = new Label("Seat No: ");
        Label gateNo = new Label("Gate No: ");
        Label time = new Label("Departure: ");

        VBox vertical = new VBox(10);
        VBox verticalSecondColumn = new VBox(10);
        vertical.setAlignment(Pos.CENTER);
        vertical.getChildren().addAll(airline, name, seatNo, gateNo);
        verticalSecondColumn.getChildren().addAll(flight, time);

        Button back = new Button("Back");
        back.setOnAction(e -> s.start(window));

        grid.add(vertical, 0, 2);
        grid.add(verticalSecondColumn, 2, 2);
        grid.add(back, 0, 4);

        window.show();
    }
}
