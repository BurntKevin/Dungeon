package unsw.dungeon;

public class Boulder extends Entity {

    public Boulder(int x, int y) {
        super(x, y);
    }

    /**
     * 
     * @param next
     * @param moveDir
     * @return
     */
    public boolean attemptPush(Entity next, String moveDir) {
        if (next instanceof Wall || next instanceof Boulder 
            || next instanceof Door || next instanceof Door) {
            return false;
        }
        
        // update coordinates if can be moved
        if (moveDir.equals("Left")) {
            x().set(getX()-1);
        }
        else if (moveDir.equals("Right")) {
            x().set(getX()+1);
        }
        else if (moveDir.equals("Up")) {
            y().set(getY()-1);
        }
        else if (moveDir.equals("Down")) {
            y().set(getY()+1);
        }
        return true;
    }
}