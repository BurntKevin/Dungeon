package unsw.dungeon;

import java.util.ArrayList;

public class OrQuest implements Mission {
    private ArrayList<Mission> quests;

    public OrQuest(ArrayList<Mission> quests) {
        this.quests = quests;
    }

    public void addQuest(Mission quest) {
        quests.add(quest);
    }

    public Boolean complete() {
        for (Mission m : quests) {
            if (m.complete() == true) {
                return true;
            }
        }
        return false;
    }
}