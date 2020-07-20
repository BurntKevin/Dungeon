/**
 * Currently not fully implemented
 * Improvements: Use more of Weapon superclass
 */

package unsw.dungeon;

/**
 * Allows the player to shoot long range
 */
public class Bow extends Weapon {
    private int usesLeft;

    /**
     * Empty initaliser for a broken bow
     */
    public Bow() {
        usesLeft = 0;
    }

    /**
     * Resets bow by adding two new uses
     * Typically called when a new bow is picked up
     */
    public void addNewBow() {
        assert usesLeft == 0;
        usesLeft = 2;
    }

    /**
     * Currently a stub
     * The bow is used to shoot a shot if possible
     * @param facingDir Direction the arrow will go
     * @return N/A
     */
    public String attemptRangedAttack(String facingDir) {
        usesLeft--;
        // TODO - implement ranged attack
        throw new IllegalArgumentException();
    }

    // /**
    //  * Currently a stub
    //  * Checks whether the bow shot hit a target
    //  * @return The hit status of the shot
    //  */
    // private boolean checkHitTarget() {
    //     // TODO - implement function to determine target
    //     return true;
    // }

    /**
     * Checks if a weapon is 
     */
    public boolean checkWeaponUsable() {
        // Checks if a weapon is usable
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Update weapon to be used
     */
    public boolean useWeapon() {
        usesLeft--;

        if (usesLeft <= 0) {
            return true;
        }
        return false;
    }
}
