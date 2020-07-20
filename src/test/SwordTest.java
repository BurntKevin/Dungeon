package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Dungeon;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.json.JSONArray;
import org.json.JSONObject;

public class SwordTest {
    /**
     * Sword initalisation tests
     */
    @Test
    public void initSwordTest() {
        Sword sword = new Sword();
        assertFalse(sword.checkWeaponUsable());
    }

    /**
     * Sword refresh
     */
    @Test
    public void swordRefreshTest() {
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
        player.moveRight();

        // Killing enemy
        player.moveRight();
    }

    /**
     * TowardsPlayerMovement away from player move
     */
    @Test
    public void checkWeaponStatus() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "sword"));
        JSONObject input = new JSONObject()
                            .put("width", "2")
                            .put("height", "1")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();
        Player player = dungeon.getPlayer();

        // Ensuring that the player's sword is unusable
        Sword sword = player.getSword();
        assertFalse(sword.checkWeaponUsable());

        // Picking up weapon
        player.moveRight();

        // Using the sword until it breaks
        sword.attemptMeleeAttack();
        assertTrue(sword.checkWeaponUsable());
        sword.attemptMeleeAttack();
        assertTrue(sword.checkWeaponUsable());
        sword.attemptMeleeAttack();
        assertTrue(sword.checkWeaponUsable());
        sword.attemptMeleeAttack();
        assertTrue(sword.checkWeaponUsable());
        sword.attemptMeleeAttack();
        assertFalse(sword.checkWeaponUsable());
    }
}
