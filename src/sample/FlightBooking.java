package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FlightBooking extends Application {
    private static SignedInUser menu = new SignedInUser();
    private static SelectSeats seats = new SelectSeats();
    private static Stage window;
    @Override
    public void start(Stage stage) {
        window = stage;
        stage.setTitle("Book Flight");
        GridPane gridpane = createBookingPane();
        addUIControls(gridpane);
        // Add Back Button
        Button back_button = new Button("Back");
        back_button.setPrefHeight(40);
        back_button.setDefaultButton(true);
        back_button.setPrefWidth(100);
        gridpane.add(back_button ,0 ,3,2,1);
        GridPane.setHalignment(back_button, HPos.LEFT);
        GridPane.setMargin(back_button, new Insets(20,0,20,0));
        back_button.setOnAction(e ->{
            menu.start(stage);
        });
        Scene scene = new Scene(gridpane, 800, 675);
        stage.setScene(scene);
        stage.show();
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

        return gridPane;
    }
    private static void addUIControls(GridPane gridpane){
        Label headerLabel = new Label("Book Flight");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
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
        DatePicker date = new DatePicker();
        gridpane.add(new Label("From: "),0,1);
        gridpane.add(from, 1, 1);
        gridpane.add(new Label("TO: "),4,1);
        gridpane.add(to, 5, 1);
        gridpane.add(new Label("Date: "),0, 2);
        gridpane.add(date,1,2);
        gridpane.add(new Label("No. Of Passengers = "),4,2);
        TextField passengers = new TextField();
        gridpane.add(passengers,5,2);
        Button button = new Button("Book");
        button.setPrefHeight(40);
        button.setDefaultButton(true);
        button.setPrefWidth(100);
        gridpane.add(button ,1 ,3,2,1);
        GridPane.setHalignment(button, HPos.RIGHT);
        GridPane.setMargin(button, new Insets(20,0,20,0));
        button.setOnAction(actionEvent -> {
            try{
                String f = from.getValue().toString();
            }
            catch(Exception e){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!", "Please enter Your Source.");
                return;
            }
            try{
                String t = to.getValue().toString();
            }
            catch(Exception e){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!" ,"Please enter your Destination");
                return;
            }
            try{
                String d = date.getValue().toString();
            }
            catch (Exception e){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!" ,"Please enter Date");
                return;
            }
            if(passengers.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, gridpane.getScene().getWindow(),
                        "Error!", "Please enter no of passengers");
                return;
            }
            try {
                seats.start(window);
            } catch (Exception e) {
                e.printStackTrace();
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
