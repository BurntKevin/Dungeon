package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class TitleController {
   
    private DungeonScreen level;

    @FXML
    private Label title;

    @FXML
    private ChoiceBox<String> lvlChoiceBox;

    @FXML
    private Button startBtn;

    @FXML
    private Button controlsBtn;

    @FXML
    private Button statsBtn;

    @FXML
    public void handleStartPress(ActionEvent event) {
        level.start(lvlChoiceBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void handleControlsPress(ActionEvent event) {
    }

    @FXML
    public void handleStatsPress(ActionEvent event) {
    }

    @FXML
    public void initialize() {

        //String[] lvlLabels = {"Level 1", "Level 2", "Level 3"}; 
        lvlChoiceBox.getItems().addAll("Level 1", "Level 2", "Level 3");

        // Set to first level initially
        lvlChoiceBox.setValue("Level 1");
    }

    public void setLevel(DungeonScreen level) {
        this.level = level;
    }
}