package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Creates a fast but randomly moving enemy
 */
public class LostGnome extends Enemy {
    /**
     * Initalising lost gnome
     * @param x x-coordinate
     * @param y y-coordinate
     * @param dungeon Dungeon which gnome lives in
     */
    public LostGnome(int x, int y, Dungeon dungeon) {
        super(x, y, 200, new RandomMovement(dungeon));
    }

    /**
     * Make gnome move
     */
    public void move() {
        super.move();
    }

    /**
     * Remove gnome from front end
     */
    public void attacked() {
        super.attacked().set(false);
    }
}
