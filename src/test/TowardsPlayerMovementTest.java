package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Player;
import unsw.dungeon.TowardsPlayerMovement;
import unsw.dungeon.Dungeon;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.json.JSONArray;
import org.json.JSONObject;

public class TowardsPlayerMovementTest {
    /**
     * TowardsPlayerMovement initalisation tests
     */
    @Test
    public void initTowardsPlayerMovementTest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 0).put("y", 1).put("type", "wall"));
        JSONObject input = new JSONObject()
                            .put("width", "1")
                            .put("height", "2")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Creating movement
        new TowardsPlayerMovement(dungeon);
    }

    /**
     * TowardsPlayerMovement towards player move
     */
    @Test
    public void moveNotBuffedPlayer() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 2).put("y", 2).put("type", "player"));
        JSONObject input = new JSONObject()
                            .put("width", "5")
                            .put("height", "5")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Creating Movement
        TowardsPlayerMovement movement = new TowardsPlayerMovement(dungeon);

        // Checking movement
        assertArrayEquals(movement.move(1, 2), new int[] {1, 0});
        assertArrayEquals(movement.move(3, 2), new int[] {-1, 0});
        assertArrayEquals(movement.move(2, 3), new int[] {0, -1});
        assertArrayEquals(movement.move(2, 1), new int[] {0, 1});
        assertArrayEquals(movement.move(2, 2), new int[] {0, 0});
    }

    /**
     * TowardsPlayerMovement away from player move
     */
    @Test
    public void moveBuffedPlayer() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 2).put("y", 2).put("type", "player"));
        entities.put(new JSONObject().put("x", 2).put("y", 3).put("type", "invincibility"));
        JSONObject input = new JSONObject()
                            .put("width", "5")
                            .put("height", "5")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Creating Movement
        TowardsPlayerMovement movement = new TowardsPlayerMovement(dungeon);

        // Making player have a potion
        Player player = dungeon.getPlayer();
        player.moveDown();

        // Checking movement
        assertArrayEquals(movement.move(1, 2), new int[] {0, -1});
        assertArrayEquals(movement.move(3, 2), new int[] {0, -1});
        assertArrayEquals(movement.move(2, 3), new int[] {-1, 0});
        assertArrayEquals(movement.move(2, 1), new int[] {0, -1});
    }
}
