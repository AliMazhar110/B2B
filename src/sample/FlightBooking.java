package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class FlightBooking {//extends Application {
    private static SignedInUser menu = new SignedInUser();
    private static FlightList list = new FlightList();
    private static Stage window;
    private static String[] seats = new String[3];
    private static String pnr;
    //@Override
    //public void start(Stage stage) {
    public void display(Stage stage, String id) {
        window = stage;
        stage.setTitle("Book Flight");
        GridPane gridpane = createBookingPane();
        addUIControls(gridpane, id);
        Font btnFont = Font.font("Century", FontWeight.NORMAL, 16);
        // Add Back Button
        Button back_button = new Button("Back");
        back_button.setPrefHeight(40);
        back_button.setStyle("-fx-background-color: #FFA500;");
        back_button.setFont(btnFont);
        back_button.setPrefWidth(100);
        gridpane.add(back_button ,0 ,3,2,1);
        GridPane.setHalignment(back_button, HPos.LEFT);
        GridPane.setMargin(back_button, new Insets(20,0,20,0));
        back_button.setOnAction(e ->{
            try {
                menu.display("Welcome", stage, "id");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        Scene scene = new Scene(gridpane, 800, 675);
        stage.setScene(scene);
        stage.show();
        gridpane.requestFocus();
    }
    private static GridPane createBookingPane(){
        //Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();
        //Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);
        //set a padding of 40px on each side
        gridPane.setPadding(new Insets(40,40,40,40));
        // Set the Horizontal gap between columns
        gridPane.setHgap(10);
        //Set the vertical gap between rows
        gridPane.setVgap(10);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("media/B2B-Background.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputStream);
        gridPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        return gridPane;
    }
    private static void addUIControls(GridPane gridpane, String id){
        Label headerLabel = new Label("Book Flight");
        headerLabel.setFont(Font.font("Century", FontWeight.BOLD, 28));
        gridpane.add(headerLabel, 2,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        ComboBox from = new ComboBox();
        from.getItems().addAll(
                "Mumbai",
                "Chennai",
                "Kolkata","Delhi"
        );
        ComboBox to = new ComboBox();
        to.getItems().addAll(
                "Mumbai",
                "Chennai",
                "Kolkata","Delhi"
        );
        Font btnFont = Font.font("Century", FontWeight.NORMAL, 16);
        Font font = Font.font("Century", FontWeight.SEMI_BOLD, 16);
        DatePicker date = new DatePicker();

        Label source = new Label("From: ");
        Label destination = new Label("To: ");
        Label dateOfJourney = new Label("Date: ");
        Label noOfPassengers = new Label("No of Passengers: ");

        source.setFont(font);
        destination.setFont(font);
        dateOfJourney.setFont(font);
        noOfPassengers.setFont(font);
        
        gridpane.add(source,0,1);
        gridpane.add(from, 1, 1);
        gridpane.add(destination,4,1);
        gridpane.add(to, 5, 1);
        gridpane.add(dateOfJourney,0, 2);
        gridpane.add(date,1,2);
        gridpane.add(noOfPassengers,4,2);
        TextField passengers = new TextField();
        gridpane.add(passengers,5,2);
        Button button = new Button("Book");
        button.setPrefHeight(40);
        button.setStyle("-fx-background-color: #FFA500;");
        button.setFont(btnFont);
        button.setPrefWidth(100);
        gridpane.add(button ,1 ,3,2,1);
        GridPane.setHalignment(button, HPos.RIGHT);
        GridPane.setMargin(button, new Insets(20,0,20,0));

        String[] f = new String[1];
        String[] t = new String[1];
        String[] d = new String[1];

        button.setOnAction(actionEvent -> {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                String today = dtf.format(now);
                String chose = date.getValue().toString();
                SimpleDateFormat
                        sdfo
                        = new SimpleDateFormat("yyyy-MM-dd");

                // Get the two dates to be compared
                Date d1 = null, d2 = null;
                try {
                    d1 = sdfo.parse(today);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    d2 = sdfo.parse(chose);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (d1.compareTo(d2) > 0) {
                    // When Date d1 > Date d2
                    showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                            "Error!", "Please Choose Different date.");
                    return;
                } else if (d1.compareTo(d2) == 0) {

                    // When Date d1 = Date d2
                    showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                            "Error!", "Cannot Book a Flight on Same Day.");
                    return;
                }
            }
            catch(Exception e){
                System.out.println("Error: "+e);
            }
            try{
                f[0] = from.getValue().toString();
            }
            catch(Exception a){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!", "Please enter Your Source.");
                return;
            }
            try{
                t[0] = to.getValue().toString();
            }
            catch(Exception a){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!" ,"Please enter your Destination");
                return;
            }
            try{
                d[0] = date.getValue().toString();
            }
            catch (Exception a){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!" ,"Please enter Date");
                return;
            }
            if(passengers.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!", "Please enter no of passengers");
                return;
            }
            if(from.getValue().toString().equals(to.getValue().toString())){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!", "Source and Destination cannot be same.");
                return;
            }
            try {
                list.display(window, id, f[0], t[0], d[0]);
            } catch (Exception a) {
                //a.printStackTrace();
                System.out.println(a.getMessage());
            }
        });
    }

    private static void showAlert(Alert.AlertType alertType,
                                  Window owner,String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}
