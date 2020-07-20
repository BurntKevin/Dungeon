package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.RandomMovement;
import unsw.dungeon.Dungeon;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.json.JSONArray;
import org.json.JSONObject;

public class RandomMovementTest {
    /**
     * RandomMovement initalisation tests
     */
    @Test
    public void initRandomMovementTest() {
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
        new RandomMovement(dungeon);
    }

    /**
     * RandomMovement test in a trapped location
     */
    @Test
    public void moveTrappedEnemy() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 0).put("y", 1).put("type", "wall"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "wall"));
        entities.put(new JSONObject().put("x", 1).put("y", 2).put("type", "wall"));
        entities.put(new JSONObject().put("x", 2).put("y", 1).put("type", "wall"));
        JSONObject input = new JSONObject()
                            .put("width", "3")
                            .put("height", "3")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Creating Movement
        RandomMovement movement = new RandomMovement(dungeon);

        // Checking movement
        System.out.println(movement.move(2, 2));
        assertArrayEquals(movement.move(2, 2), new int[] {0, 0});
    }
}
