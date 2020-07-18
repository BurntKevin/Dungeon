package unsw.dungeon;


/**
 * An item entity which co-ordinates and a visual icon,
 * may be picked up by the player
 */
public class PickUp extends Entity {
    private Item obtainedItem;
    private String itemID;

    public PickUp(int x, int y, Item item, String itemID) {
        super(x, y);
        this.obtainedItem = item;
        this.itemID = itemID;
    }

    public Item getItemFromPickUp() {
        return obtainedItem;
    }

    public String getItemID() {
        return itemID; 
    }
}
