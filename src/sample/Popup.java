package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Popup {

    private static boolean result;
    public static boolean display(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Verifying E-Mail");
        GridPane gridPane = null;
        try {
            gridPane = createCheckOTPPane();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        result = addUIControls(gridPane, primaryStage);
        Scene scene = new Scene(gridPane,500,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();
        return result;
    }

    private static GridPane createCheckOTPPane() throws FileNotFoundException {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40,40,40,40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        FileInputStream inputStream = new FileInputStream("media/enter-otp.png");
        Image image = new Image(inputStream);
        gridPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        return gridPane;
    }

    private static boolean addUIControls(GridPane gridPane, Stage stage){

        Label headerLabel = new Label("Are you sure");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(headerLabel,1,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);

        Button yes = new Button("Yes");
        yes.setPrefHeight(40);
        yes.setStyle("-fx-background-color: #FFA500;");
        yes.setPrefWidth(100);
        gridPane.add(yes,0,3);
        yes.setOnAction(e->{
            result = true;
        });

        //Add Resend Button
        Button no = new Button("No");
        no.setPrefHeight(40);
        no.setStyle("-fx-background-color: #FFA500;");
        no.setPrefWidth(100);
        no.setOnAction(e -> {
           result = false;
        });
        gridPane.add(no,3,3);
        GridPane.setHalignment(no, HPos.CENTER);
        GridPane.setMargin(no, new Insets(20,0,20,0));
        return result;
    }
}