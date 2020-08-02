package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogScreen {

    private Stage stage;
    private String title;
    private LogController controller;

    private Scene scene;

    public LogScreen(Stage stage, TitleScreen mainMenu, Log log) throws IOException {
        this.stage = stage;
        title = "Game Statistics";

        controller = new LogController(mainMenu, log);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("log.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        root.requestFocus();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public LogController getController() {
        return controller;
    }
}
