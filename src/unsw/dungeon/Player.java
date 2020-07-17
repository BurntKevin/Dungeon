package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Weapon weapon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.weapon = null;
    }

    public void moveUp() {
        Entity nextTile = dungeon.getItem(getX(), getY() - 1);
        if (getY() > 0 && ! (nextTile instanceof Wall))
            y().set(getY() - 1);

            if (nextTile instanceof PickUpItem) {
                pickUp((PickUpItem) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveDown() {
        Entity nextTile = dungeon.getItem(getX(), getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && ! (nextTile instanceof Wall))
            y().set(getY() + 1);

            if (nextTile instanceof PickUpItem) {
                pickUp((PickUpItem) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveLeft() {
        Entity nextTile = dungeon.getItem(getX() - 1, getY());
        if (getX() > 0 && ! (nextTile instanceof Wall))
            x().set(getX() - 1);

            if (nextTile instanceof PickUpItem) {
                pickUp((PickUpItem) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveRight() {
        Entity nextTile = dungeon.getItem(getX() + 1, getY());
        if (getX() < dungeon.getWidth() - 1 && ! (nextTile instanceof Wall))
            x().set(getX() + 1);

            if (nextTile instanceof PickUpItem) {
                pickUp((PickUpItem) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attacked() {
        if (! (weapon instanceof Weapon)) {
            System.out.println("Player died");
        } else {
            if (weapon.used()) {
                // Weapon has no more durability
                weapon = null;

                // TODO - Remove item from memory
            }
        }
    }

    public void pickUp(PickUpItem item) {
        System.out.println(item.getPickUpItem());
        if (item.getPickUpItem() instanceof Weapon && weapon == null) {
            System.out.println("Picking up weapon");
            setWeapon((Weapon) item.getPickUpItem());

            dungeon.logItem(item);
            dungeon.removeEntity(item);
            item.confirmPickedUp().set(false);
        } else if (item.getPickUpItem() instanceof Treasure) {
            dungeon.logItem(item);
            dungeon.removeEntity(item);
            item.confirmPickedUp().set(false);
        }
        System.out.println("Pickup function called");
    }

    private void finishGame(Exit exit) {
        exit.enter();
    }
}
