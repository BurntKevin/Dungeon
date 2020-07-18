package unsw.dungeon;

public class Portal extends Entity {

    // new teleport locations
    int toX;
    int toY;

    public Portal(int x, int y, int toX, int toY) {
        super(x, y);
        this.toX = toX;
        this.toY = toY;
    }

    public int getTeleX() {
        return toX;
    }

    public int getTeleY() {
        return toY;
    }
}