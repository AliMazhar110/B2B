package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    private static Stage window;
    private static SignedInUser s = new SignedInUser();
    private static RegistrationForm r = new RegistrationForm();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            welcomePage(primaryStage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void welcomePage(Stage primaryStage) throws FileNotFoundException {
        window = primaryStage;
        primaryStage.setTitle("B2B Sign-In/Sign-Up//");
        FileInputStream inputStream = new FileInputStream("media/B2B-FINAL.png");
        Image image = new Image(inputStream);
        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        grid.setAlignment(Pos.CENTER_RIGHT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40, 40, 40, 40));

        ColumnConstraints columnOneConstraints = new ColumnConstraints(
                200, 200, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().addAll(columnOneConstraints);
        Scene scene = new Scene(grid, 800, 675);
        primaryStage.setScene(scene);

        Font font = Font.font("Century", FontWeight.SEMI_BOLD, 16);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Century", FontWeight.EXTRA_BOLD, 28));
        grid.add(scenetitle, 9, 0);

        Label userName = new Label("User Name:"); // UserName
        grid.add(userName, 9, 1);
        userName.setFont(font);

        TextField userNameField = new TextField(); // Password
        grid.add(userNameField, 10, 1);

        Label password = new Label("Password:");
        grid.add(password, 9, 2);
        password.setFont(font);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 10, 2);

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
        grid.add(vbBtn, 9, 4);
        grid.add(hbBtn, 10, 4);

        //signIn.setTextFill(Color.LIGHTBLUE);
        //signUp.setTextFill(Color.LIGHTBLUE);
        //close.setTextFill(Color.LIGHTBLUE);

        signIn.setStyle("-fx-background-color: #FFA500;");
        signUp.setStyle("-fx-background-color: #FFA500;");
        close.setStyle("-fx-background-color: #FFA500;");

        final Text actiontarget = new Text();
        grid.add(actiontarget, 10, 6);

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
                if (Database.loginUser(userNameField.getText(),
                            passwordField.getText())) {
                    try {
                        s.display("Welcome user", window, userNameField.getText());
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
                else {
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(),
                        "Error!", "Username or password are incorrect");
                    return;
                }
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
        grid.requestFocus();
        primaryStage.show();
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
