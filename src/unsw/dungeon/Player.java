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
    private Potion invStatus;
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
        this.key = new Key();
        this.invStatus = new Potion();
        this.facingDir = "Right";
    }

    public void moveUp() {
        Entity nextTile = dungeon.getItem(getX(), getY() - 1);
        invStatus.minusInvTimer();
        if (getY() > 0 && ! (nextTile instanceof Wall))
            y().set(getY() - 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                portalTeleport((Portal) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveDown() {
        Entity nextTile = dungeon.getItem(getX(), getY() + 1);
        invStatus.minusInvTimer();
        if (getY() < dungeon.getHeight() - 1 && ! (nextTile instanceof Wall))
            y().set(getY() + 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                portalTeleport((Portal) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveLeft() {
        Entity nextTile = dungeon.getItem(getX() - 1, getY());
        invStatus.minusInvTimer();
        if (getX() > 0 && ! (nextTile instanceof Wall))
            x().set(getX() - 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                portalTeleport((Portal) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    public void moveRight() {
        Entity nextTile = dungeon.getItem(getX() + 1, getY());
        invStatus.minusInvTimer();
        if (getX() < dungeon.getWidth() - 1 && ! (nextTile instanceof Wall))
            x().set(getX() + 1);

            if (nextTile instanceof PickUp) {
                pickUpItem((PickUp) nextTile);
            } else if (nextTile instanceof Portal) {
                portalTeleport((Portal) nextTile);
            } else if (nextTile instanceof Exit) {
                finishGame((Exit) nextTile);
            }
    }

    private void setPosition(int teleX, int teleY) {
        x().set(teleX);
        y().set(teleY);
    }

    /**
     * Updates the player's position after they have travelled through given portal
     * @param EntryPortal portal which is entered by player
     */
    private void portalTeleport(Portal EntryPortal) {
        for (Portal p: dungeon.getPortals()) {
            if (EntryPortal.checkPortalsMatch(p)) {
                setPosition(p.getEntryX(), p.getEntryY());
            }
        }
    }

    public boolean isInvisible() {
        return invStatus.checkPotionActive();
    }

    public void fireRanged() {
        // TODO - add
    }

    /**
     * 
     * @return boolean whether player's counteratk was successful (false indicates game over)
     */
    public boolean attacked() {
        
        if (invStatus.checkPotionActive()) {
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
            if (! invStatus.checkPotionActive()) {
                // activate invincibility visual effect
            }
            invStatus.usePotion();
        } 
        else if (curr instanceof Key) {
            if (! key.checkCarryingKey()) {
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
