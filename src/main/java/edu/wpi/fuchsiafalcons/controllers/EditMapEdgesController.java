package edu.wpi.fuchsiafalcons.controllers;

import edu.wpi.fuchsiafalcons.entities.EdgeEntry;
import edu.wpi.fuchsiafalcons.utils.CSVManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class EditMapEdgesController {

    @FXML
    private Button goBack;

    @FXML
    private Button newEdge;

    @FXML
    private Button editEdge;

    @FXML
    private Button deleteEdge;

    @FXML
    private Button loadCSV;

    @FXML
    private Button saveCSV;

    @FXML
    private TextField CSVFile;

    @FXML
    private Label CSVErrorLabel;

    @FXML
    private TableView<EdgeEntry> edgeTable;

    @FXML
    private TableColumn<EdgeEntry, String> edgeIDColumn;

    // Create an observable list of edges
    private ObservableList<EdgeEntry> edgeEntryObservableList = FXCollections.observableArrayList();

    @FXML
    private ScrollPane scroll;
    @FXML
    private ImageView map;

    /**
     * Initializes controller, called when EditMapEdges.fxml is loaded.
     *
     * @author Leo Morris
     */
    @FXML
    private void initialize() {
        // Clear the file error label
        CSVErrorLabel.setStyle("-fx-text-fill: black");
        CSVErrorLabel.setText("");

        // setup the map view
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        map.setPreserveRatio(true);
        final Image image = new Image(getClass().getResourceAsStream("/maps/01_thefirstfloor.png"));
        final double zoomLevel = 4.0;
        final double width = image.getWidth()/zoomLevel;
        final double height = image.getHeight()/zoomLevel;
        map.setFitWidth(width);
        map.setFitHeight(height);
        map.setImage(image); // Copied from EditMapEdges - LM

        // Set the save button to disabled by default (enabled by a valid file name being entered)
        saveCSV.setDisable(true);

        //FIXME: do better, hook into db
        try {
            edgeEntryObservableList.addAll( CSVManager.load("L1Edges.csv").stream().map(line-> {
                return new EdgeEntry(line[0], line[1], line[2]);
            }).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        edgeTable.setItems(edgeEntryObservableList);

        // Initialize the edges table with edges
        edgeIDColumn.setCellValueFactory(cellData -> cellData.getValue().edgeIDProperty());

        // Set initial selected edge to null
        showSelectedEdge(null);

        // Listen for selection changes on the table view
        edgeTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showSelectedEdge(newValue)));
    }

    /**
     * Called from listener on the table view.
     * Shows the selected edge on the map view.
     * @param edge the selected edge
     * @author Leo Morris
     */
    public void showSelectedEdge(EdgeEntry edge){
        //TODO show selected edge on Map
    }

    /**
     * Deletes the selected node from the table. Called when the delete button is pressed
     * Creates an alert pop-up if no edge is selected
     * @author Leo Morris
     */
    @FXML
    private void handleDelete(){
        // Get the current selected edge
        int index = edgeTable.getSelectionModel().getSelectedIndex();

        // Check for a valid index (-1 = no selection)
        if(index >= 0){
            // Remove the edge, this will update the TableView automatically
            edgeTable.getItems().remove(index);
        } else {
            // Create an alert to inform the user there is no edge selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null); // Appears on top of all other windows
            alert.setTitle("No Selection");
            alert.setHeaderText("No Edge Selected");
            alert.setContentText("Please select an edge from the list");

            alert.showAndWait();
        }
    }

    /**
     * Called when an edge is selected to be edited
     * @author Karen Hou
     */
    @FXML
    private void handleEditEdge() throws IOException {
        EdgeEntry selectedEdge = edgeTable.getSelectionModel().getSelectedItem();
        if(selectedEdge != null){
            openEditDialogue(selectedEdge);
        }
    }

    /**
     * Called when an edge is to be created
     * @author Karen Hou
     */
    @FXML
    private void handleNewEdge() throws IOException {
        EdgeEntry newEdge = new EdgeEntry();
        openEditDialogue(newEdge);
        if(newEdge.edgeIDProperty().getValue().isEmpty() || newEdge.startNodeProperty().getValue().isEmpty() ||
            newEdge.endNodeProperty().getValue().isEmpty())
            return; //FIXME: DO BETTER ERROR CHECKING
        edgeEntryObservableList.add(newEdge);
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

        if (buttonPushed == goBack) {
            stage = (Stage) buttonPushed.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/edu/wpi/fuchsiafalcons/fxml/DefaultPageView.fxml"));
            stage.getScene().setRoot(root);
            stage.setTitle("Default Page");
            stage.show();
        }
    }

    /**
     * Opens edit dialogue to edit edge
     * Written with code from KD
     * @param editedEdge is the edge being edited
     * @author Karen Hou
     */
    private void openEditDialogue(EdgeEntry editedEdge) throws IOException{
        FXMLLoader editDialogueLoader = new FXMLLoader();
        editDialogueLoader.setLocation(getClass().getResource("/edu/wpi/fuchsiafalcons/fxml/EditMapEdgeDialogueView.fxml"));
        Stage dialogueStage = new Stage();
        Parent root = editDialogueLoader.load();
        EditMapEdgeDialogueViewController editDialogueController = editDialogueLoader.getController();

        editDialogueController.setDialogueStage(dialogueStage);
        editDialogueController.setEdge(editedEdge);

        dialogueStage.setTitle("Edit Edge");
        dialogueStage.initModality(Modality.WINDOW_MODAL);
        dialogueStage.initOwner((Stage) goBack.getScene().getWindow());
        dialogueStage.setScene(new Scene(root));

        dialogueStage.showAndWait();
    }

    /**
     * Saves the current list of edges to a CSV file at the location specified in the TextField by the user
     * Written with code from KD and AHF
     * @param actionEvent Save button action
     * @author Leo Morris
     */
    public void handleSaveButton(ActionEvent actionEvent){
        final String fileName = CSVFile.getText();

        final List<String[]> data = new LinkedList<String[]>();
        Collections.addAll(data, "edgeID, startingNode, endingNode".split(","));

        data.addAll(edgeEntryObservableList.stream().map(edge->{
            return new String[] {
                    edge.edgeIDProperty().getValue(),
                    edge.startNodeProperty().getValue(),
                    edge.endNodeProperty().getValue()
            };
        }).collect(Collectors.toList()));
        try{
            CSVManager.writeFile(fileName, data);
        } catch (Exception e){
            CSVErrorLabel.setStyle("-fx-text-fill: red");
            CSVErrorLabel.setText(e.getMessage());
            e.printStackTrace();
            return;
        }

        CSVErrorLabel.setStyle("-fx-text-fill: black");
        CSVErrorLabel.setText("File successfully exported");

        CSVFile.setText("");
        saveCSV.setDisable(true);
    }

    /**
     * Disables the save and load buttons if the file name is invalid
     * @param keyEvent Calls on key release in the CSVFile TextField
     */
    @FXML
    public void handleFileNameType(KeyEvent keyEvent){
        CSVErrorLabel.setText("");
        CSVErrorLabel.setStyle("-fx-text-fill: black");

        final boolean disableBtns = !CSVFile.getText().endsWith(".csv");

        saveCSV.setDisable(disableBtns);
        loadCSV.setDisable(false); //FIXME: CHANGE CONDITION ONCE LOADING FROM JAR IS IMPLEMENTED
    }

    /**
     * Loads in the CSV file specified in the CSVFile TextField by the user
     * Written with code from KD and AHF
     * @param actionEvent Pressing the LOad CSV Button
     * @author Leo Morris
     */
    public void handleLoadButtonClicked(ActionEvent actionEvent){
        final String fileName = CSVFile.getText();

        edgeEntryObservableList.clear();

        List<String[]> edgeData = null;

        try {
            edgeData = (fileName == null || fileName.trim().isEmpty()) ? CSVManager.load("L1Edges.csv") : CSVManager.load(new File(fileName));
        } catch (Exception e){
            CSVErrorLabel.setStyle("-fx-text-fill: red");
            CSVErrorLabel.setText(e.getMessage());
            e.printStackTrace();
            return;
        }

        if(edgeData != null){
            if(!edgeData.isEmpty() && edgeData.get(0).length == 3) {
                edgeEntryObservableList.addAll(edgeData.stream().map(line-> {
                    return new EdgeEntry(line[0], line[1], line[2]);
                }).collect(Collectors.toList()));
            }
        }
    }

}
