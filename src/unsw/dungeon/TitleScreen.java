package unsw.dungeon;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TitleScreen {
    private Stage stage;
    private String title;
    private TitleController controller;

    private Scene scene;

    public TitleScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Title";

        controller = new TitleController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("title.fxml"));
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

    public TitleController getController() {
        return controller;
    }

    public void setControllerLevel(DungeonScreen level) {
        controller.setLevel(level);
    }

}