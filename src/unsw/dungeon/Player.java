package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Log log;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.log = new Log();
    }

    public void moveUp() {
        Entity next_tile = dungeon.getItem(getX(), getY() - 1);
        if (getY() > 0 && ! (next_tile instanceof Wall))
            y().set(getY() - 1);

            if (next_tile instanceof Item) {
                pickUp((Item) next_tile);
            }
    }

    public void moveDown() {
        Entity next_tile = dungeon.getItem(getX(), getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && ! (next_tile instanceof Wall))
            y().set(getY() + 1);

            if (next_tile instanceof Item) {
                pickUp((Item) next_tile);
            }
    }

    public void moveLeft() {
        Entity next_tile = dungeon.getItem(getX() - 1, getY());
        if (getX() > 0 && ! (next_tile instanceof Wall))
            x().set(getX() - 1);

            if (next_tile instanceof Item) {
                pickUp((Item) next_tile);
            }
    }

    public void moveRight() {
        Entity next_tile = dungeon.getItem(getX() + 1, getY());
        if (getX() < dungeon.getWidth() - 1 && ! (next_tile instanceof Wall))
            x().set(getX() + 1);

            if (next_tile instanceof Item) {
                pickUp((Item) next_tile);
            }
    }

    public void pickUp(Item item) {
        if (item.pickUp() == true) {
            dungeon.removeEntity(item);

            log.logItem(item);
        }
    }
}
