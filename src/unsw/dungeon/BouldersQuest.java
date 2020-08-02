package unsw.dungeon;

/**
 * Exit quest to complete the dungeon
 */
public class BouldersQuest implements Mission {
    private Dungeon dungeon;

    /**
     * Initalises a quest
     * @param dungeon
     */
    public BouldersQuest(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Boolean complete() {
        return dungeon.allSwitchesActivated();
    }
}
