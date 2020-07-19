package unsw.dungeon;

public class Potion implements Item {
    private int invTurnsLeft;

    public Potion() { 
        invTurnsLeft = 0;
    }

    public void usePotion() {
        invTurnsLeft = 10;
    }
    
    public void minusInvTimer() {
        invTurnsLeft = invTurnsLeft-1;
    }

    public boolean checkPotionActive() {
        if (invTurnsLeft <= 0) {
            return false;
        }
        return true;
    }
}