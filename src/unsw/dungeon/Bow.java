package unsw.dungeon;

public class Bow implements Weapon, Item {
    int usesLeft;

    public Bow() {
        this.usesLeft = 2;
    }

    public Boolean useWeapon() {
        usesLeft--;
        
        if (usesLeft <= 0) {
            return true;
        }
        return false;
    }

}