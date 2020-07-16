package unsw.dungeon;


public class PickUpItem extends Entity {
    private Item pickUpItem;

    public PickUpItem(int x, int y, Item item) {
        super(x, y);
        this.pickUpItem = item;
    }

    public Item getPickUpItem() {
        return pickUpItem;
    }
}
