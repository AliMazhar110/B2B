package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FlightList {//extends Application{
    private static final FlightBooking flights = new FlightBooking();
    private static final SelectSeats seats = new SelectSeats();
    private static String airline;
    private static String flightNo;
    private static String departure;
    private static String arrival;

    public void display(Stage stage, String id, String source,
                        String destination, String date) {
        stage.setTitle("Select Flight");
        GridPane gridPane = createFlightPane();

        Label headerLabel = new Label("\tFlights from "+ source +" to "+ destination);
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD,20));
        gridPane.add(headerLabel,0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        ArrayList<Flights> f = Database.showFlights(source, destination);
        Rectangle rectangle1 = new Rectangle(700,100, Color.TAN);
        rectangle1.setArcHeight(30);
        rectangle1.setArcWidth(30);
        CheckBox btn1 = new CheckBox();
        Text to1 = new Text("\t"+ f.get(0).getAirline() +"\t\t"+ f.get(0).getDepartureTime() +
                "\t\t"+ f.get(0).getDuration() +"\t\t"+ f.get(0).getArrivalTime() +"\t\tPrice-"+
                f.get(0).getPrice());
        to1.setFont(new Font("Verdana Bold",16));
        GridPane gridPane1 = new GridPane();
        gridPane1.setHgap(10);
        // set Vertical gap
        gridPane1.setVgap(10);
        gridPane1.add(btn1,0,1);
        gridPane1.add(to1,0,1);
        gridPane1.add(rectangle1,0,1);
        to1.toFront();
        btn1.toFront();
        Rectangle rectangle2 = new Rectangle(700,100, Color.TAN);
        CheckBox btn2 = new CheckBox();
        rectangle2.setArcHeight(30);
        rectangle2.setArcWidth(30);
        Text to2 = new Text("\t"+ f.get(1).getAirline() +"\t\t"+ f.get(1).getDepartureTime() +
                "\t\t"+ f.get(1).getDuration() +"\t\t"+ f.get(1).getArrivalTime() +"\t\tPrice-"+
                f.get(1).getPrice());
        to2.setFont(new Font("Verdana Bold",16));
        gridPane1.add(rectangle2,0,2,2,1);
        gridPane1.add(btn2,0,2,2,1);
        gridPane1.add(to2,0,2,2,1);
        to2.toFront();
        btn2.toFront();
        Rectangle rectangle3 = new Rectangle(700,100, Color.TAN);
        rectangle3.setArcHeight(30);
        rectangle3.setArcWidth(30);
        CheckBox btn3 = new CheckBox();
        Text to3 = new Text("\t"+ f.get(2).getAirline() +"\t\t"+ f.get(2).getDepartureTime() +
                "\t\t"+ f.get(2).getDuration() +"\t\t"+ f.get(2).getArrivalTime() +"\t\tPrice-"+
                f.get(2).getPrice());
        to3.setFont(new Font("Verdana Bold",16));
        gridPane1.add(rectangle3,0,3,2,1);
        gridPane1.add(btn3,0,3,2,1);
        gridPane1.add(to3,0,3,2,1);
        to3.toFront();
        btn3.toFront();
        gridPane.add(gridPane1,0,1,2,1);

        Scene scene = new Scene(gridPane, 800, 675);
        Button back_button = new Button("Back");
        back_button.setPrefHeight(40);
        back_button.setStyle("-fx-background-color: #FFA500;");
        back_button.setFont(Font.font("Century", FontWeight.NORMAL, 16));
        back_button.setPrefWidth(100);
        gridPane.add(back_button, 0, 5, 2, 1);
        Button book = new Button("BOOK");
        book.setPrefHeight(40);
        book.setStyle("-fx-background-color: #FFA500;");
        book.setFont(Font.font("Century", FontWeight.NORMAL, 16));
        book.setPrefWidth(100);
        gridPane.add(book, 3, 5, 2, 1);
        GridPane.setHalignment(back_button, HPos.LEFT);
        GridPane.setMargin(back_button, new Insets(20, 0, 20, 0));
        GridPane.setHalignment(book, HPos.LEFT);
        GridPane.setMargin(book, new Insets(20, 0, 20, 0));
        back_button.setOnAction(e -> {
            flights.display(stage, id);
        });
        book.setOnAction(e -> {
            try {
                if (btn1.isSelected()) {
                    System.out.println("In button 1");
                    airline = f.get(0).getAirline();
                    System.out.println(airline);
                    flightNo = f.get(0).getFlightNo();
                    departure = f.get(0).getDepartureTime();
                    arrival = f.get(0).getArrivalTime();
                }
                if (btn2.isSelected()) {
                    System.out.println("In button 1");
                    airline = f.get(1).getAirline();
                    flightNo = f.get(1).getFlightNo();
                    departure = f.get(1).getDepartureTime();
                    arrival = f.get(1).getArrivalTime();
                }
                if (btn3.isSelected()) {
                    System.out.println("In button 1");
                    airline = f.get(2).getAirline();
                    flightNo = f.get(2).getFlightNo();
                    departure = f.get(2).getDepartureTime();
                    arrival = f.get(2).getArrivalTime();
                }

                seats.display(stage, id, source, destination, date, airline, flightNo,
                        departure, arrival);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        stage.setScene(scene);
        stage.show();
        gridPane.requestFocus();
    }

    private static GridPane createFlightPane() {
        // Instantiate new GridPane
        GridPane gridPane = new GridPane();
        // Position the Pane at the center of the screen.
        gridPane.setAlignment(Pos.TOP_CENTER);
        // set Padding
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        // set Horizontal gap
        gridPane.setHgap(10);
        // set Vertical gap
        gridPane.setVgap(10);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("media/B2B-Background.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputStream);
        gridPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        return gridPane;
    }

//        ListView listView = new ListView();
//        listView.setPrefSize(500, 500);
//        listView.setEditable(true);
//        names.clear();
//        names.addAll("From: Delhi - To: Mumbai   Duration: 3hrs",
//                "",
//                "From: Delhi - To: Chennai   Duration: 6hrs",
//                "",
//                "From: Delhi - To: Kolkata   Duration: 3hrs");
//        listView.setItems(names);
//        listView.setCellFactory(param -> new RadioListCell());
//        gridPane.getChildren().add(listView);
//        listView.setFixedCellSize(100);

//    private static class RadioListCell extends ListCell<String> {
//        @Override
//        public void updateItem(String obj, boolean empty) {
//            super.updateItem(obj, empty);
//            if (empty) {
//                setText(null);
//                setGraphic(null);
//            } else {
//                RadioButton radioButton = new RadioButton(obj);
//                radioButton.setToggleGroup(group);
//                // Add Listeners if any
//                setGraphic(radioButton);
//            }
//        }
//    }

}
