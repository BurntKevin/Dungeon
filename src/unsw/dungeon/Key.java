package unsw.dungeon;

public class Key implements Item {
    boolean carryingKey;
    int keyID;

    public Key() {
        carryingKey = false; 
        keyID = -1;
    }

    public void equipKey(Key nextKey) {
        keyID = nextKey.getKeyID();
        carryingKey = true;
    }

    public boolean checkCarryingKey() {
        return carryingKey;
    }

    public int getKeyID() {
        return keyID; 
    }

    public void useKey() {
        carryingKey = false;
    }
}