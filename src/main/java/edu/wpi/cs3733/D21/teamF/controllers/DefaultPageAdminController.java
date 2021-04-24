package edu.wpi.cs3733.D21.teamF.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class DefaultPageAdminController {

    @FXML
    private JFXButton editMap;
    @FXML
    private JFXButton manageServices;
    @FXML
    private JFXButton AStarDemo;
    @FXML
    private JFXButton serviceRequest;
    @FXML
    private JFXButton manageAccount;
    @FXML
    private JFXButton quit;
    @FXML
    private ImageView directionsImage;
    @FXML
    private ImageView serviceButton;
    @FXML
    private ImageView manageAccounts;
    @FXML
    private ImageView mapIcon;
    @FXML
    private ImageView serviceManageIcon;
    @FXML
    private void initialize(){
        final Image directions = new Image(getClass().getResourceAsStream("/imagesAndLogos/directionsArrow.png"));
        directionsImage.setImage(directions);
        final Image serviceRequest = new Image(getClass().getResourceAsStream("/imagesAndLogos/serviceButton.png"));
        serviceButton.setImage(serviceRequest);
        final Image account = new Image(getClass().getResourceAsStream("/imagesAndLogos/manageAccounts.png"));
        manageAccounts.setImage(account);
        final Image map = new Image(getClass().getResourceAsStream("/imagesAndLogos/map.png"));
        mapIcon.setImage(map);
        final Image serviceManage = new Image(getClass().getResourceAsStream("/imagesAndLogos/manageServices.png"));
        serviceManageIcon.setImage(serviceManage);
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
        Stage stage;
        Parent root;

        if (buttonPushed == editMap) {
            stage = (Stage) buttonPushed.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/D21/teamF/fxml/mapEditView.fxml"));
            stage.getScene().setRoot(root);
            stage.setTitle("Edit Map Menu");
            stage.show();
        } else if (buttonPushed == manageServices) {
            stage = (Stage) buttonPushed.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/D21/teamF/fxml/MarkRequestsCompleteView.fxml"));
            stage.getScene().setRoot(root);  //Changing the stage
            stage.setTitle("Service Request Manager");
            stage.show();
        } else if (buttonPushed == AStarDemo) {
            stage = (Stage) buttonPushed.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/D21/teamF/fxml/AStarDemoView.fxml"));
            stage.getScene().setRoot(root);
            stage.setTitle("AStar Pathfinding Demo");
            stage.show();

        } else if (buttonPushed == serviceRequest) {

            stage = (Stage) buttonPushed.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/D21/teamF/fxml/ServiceRequestHomeView.fxml"));
            stage.getScene().setRoot(root);
            stage.setTitle("Service Request Home");
            stage.show();

        } else if (buttonPushed == manageAccount) {
            stage = (Stage) buttonPushed.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/D21/teamF/fxml/AccountManagerView.fxml"));
            //Scene scene = new Scene(root);
            //stage.setScene(scene);
            stage.getScene().setRoot(root);
            stage.setTitle("Account Manager");
            stage.show();

        } else if (buttonPushed == quit) {
            Platform.exit();
        }
    }
}
