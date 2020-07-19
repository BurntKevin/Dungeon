package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image gnomeImage;
    private Image treasureImage;
    private Image swordImage;
    private Image exitImage;
    private Image lostGnomeImage;
    private Image camoGnomeImage;
    private Image closedDoorImage;
    private Image portalImage;
    private Image keyImage;
    private Image potionImage;

    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        gnomeImage = new Image((new File("images/gnome.png")).toURI().toString());
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        lostGnomeImage = new Image((new File("images/lost_gnome.png")).toURI().toString());
        camoGnomeImage = new Image((new File("images/camo_gnome.png")).toURI().toString());
        closedDoorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        potionImage = new Image((new File("images/bubbly.png")).toURI().toString());
    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Gnome gnome) {
        ImageView view = new ImageView(gnomeImage);
        addEntity(gnome, view);
    }

    @Override
    public void onLoad(LostGnome lostGnome) {
        ImageView view = new ImageView(lostGnomeImage);
        addEntity(lostGnome, view);
    }
    
    @Override
    public void onLoad(CamoGnome camoGnome) {
        ImageView view = new ImageView(camoGnomeImage);
        addEntity(camoGnome, view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(closedDoorImage);
        addEntity(door, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    @Override
    public void onLoad(PickUp item) {
        // Using player to indicate missing texture - #TODO
        ImageView view = new ImageView(playerImage);
        if (item.getItemFromPickUp() instanceof Sword) {
            view = new ImageView(swordImage);
        } else if (item.getItemFromPickUp() instanceof Treasure) {
            view = new ImageView(treasureImage);
        } else if (item.getItemFromPickUp() instanceof Potion) {
            view = new ImageView(potionImage);
        } else if (item.getItemFromPickUp() instanceof Key) {
            view = new ImageView(keyImage);
        }
        addEntity(item, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    private void addEntity(PickUp item, ImageView view) {
        System.out.println("I am an item");
        trackPosition(item, view);
        trackStatus(item, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    private void trackStatus(PickUp item, Node node) {
        item.confirmPickedUp().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                System.out.println("Removing pick up item from front end");
                node.setVisible(false);
            }
        });

        // private GridPane squares;
        // for (ImageView entity : initialEntities) {
        //     squares.getChildren().add(entity);
        // }
        // private List<ImageView> initialEntities;
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }
}
