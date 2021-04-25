package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    private static Stage window;
    private static SignedInUser s = new SignedInUser();
    private static RegistrationForm r = new RegistrationForm();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        welcomePage(primaryStage);
    }

    public static void welcomePage(Stage primaryStage) {
        window = primaryStage;
        primaryStage.setTitle("B2B Sign-In/Sign-Up//");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));

        Scene scene = new Scene(grid, 800, 675);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Century", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:"); // UserName
        grid.add(userName, 0, 1);

        TextField userNameField = new TextField(); // Password
        grid.add(userNameField, 1, 1);

        Label password = new Label("Password:");
        grid.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button signIn = new Button("Sign in"); // Sign in button
        Button signUp = new Button("Sign up"); // Sign up button
        Button close = new Button("Exit"); // exit the app button
        Label label = new Label();
        HBox hbBtn = new HBox(10);
        VBox vbBtn = new VBox(10);
        vbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        vbBtn.getChildren().addAll(label, signUp, close);
        hbBtn.getChildren().add(signIn);
        grid.add(vbBtn, 0, 4);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        signIn.setOnAction(e -> { // Sign in window with error handling
            if (userNameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your username");
                return;
            } if (passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Please enter your password");
                return;
            } else {
                s.start(window);
            }
        });

        signUp.setOnAction(e ->{
            r.start(primaryStage);
        }); // Sign up window
        window.setOnCloseRequest(e -> {
            e.consume();
            window.close();
        });
        close.setOnAction(e -> window.close());

        primaryStage.show();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
