package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        TitleScreen titleScreen = new TitleScreen(primaryStage);

        Log gameLog = new Log();
        titleScreen.setControllerLog(gameLog);

        DungeonScreen testLevel = new DungeonScreen(primaryStage, gameLog, titleScreen);
        titleScreen.setControllerLevel(testLevel);

        // Set the initial log, and pass to current level
        LogScreen logScreen = new LogScreen(primaryStage, titleScreen, gameLog);
        titleScreen.setControllerStats(logScreen);

        titleScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
