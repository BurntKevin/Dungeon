package unsw.dungeon;

import java.util.ArrayList;

public class AndQuest implements Mission {
    private ArrayList<Mission> quests;

    public AndQuest(ArrayList<Mission> quests) {
        this.quests = quests;
    }

    public void addQuest(Mission quest) {
        quests.add(quest);
    }

    public Boolean complete() {
        for (Mission m : quests) {
            if (m.complete() != true) {
                return false;
            }
        }
        return true;
    }
}
