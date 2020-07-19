package unsw.dungeon;

public class Bow extends Weapon {
    private int usesLeft;

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

    public String attemptRangedAttack(String facingDir) {
        usesLeft--;
        // TODO - implement ranged attack
        throw new IllegalArgumentException();
    }

    private boolean checkHitTarget() {
        // TODO - implement function to determine target
        return true;
    }

    public boolean checkWeaponUsable() {
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }
    
    public boolean useWeapon() {
        usesLeft--;
        
        if (usesLeft <= 0) {
            return true;
        }
        return false;
    }

}