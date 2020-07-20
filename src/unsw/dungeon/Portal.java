package unsw.dungeon;

import java.util.ArrayList;

/**
 * Teleports entities across the map
 */
public class Portal extends Entity {
    private int portID;

    /**
     * Initalises a portal
     * @param x x-coordinate
     * @param y y-coordinate
     * @param id Portal identification number
     */
    public Portal(int x, int y, int id) {
        super(x, y);
    }

    /**
     * Finds the matching portal
     * @param allPortals
     * @return
     */
    public Portal pairedPortal(ArrayList<Portal> allPortals) {
        // For all portals
        for (Portal p : allPortals) {
            // Find a potential matching portal
            if (checkPortalsMatch(p)) {
                // Found portal
                return p;
            }
        }

        // Could not find portal
        return new Portal(-1, -1, -1);
    }

    /**
     * Checks if a portal matches another portal
     * @param p Portal
     * @return Match status (boolean)
     */
    public boolean checkPortalsMatch(Portal p) {
        if (portID == p.getPortID()) {
            if (p.getEntryX() != getX() || p.getEntryY() != getY())
            return true;
        }
        return false;
    }

    /**
     * Obtains the portal identification
     * @return portID (int)
     */
    public int getPortID() { 
        return portID;
    }

    /**
     * Finds the x coordinate
     * @return x-coordinate (int)
     */
    public int getEntryX() {
        return getX();
    }

    /**
     * Finds the y coordinate
     * @return y-coordinate (int)
     */
    public int getEntryY() {
        return getY();
    }
}