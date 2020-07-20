package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.Quest;


public class ExitTest {
    /**
     * Exit initalisation test
     */
    @Test
    public void initExitTest() {
        // Creating an empty exist
        new Exit(1, 2);
    }

    /**
     * Entering exit
     */
    @Test
    public void unsuccessfulAndQuest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "exit"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "sword"));
        entities.put(new JSONObject().put("x", 3).put("y", 0).put("type", "exit"));
        entities.put(new JSONObject().put("x", 4).put("y", 0).put("type", "gnome"));
        JSONObject input = new JSONObject()
                            .put("width", "5")
                            .put("height", "1")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Successful completion
        Exit exit = new Exit(0, 0);
        exit.addMission(new Quest("exit", dungeon));
        exit.enter();

        // Unsuccessful completion
        exit.addMission(new Quest("treasure", dungeon));
        exit.enter();
    }
}
