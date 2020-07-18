package unsw.dungeon;

public class Quest implements Mission {
    private String goal;
    private Dungeon dungeon;

    public Quest(String goal, Dungeon dungeon) {
        this.goal = goal;
        this.dungeon = dungeon;
    }

    // Assumes that quest is only called when player at exit - # TODO probably will need to change in the future
    public Boolean complete() {
        if (goal.equals("exit")) {
            return true;
        } else if (goal.equals("treasure")) {
            return dungeon.getTreasureLeft() == 0;
        }
        return false;
    }
}
