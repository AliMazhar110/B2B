package sample;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FlightStatus {

    private static SignedInUser s = new SignedInUser();

    public static void display(Stage window, String nameOfUser, String airlineName,
                               String flightNumber, String time) {

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

        window.setTitle("Flight status");

        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);
        Font font = Font.font("Century", FontWeight.SEMI_BOLD, 16);

        Label name = new Label(nameOfUser);
        name.setFont(font);
        Label airline = new Label(airlineName);
        airline.setFont(font);
        Label flight = new Label(flightNumber);
        flight.setFont(font);
        Label departure = new Label(time);
        departure.setFont(font);

        VBox vertical = new VBox(10);
        vertical.setAlignment(Pos.CENTER_LEFT);
        vertical.getChildren().addAll(name, airline, flight, departure);

        Button exitWindow = new Button("Back");
        exitWindow.setOnAction(e -> {
            try {
                s.display("Welcome", window, "id");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        exitWindow.setStyle("-fx-background-color: #FFA500;");
        exitWindow.setFont(Font.font("Century", FontWeight.NORMAL, 16));

        grid.add(exitWindow, 0, 4);
        grid.add(vertical, 0, 2);

        window.show();
    }
}