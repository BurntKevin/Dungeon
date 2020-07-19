package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public class LostGnome extends Enemy {
    public LostGnome(int x, int y, Dungeon dungeon) {
        super(x, y, 200, new RandomMovement(dungeon));
    }

    public void move() {
        super.move();
    }

    public void attacked() {
        super.attacked().set(false);
    }
}
