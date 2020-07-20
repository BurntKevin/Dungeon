package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Quest;


public class QuestTest {

    /**
     * Quest initalisation test
     */
    @Test
    public void initQuestTest() {
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

        // Creating quests
        new Quest("exit", dungeon);
        new Quest("treasure", dungeon);
        new Quest("boulders", dungeon);
        new Quest("enemies", dungeon);
        new Quest("test", dungeon);
    }

    /**
     * Testing successful quests
     */
    @Test
    public void successfulQuestsTest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "exit"));
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
        Quest quest2 = new Quest("treasure", dungeon);
        Quest quest3 = new Quest("boulders", dungeon);
        Quest quest4 = new Quest("enemies", dungeon);
        Quest quest5 = new Quest("test", dungeon);

        // Checking quest completion
        assertTrue(quest1.complete());
        assertTrue(quest2.complete());
        assertTrue(quest3.complete());
        assertTrue(quest4.complete());
        assertFalse(quest5.complete());
    }

    /**
     * Testing unsuccessful quests
     */
    @Test
    public void unsuccessfulQuestsTest() {
        // Setting up environment
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "exit"));
        entities.put(new JSONObject().put("x", 1).put("y", 0).put("type", "boulder"));
        entities.put(new JSONObject().put("x", 2).put("y", 0).put("type", "switch"));
        entities.put(new JSONObject().put("x", 3).put("y", 0).put("type", "gnome"));
        JSONObject input = new JSONObject()
                            .put("width", "4")
                            .put("height", "1")
                            .put("entities", entities)
                            .put("goal-condition", new JSONObject().put("goal", "exit"));

        // Obtaining dungeon
        TestLoader loader = new TestLoader(input);
        Dungeon dungeon = loader.load();

        // Creating quests
        Quest quest1 = new Quest("exit", dungeon);
        Quest quest2 = new Quest("boulders", dungeon);
        Quest quest3 = new Quest("enemies", dungeon);

        // Checking quest completion
        assertFalse(quest1.complete());
        assertFalse(quest2.complete());
        assertFalse(quest3.complete());
    }
}
