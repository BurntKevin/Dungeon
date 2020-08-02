package unsw.dungeon;

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
        Player player = dungeon.getPlayer();
        return exit.getX() == player.getX() && exit.getY() == player.getY();
    }
}
