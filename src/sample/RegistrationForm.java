package sample;

/*import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegistrationForm extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Registration Form");
        // Create the registration form pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid Pane
        addUIControls(gridPane);
        //Create a scene with the registration form gridPane as the root node.
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private GridPane createRegistrationFormPane(){
        //Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();
        //Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);
        //set a padding of 40px on each side
        gridPane.setPadding(new Insets(40,40,40,40));
        // Set the Horizontal gap between columns
        gridPane.setHgap(10);
        //Set the vertical gap between rows
        gridPane.setVgap(10);

        //Add Column Constraints
        //columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        //columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstraints.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstraints);
        return gridPane;
    }
    private void addUIControls(GridPane gridPane){
        //Add Header
        Label headerLabel = new Label("Fill The Details - ");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name: ");
        gridPane.add(nameLabel, 0,1);

        //Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(30);
        gridPane.add(nameField, 1,1);

        // Add User Label
        Label userLabel = new Label("User Name: ");
        gridPane.add(userLabel, 0,2);

        //Add User Text Field
        TextField userField = new TextField();
        nameField.setPrefHeight(30);
        gridPane.add(userField, 1,2);

        // Add Email Label
        Label emailLabel = new Label("Email Id: ");
        gridPane.add(emailLabel,0,3);

        //Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(30);
        gridPane.add(emailField, 1,3);

        //Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 4);

        //Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(30);
        gridPane.add(passwordField, 1 , 4);

        //Add Register Button
        Button submitButton = new Button("Register");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20,0,20,0));
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(nameField.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter your name");
                    return;
                }
                if(userField.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!" ,"Please enter your username");
                    return;
                }
                if(emailField.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter your email");
                    return;
                }
                if(passwordField.getText().isEmpty()){
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter your password");
                    return;
                }
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Sign Up Successful","Welcome "+userField.getText());
            }
        });
    }
    private void showAlert(Alert.AlertType alertType, Window owner,String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public static void main(String[] args){
        launch(args);
    } 
}*/
