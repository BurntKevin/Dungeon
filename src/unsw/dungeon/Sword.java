package unsw.dungeon;

/**
 * Checks if a Sword is in use
 */
public class Sword extends Weapon {
    private int usesLeft;
    
    /**
     * Default: initialize sword with no uses, as player does not yet have
     * a usable weapon
     */
    public Sword() {
        this.usesLeft = 0;
    }

    /**
     * Resets sword by adding two new uses
     */
    public void addNewSword() {
        assert usesLeft == 0;
        usesLeft = 5;
    }

    /**
     * Use weapon for attack if possible
     * @return boolean indicating whether attack suceeded
     */
    public boolean attemptMeleeAttack() {
        // Attempting attack
        if (checkWeaponUsable()) {
            // Weapon can be used
            usesLeft--;
            return true;
        }
        return false;
    }

    /**
     * Determine whether a usable weapon is equipped
     * @return boolean indicating whether another sword may be picked up
     */
    public boolean checkWeaponUsable() {
        return usesLeft > 0;
    }
}
