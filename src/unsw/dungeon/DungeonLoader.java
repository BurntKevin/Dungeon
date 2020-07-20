package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

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

    public DungeonLoader(JSONObject json){
        this.json = json;
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        // Creating entities
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
        case "lost_gnome":
            LostGnome lostGnome = new LostGnome(x, y, dungeon);
            onLoad(lostGnome);
            entity = lostGnome;
            break;
        case "camo_gnome":
            CamoGnome camoGnome = new CamoGnome(x, y, dungeon);
            onLoad(camoGnome);
            entity = camoGnome;
            break;
        case "sword":
            Sword sword = new Sword();
            PickUp swordPU = new PickUp(x, y, (Item) sword, "Sword");
            onLoad(swordPU);
            entity = swordPU;
            break;
        case "invincibility":
            Potion potion = new Potion(); 
            PickUp potionPU = new PickUp(x, y, (Item) potion, "Potion");
            onLoad(potionPU);
            entity = potionPU;
            break;
        case "key":
            int keyid = json.getInt("id");            
            Key key = new Key(keyid);
            PickUp keyPU = new PickUp(x, y, (Item) key, "Key");
            onLoad(keyPU);
            entity = keyPU;
            break;
        case "portal":
            int portid = json.getInt("id");            
            Portal portal = new Portal(x, y, portid);
            onLoad(portal);
            entity = portal;
            break;
        case "boulder":
            Boulder boulder = new Boulder(x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Switch plate = new Switch(x, y);
            onLoad(plate);
            entity = plate;
            break;
        case "door":
            int doorid = json.getInt("id");
            Door door = new Door(x, y, doorid);
            onLoad(door);
            entity = door;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;

            // Creating quests
            // Obtaining goal
            JSONObject jsonGoalCondition = this.json.getJSONObject("goal-condition");
            String jsonGoal = jsonGoalCondition.getString("goal");

            // Simple quest of simply arriving to exit
            Quest questExit = new Quest("exit", dungeon);
            exit.addMission(questExit);

            // Creating AND, OR goals
            JSONArray jsonQuests = null;
            switch (jsonGoal) {
                case "OR":
                    jsonQuests = jsonGoalCondition.getJSONArray("subgoals");
                    OrQuest OrQuest = new OrQuest(new ArrayList<Mission>());
                    for (int i = 0; i < jsonQuests.length(); i++) {
                        JSONObject jsonSpecificQuest = (JSONObject) jsonQuests.get(i);
                        Quest additionalQuest = new Quest(jsonSpecificQuest.getString("goal"), dungeon);
                        OrQuest.addQuest(additionalQuest);
                    }
                    exit.addMission(OrQuest);
                    break;
                case "AND":
                    jsonQuests = jsonGoalCondition.getJSONArray("subgoals");
                    AndQuest AndQuest = new AndQuest(new ArrayList<Mission>());
                    for (int i = 0; i < jsonQuests.length(); i++) {
                        JSONObject jsonSpecificQuest = (JSONObject) jsonQuests.get(i);
                        Quest additionalQuest = new Quest(jsonSpecificQuest.getString("goal"), dungeon);
                        AndQuest.addQuest(additionalQuest);
                    }
                    exit.addMission(AndQuest);
                    break;
            }
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Gnome gnome);

    public abstract void onLoad(LostGnome lostGnome);

    public abstract void onLoad(CamoGnome camoGnome);

    public abstract void onLoad(PickUp pickup);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Switch plate);
}
