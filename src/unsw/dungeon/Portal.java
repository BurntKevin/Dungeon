package unsw.dungeon;

import java.util.ArrayList;

public class Portal extends Entity {

    private int portID;

    public Portal(int x, int y, int id) {
        super(x, y);
    }

    public Portal pairedPortal(ArrayList<Portal> allPortals) {
        for (Portal p : allPortals) {
            if (checkPortalsMatch(p)) {
                return p;
            }
        }
        return new Portal(-1,-1,-1); // in error case
    }

    public boolean checkPortalsMatch(Portal p) {
        if (portID == p.getPortID()) {
            if (p.getEntryX() != getX() || p.getEntryY() != getY())
            return true;
        }
        return false;
    }

    public int getPortID() { 
        return portID;
    }

    public int getEntryX() {
        return getX();
    }

    public int getEntryY() {
        return getY();
    }
}