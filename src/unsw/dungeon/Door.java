package unsw.dungeon;

public class Door extends Entity {

    private int keyID;
    private boolean locked; 

    public Door(int x, int y, int keyID) {
        super(x, y);
        this.keyID = keyID;
        locked = true;
    }

    /**
     * 
     * @param currKeyID integer corresponding to current key
     * @return boolean indicating whether door was opened successfully
     */
    public boolean attemptUnlock(Key currKey) {
        if (keyID == currKey.getKeyID()) {
            locked = false;
            return true;
        }
        return false;
    }

    public boolean checkOpen() {
        return (! locked);
    }
}