/**
 * Currently not fully implemented
 */

package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Allows the player to shoot long range
 */
public class Bow extends Weapon {
    
    private IntegerProperty usesLeft;
    private Dungeon dungeon;

    /**
     * Empty initaliser for a broken bow
     */
    public Bow(Dungeon dungeon) {
        usesLeft = new SimpleIntegerProperty(0);
        this.dungeon = dungeon;
    }

    /**
     * Resets bow by adding two new uses
     * Typically called when a new bow is picked up
     */
    public void addNewBow() {
        usesLeft.setValue(2);
    }

    /**
     * Checks if a weapon is 
     */
    public boolean checkWeaponUsable() {
        // Checks if a weapon is usable
        if (usesLeft.getValue() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Update weapon to be used
     */
    public boolean useWeapon() {
        usesLeft.setValue(usesLeft.getValue()-1);

        if (usesLeft.getValue() <= 0) {
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
    
    public IntegerProperty getUsesProperty() {
        return usesLeft;
    }
}
