package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The player entity
 */
public class Player extends Entity {
    private Dungeon dungeon;    
    private String facingDir; // facingDir := "Left" | "Right" | "Up" | "Down", forms part of extension so animation can be done later
    private Sword melee;
    private Bow ranged;
    private Potion invStatus;
    private Key key;
    private SimpleBooleanProperty view;
    private boolean alive;

    /**
     * Create a player positioned in square (x,y)
     * @param dungeon Dungeon which player lives in
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.melee = new Sword();
        this.ranged = new Bow(dungeon);
        this.key = new Key();
        this.invStatus = new Potion();
        this.facingDir = "Right";
        this.view = new SimpleBooleanProperty(true);
        this.alive = true;
    }

    /**
     * Player move up action
     */
    public void moveUp() {
        // Checking if player is still alive
        if (alive) {
            // Get next tile
            Entity nextTile = dungeon.getItem(getX(), getY() - 1);

            // Setting player status
            invStatus.minusInvTimer();

            // Moving player
            if (nextTile instanceof Door) {
                if (enterDoor((Door) nextTile))
                y().set(getY() - 1);
            }
            else if (nextTile instanceof Boulder) {
                Entity next = dungeon.getItem(getX(), getY() - 2);
                if (pushBoulder((Boulder) nextTile, next, "Up")) {
                    y().set(getY() - 1);
                }
            }
            else if (getY() > 0 && ! (nextTile instanceof Wall)) {
                y().set(getY() - 1);

                if (nextTile instanceof PickUp) {
                    pickUpItem((PickUp) nextTile);
                } else if (nextTile instanceof Portal) {
                    portalTeleport((Portal) nextTile);
                } else if (nextTile instanceof Exit) {
                    finishGame((Exit) nextTile);
                }
            }

            // Updating player status
            facingDir = "Up";
        }
    }

    /**
     * Player move down action
     */
    public void moveDown() {
        if (alive) {
            // Obtaining next tile
            Entity nextTile = dungeon.getItem(getX(), getY() + 1);

            // Setting player status
            invStatus.minusInvTimer();

            // Moving player
            if (nextTile instanceof Door) {
                if (enterDoor((Door) nextTile))
                y().set(getY() + 1);
            }
            else if (nextTile instanceof Boulder) {
                Entity next = dungeon.getItem(getX(), getY() + 2);
                if (pushBoulder((Boulder) nextTile, next, "Down")) {
                    y().set(getY() + 1); // move player as well
                }
            }
            else if (getY() < dungeon.getHeight() - 1 && ! (nextTile instanceof Wall)) {
                y().set(getY() + 1);

                if (nextTile instanceof PickUp) {
                    pickUpItem((PickUp) nextTile);
                } else if (nextTile instanceof Portal) {
                    portalTeleport((Portal) nextTile);
                } else if (nextTile instanceof Exit) {
                    finishGame((Exit) nextTile);
                }
            }

            // Updating player status
            facingDir = "Down";
        }
    }

    /**
     * Player move left action
     */
    public void moveLeft() {
        if (alive) {
            // Obtaining action
            Entity nextTile = dungeon.getItem(getX() - 1, getY());

            // Updating status
            invStatus.minusInvTimer();

            // Moving player
            if (nextTile instanceof Door) {
                if (enterDoor((Door) nextTile))
                x().set(getX() - 1);
            }
            else if (nextTile instanceof Boulder) {
                Entity next = dungeon.getItem(getX() - 2, getY());
                if (pushBoulder((Boulder) nextTile, next, "Left")) {
                    x().set(getX() - 1); // move player as well
                }
            }
            else if (getX() > 0 && ! (nextTile instanceof Wall)) {
                x().set(getX() - 1);

                if (nextTile instanceof PickUp) {
                    pickUpItem((PickUp) nextTile);
                } else if (nextTile instanceof Portal) {
                    portalTeleport((Portal) nextTile);
                } else if (nextTile instanceof Exit) {
                    finishGame((Exit) nextTile);
                }
            }

            // Updating player status
            facingDir = "Left";
        }
    }

