package unsw.dungeon;

public abstract class Enemy extends Entity {
    private int speed;
    private int chargedSpeed;
    private MovementType movementType;

    public Enemy(int x, int y, int speed, MovementType movementType) {
        super(x, y);
        this.speed = speed;
        this.chargedSpeed = 0;
        this.movementType = movementType;
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public void move() {
        // Updating to new turn
        chargedSpeed += speed;
        
        // Checking if a move is required
        while (chargedSpeed >= 100) {
            int[] shift = movementType.move(super.getX(), super.getY());
            super.x().set(super.getX() + shift[0]);
            super.y().set(super.getY() + shift[1]);
            chargedSpeed -= 100;
        }
    }
}
