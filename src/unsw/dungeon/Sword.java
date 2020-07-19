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
        usesLeft = 5;
    }

    /**
     * Use weapon for attack if possible
     * @return boolean indicating whether attack suceeded
     */
    public boolean attemptMeleeAttack() {
        if (usesLeft <= 0) {
            return false;        
        }
        usesLeft--;
        return true;
    }

    /**
     * Determine whether a usable weapon is equipped
     * @return boolean indicating whether another sword may be picked up
     */
    public boolean checkWeaponUsable() {
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }
}
