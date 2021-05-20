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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    private static GridPane getGrid() throws FileNotFoundException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));
        FileInputStream inputStream = new FileInputStream("media/B2B-Background.png");
        Image image = new Image(inputStream);
        grid.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        return grid;
    }

    private static void display(Stage window, String name, String airline,
                                String flightNumber, String departure,
                                String numberOfPassengers, String seatNumber) {

        GridPane grid = null;
        try {
            grid = getGrid();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        window.setTitle("Cancel Ticket");
        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Font btnFont = Font.font("Century", FontWeight.NORMAL, 16);

        Label nameOfUser = new Label(name);
        Label flightProvider = new Label(airline);
        Label flight = new Label(flightNumber);
        Label seatNo = new Label(seatNumber);
        Label time = new Label(departure);
        Label noOfPassengers = new Label(numberOfPassengers);

        Font font = Font.font("Century", FontWeight.EXTRA_BOLD, 16);

        nameOfUser.setFont(font);
        flightProvider.setFont(font);
        flight.setFont(font);
        seatNo.setFont(font);
        time.setFont(font);
        noOfPassengers.setFont(font);

        VBox v = new VBox(10);
        VBox secondColumn = new VBox(10);
        v.setAlignment(Pos.CENTER);
        secondColumn.setAlignment(Pos.CENTER_LEFT);
        v.getChildren().addAll(nameOfUser, flightProvider,
                flight, time);
        secondColumn.getChildren().addAll(noOfPassengers, seatNo);

        Button back = new Button("Back");
        Button cancelTicket = new Button("Cancel Ticket");
        back.setStyle("-fx-background-color: #FFA500;");
        back.setFont(btnFont);
        cancelTicket.setStyle("-fx-background-color: #FFA500;");
        cancelTicket.setFont(btnFont);
        String message = "Cancel Ticket";
        cancelTicket.setOnAction(e -> cancelTicketConfirmation(message));
        back.setOnAction(e -> s.start(window));

        grid.add(v, 0, 2);
        grid.add(secondColumn, 2, 2);
        grid.add(back, 0, 4);
        grid.add(cancelTicket, 2, 4);

        window.show();
    }

    public static void cancelTicketConfirmation(String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("CONFIRMATION");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button confirm = new Button("Confirm");
        Button back = new Button("Back");

        confirm.setOnAction(e -> window.close());
        back.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, confirm, back);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}