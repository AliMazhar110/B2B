package sample;

import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EditProfile {

    private static SignedInUser s = new SignedInUser();

    public static void display(Stage window) throws FileNotFoundException {

        window.setTitle("Edit Profile");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));
        FileInputStream inputStream = new FileInputStream("media/B2B-Background.png");
        Image image = new Image(inputStream);
        grid.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        Font btnFont = Font.font("Century", FontWeight.NORMAL, 16);
        Font font = Font.font("Century", FontWeight.SEMI_BOLD, 16);

        Scene scene = new Scene(grid, 800, 675);
        window.setScene(scene);

        Text scenetitle = new Text("Enter the new details");
        scenetitle.setFont(Font.font("Century", FontWeight.NORMAL, 28));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label fullName = new Label("Full Name: "); // Full Name
        fullName.setFont(font);
        grid.add(fullName, 0, 1);
        TextField fullNameField = new TextField();
        grid.add(fullNameField, 1, 1);

        Label userName = new Label("User Name:"); // User Name
        userName.setFont(font);
        grid.add(userName, 0, 2);
        TextField userNameField = new TextField();
        grid.add(userNameField, 1, 2);

        Label emailID = new Label("Email ID: "); // Email ID
        emailID.setFont(font);
        grid.add(emailID, 0, 3);
        TextField emailIDField = new TextField();
        grid.add(emailIDField, 1, 3);

        Label mobileNo = new Label("Mobile No: "); // Mobile No
        mobileNo.setFont(font);
        grid.add(mobileNo, 0, 4);
        TextField mobileNoField = new TextField();
        grid.add(mobileNoField, 1, 4);

        Label oldPassword = new Label("Old Password:"); // Old Password
        oldPassword.setFont(font);
        grid.add(oldPassword, 0, 5);
        PasswordField oldPasswordField = new PasswordField();
        grid.add(oldPasswordField, 1, 5);

        Label newPassword = new Label("New Password:"); // New Password
        newPassword.setFont(font);
        grid.add(newPassword, 0, 6);
        PasswordField newPasswordField = new PasswordField();
        grid.add(newPasswordField, 1, 6);

        Label confirmPassword = new Label("Confirm New Password:"); // Confirm Password
        confirmPassword.setFont(font);
        grid.add(confirmPassword, 0, 7);
        PasswordField confirmPasswordField = new PasswordField();
        grid.add(confirmPasswordField, 1, 7);

        Button confirm = new Button("Confirm and Submit");
        Button back = new Button("Back");
        VBox align = new VBox(10);
        align.setAlignment(Pos.CENTER);

        confirm.setStyle("-fx-background-color: #FFA500;");
        confirm.setFont(btnFont);
        back.setStyle("-fx-background-color: #FFA500;");
        back.setFont(btnFont);

        align.getChildren().addAll(confirm, back);
        grid.add(align, 1, 8);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        confirm.setOnAction(e -> {
            if (userNameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your username");
                return;
            } if (fullNameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your full name");
                return;
            } if (emailIDField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your email ID");
                return;
            } if (mobileNoField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your Mobile number");
                return;
            } if (oldPasswordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your old password");
                return;
            } if (newPasswordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your new password");
                return;
            } if (confirmPasswordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please confirm your new password");
                return;
            } else {
                showAlert(Alert.AlertType.CONFIRMATION,
                        grid.getScene().getWindow(), "Confirmation!",
                        "Do you confirm the changes");
                s.start(window);
            }
        });

        back.setOnAction(e -> {
            s.start(window);
        });
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
}

