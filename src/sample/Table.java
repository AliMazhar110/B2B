package sample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Table extends Application {
    static int count = 0;
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        //Label for education
        Label label = new Label("File Data:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);
        //Creating a table view
        FileInputStream inputStream = new FileInputStream("media/register-3.jpg");
        Image image = new Image(inputStream);
        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        TableView<FileData> table = new TableView<>();
        final ObservableList<FileData> data = FXCollections.observableArrayList(
                new FileData("Ali Mazhar Luqmani", "SpiceJet", "12345","H1234",
                        "DEL", "BOM","23:50", "01:60"),
                new FileData("Harsh Vardhan Guleria", "Vistara", "67789","A1234",
                        "DEL", "BOM","12:56","14:50" ),
                new FileData("Harsh Vardhan Guleria", "Vistara", "67789","A1234",
                        "DEL", "BOM","12:56","14:50" )
        );
        //Creating columns
        TableColumn<FileData, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameCol.setPrefWidth(150);

        TableColumn<FileData, String> airplaneCol = new TableColumn<>("Airplane");
        airplaneCol.setCellValueFactory(new PropertyValueFactory<>("Airplane"));
        airplaneCol.setPrefWidth(70);

        TableColumn<FileData, String> flightNumber = new TableColumn<>("Flight Number");
        flightNumber.setCellValueFactory(new PropertyValueFactory<>("FlightNumber"));
        flightNumber.setPrefWidth(80);

        TableColumn<FileData, String> pnr = new TableColumn<>("PNR");
        pnr.setCellValueFactory(new PropertyValueFactory<>("PNR"));
        pnr.setPrefWidth(70);

        TableColumn<FileData, String> departure = new TableColumn<>("Departure");
        departure.setCellValueFactory(new PropertyValueFactory<>("Departure"));
        departure.setPrefWidth(70);

        TableColumn<FileData, String> arrival = new TableColumn<>("Arrival");
        arrival.setCellValueFactory(new PropertyValueFactory<>("Arrival"));
        arrival.setPrefWidth(70);

        TableColumn<FileData, String> source = new TableColumn<>("Source");
        source.setCellValueFactory(new PropertyValueFactory<>("Source"));
        arrival.setPrefWidth(80);

        TableColumn<FileData, String> destination = new TableColumn<>("Destination");
        destination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        arrival.setPrefWidth(80);

        TableColumn<FileData, String> actionCol = new TableColumn<>("");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        actionCol.setPrefWidth(35);
        //Adding data to the table
        ObservableList<String> list = FXCollections.observableArrayList();
        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(actionCol,nameCol,airplaneCol,flightNumber,pnr,source,
                destination, departure, arrival);
        //Setting the size of the table
        table.setMaxSize(750, 600);
        table.setFixedCellSize(35);
        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(
                table.getFixedCellSize()).add(30));
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 50, 50, 60));
        Button delete = new Button("Delete");
        delete.setOnAction(e -> {
            for(int i=0;i<data.size();i++){
                FileData d = table.getItems().get(i);
                if (d.getCheckBox().isSelected()){
                    count++;
                }
            }
            if (count>1 || count ==0){
                count=0;
                showAlert(Alert.AlertType.ERROR, vbox.getScene().getWindow(),
                        "Error!", "Zero or More than one Flight was selected.");
            }
            else {
                count=0;
                try {
                    for (FileData filedata : table.getItems()) {
                        if (filedata.getCheckBox().isSelected()) {
                            data.remove(filedata);
                        }
                    }
                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });
        vbox.getChildren().addAll(label, table,delete);
        //Setting the scene
        grid.getChildren().add(vbox);
        Scene scene = new Scene(grid, 800, 600);
        stage.setTitle("Table View Example");
        stage.setScene(scene);
        stage.show();
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
    public static void main(String[] args){
        launch(args);
    }
}
