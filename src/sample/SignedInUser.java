package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class SignedInUser {

    private static FlightBooking f = new FlightBooking();

    public static void display(String title) {
        Stage window = new Stage();

        // to take input in this window only and not other open windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        Label label = new Label();
        Button editProfile = new Button("Edit profile");
        Button bookFlight = new Button("Book flight");
        Button cancelFlight = new Button("Cancel a flight");
        Button viewFlightStatus = new Button("View flight status");
        Button logout = new Button("Logout");
        editProfile.setOnAction(e -> System.out.println("Edit profile clicked"));
        bookFlight.setOnAction(e -> FlightBooking.display());
        cancelFlight.setOnAction(e -> System.out.println("Cancel a flight clicked"));
        viewFlightStatus.setOnAction(e -> {
            FlightStatus.display("User", "Airline",
                    "Flight Number", "Departure time");
        });// System.out.println("View Flight status clicked"));
        logout.setOnAction(e ->  {
            System.out.println("Successfully Logged out");
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, viewFlightStatus,
                editProfile, bookFlight, cancelFlight, logout);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
