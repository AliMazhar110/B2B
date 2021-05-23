package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    private int c;

    public void display(Stage stage, String id, String source,
                        String destination, String date) {
        stage.setTitle("Select Flight");
        GridPane gridPane = createFlightPane();

        Label headerLabel = new Label("\tFlights from "+ source +" to "+ destination);
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD,20));
        headerLabel.setTextFill(Color.ORANGE);
        gridPane.add(headerLabel,1,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        ArrayList<Flights> f = Database.showFlights(source, destination);
        GridPane[] gridArray = new GridPane[3];
        CheckBox[] btn = new CheckBox[3];
        for(int i=0;i<3;i++){
            gridArray[i] = new GridPane();
            gridArray[i].setAlignment(Pos.TOP_CENTER);
            btn[i] = new CheckBox();
        }
        int count = 0;
        for(Flights flg: f){
            gridArray[count].setBackground(new Background(new BackgroundFill(Color.ORANGE,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            gridArray[count].setPrefHeight(80);
            gridArray[count].setPrefWidth(500);

            Label flightName = new Label(" AirLine - "+flg.getAirline());
            flightName.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(flightName,2,0);

            Label flightNumber = new Label(" |  Flight Number - "+flg.getFlightNo());
            flightNumber.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(flightNumber,3,0);

            gridArray[count].add(btn[count],0,1);

            Label from = new Label(" From - "+flg.getSource());
            from.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(from,2,1);

            Label to = new Label(" |  To - "+flg.getDestination());
            to.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(to,3,1);

            Label departure = new Label(" Departure - "+flg.getDepartureTime());
            departure.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(departure,2,2);

            Label arrival = new Label(" |  Arrival - "+flg.getArrivalTime());
            arrival.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(arrival,3,2);

            Label price = new Label(" \t\tPrice - Rs. "+flg.getPrice());
            price.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            price.setTextFill(Color.BLACK);
            gridArray[count].add(price,2,3);
            gridPane.add(gridArray[count],1,count+1,2,1);
            count++;
        }

        Scene scene = new Scene(gridPane, 800, 675);
        Button back_button = new Button("Back");
        back_button.setPrefHeight(40);
        back_button.setStyle("-fx-background-color: #FFA500;");
        back_button.setFont(Font.font("Century", FontWeight.NORMAL, 16));
        back_button.setPrefWidth(100);
        gridPane.add(back_button, 0, 5);
        Button book = new Button("BOOK");
        book.setPrefHeight(40);
        book.setStyle("-fx-background-color: #FFA500;");
        book.setFont(Font.font("Century", FontWeight.NORMAL, 16));
        book.setPrefWidth(100);
        gridPane.add(book, 7, 5);
        GridPane.setHalignment(back_button, HPos.LEFT);
        //GridPane.setMargin(back_button, new Insets(20, 0, 20, 0));
        GridPane.setHalignment(book, HPos.LEFT);
        //GridPane.setMargin(book, new Insets(20, 0, 20, 0));
        back_button.setOnAction(e -> {
            flights.display(stage, id);
        });
        book.setOnAction(e -> {
            c = 0;
            for(int i=0;i<f.size();i++){
                if(btn[i].isSelected()){
                    c++;
                }
            }
            if(c>1 || c==0){
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Error!", "Zero or More than one Flight was selected.");
                return;
            }
            try {
                if (btn[0].isSelected()) {
                    System.out.println("In button 1");
                    airline = f.get(0).getAirline();
                    System.out.println(airline);
                    flightNo = f.get(0).getFlightNo();
                    departure = f.get(0).getDepartureTime();
                    arrival = f.get(0).getArrivalTime();
                }
                if (btn[1].isSelected()) {
                    System.out.println("In button 1");
                    airline = f.get(1).getAirline();
                    flightNo = f.get(1).getFlightNo();
                    departure = f.get(1).getDepartureTime();
                    arrival = f.get(1).getArrivalTime();
                }
                if (btn[2].isSelected()) {
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
        gridPane.setHgap(0);
        // set Vertical gap
        gridPane.setVgap(20);
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

    private static void showAlert(Alert.AlertType alertType,
                                  Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
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
