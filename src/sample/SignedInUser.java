package sample;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
//import javafx.application.Application;
import javafx.scene.layout.Background;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SignedInUser{// extends Application {
    private static FlightBooking book = new FlightBooking();
    private static CancelTicket c = new CancelTicket();

    /*@Override
    public void start(Stage window) {
        String title = "Welcome user";
        try {
            display(title, window);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    public static void display(String title, Stage window, String id) throws FileNotFoundException {

        window.setTitle(title);

        Font btnFont = Font.font("Century", FontWeight.NORMAL, 16);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(40, 40, 40, 40));
        FileInputStream inputStream = new FileInputStream("media/B2B-Background.png");
        Image image = new Image(inputStream);
        grid.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Button editProfile = new Button("Edit profile");
        Button bookFlight = new Button("Book flight");
        Button cancelFlight = new Button("Cancel a flight");
        Button viewFlightStatus = new Button("View flight status");
        Button logout = new Button("Logout");

        editProfile.setStyle("-fx-background-color: #FFA500;");
        editProfile.setFont(btnFont);
        bookFlight.setStyle("-fx-background-color: #FFA500;");
        bookFlight.setFont(btnFont);
        cancelFlight.setStyle("-fx-background-color: #FFA500;");
        cancelFlight.setFont(btnFont);
        viewFlightStatus.setStyle("-fx-background-color: #FFA500;");
        viewFlightStatus.setFont(btnFont);
        logout.setStyle("-fx-background-color: #FFA500;");
        logout.setFont(btnFont);

        editProfile.setOnAction(e -> { // Edit profile
            try {
                EditProfile.display(window, id);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        bookFlight.setOnAction(e -> { // Book flight
            book.display(window, id);
        });
        cancelFlight.setOnAction(e -> { // cancel flight
            try {
                c.display(window, id);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        viewFlightStatus.setOnAction(e -> { // view flight status
            FlightStatus.display(window, id);
        });
        logout.setOnAction(e ->  { // logout
            System.out.println("Successfully Logged out");
            try {
                Main.welcomePage(window);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(viewFlightStatus,
                editProfile, bookFlight,
                cancelFlight, logout);
        layout.setAlignment(Pos.CENTER);
        grid.add(layout, 0, 1);

        window.show();
    }
}