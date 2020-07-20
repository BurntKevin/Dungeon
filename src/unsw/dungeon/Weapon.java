package unsw.dungeon;

/**
 * General weapon class for weapons
 */
public abstract class Weapon implements Item {
    private int usesLeft;

    // /**
    //  * Creates a weapon class
    //  * @return
    //  */
    // public boolean Weapon(usesLeft) {
    //     this.usesLeft = usesLeft;
    // }

    /**
     * Used to attack
     * @return Success of attack
     */
    public boolean useWeapon() {
        if (checkWeaponUsable()) {
            usesLeft--;
            return true;
        }
        return false;
    }

    /**
     * Checks if a weapon is usable
     * @return Status of weapon (boolean)
     */
    public boolean checkWeaponUsable() {
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }
}
