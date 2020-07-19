package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public class CamoGnome extends Enemy {
    public CamoGnome(int x, int y, Dungeon dungeon) {
        super(x, y, 50, new TowardsPlayerMovement(dungeon));
    }

    public void move() {
        super.move();
    }

    public void attacked() {
        super.attacked().set(false);
    }
}
