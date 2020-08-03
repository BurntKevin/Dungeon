package unsw.dungeon;

/**
 * Exit quest to complete the dungeon
 */
public class EnemyQuest implements Mission {
    private Dungeon dungeon;

    /**
     * Initalises a quest
     * @param dungeon
     */
    public EnemyQuest(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Boolean complete() {
        return dungeon.enemiesRemaining() == 0;
    }

    public String descript() {
        return "Defeat all enemies";
    }
}
