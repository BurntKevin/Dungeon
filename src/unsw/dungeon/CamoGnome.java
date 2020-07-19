package unsw.dungeon;

public class CamoGnome extends Enemy {
    public CamoGnome(int x, int y, Dungeon dungeon) {
        super(x, y, 50, new TowardsPlayerMovement(dungeon));
    }

    public void move() {
        super.move();
    }
}
