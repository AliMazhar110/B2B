package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SelectSeats extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Select Seats");
        GridPane gridPane = new GridPane();
        addUIGridPane(gridPane);
        Scene scene = new Scene(gridPane,800,675);
        primaryStage.setScene(scene);
        primaryStage.show();
        gridPane.requestFocus();
    }
    private void addUIGridPane(GridPane gridPane) throws FileNotFoundException {
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        FileInputStream inputStream = new FileInputStream("media/seat.png");
        Image img = new Image(inputStream);
        Button[] btns_left = new Button[20];
        Button[] btns_right = new Button[30];
        for (int i=0;i< btns_left.length;i++){
            btns_left[i] = new Button();
        }
        for (int i=0;i< btns_right.length;i++){
            btns_right[i] = new Button();
        }
        int count =0;
        for(int i=0;i<10;i++){
            for(int j=0;j<2;j++){
                ImageView view = new ImageView(img);
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                btns_left[count].setTranslateX(200);
                btns_left[count].setTranslateY(25);
                btns_left[count].setPrefSize(30,30);
                btns_left[count].setGraphic(view);
                gridPane.add(btns_left[count],j,i);
                count++;
            }
        }
        count =0;
        for(int i=0;i<10;i++){
            for(int j=9;j<12;j++){
                ImageView view = new ImageView(img);
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                btns_right[count].setTranslateX(200);
                btns_right[count].setTranslateY(25);
                btns_right[count].setPrefSize(30,30);
                btns_right[count].setGraphic(view);
                gridPane.add(btns_right[count],j,i);
                count++;
            }
        }

    }
    public static void main(String[] args){
        launch(args);
    }
}