    /**
     * Moving the player right
     */
    public void moveRight() {
        if (alive) {
            // Setting tile
            Entity nextTile = dungeon.getItem(getX() + 1, getY());

            // Updating player
            invStatus.minusInvTimer();

            // Moving player
            if (nextTile instanceof Door) {
                if (enterDoor((Door) nextTile))
                x().set(getX() + 1);
            }
            else if (nextTile instanceof Boulder) {
                Entity next = dungeon.getItem(getX() + 2, getY());
                if (pushBoulder((Boulder) nextTile, next, "Right")) {
                    x().set(getX() + 1); // move player as well
                }
            }
            else if (getX() < dungeon.getWidth() - 1 && ! (nextTile instanceof Wall)) {
                x().set(getX() + 1);

                if (nextTile instanceof PickUp) {
                    pickUpItem((PickUp) nextTile);
                } else if (nextTile instanceof Portal) {
                    portalTeleport((Portal) nextTile);
                } else if (nextTile instanceof Exit) {
                    finishGame((Exit) nextTile);
                }
            }

            // Updating player status
            facingDir = "Right";
        }
    }

    /**
     * Player entering a door
     * @param nextDoor
     * @return Success of entering a door (Boolean)
     */
    private boolean enterDoor(Door nextDoor) {
        if (nextDoor.checkOpen()) {
            return true;
        }
        else {
            if (nextDoor.attemptUnlock(key)) {
                key.useKey();
                return true;
            }
        }
        return false;
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

    /**
     * Returns if the player is invincible
     * @return
     */
    public boolean isInvincible() {
        return invStatus.checkPotionActive();
    }

    /**
     * 
     * @param b Boulder
     * @param behind Entity behind
     * @param pushDir Pushing direction
     * @return Success of push (boolean)
     */
    public boolean pushBoulder(Boulder b, Entity behind, String pushDir) {
        return b.attemptPush(behind, pushDir);
    }

    /**
     * Fires a bow shot
     */
    public void fireRanged() {
        ranged.fire(super.getX(), super.getY(), facingDir);
    }

    /**
     * Used when the player is attacked
     * @return boolean whether player's counteratk was successful (false indicates game over)
     */
    public boolean attacked() {
        // Has a potion
        if (invStatus.checkPotionActive()) {
            return true;
        }
        // Has a weapon to defend
        else if (melee.attemptMeleeAttack()) {
            return true;
        }

        // Updating status of player as they died
        death().set(false);
        alive = false;

        return false;
    }

    /**
     * Removes a player from the front end
    */
    public SimpleBooleanProperty death() {
        return view;
    }

    public void pickUpItem(PickUp item) {

        Item curr = item.getItemFromPickUp();

        if (curr instanceof Sword) {
            if (! melee.checkWeaponUsable()) {
                // Able to pick up a new weapon
                melee.addNewSword();
                dungeon.logItem(item);
                dungeon.removeEntity(item);
                item.confirmPickedUp().set(false);
            }
        }
        else if (curr instanceof Bow) {
            if (! ranged.checkWeaponUsable()) {
                // Able to pick up a new weapon
                ranged.addNewBow();
                dungeon.logItem(item);
                dungeon.removeEntity(item);
                item.confirmPickedUp().set(false);
            }
        }
        else if (curr instanceof Potion) {
            if (! invStatus.checkPotionActive()) {
                // TODO: Activate invincibility visual effect
                invStatus.usePotion();
                dungeon.removeEntity(item);
                item.confirmPickedUp().set(false);
            }
        } 
        else if (curr instanceof Key) {
            if (! key.checkCarryingKey()) {
                key.equipKey((Key) curr);
                dungeon.removeEntity(item);
                item.confirmPickedUp().set(false);
            }
        }
        else if (curr instanceof Treasure) {
            dungeon.logItem(item);
            dungeon.removeEntity(item);
            item.confirmPickedUp().set(false);
        }
        System.out.println("Pickup function called");
    }

    /**
     * Player tries to finish the game through the exit
     * @param exit Exit of game
     */
    private void finishGame(Exit exit) {
        exit.enter();
    }

    /**
     * Returns the sword of the player
     */
    public Sword getSword() {
        return melee;
    }

    public ArrayList<IntegerProperty> getInventory() {
        return new ArrayList<>();
    }
}
