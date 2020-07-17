package unsw.dungeon;

public class Weapon implements Item {

    private String weaponType; // weaponType := "melee" | "ranged"
    private int usesLeft; 

    public Boolean useWeapon() {
        throw new IllegalArgumentException();
    }
    public String useWeapon(String facingDir) {
        throw new IllegalArgumentException();
    }

    public Boolean checkWeaponUsable() {
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }

}
