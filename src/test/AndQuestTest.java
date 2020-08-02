package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.AndQuest;
import unsw.dungeon.Dungeon;


public class AndQuestTest {
    /**
     * AndQuest initalisation test
     */
    @Test
    public void initAddQuestTest() {
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

        // Creating quests
        Quest quest1 = new Quest("exit", dungeon);
        Quest quest2 = new Quest("enemies", dungeon);
        AndQuest quest3 = new AndQuest();
        quest3.addQuest(quest1);
        quest3.addQuest(quest2);
    }

    /**
     * Unsuccessful and quest
     */
    @Test
    public void unsuccessfulAndQuest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "exit"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "treasure"));
        JSONObject input = new JSONObject()
                            .put("width", "2")
                            .put("height", "0")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Creating quests
        Quest quest1 = new Quest("exit", dungeon);
        Quest quest2 = new Quest("treasure", dungeon);
        AndQuest quest3 = new AndQuest();
        quest3.addQuest(quest1);
        quest3.addQuest(quest2);

        // Checking if the quest is complete
        assertFalse(quest3.complete());
    }

    /**
     * Testing successful quests
     */
    @Test
    public void successfulAndQuestTest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "exit"));
        JSONObject input = new JSONObject()
                            .put("width", "2")
                            .put("height", "0")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Creating quests
        Quest quest1 = new Quest("exit", dungeon);
        Quest quest2 = new Quest("treasure", dungeon);
        AndQuest quest3 = new AndQuest();
        quest3.addQuest(quest1);
        quest3.addQuest(quest2);

        // Checking if the quest is complete
        assertTrue(quest3.complete());
    }
}
