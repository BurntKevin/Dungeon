package unsw.dungeon;

/**
 * Exit quest to complete the dungeon
 */
public class TreasureQuest implements Mission {
    private Dungeon dungeon;

    /**
     * Initalises a quest
     * @param dungeon
     */
    public TreasureQuest(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Boolean complete() {
        return dungeon.getTreasureLeft() == 0;
    }

    public String descript() {
        return "Collect all treasure";
    }
}
