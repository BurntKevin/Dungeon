package unsw.dungeon;

/**
 * Potion for the user to use
 */
public class Potion implements Item {
    private int invTurnsLeft;

    /**
     * Initalises a potion
     */
    public Potion() { 
        invTurnsLeft = 0;
    }

    /**
     * Activates a potion
     */
    public void usePotion() {
        invTurnsLeft = 10;
    }

    /**
     * Potion expiring
     */
    public void minusInvTimer() {
        invTurnsLeft = invTurnsLeft-1;
    }

    /**
     * Check if the potion has been fully used
     * @return
     */
    public boolean checkPotionActive() {
        if (invTurnsLeft <= 0) {
            return false;
        }
        return true;
    }
}