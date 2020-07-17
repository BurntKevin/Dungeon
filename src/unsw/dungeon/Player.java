package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;    
    private String faceDirection; // "Left" | "Right" | "Up" | "Down", forms part of extension so animation can be done later
    private Weapon melee;
    private Weapon ranged;
    private int invisTurnsLeft;
    private Log log;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.weapon = null;
        this.log = new Log();
    }

    public void moveUp() {
        Entity next_tile = dungeon.getItem(getX(), getY() - 1);
        if (getY() > 0 && ! (next_tile instanceof Wall))
            y().set(getY() - 1);

            if (next_tile instanceof PickUpItem) {
                pickUp((PickUpItem) next_tile);
            }
    }

    public void moveDown() {
        Entity next_tile = dungeon.getItem(getX(), getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && ! (next_tile instanceof Wall))
            y().set(getY() + 1);

            if (next_tile instanceof PickUpItem) {
                pickUp((PickUpItem) next_tile);
            }
    }

    public void moveLeft() {
        Entity next_tile = dungeon.getItem(getX() - 1, getY());
        if (getX() > 0 && ! (next_tile instanceof Wall))
            x().set(getX() - 1);

            if (next_tile instanceof PickUpItem) {
                pickUp((PickUpItem) next_tile);
            }
    }

    public void moveRight() {
        Entity next_tile = dungeon.getItem(getX() + 1, getY());
        if (getX() < dungeon.getWidth() - 1 && ! (next_tile instanceof Wall))
            x().set(getX() + 1);

            if (next_tile instanceof PickUpItem) {
                pickUp((PickUpItem) next_tile);
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
        Item curr = item.getPickUpItem();
        if (item.getPickUpItem() instanceof Weapon && weapon == null) {
            setWeapon((Weapon) item.getPickUpItem());
            log.logItem(item);
            dungeon.removeEntity(item);
        }
        else if (item.)
        } else if (item.getPickUpItem() instanceof Treasure) {
            log.logItem(item);
            dungeon.removeEntity(item);
        }
    }
}
