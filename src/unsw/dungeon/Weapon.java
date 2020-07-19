package unsw.dungeon;

public class Weapon implements Item {

    private String weaponType; // weaponType := "melee" | "ranged"
    private int usesLeft; 

    public boolean useWeapon() {
        throw new IllegalArgumentException();
    }
    public String useWeapon(String facingDir) {
        throw new IllegalArgumentException();
    }

    public boolean checkWeaponUsable() {
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }

}
