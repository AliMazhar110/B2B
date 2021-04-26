package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class SelectSeats extends Application {
    private static FlightBooking flight = new FlightBooking();
    public static int pass=0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Select Seats");
        GridPane gridPane = new GridPane();
        addUIGridPane(gridPane);
        //Add Back Button
        Button back_button = new Button("Back");
        back_button.setPrefHeight(40);
        back_button.setDefaultButton(true);
        back_button.setPrefWidth(100);
        gridPane.add(back_button, 5, 14, 2, 1);
        GridPane.setHalignment(back_button, HPos.LEFT);
        GridPane.setMargin(back_button, new Insets(20, 0, 20, 0));
        back_button.setOnAction(e -> {
            flight.start(primaryStage);
        });
        Scene scene = new Scene(gridPane, 800, 675);
        primaryStage.setScene(scene);
        primaryStage.show();
        gridPane.requestFocus();
    }

    private void addUIGridPane(GridPane gridPane) throws Exception {
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
        //int limit = FlightBooking.getPassengers();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                Button button = btns_left[count];
                button.setOnAction(e -> {
                    button.setDisable(true);
                });
                if (button.isDisable()) {
                    pass++;
                }
                count++;
            }
        }
        count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = btns_right[count];
                button.setOnAction(e -> {
                    button.setDisable(true);
                });
                if (button.isDisable()) {
                    pass++;
                }
                count++;
            }
        }

}

    public static void main(String[] args) {
        launch(args);
    }
}
