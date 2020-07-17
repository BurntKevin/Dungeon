package unsw.dungeon;

public class Sword implements Weapon, Item {
    private int durability;
    
    public Sword() {
        this.durability = 5;
    }

    public Boolean used() {
        durability--;
        
        if (durability <= 0) {
            return true;
        }
        return false;
    }
}
