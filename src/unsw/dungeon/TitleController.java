package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class TitleController {
   
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
    }

    @FXML
    public void handleControlsPress(ActionEvent event) {
    }

    @FXML
    public void handleStatsPress(ActionEvent event) {
    }

    @FXML
    public void initialize() {

        String[] lvlLabels = {"Level 1", "Level 2", "Level 3"}; 
        lvlChoiceBox.getItems().addAll(lvlLabels);

        // Start at first level if none choosen
        lvlChoiceBox.setValue("Level 1");
    }
}