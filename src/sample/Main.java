package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    private static RegistrationForm r = new RegistrationForm();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // String[] args;
        window = primaryStage;
        primaryStage.setTitle("JavaFX Welcome");

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

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button signIn = new Button("Sign in");
        Button signUp = new Button("Sign up");
        Button close = new Button("Exit");
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

        signIn.setOnAction(e -> SignedInUser.display("Welcome"));
        signUp.setOnAction(e -> {
            try {
                r.start(window);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });// System.out.println("Create account clicked"));
        window.setOnCloseRequest(e -> {
           e.consume();
           window.close();
        });
        close.setOnAction(e -> window.close());

        primaryStage.show();
    }
}
