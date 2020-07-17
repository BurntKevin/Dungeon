package unsw.dungeon;

import java.util.ArrayList;

public class Exit extends Entity {
    private ArrayList<Mission> quests;
    
    public Exit(int x, int y) {
        super(x, y);
        quests = new ArrayList<Mission>();
    }

    public void addMission(Mission mission) {
        quests.add(mission);
    }

    public void enter() {
        // Checking if sufficient quests are satisfised
        for (Mission m : quests) {
            if (m.complete() == false) {
                System.out.println("Not complete yet");
                return;
            }
        }
        System.out.println("Game over - finished");
    }
}