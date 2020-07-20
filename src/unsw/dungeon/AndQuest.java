package unsw.dungeon;

import java.util.ArrayList;

/**
 * A type of quest where all quests need to be completed
 */
public class AndQuest implements Mission {
    private ArrayList<Mission> quests;

    /**
     * Initalises AndQuest with missions to complete
     * @param quests Missions to be done
     */
    public AndQuest(ArrayList<Mission> quests) {
        this.quests = quests;
    }

    /**
     * Adds a quest to the list of quests to complete
     * @param quest Mission to be done
     */
    public void addQuest(Mission quest) {
        quests.add(quest);
    }

    /**
     * Checks if the quest has been complete
     */
    public Boolean complete() {
        for (Mission m : quests) {
            if (m.complete() != true) {
                return false;
            }
        }
        return true;
    }
}
