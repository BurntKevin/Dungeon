package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {

    private int keyID;
    private boolean locked; 
    private SimpleBooleanProperty view;

    public Door(int x, int y, int keyID) {
        super(x, y);
        this.keyID = keyID;
        this.locked = true;
        this.view = new SimpleBooleanProperty(true);
    }

    /**
     * 
     * @param currKeyID integer corresponding to current key
     * @return boolean indicating whether door was opened successfully
     */
    public boolean attemptUnlock(Key currKey) {
        if (keyID == currKey.getKeyID()) {
            locked = false;
            doorOpened().set(false);
            return true;
        }
        return false;
    }

    public SimpleBooleanProperty doorOpened() {
        return view;
    }

    public boolean checkOpen() {
        return (! locked);
    }
}