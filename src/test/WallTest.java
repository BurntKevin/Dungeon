package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Wall;
import unsw.dungeon.Player;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;

public class WallTest {
    /**
     * Wall initalisation tests
     */
    @Test
    public void initWallTest() {
        // Creating walls
        Wall wall1 = new Wall(0, 0);
        Wall wall2 = new Wall(-1, -1);
        Wall wall3 = new Wall(5, 21);

        assertEquals(wall1.getX(), 0);
        assertEquals(wall1.getY(), 0);
        assertEquals(wall2.getX(), -1);
        assertEquals(wall2.getY(), -1);
        assertEquals(wall3.getX(), 5);
        assertEquals(wall3.getY(), 21);
    }

    /**
     * Testing player walking into a wall
     */
    @Test
    public void playerWallTest() {
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
        
        // Attempting to walk player into a wall
        Player player = dungeon.getPlayer();
        player.moveDown();

        // Ensuring the player did not walk into the wall
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
    }

    /**
     * Testing enemy walking into a wall
     */
    @Test
    public void enemyWallTest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 0).put("y", 1).put("type", "wall"));
        entities.put(new JSONObject().put("x", 0).put("y", 2).put("type", "gnome"));
        JSONObject input = new JSONObject()
                               .put("width", "1")
                               .put("height", "3")
                               .put("entities", entities)
                               .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Triggering next turn; make enemy move
        Player player = dungeon.getPlayer();
        player.moveRight();

        // Obtaining enemy
        Enemy enemy = dungeon.getEnemies().get(0);

        // Ensuring the player did not walk into the wall
        assertEquals(enemy.getX(), 0);
        assertEquals(enemy.getY(), 2);
    }
}
