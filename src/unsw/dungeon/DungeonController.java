package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 */
public class DungeonController {
    
    private Image sword;
    private Image bow;
    private Image potion;
    private Image quest;

    private Tooltip questList = new Tooltip();

    @FXML
    private Button returnBtn;

    @FXML
    private ImageView questImg;

    @FXML
    private ImageView swordImg;

    @FXML
    private ImageView bowImg;

    @FXML
    private ImageView potionImg;

    @FXML
    private Label swordVal;

    @FXML
    private Label bowVal;

    @FXML
    private Label potionVal;

    // @FXML Pane gamePane;

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Player playerCoop;

    private ArrayList<Enemy> enemies;

    private ArrayList<PickUp> itemPickUps;

    private TitleScreen mainMenu;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.playerCoop = dungeon.getPlayerCoop();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.itemPickUps = new ArrayList<PickUp>();
        this.enemies = dungeon.getEnemies();
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        
        potion = new Image((new File("images/bubbly.png")).toURI().toString());
        sword = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        bow = new Image((new File("images/bow.png")).toURI().toString());
        quest = new Image((new File("images/exit.png")).toURI().toString());

        potionImg.setImage(potion);
        swordImg.setImage(sword);
        bowImg.setImage(bow);
        questImg.setImage(quest);
        
        questList.setText("Hello !!");
        Tooltip.install(questImg, questList);
        ArrayList<IntegerProperty> uses = player.getInventoryStatus();

        potionVal.textProperty().bind(uses.get(0).asString());
        swordVal.textProperty().bind(uses.get(1).asString());
        bowVal.textProperty().bind(uses.get(2).asString());

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
    public void handleReturnPress(ActionEvent event) {
        mainMenu.start();
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
            case M:
                player.fireRanged();
                break;
            case W:
                if (playerCoop != null) {
                    playerCoop.moveUp();
                }
                break;
            case A:
                if (playerCoop != null) {
                    playerCoop.moveLeft();
                }
                break;
            case S:
                if (playerCoop != null) {
                    playerCoop.moveDown();
                }
                break;
            case D:
                if (playerCoop != null) {
                    playerCoop.moveRight();
                }
                break;
            case F:
                if (playerCoop != null) {
                    playerCoop.fireRanged();
                }
                break;
            default:
                break;
        }

        System.out.println("Next turn");
        moveEnemies();
        checkPlayersStatus();
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
    private void checkPlayersStatus() {
        // Checks if a player is meant to be dead
        // Obtaining player coordinates
        ArrayList<Player> players = dungeon.getPlayers();

        for (Player p : players) {
            for (Enemy e : new ArrayList<Enemy>(enemies)) {
                if (e.getX() == p.getX() && e.getY() == p.getY()) {
                    if (e.readyToAttack() && p.attacked()) {
                        killEnemy(e);
                    }
                }
            }

            // Checking for potential items to pickup
            for (PickUp i : itemPickUps) {
                if (i.checkTilesCoincide(p.getX(), p.getY())) {
                    player.pickUpItem(i);
                }
            }
        }
        //squares.setFocusTraversable(true);
        //squares.requestFocus();
    }

    public void setMenu(TitleScreen menu) {
        this.mainMenu = menu;
    }

    public void setLog(Log log) {
        dungeon.setLog(log);
    }
}
