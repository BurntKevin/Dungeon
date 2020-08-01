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
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private ArrayList<Enemy> enemies;

    private ArrayList<PickUp> itemPickUps;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.itemPickUps = new ArrayList<PickUp>();
        this.enemies = dungeon.getEnemies();
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
        case R:
            player.fireRanged();
        default:
            break;
        }

        moveEnemies();
        checkPlayerStatus();
    }

    private void moveEnemies() {
        for (Enemy e : enemies) {
            e.move();
        }
    }

    public void killEnemy(Enemy e) {
        System.out.println("Enemy attacked");
        enemies.remove(e);
        dungeon.removeEntity(e);
        e.attacked().set(false);
    }

    /**
     * Determines what actions to take for current turn
     * depending on what entities are in the same tile as the player
     */
    private void checkPlayerStatus() {
        // Checks if a player is meant to be dead
        // Obtaining player coordinates
        int[] playerCoordinate = dungeon.getPlayerCoordinates();
        for (Enemy e : new ArrayList<Enemy>(enemies)) {
            if (e.getX() == playerCoordinate[0] && e.getY() == playerCoordinate[1]) {
                if (player.attacked()) {
                    killEnemy(e);
                }
            }
        }

        // Checking for potential items to pickup
        for (PickUp i : itemPickUps) {
            if (i.checkTilesCoincide(playerCoordinate[0], playerCoordinate[1])) {
                player.pickUpItem(i);
            }
        }
    }
}
