package edu.wpi.cs3733.D21.teamF.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.D21.teamF.utils.SceneContext;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DefaultPageController {
    @FXML
    private JFXButton navigation;
    @FXML
    private JFXButton serviceRequest;
    @FXML
    private JFXButton quit;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton covidSurvey;

    @FXML private void initialize(){
        // Apply fonts to title and buttons

        // CLear visual focus for login button (unknown why it defaults to false) - LM
        loginButton.setDisableVisualFocus(true);
    }
    /**
     * Handles the pushing of a button on the screen
     * @param actionEvent the button's push
     * @throws IOException in case of scene switch, if the next fxml scene file cannot be found
     * @author ZheCheng Song
    */
    @FXML
    private void handleButtonPushed(ActionEvent actionEvent) throws IOException {

        Button buttonPushed = (Button) actionEvent.getSource();  //Getting current stage

        if (buttonPushed == loginButton) {
            SceneContext.getSceneContext().switchScene("/edu/wpi/cs3733/D21/teamF/fxml/Login.fxml");
        } else if (buttonPushed == navigation) {
            SceneContext.getSceneContext().switchScene("/edu/wpi/cs3733/D21/teamF/fxml/AStarDemoView.fxml");
        } else if (buttonPushed == serviceRequest) {
            SceneContext.getSceneContext().switchScene("/edu/wpi/cs3733/D21/teamF/fxml/ServiceRequestHomeNewView.fxml");
        } else if (buttonPushed == quit) {
            Platform.exit();
        }
        else if (buttonPushed == covidSurvey){
            SceneContext.getSceneContext().switchScene("/edu/wpi/cs3733/D21/teamF/fxml/CovidSurveyView.fxml");
        }
    }




}
