/**
 * Currently not fully implemented
 * Improvements: Use more of Weapon superclass
 */

package unsw.dungeon;

/**
 * Allows the player to shoot long range
 */
public class Bow extends Weapon {
    private int usesLeft;
    private Dungeon dungeon;

    /**
     * Empty initaliser for a broken bow
     */
    public Bow(Dungeon dungeon) {
        usesLeft = 0;
        this.dungeon = dungeon;
    }

    /**
     * Resets bow by adding two new uses
     * Typically called when a new bow is picked up
     */
    public void addNewBow() {
        usesLeft = 2;
    }

    /**
     * Checks if a weapon is 
     */
    public boolean checkWeaponUsable() {
        // Checks if a weapon is usable
        if (usesLeft <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Update weapon to be used
     */
    public boolean useWeapon() {
        usesLeft--;

        if (usesLeft <= 0) {
            return true;
        }
        return false;
    }

    public void fire(int x, int y, String direction) {
        System.out.println("I want to shot " + direction);
        if (checkWeaponUsable()) {
            // Utilising weapon
            useWeapon();

            System.out.println("I shot");

            // Firing shot
           do {
                Enemy enemy = dungeon.getEnemy(x, y);
                if (enemy != null) {
                    dungeon.removeEntity(enemy);
                    enemy.attacked().set(false);
                    System.out.print("Killed enemy with bow");
                    break;
                }

                if (direction == "Up") {
                    y -= 1;
                } else if (direction == "Down") {
                    y += 1;
                } else if (direction == "Left") {
                    x -= 1;
                } else if (direction == "Right") {
                    x += 1;
                }
                System.out.println(x + " " + y);
            } while (dungeon.validArrowLocation(x, y));
        } else {
            dungeon.logDryFireRanged();
        }
    }
}
