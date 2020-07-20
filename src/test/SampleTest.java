package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Player;

public class SampleTest {
    @Test
    public void blahTest() {
        JSONArray entities = new JSONArray();
        entities.put(new JSONObject().put("x", 0).put("y", 0).put("type", "player"));
        JSONObject input = new JSONObject()
                               .put("width", "1")
                               .put("height", "1")
                               .put("entities", entities)
                               .put("goal-condition", new JSONObject().put("goal", "exit"));

        System.out.println(input);

        //TestLoader loader = new TestLoader(input);
        TestLoader loader = new TestLoader(input);
        Player player = loader.getPlayer();
        System.out.println(player);
    }
}
