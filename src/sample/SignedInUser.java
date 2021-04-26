package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.application.Application;

public class SignedInUser extends Application {
    private static FlightBooking book = new FlightBooking();
    private static Ticket t = new Ticket();

    @Override
    public void start(Stage window) {
        String title = "Welcome user";
        display(title, window);
    }

    private static void display(String title, Stage window) {

        window.setTitle(title);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));

        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Label label = new Label();
        Button editProfile = new Button("Edit profile");
        Button bookFlight = new Button("Book flight");
        Button cancelFlight = new Button("Cancel a flight");
        Button viewFlightStatus = new Button("View flight status");
        Button logout = new Button("Logout");
        Button viewBoardingPass = new Button("View Boarding pass");

        editProfile.setOnAction(e -> System.out.println("Edit profile clicked"));
        bookFlight.setOnAction(e -> {
            book.start(window);
        });
        cancelFlight.setOnAction(e -> System.out.println("Cancel a flight clicked"));
        viewFlightStatus.setOnAction(e -> {
            FlightStatus.display("User", "Airline",
                    "Flight Number", "Departure time");
        });
        logout.setOnAction(e ->  {
            System.out.println("Successfully Logged out");
            Main.welcomePage(window);
        });
        viewBoardingPass.setOnAction(e -> {
            try {
                t.start(window);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, viewBoardingPass,
                viewFlightStatus, editProfile, bookFlight,
                cancelFlight, logout);
        layout.setAlignment(Pos.CENTER);
        grid.add(layout, 0, 1);

        window.show();
    }
}
