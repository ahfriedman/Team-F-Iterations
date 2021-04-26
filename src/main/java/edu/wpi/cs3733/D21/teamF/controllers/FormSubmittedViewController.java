package edu.wpi.cs3733.D21.teamF.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

/**
 * Controller for Form Submitted Pop Up
 */
public class FormSubmittedViewController {

    @FXML private JFXButton okButton;

    /**
     * Handles ok button by closing window.
     * @author keithdesantis
     */
    @FXML
    private void okButtonPushed() {
        ( (Stage) okButton.getScene().getWindow()).close();
    }

    @FXML
    public void handleHoverOn(javafx.scene.input.MouseEvent mouseEvent) {
        JFXButton btn = (JFXButton) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #F0C808; -fx-text-fill: #000000;");
    }

    @FXML
    public void handleHoverOff(javafx.scene.input.MouseEvent mouseEvent) {
        JFXButton btn = (JFXButton) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #03256C; -fx-text-fill: #FFFFFF;");
    }
}
