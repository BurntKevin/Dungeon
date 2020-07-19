package unsw.dungeon;

import java.util.ArrayList;

public class Switch extends Entity {

    public Switch(int x, int y) {
        super(x, y);
    }
    
    public boolean checkActivated(ArrayList<Boulder> currBoulders) {
        for (Boulder b : currBoulders) {
            if (b.getX() == getX() && b.getY() == getY()) {
                return true;
            }
        }
        return false;
    }
}