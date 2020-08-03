package unsw.dungeon;

import java.util.ArrayList;

public class AllQuests implements Mission {
    private static AllQuests instance = null;
    private ArrayList<Mission> quests;

    public AllQuests(ArrayList<Mission> quests) {
        this.quests = quests;
    }

    public static synchronized AllQuests createInstance(ArrayList<Mission> quests) {
        System.out.println(quests.size() + "COOL");
        instance = new AllQuests(quests);
        return instance;
    }

    public static synchronized AllQuests getInstance() {
        System.out.println(instance);
        return instance;
    }

    public Boolean complete() {
        for (Mission m : quests) {
            System.out.println(m.complete());
            if (!m.complete()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Mission> getQuests() {
        return quests;
    }
}
