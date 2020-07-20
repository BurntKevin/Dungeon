package unsw.dungeon;

/**
 * General weapon class for weapons
 */
public class Weapon implements Item {
    private String weaponType; // weaponType := "melee" | "ranged"
    private int usesLeft; 

    /**
     * Used to attack
     * @return Success of attack
     */
    public boolean useWeapon() {
        // Todo for bow
        throw new IllegalArgumentException();
    }

    /**
     * Used to attack
     * @param facingDir Facing direction of player
     * @return Success of attack
     */
    public String useWeapon(String facingDir) {
        // Todo for bow
        throw new IllegalArgumentException();
    }

    /**
     * Checks if a waepon is usable
     * @return Status of swod (boolean)
     */
    public boolean checkWeaponUsable() {
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }
}
