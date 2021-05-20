package sample;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.application.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

        Label name = new Label("Name: ");
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

        Button back = new Button("Back");
        back.setOnAction(e -> s.start(window));
        back.setStyle("-fx-background-color: #FFA500;");
        back.setFont(Font.font("Century", FontWeight.NORMAL, 16));

        grid.add(vertical, 0, 2);
        grid.add(verticalSecondColumn, 2, 2);
        grid.add(back, 1, 4);

        window.show();
    }
}
