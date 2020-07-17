package unsw.dungeon;

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
        usesLeft = 2;
    }

    /**
     * Use weapon for attack if possible
     * returns true/false depending on whether attack suceeded
     */
    public Boolean attemptMeleeAttack() {
        if (usesLeft <= 0) {
            return false;        
        }
        usesLeft--;
        return true;
    }

    public Boolean checkWeaponUsable() {
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }
}
