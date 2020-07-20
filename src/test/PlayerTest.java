package test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

public class PlayerTest {
    /**
     * Tests player initalisation
     */
    @Test
    public void initPlayerTest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "sword"));
        entities.put(new JSONObject().put("x", 4).put("y", 0).put("type", "gnome"));
        JSONObject input = new JSONObject()
                            .put("width", "5")
                            .put("height", "1")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        new Player(dungeon, 0, 0);
    }

    /**
     * Setting play status
     */
    @Test
    public void playerStatusTest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "sword"));
        entities.put(new JSONObject().put("x", 4).put("y", 0).put("type", "gnome"));
        JSONObject input = new JSONObject()
                            .put("width", "5")
                            .put("height", "1")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Picking up weapon
        assertFalse(player.isInvincible());

        // Ability to kill enemies
        assertFalse(player.attacked());
    }
}