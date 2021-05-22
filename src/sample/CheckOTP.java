package sample;
import com.email.durgesh.Email;
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
import javafx.stage.Window;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CheckOTP {
    private static int otp;
    private static final Main main = new Main();
    public static void display(Stage window){
        Stage primaryStage = window;
        primaryStage.setTitle("Verifying E-Mail");
        GridPane gridPane = null;
        try {
            gridPane = createCheckOTPPane();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        addUIControls(gridPane,primaryStage);
        Scene scene = new Scene(gridPane,500,400);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();
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
    private static void addUIControls(GridPane gridPane,Stage window){
        Label headerLabel = new Label("Enter OTP - ");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(headerLabel,1,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        Label message = new Label("Check your E-Mail.");
        message.setFont(Font.font("Arial", FontWeight.BOLD,16));
        gridPane.add(message,1,1,2,1);
        GridPane.setHalignment(message, HPos.CENTER);

        //Adding OTP label
        Label otpLabel = new Label("OTP - ");
        otpLabel.setFont(Font.font("Arial", FontWeight.BOLD,14));
        gridPane.add(otpLabel,0,3);

        //Adding OTP Text Field
        TextField otpField = new TextField();
        gridPane.add(otpField,1,3);
        otp = generateOTP();
        sendMail();
        //Add Submit Button
        Button submit = new Button("Submit");
        submit.setPrefHeight(40);
        submit.setStyle("-fx-background-color: #FFA500;");
        submit.setPrefWidth(100);
        gridPane.add(submit,2,3);
        submit.setOnAction(e->{
            if(otpField.getText().isEmpty()){
                showAlertPart2(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Error!", "Please enter your name");
            }
            else{
                if (otp == Integer.parseInt(otpField.getText())) {
                    showAlert(Alert.AlertType.INFORMATION,gridPane.getScene().getWindow(),
                            "Success","OTP verified");
                    main.start(window);
                }
                else{
                    showAlert(Alert.AlertType.INFORMATION,gridPane.getScene().getWindow(),
                            "Failed","Please Try Again");
                }
            }
        });

        //Add Resend Button
        Button resend = new Button("Resend");
        resend.setPrefHeight(40);
        resend.setStyle("-fx-background-color: #FFA500;");
        resend.setPrefWidth(100);
        gridPane.add(resend,1,5);
        GridPane.setHalignment(resend, HPos.CENTER);
        GridPane.setMargin(resend, new Insets(20,0,20,0));
        resend.setOnAction(e->{
            sendMail();
        });
    }
    private static void sendMail(){
        try{
            generateOTP();
            Email email = new Email("luqmani.mazhar.ali@gmail.com","FATIMA@92");
            //From
            email.setFrom("luqmani.mazhar.ali@gmail.com","B2B");
            email.setSubject("Your OTP");
            email.setContent("<h1>Here is your OTP(Don't share it with anyone) = <h1>"+otp,"text/html");
            email.addRecipient(RegistrationForm.email);
            email.send();
        }
        catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    private static void showAlertPart2(Alert.AlertType alertType, Window owner, String type, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(type+", "+message);
        alert.showAndWait();
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String type, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(type+", "+message);

        alert.showAndWait();
    }
    private static int generateOTP(){
        return (int)(Math.random()*9000)+1000;
    }
}
