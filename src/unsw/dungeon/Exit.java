package unsw.dungeon;

import java.util.ArrayList;

/**
 * Exit of dungeon
 */
public class Exit extends Entity {
    private ArrayList<Mission> quests;

    /**
     * Initalises Exit
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Exit(int x, int y) {
        super(x, y);
        quests = new ArrayList<Mission>();
    }

    /**
     * Adds a mission for an exit
     * @param mission Mission
     */
    public void addMission(Mission mission) {
        quests.add(mission);
    }

    /**
     * Checks if a player is allowed to exit dungeon
     */
    public void enter() {
        // Checking if sufficient quests are satisfised
        for (Mission m : quests) {
            if (m.complete() == false) {
                // Game not completed yet
                System.out.println("Not complete yet");
                return;
            }
        }
        System.out.println("Game over - finished");
    }
}