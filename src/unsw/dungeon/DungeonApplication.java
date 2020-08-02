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

        DungeonScreen testLevel = new DungeonScreen(primaryStage, gameLog);
        titleScreen.setControllerLevel(testLevel);

        // Set the initial log, and pass to current level
        LogScreen logScreen = new LogScreen(primaryStage, titleScreen, gameLog);
        titleScreen.setControllerStats(logScreen);

        titleScreen.start();
        /*
        primaryStage.setTitle("Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("test.json");

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    public static void main(String[] args) {
        launch(args);
    }
}
