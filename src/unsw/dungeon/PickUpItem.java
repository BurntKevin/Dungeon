package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public class PickUpItem extends Entity {
    private Item pickUpItem;
    private SimpleBooleanProperty view;

    public PickUpItem(int x, int y, Item item) {
        super(x, y);
        this.pickUpItem = item;
        this.view = new SimpleBooleanProperty(true);
    }

    public Item getItemFromPickup() {
        return pickUpItem;
    }

    public SimpleBooleanProperty confirmPickedUp() {
        return view;
    }
}
