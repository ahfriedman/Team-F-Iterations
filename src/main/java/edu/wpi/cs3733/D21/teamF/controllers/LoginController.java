package edu.wpi.cs3733.D21.teamF.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.wpi.cs3733.D21.teamF.database.DatabaseAPI;
import edu.wpi.cs3733.D21.teamF.entities.CurrentUser;
import edu.wpi.cs3733.D21.teamF.utils.SceneContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton signIn;
    @FXML
    private Label errorMessage;
    @FXML
    private JFXButton skipSignIn;

    public void handleButtonPushed(ActionEvent actionEvent) throws IOException, SQLException {
        Button buttonPushed = (JFXButton) actionEvent.getSource();  //Getting current stage
        if (buttonPushed == signIn) {
            if (!DatabaseAPI.getDatabaseAPI().verifyAdminExists()) {
                DatabaseAPI.getDatabaseAPI().addUser("admin", "administrator", "admin", "admin");
                DatabaseAPI.getDatabaseAPI().addUser("staff", "employee", "staff", "staff");
                DatabaseAPI.getDatabaseAPI().addUser("guest", "visitor", "guest", "guest");
            }
            String user = username.getText();
            String pass = password.getText();

            if (CurrentUser.getCurrentUser().login(user, pass)) {
                SceneContext.getSceneContext().loadDefault();
            }
            else {
                errorMessage.setStyle("-fx-text-fill: #c60000FF;");
                password.setText("");
            }
        }
        else if (buttonPushed == skipSignIn) {
               SceneContext.getSceneContext().loadDefault();
        }

    }



}
