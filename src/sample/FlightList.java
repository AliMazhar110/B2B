package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FlightList extends Application{
    private static FlightBooking flight_list = new FlightBooking();
    public static final ObservableList names = FXCollections.observableArrayList();
    private static ToggleGroup group =  new ToggleGroup();

    @Override
    public void start(Stage stage){
        stage.setTitle("Select Flight");
        GridPane gridPane = createFlightPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 675);
        stage.setScene(scene);
        stage.show();
    }
    private static GridPane createFlightPane(){
        // Instantiate new GridPane
        GridPane gridPane = new GridPane();
        // Position the Pane at the center of the screen.
        gridPane.setAlignment(Pos.TOP_CENTER);
        // set Padding
        gridPane.setPadding(new Insets(40, 40 , 40 , 40));
        // set Horizontal gap
        gridPane.setHgap(10);
        // set Vertical gap
        gridPane.setVgap(10);

        return gridPane;
    }
    private static void addUIControls(GridPane gridPane){
        Label headerLabel = new Label("Select Flight");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD,20));
        gridPane.add(headerLabel,0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        ListView listView = new ListView();
        listView.setPrefSize(500, 500);
        listView.setEditable(true);
        names.addAll("From: Delhi - To: Mumbai   Duration: 3hrs",
                "From: Delhi - To: Chennai   Duration: 6hrs",
                "From: Delhi - To: Kolkata   Duration: 3hrs",
                "From: Mumbai - To: Chennai   Duration: 5hrs",
                "From: Kolkata - To: Delhi   Duration: 3hrs");
        listView.setItems(names);
        listView.setCellFactory(param -> new RadioListCell());
        gridPane.getChildren().add(listView);
        listView.setFixedCellSize(70);
    }
    private static class RadioListCell extends ListCell<String> {
        @Override
        public void updateItem(String obj, boolean empty) {
            super.updateItem(obj, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                RadioButton radioButton = new RadioButton(obj);
                radioButton.setToggleGroup(group);
                // Add Listeners if any
                setGraphic(radioButton);
            }
        }
    }
}
