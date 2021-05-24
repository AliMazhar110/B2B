package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SelectSeats {//extends Application {
    private static final FlightList list = new FlightList();
    private static final SignedInUser loggedIn = new SignedInUser();
    private int pass;
    private static int[] seats = new int[50];
    //@Override
    //public void start(Stage primaryStage) throws Exception {
    public void display(Stage primaryStage, String id, String source,
                          String destination,String date, String airline,
                        String flightNo, String dep, String arr) throws Exception {
        pass = 0;
        primaryStage.setTitle("Select Seats");
        GridPane gridPane = new GridPane();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("media/B2B-Background.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputStream);
        gridPane.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        addUIGridPane(primaryStage, gridPane, id, source, destination, date, airline, flightNo,
                dep, arr);
        //Add Back Button
        Button back_button = new Button("BACK");
        back_button.setPrefHeight(40);
        back_button.setStyle("-fx-background-color: #FFA500;");
        back_button.setFont(Font.font("Century", FontWeight.NORMAL, 16));
        back_button.setPrefWidth(100);
        back_button.setTranslateX(200);
        back_button.setTranslateY(25);
        gridPane.add(back_button, 0, 15, 2, 1);
        GridPane.setHalignment(back_button, HPos.CENTER);
        GridPane.setMargin(back_button, new Insets(20, 0, 20, 0));
        back_button.setOnAction(e -> {
            list.display(primaryStage, id, source, destination, date);
        });
        Scene scene = new Scene(gridPane, 800, 675);
        primaryStage.setScene(scene);
        primaryStage.show();
        gridPane.requestFocus();
    }

    private void addUIGridPane(Stage stage, GridPane gridPane, String id, String source,
                               String destination, String date, String airline,
                               String flightNo, String dep, String arr) throws Exception {
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        FileInputStream inputStream = new FileInputStream("media/seat.png");
        Image img = new Image(inputStream);
        Button[] btns_left = new Button[20];
        Button[] btns_right = new Button[30];
        for (int i = 0; i < btns_left.length; i++) {
            btns_left[i] = new Button();
        }
        for (int i = 0; i < btns_right.length; i++) {
            btns_right[i] = new Button();
        }
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                ImageView view = new ImageView(img);
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                btns_left[count].setTranslateX(200);
                btns_left[count].setTranslateY(25);
                btns_left[count].setPrefSize(30, 30);
                btns_left[count].setGraphic(view);
                gridPane.add(btns_left[count], j, i);
                count++;
            }
        }
        count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 9; j < 12; j++) {
                ImageView view = new ImageView(img);
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                btns_right[count].setTranslateX(200);
                btns_right[count].setTranslateY(25);
                btns_right[count].setPrefSize(30, 30);
                btns_right[count].setGraphic(view);
                gridPane.add(btns_right[count], j, i);
                count++;
            }
        }
        count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                Button button = btns_left[count];
                int finalCount = count;
                button.setOnAction(e -> {
                    button.setDisable(true);
                    seats[pass++] = finalCount +1;
                });
                count++;
            }
        }
        count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = btns_right[count];
                int finalCount = count;
                button.setOnAction(e -> {
                    button.setDisable(true);
                    seats[pass++] = finalCount +21;
                });
                count++;
            }
        }

        Button book = new Button("BOOK");
        book.setStyle("-fx-background-color: #FFA500;");
        book.setFont(Font.font("Century", FontWeight.NORMAL, 16));
        book.setPrefWidth(100);
        book.setTranslateX(200);
        book.setTranslateY(25);
        gridPane.add(book, 9, 15,2,1);
        GridPane.setHalignment(book, HPos.LEFT);
        GridPane.setMargin(book, new Insets(20, 0, 20, 0));
        book.setOnAction(e -> {
            showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(),
                    "Seats Booked","last seat booked ="+ getMessage());
            String pnr = generateRandom() +"";
            if (Database.bookFlight(source, destination, date, airline, flightNo,
                    pnr, id, getMessage(), dep, arr, "true")) {
                try {
                    Notification.sendNotification(id);
                    loggedIn.display("Welcome", stage, id);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
    }
    private String getMessage(){
        String str = "";
        for(int i=0;i<pass;i++){
            str += seats[i] +", ";
        }
        return str;
    }
    private static void showAlert(Alert.AlertType alertType,
                                  Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    private static int generateRandom(){
        return (int)(Math.random()*900000)+100000;
    }

    /*public static void main(String[] args) {
        launch(args);
    }*/
}
