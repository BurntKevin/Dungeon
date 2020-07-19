package unsw.dungeon;

public class LostGnome extends Enemy {
    public LostGnome(int x, int y, Dungeon dungeon) {
        super(x, y, 200, new RandomMovement(dungeon));
    }

    public void move() {
        super.move();
    }
}
