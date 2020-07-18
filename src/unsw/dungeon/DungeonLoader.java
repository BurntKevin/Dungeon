package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "treasure":
            Treasure treasure = new Treasure();
            PickUp treasurePU = new PickUp(x, y, (Item) treasure, "Treasure");
            onLoad(treasurePU);
            entity = treasurePU;
            break;
        case "gnome":
            Gnome gnome = new Gnome(x, y, dungeon);
            onLoad(gnome);
            entity = gnome;
            break;
        case "sword":
            Sword sword = new Sword();
            PickUp swordPU = new PickUp(x, y, (Item) sword, "Sword");
            onLoad(swordPU);
            entity = swordPU;
            break;
        // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Gnome gnome);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(PickUp pickup);

    // TODO Create additional abstract methods for the other entities

}
