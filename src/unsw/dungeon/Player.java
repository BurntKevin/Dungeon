package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;    
    private String facingDir; // facingDir := "Left" | "Right" | "Up" | "Down", forms part of extension so animation can be done later
    private Sword melee;
    private Bow ranged;
    private Potion invisStatus;
    private boolean keyObtained;
    private Log log;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.melee = new Sword();
        this.ranged = new Bow();
        this.log = new Log();
        this.keyObtained = false;
        this.invisStatus = new Potion();
        this.facingDir = "Right";
    }

    public void moveUp() {
        Entity next_tile = dungeon.getItem(getX(), getY() - 1);
        if (getY() > 0 && ! (next_tile instanceof Wall))
            y().set(getY() - 1);

            if (next_tile instanceof PickUp) {
                pickUpItem((PickUp) next_tile);
            }
    }

    public void moveDown() {
        Entity next_tile = dungeon.getItem(getX(), getY() + 1);
        if (getY() < dungeon.getHeight() - 1 && ! (next_tile instanceof Wall))
            y().set(getY() + 1);

            if (next_tile instanceof PickUp) {
                pickUpItem((PickUp) next_tile);
            }
    }

    public void moveLeft() {
        Entity next_tile = dungeon.getItem(getX() - 1, getY());
        if (getX() > 0 && ! (next_tile instanceof Wall))
            x().set(getX() - 1);

            if (next_tile instanceof PickUp) {
                pickUpItem((PickUp) next_tile);
            }
    }

    public void moveRight() {
        Entity next_tile = dungeon.getItem(getX() + 1, getY());
        if (getX() < dungeon.getWidth() - 1 && ! (next_tile instanceof Wall))
            x().set(getX() + 1);

            if (next_tile instanceof PickUp) {
                pickUpItem((PickUp) next_tile);
            }
    }

    public void fireRanged() {
        // TODO - add 
    }

    /**
     * 
     * @return boolean whether player's counteratk was successful (false indicates game over)
     */
    public boolean attacked() {
        
        if (invisStatus.checkPotionActive()) {
            return true;
        }
        else if (melee.attemptMeleeAttack()) { // usable weapon equipped
            return true;
        } 
        return false;
    }

    public void pickUpItem(PickUp item) {

        Item curr = item.getItemFromPickUp();

        if (curr instanceof Sword) {
            if (melee.checkWeaponUsable()) { // usable weapon already equipped 
                // don't pick up
            }
            else {
                melee.addNewSword(); // reset number of uses left
                log.logItem(item);
                dungeon.removeEntity(item);
            }
        }
        else if (curr instanceof Potion) {
            if (! invisStatus.checkPotionActive()) {
                // activate invisibility visual effect
            }
            invisStatus.usePotion();
        } 
        else if (curr instanceof Treasure) {
            log.logItem(item);
            dungeon.removeEntity(item);
        }
    }
}
