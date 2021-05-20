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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CheckOTP {
    private static int otp;
    public static void display(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Verifying E-Mail");
        GridPane gridPane = createCheckOTPPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane,300,200);
        primaryStage.setScene(scene);
        primaryStage.show();
        gridPane.toFront();
    }
    private static GridPane createCheckOTPPane(){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40,40,40,40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }
    private static void addUIControls(GridPane gridPane){
        Label headerLabel = new Label("Enter OTP - ");
        headerLabel.setFont(Font.font("Garamond", FontWeight.SEMI_BOLD, Double.parseDouble("20")));
        gridPane.add(headerLabel,0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        Label message = new Label("Check your E-Mail.");
        message.setFont(Font.font("Garamond", FontWeight.NORMAL, Double.parseDouble("16")));
        gridPane.add(message,0,1,2,1);
        GridPane.setHalignment(message, HPos.CENTER);

        //Adding OTP label
        Label otpLabel = new Label("OTP - ");
        gridPane.add(otpLabel,0,2);

        //Adding OTP Text Field
        TextField otpField = new TextField();
        gridPane.add(otpField,1,2);
        otp = generateOTP();
        sendMail();
        //Add Submit Button
        Button submit = new Button("Submit");
        submit.setPrefHeight(40);
        submit.setDefaultButton(true);
        submit.setPrefWidth(200);
        gridPane.add(submit,2,2);
        submit.setOnAction(e->{
            if(otpField.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                        "Error!", "Please enter your name");
                return;
            }
            else{
                if (otp == Integer.parseInt(otpField.getText())) {
                    showAlert(Alert.AlertType.CONFIRMATION,gridPane.getScene().getWindow(),"Success","Otp Verified");
                }
                else{
                    showAlert(Alert.AlertType.ERROR,gridPane.getScene().getWindow(),"Failed","Please Try Again");
                }
            }
        });

        //Add Resend Button
        Button resend = new Button("Resend");
        resend.setPrefHeight(40);
        resend.setDefaultButton(true);
        resend.setPrefWidth(200);
        gridPane.add(resend,0,4);
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
            e.printStackTrace();
        }
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
    private static int generateOTP(){
        return (int)(Math.random()*9000)+1000;
    }
}
