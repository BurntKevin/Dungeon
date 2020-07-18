package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private ArrayList<Enemy> enemies;

    private ArrayList<PickUp> itempickups;

    private ArrayList<Door> doors;

    private ArrayList<Portal> portals;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.enemies = dungeon.getEnemies();        
        this.doors = dungeon.getDoors();
        this.portals = dungeon.getPortals();
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        default:
            break;
        }

        if (!player.isInvisible()) {
            moveEnemies();
        }
        else {
            // flee(); // waiting for implementation
        }
        checkPlayerStatus();
    }

    private void moveEnemies() {
        for (Enemy e : enemies) {
            e.move();
        }
    }
    
    /**
     * Determines what actions to take for current turn
     * depending on what entities are in the same tile as the player
     */
    private void checkPlayerStatus() {
        // Checks if a player is meant to be dead
        // Obtaining player coordinate
        int[] playerCoordinate = dungeon.getPlayerCoordinates();
        for (Enemy e : enemies) {
            if (e.getX() == playerCoordinate[0] && e.getY() == playerCoordinate[1]) {
                player.attacked();
            }
        }
        for (PickUp i : itempickups) {
            if (i.checkTilesCoincide(playerCoordinate[0], playerCoordinate[1])) {
                player.pickUpItem(i);
            }
        }
    }
}

