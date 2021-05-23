package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FlightStatus {

    private static SignedInUser s = new SignedInUser();

    public static void display(Stage window, String id) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(40, 40, 40, 40));
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("media/B2B-Background.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputStream);
        grid.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        grid.setAlignment(Pos.TOP_CENTER);
        window.setTitle("Flight status");
        ArrayList<BookedFlights> f = Database.showBookedFlight(id);
        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);
        GridPane[] gridArray = new GridPane[5];
        for(int i=0;i<5;i++){
            gridArray[i] = new GridPane();
            gridArray[i].setAlignment(Pos.TOP_CENTER);
        }
        int count = 0;
        Label header = new Label("Your Status");
        header.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.ITALIC,20));
        header.setTextFill(Color.ORANGE);
        GridPane.setHalignment(header,HPos.CENTER);
        GridPane.setMargin(header,new Insets(20,0,20,0));
        grid.add(header,0,0,2,1);
        for(BookedFlights booked: f){
            gridArray[count].setBackground(new Background(new BackgroundFill(Color.ORANGE,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            gridArray[count].setPrefHeight(100);
            gridArray[count].setPrefWidth(650);

            Label flightName = new Label(" AirLine - "+booked.getAirline());
            flightName.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(flightName,1,0);

            Label flightNumber = new Label(" |  Flight Number - "+booked.getFlightNo());
            flightNumber.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(flightNumber,2,0);

            Label pnr = new Label(" |  PNR - "+booked.getPNR());
            pnr.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(pnr,3,0);

            Label from = new Label(" From - "+booked.getSource());
            from.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(from,1,1);

            Label to = new Label(" |        To - "+booked.getDestination());
            to.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(to,2,1);

            Label seats = new Label(" |  Seats - "+booked.getSeats());
            seats.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(seats,3,1);

            Label departure = new Label(" Departure - "+booked.getDepartureTime());
            departure.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(departure,1,2);

            Label arrival = new Label(" |        Arrival - "+booked.getArrivalTime());
            arrival.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(arrival,2,2);

            Label date = new Label(" |  Date - "+booked.getDate());
            date.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(date,3,2);

            String str = "";
            if(booked.getStatus().equals("true")){
                str+="On Time";
            }
            else{
                str+="Delayed";
            }
            Label status = new Label(" |   Status = "+str);
            status.setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.ITALIC, 16));
            gridArray[count].add(status,2,3);

            grid.add(gridArray[count],0,count+1,2,1);
            count++;
        }
        Button exitWindow = new Button("Back");
        exitWindow.setOnAction(e -> {
            try {
                s.display("Welcome", window, id);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        exitWindow.setStyle("-fx-background-color: #FFA500;");
        exitWindow.setFont(Font.font("Century", FontWeight.NORMAL, 16));

        grid.add(exitWindow, 0, 4);

        window.show();
    }
}