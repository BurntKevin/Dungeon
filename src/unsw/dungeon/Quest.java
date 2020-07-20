package unsw.dungeon;

/**
 * A simple quest
 */
public class Quest implements Mission {
    private String goal;
    private Dungeon dungeon;

    /**
     * Initalises a quest
     * @param goal
     * @param dungeon
     */
    public Quest(String goal, Dungeon dungeon) {
        this.goal = goal;
        this.dungeon = dungeon;
    }

    /**
     * Invariant: Assumes player at exit
     */
    public Boolean complete() {
        if (goal.equals("exit")) {
            return true;
        } else if (goal.equals("treasure")) {
            return dungeon.getTreasureLeft() == 0;
        } else if (goal.equals("boulders")) {
            return dungeon.allSwitchesActivated();
        } else if (goal.equals("enemies")) {
            return dungeon.enemiesRemaining() == 0;
        }
        System.out.println("Unknown case" + goal);
        return false;
    }
}
