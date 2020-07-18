package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

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
    private Key key;
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
        Entity nextTile = dungeon.getItem(getX(), getY() - 1);
        invisStatus.minusInvisTimer();
        if (getY() > 0 && ! (nextTile instanceof Wall))
            y().set(getY() - 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                setPosition(((Portal) nextTile).getTeleX(), ((Portal) nextTile).getTeleY());
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveDown() {
        Entity nextTile = dungeon.getItem(getX(), getY() + 1);
        invisStatus.minusInvisTimer();
        if (getY() < dungeon.getHeight() - 1 && ! (nextTile instanceof Wall))
            y().set(getY() + 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                setPosition(((Portal) nextTile).getTeleX(), ((Portal) nextTile).getTeleY());
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveLeft() {
        Entity nextTile = dungeon.getItem(getX() - 1, getY());
        invisStatus.minusInvisTimer();
        if (getX() > 0 && ! (nextTile instanceof Wall))
            x().set(getX() - 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                setPosition(((Portal) nextTile).getTeleX(), ((Portal) nextTile).getTeleY());
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveRight() {
        Entity nextTile = dungeon.getItem(getX() + 1, getY());
        invisStatus.minusInvisTimer();
        if (getX() < dungeon.getWidth() - 1 && ! (nextTile instanceof Wall))
            x().set(getX() + 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                setPosition(((Portal) nextTile).getTeleX(), ((Portal) nextTile).getTeleY());

            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void setPosition(int teleX, int teleY) {
        x().set(teleX);
        y().set(teleY);
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
                dungeon.logItem(item);
                dungeon.removeEntity(item);
                item.confirmPickedUp().set(false);
            }
        }
        else if (curr instanceof Potion) {
            if (! invisStatus.checkPotionActive()) {
                // activate invisibility visual effect
            }
            invisStatus.usePotion();
        } 
        else if (curr instanceof Key) {
            if (! key.carryingKey) {
                key.equipKey((Key) curr);
            }
        }
        else if (curr instanceof Treasure) {
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
