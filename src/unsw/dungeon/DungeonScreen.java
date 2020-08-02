package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
    private Stage stage;
    private String title;
    private TitleController controller;

    private Scene scene;

    public DungeonScreen(Stage stage, Log log) throws IOException {
        this.stage = stage;
        //title = "Level "+level;
        
        //String fname = "level"+level;
        String fname = "test";
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(fname+".json");
        DungeonController controller = dungeonLoader.loadController();
        controller.setLog(log);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        root.requestFocus();
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void start(String choice, Log log) {
        try {
            setLevel(choice, log);
        }
        catch (Exception e) {
            System.out.println("Could not load level.");
        }
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public TitleController getController() {
        return controller;
    }

    private void setLevel(String choice, Log log) throws IOException {

        String fname = "level"+(choice.charAt(choice.length()-1));

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(fname+".json");
        DungeonController controller = dungeonLoader.loadController();
        controller.setLog(log);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        root.requestFocus();
        scene = new Scene(root);
        stage.setScene(scene);
    }
   

}