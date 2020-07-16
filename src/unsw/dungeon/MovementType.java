package unsw.dungeon;

public interface MovementType {
    public MovementType(Dungeon dungeon);

    public int[] move(int x, int y);
}
