package unsw.dungeon;

public class Potion implements Item {
    private int invisTurnsLeft;

    public Potion() { 
        invisTurnsLeft = 0;
    }

    public void usePotion() {
        invisTurnsLeft = 10;
    }
    
    public void minusInvisTimer() {
        invisTurnsLeft = invisTurnsLeft-1;
    }

    public Boolean checkPotionActive() {
        if (invisTurnsLeft <= 0) {
            return false;
        }
        return true;
    }
}