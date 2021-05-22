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
import java.util.Optional;

public class EditProfile {

    private static SignedInUser s = new SignedInUser();

    public static void display(Stage window, String id) throws FileNotFoundException {

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

        Label mobileNo = new Label("Mobile No: "); // Mobile No
        mobileNo.setFont(font);
        grid.add(mobileNo, 0, 2);
        TextField mobileNoField = new TextField();
        grid.add(mobileNoField, 1, 2);

        Label oldPassword = new Label("Old Password:"); // Old Password
        oldPassword.setFont(font);
        grid.add(oldPassword, 0, 3);
        PasswordField oldPasswordField = new PasswordField();
        grid.add(oldPasswordField, 1, 3);

        Label newPassword = new Label("New Password:"); // New Password
        newPassword.setFont(font);
        grid.add(newPassword, 0, 4);
        PasswordField newPasswordField = new PasswordField();
        grid.add(newPasswordField, 1, 4);

        Label confirmPassword = new Label("Confirm New Password:"); // Confirm Password
        confirmPassword.setFont(font);
        grid.add(confirmPassword, 0, 5);
        PasswordField confirmPasswordField = new PasswordField();
        grid.add(confirmPasswordField, 1, 5);

        Button confirm = new Button("Confirm and Submit");
        Button back = new Button("Back");
        VBox align = new VBox(20);
        align.setAlignment(Pos.CENTER);

        confirm.setStyle("-fx-background-color: #FFA500;");
        confirm.setFont(btnFont);
        back.setStyle("-fx-background-color: #FFA500;");
        back.setFont(btnFont);

        align.getChildren().addAll(confirm, back);
        grid.add(align, 1, 7);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        confirm.setOnAction(e -> {
            if (fullNameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your full name");
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
            } if (!confirmPasswordField.getText().equals(newPasswordField.getText())) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Passwords do not match");
                return;
            } else {
                if (showAlertPart2(Alert.AlertType.CONFIRMATION, grid.getScene().getWindow())){
                    grid.requestFocus();
                    try {
                        if (Database.editProfile(id, fullNameField.getText(),
                                newPasswordField.getText(), mobileNoField.getText())) {
                            s.display("Welcome", window, id);
                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });

        back.setOnAction(e -> {
            try {
                    s.display("Welcome", window, id);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
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
    private static boolean showAlertPart2(Alert.AlertType alertType,
                                       Window owner){
        Alert alert = new Alert(alertType);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}

