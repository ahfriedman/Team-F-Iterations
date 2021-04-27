package edu.wpi.cs3733.D21.teamF.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneContext {

    private Stage stage;
    private static SceneContext sceneContext = new SceneContext();

    private SceneContext() {}

    public static SceneContext getSceneContext() { return sceneContext; }

    public void switchScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/D21/teamF/fxml/" + fxml));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}