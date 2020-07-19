package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * An item entity which co-ordinates and a visual icon,
 * may be picked up by the player
 */
public class PickUp extends Entity {
    private Item obtainedItem;
    private String itemID; // itemID := "Sword" | "Bow" | "Potion" | "Treasure"
    private SimpleBooleanProperty view;

    public PickUp(int x, int y, Item item, String itemID) {
        super(x, y);
        this.obtainedItem = item;
        this.itemID = itemID;
        this.view = new SimpleBooleanProperty(true);
    }

    public Item getItemFromPickUp() {
        return obtainedItem;
    }

    public String getItemID() {
        return itemID; 
    }

    public SimpleBooleanProperty confirmPickedUp() {
        return view;
    }
}
