package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public class Gnome extends Enemy {
    public Gnome(int x, int y, Dungeon dungeon) {
        super(x, y, 100, new TowardsPlayerMovement(dungeon));
    }

    public void move() {
        super.move();
    }

    public void attacked() {
        super.attacked().set(false);
    }
}
