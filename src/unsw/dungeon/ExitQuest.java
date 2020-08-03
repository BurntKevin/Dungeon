package unsw.dungeon;

import java.util.ArrayList;

/**
 * Exit quest to complete the dungeon
 */
public class ExitQuest implements Mission {
    private Dungeon dungeon;

    /**
     * Initalises a quest
     * @param dungeon
     */
    public ExitQuest(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Boolean complete() {
        Exit exit = dungeon.getExit();
        ArrayList<int[]> coordinates = dungeon.getPlayersCoordinates();

        // Checking if a player has finished the game
        for (int[] c : coordinates) {
            if (c[0] == exit.getX() && c[1] == exit.getY()) {
                return true;
            }
        }

        // Mission not complete
        return false;
    }
}
