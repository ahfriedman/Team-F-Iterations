package edu.wpi.cs3733.D21.teamF.controllers;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.D21.teamF.database.DatabaseAPI;
import edu.wpi.cs3733.D21.teamF.utils.SceneContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Controller for Floral Delivery Service View
 * @author keithdesantis
 */
public class FloralDeliveryService extends ServiceRequests{

    @FXML private JFXRadioButton bouquetButton;
    @FXML private JFXRadioButton vaseButton;
    @FXML private JFXRadioButton potButton;
    @FXML private JFXButton clearButton;
    @FXML private JFXTextField deliveryField;
    @FXML private JFXDatePicker dateField;
    @FXML private JFXTextField nameField;
    @FXML private JFXTextField cardNumberField;
    @FXML private JFXTextField cardCVCField;
    @FXML private JFXTextField cardExpField;
    @FXML private JFXCheckBox roseCheckBox;
    @FXML private JFXCheckBox tulipCheckBox;
    @FXML private JFXCheckBox violetCheckBox;
    @FXML private JFXCheckBox sunflowerCheckBox;
    @FXML private JFXCheckBox orchidCheckBox;
    @FXML private JFXCheckBox daisyCheckBox;
    @FXML private Label successField;
    @FXML private ImageView logoHome;


    @FXML
    public void initialize() {
        Image img = new Image(getClass().getResourceAsStream("/imagesAndLogos/BandWLogo.png"));
        logoHome.setImage(img);
    }

    /**
     * Handles the push of a radio button (sets up Toggle Groups)
     * @param actionEvent button being pushed
     * @author KeithDeSantis
     */
    @FXML
    private void handleRadioButtonClicked(ActionEvent actionEvent) {
        ToggleGroup groupContainer = new ToggleGroup(); // group for container buttons
        bouquetButton.setToggleGroup(groupContainer);
        vaseButton.setToggleGroup(groupContainer);
        potButton.setToggleGroup(groupContainer);
    }

    /**
     * Will eventually save the request to DB
     * @param actionEvent
     * @author KD
     */
    public void handleSubmit(ActionEvent actionEvent) throws SQLException, IOException {
        if(formFilled()) {
            String type = "Flower Delivery";
            String uuid = UUID.randomUUID().toString();
            String additionalInfo = "Date: " + dateField.getValue() + "Deliver to: " + deliveryField.getText() +
            "CC Number: " + cardNumberField.getText() + "CC CVC: " + cardCVCField.getText() + "CC Exp. Date: " + cardExpField.getText();
            DatabaseAPI.getDatabaseAPI().addServiceReq(uuid, type, "", "false", additionalInfo);
            // Loads form submitted window and passes in current stage to return to request home
            openSuccessWindow();
        }
    }

    public boolean formFilled() {
        boolean isFilled = true;
        if(!(bouquetButton.isSelected() || vaseButton.isSelected() || potButton.isSelected())) {
            isFilled = false;
            bouquetButton.setStyle("-fx-text-fill: #e8321e");
            vaseButton.setStyle("-fx-text-fill: #e8321e");
            potButton.setStyle("-fx-text-fill: #e8321e");
        }
        else {
            bouquetButton.setStyle("-fx-text-fill: #000000");
            vaseButton.setStyle("-fx-text-fill: #000000");
            potButton.setStyle("-fx-text-fill: #000000");
        }
        if(!(roseCheckBox.isSelected() || tulipCheckBox.isSelected() || violetCheckBox.isSelected() || sunflowerCheckBox.isSelected() || orchidCheckBox.isSelected() || daisyCheckBox.isSelected())) {
            isFilled = false;
            roseCheckBox.setStyle("-fx-text-fill: #e8321e");
            tulipCheckBox.setStyle("-fx-text-fill: #e8321e");
            violetCheckBox.setStyle("-fx-text-fill: #e8321e");
            sunflowerCheckBox.setStyle("-fx-text-fill: #e8321e");
            orchidCheckBox.setStyle("-fx-text-fill: #e8321e");
            daisyCheckBox.setStyle("-fx-text-fill: #e8321e");
        }
        else {
            roseCheckBox.setStyle("-fx-text-fill: #000000");
            tulipCheckBox.setStyle("-fx-text-fill: #000000");
            violetCheckBox.setStyle("-fx-text-fill: #000000");
            sunflowerCheckBox.setStyle("-fx-text-fill: #000000");
            orchidCheckBox.setStyle("-fx-text-fill: #000000");
            daisyCheckBox.setStyle("-fx-text-fill: #000000");
        }
        if(deliveryField.getText().length() == 0) {
            isFilled = false;
            deliveryField.setStyle("-fx-background-color: #ffbab8;");
        }
        else {
            deliveryField.setStyle("-fx-background-color: transparent;");
        }
        if(nameField.getText().length() == 0) {
            isFilled = false;
            nameField.setStyle("-fx-background-color: #ffbab8;");
        }
        else {
            nameField.setStyle("-fx-background-color: transparent;");
        }
        if(cardNumberField.getText().length() == 0) {
            isFilled = false;
            cardNumberField.setStyle("-fx-background-color: #ffbab8;");
        }
        else {
            cardNumberField.setStyle("-fx-background-color: transparent;");
        }
        if(cardCVCField.getText().length() == 0) {
            isFilled = false;
            cardCVCField.setStyle("-fx-background-color: #ffbab8;");
        }
        else {
            cardCVCField.setStyle("-fx-background-color: transparent;");
        }
        if(cardExpField.getText().length() == 0) {
            isFilled = false;
            cardExpField.setStyle("-fx-background-color: #ffbab8;");
        }
        else {
            cardExpField.setStyle("-fx-background-color: transparent;");
        }
        if(dateField.getValue() == null) {
            isFilled = false;
            dateField.setStyle("-fx-background-color: #ffbab8;");
        }
        else {
            dateField.setStyle("-fx-background-color: transparent;");
        }
        return isFilled;
    }

    public void handleClear() {
        bouquetButton.setSelected(false);
        vaseButton.setSelected(false);
        potButton.setSelected(false);
        roseCheckBox.setSelected(false);
        tulipCheckBox.setSelected(false);
        violetCheckBox.setSelected(false);
        sunflowerCheckBox.setSelected(false);
        orchidCheckBox.setSelected(false);
        daisyCheckBox.setSelected(false);
        deliveryField.setText("");
        nameField.setText("");
        cardNumberField.setText("");
        cardExpField.setText("");
        cardCVCField.setText("");
        dateField.setValue(null);
        bouquetButton.setStyle("-fx-text-fill: #000000");
        vaseButton.setStyle("-fx-text-fill: #000000");
        potButton.setStyle("-fx-text-fill: #000000");
        roseCheckBox.setStyle("-fx-text-fill: #000000");
        tulipCheckBox.setStyle("-fx-text-fill: #000000");
        violetCheckBox.setStyle("-fx-text-fill: #000000");
        sunflowerCheckBox.setStyle("-fx-text-fill: #000000");
        orchidCheckBox.setStyle("-fx-text-fill: #000000");
        daisyCheckBox.setStyle("-fx-text-fill: #000000");
        deliveryField.setStyle("-fx-background-color: transparent;");
        nameField.setStyle("-fx-background-color: transparent;");
        cardNumberField.setStyle("-fx-background-color: transparent;");
        cardCVCField.setStyle("-fx-background-color: transparent;");
        cardExpField.setStyle("-fx-background-color: transparent;");
        dateField.setStyle("-fx-background-color: transparent;");
    }
}

