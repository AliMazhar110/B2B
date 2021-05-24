package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.Background;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CancelTicket {//extends Application {

    private static SignedInUser s = new SignedInUser();
    private static Table t = new Table();
    /*@Override
    public void start(Stage primaryStage) {
        // SQL code to get name, airline, flight number, departure time,
        // gate number, number of passengers and seat number from the database
        // to be added soon

        display(primaryStage, "Name of User", "Airline", "Flight Number", "Departure",
                "Number Of Passengers", "Seat Number");
    }*/

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

    public void display(Stage window, String id) throws FileNotFoundException {
        GridPane grid = null;
        try {
            grid = getGrid();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        window.setTitle("Cancel Ticket");
        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);
        ArrayList<BookedFlights> b = Database.showBookedFlight(id);
        ArrayList<FileData> data = new ArrayList<>();
        ArrayList<String> p = new ArrayList<>();
        Font btnFont = Font.font("Century", FontWeight.NORMAL, 16);

        Label airline = new Label(b.get(0).getAirline());
        Label flight = new Label(b.get(0).getFlightNo());
        Label seatNo = new Label(b.get(0).getSeats());
        Label departureTime = new Label(b.get(0).getDepartureTime());
        Label arrivalTime = new Label(b.get(0).getArrivalTime());

        Font font = Font.font("Century", FontWeight.EXTRA_BOLD, 16);

        for (BookedFlights bf : b) {
            data.add(new FileData(bf.getAirline(), bf.getFlightNo(),
                    bf.getPNR(), bf.getSource(), bf.getDestination(),
                    bf.getDepartureTime(), bf.getArrivalTime()));
            p.add(bf.getPNR());
        }

        ObservableList<FileData> d = FXCollections.observableArrayList(data);
        t.display(window, id, p, d);

        airline.setFont(font);
        departureTime.setFont(font);
        flight.setFont(font);
        seatNo.setFont(font);
        arrivalTime.setFont(font);
        VBox v = new VBox(10);
        VBox secondColumn = new VBox(10);
        v.setAlignment(Pos.CENTER);
        secondColumn.setAlignment(Pos.CENTER_LEFT);
        v.getChildren().addAll(airline,
                flight, departureTime);
        secondColumn.getChildren().addAll(flight, seatNo);

        Button back = new Button("Back");
        Button cancelTicket = new Button("Cancel Ticket");
        back.setStyle("-fx-background-color: #FFA500;");
        back.setFont(btnFont);
        cancelTicket.setStyle("-fx-background-color: #FFA500;");
        cancelTicket.setFont(btnFont);
        String message = "Cancel Ticket";
        cancelTicket.setOnAction(e -> {
          /*if (Popup.display()) {
                if (Database.cancelFlight())
            }*/
            cancelTicketConfirmation("Do you confirm");
        });
        back.setOnAction(e -> {
            try {
                s.display("Welcome", window, id);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

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