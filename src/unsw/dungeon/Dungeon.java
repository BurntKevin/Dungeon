package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Log log;

    /**
     * Initalises the Dungeon map
     * @param width Width of map
     * @param height Height of map
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
        this.player = null;
        this.log = new Log();
    }

    /**
     * Gets the width of the map
     * @return Width of map (int)
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the map
     * @return Height of map (int)
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the player of the dungeon
     * @return Player (Player)
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the new player of the dungeon
     * @param player Player (Player)
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Adds a new entity to the dungeon
     * @param entity Entity to add (Entity)
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Removes an entity from the dungeon
     * @param entity Entity to remove (Entity)
     */
    public void removeEntity(Entity entity) {
        // Todo - Remove from front end interface
        System.out.println("Removing entity");
        entities.remove(entity);
    }

    /**
     * Obtains a potential interactable item from the map given its coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Item on x, y
     */
    public Entity getItem(int x, int y) {
        // Checking all items
        for (Entity entity : entities) {
            // Checking if the item matches the desired coordinates
            if (entity.getX() == x && entity.getY() == y && ! (entity instanceof Switch)) {
                // Found item which can be interacted with
                return entity;
            }
        }

        // Could not find item
        return null;
    }

    /**
     * Obtains all enemies in the dungeon
     * @return List of enemies (ArrayList<Enemy>)
     */
    public ArrayList<Enemy> getEnemies() {
        // Creating a list of enemies
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        // For all entities
        for (Entity e : entities) {
            // Checking if the entity is an enemy
            if (e instanceof Enemy) {
                // Entity is an enemy
                enemies.add((Enemy) e);
            }
        }

        return enemies;
    }

    /**
     * Obtains portals
     * @return List of all portals (ArrayList<Portal>)
     */
    public ArrayList<Portal> getPortals() {
        // Initalising list of portals
        ArrayList<Portal> portals = new ArrayList<Portal>();

        // For all entities
        for (Entity e : entities) {
            // Checking if they are a portal
            if (e instanceof Portal) {
                // Found a portal
                portals.add((Portal) e);
            }
        }

        return portals;
    }

    /**
     * Obtains doors in dungeon
     * @return List of all doors (ArrayList<Door>)
     */
    public ArrayList<Door> getDoors() {
        // Initalising a list of doors
        ArrayList<Door> doors = new ArrayList<Door>();

        // For all entities
        for (Entity e : entities) {
            // Checking if they are a door
            if (e instanceof Door) {
                // Found a door
                doors.add((Door) e);
            }
        }

        return doors;
    }

    /**
     * Obtains the player's coordinates in a length 2 array
     * @return Coordinates ({x, y})
     */
    public int[] getPlayerCoordinates() {
        // Obtaining player's coordinates
        int[] coordinates = {player.getX(), player.getY()};

        return coordinates;
    }

    public boolean inDungeon(int x, int y) {
        return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
    }

    /**
     * Checks for possible moves a player can do given a coordinate
     * @param x x-coordinate at dungeon
     * @param y y-coordinate at dungeon
     * @return Positions moveable: [moveLeft, moveRight, moveUp, moveDown]
     */
    public Boolean[] validAdjacentMoves(int x, int y) {
        Boolean[] result = {x - 1 >= 0, x + 1 < width, y - 1 >= 0, y + 1 < height};

        // Checking all entities
        for (Entity entity : entities) {
            // Checking if there is an issue with it moving left or right
            if (entity instanceof Obstacle) {
                if (entity.getX() == x - 1 && entity.getY() == y) {
                    result[0] = false;
                } else if (entity.getX() == x + 1 && entity.getY() == y) {
                    result[1] = false;
                } else if (entity.getX() == x && entity.getY() == y - 1) {
                    result[2] = false;
                } else if (entity.getX() == x && entity.getY() == y + 1) {
                    result[3] = false;
                }
            }
        }

        return result;
    }

    /**
     * Obtains the dungeon as a 2D array
     * @return Dungeon as a 2D array (Entity[][])
     */
    public Entity[][] getBoard() {
        // Creating a board where every tile can be walked through
        Entity[][] board = new Entity[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = null;
            }
        }

        // Placing all entities
        for (Entity e: entities) {
            board[e.getX()][e.getY()] = e;
        }

        return board;
    }

    /**
     * Reports item activity occurring in the dungeon to a log
     * @param item Item which has been picked up
     */
    public void logItem(PickUp item) {
        log.logItem(item);
    }

    /**
     * Checks the remaining treasure in the Dungeon
     * @return Treasure remaining in the Dungeon
     */
    public int getTreasureLeft() {
        // Initalising treasure count
        int treasureCount = 0;

        // For all entities
        for (Entity e : entities) {
            // If it is a pickup item
            if (e instanceof PickUp) {
                // Turn the entity into a pickup object
                PickUp e_item = (PickUp) e;
                if (e_item.getItemFromPickUp() instanceof Treasure) {
                    // Item is a treasure
                    treasureCount++;
                }
            }
        }

        return treasureCount;
    }

    /**
     * Checks if a player is under effect of a potion
     * @return Player affected by potion
     */
    public Boolean buffedPlayer() {
        return player.isInvincible();
    }

    /**
     * Checks if a tile can be moved towards by a player
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Availability of location for the player
     */
    public Boolean validPlayerTile(int x, int y) {
        // For all entities
        for (Entity e : entities) {
            // Checking if the tile can be moved towards
            if ((e instanceof Obstacle) && e.getX() == x && e.getY() == y) {
                // Cannot move into this tile
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a tile can be moved towards by an enemy
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Availability of location for the player
     */
    public Boolean validEnemyTile(int x, int y) {
        // For all entities
        for (Entity e : entities) {
            // Checking if the tile can be moved towards
            if (e.getX() == x && e.getY() == y) {
                if (e instanceof Obstacle || e instanceof Enemy || e instanceof Door) {
                    // Cannot move into this tile
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if all switches are activated
     * @return
     */
    public Boolean allSwitchesActivated() {
        // Obtaining required data
        ArrayList<Boulder> boulders = getBoulders();
        ArrayList<Switch> switches = getSwitches();
        Boolean foundMatch = false;

        // Checking if all switches are activated
        for (Switch s : switches) {
            // Finding matching if possible
            for (Boulder b : boulders) {
                if (b.getX() == s.getX() && b.getY() == s.getY()) {
                    foundMatch = true;
                    break;
                }
            }
            // No match
            if (foundMatch != true) {
                return false;
            }
            foundMatch = false;
        }
        return true;
    }

    /**
     * Obtaining all switches
     * @return
     */
    public ArrayList<Switch> getSwitches() {
        // Initalising switches array
        ArrayList<Switch> switches = new ArrayList<Switch>();

        // Checking all entities of switches
        for (Entity e : entities) {
            if (e instanceof Switch) {
                switches.add((Switch) e);
            }
        }

        return switches;
    }

    /**
     * Checks dungeon for all boulders
     * @return
     */
    public ArrayList<Boulder> getBoulders() {
        // Initalising all boulders
        ArrayList<Boulder> boulders = new ArrayList<Boulder>();

        // Checks all entities for boulders
        for (Entity e : entities) {
            if (e instanceof Boulder) {
                boulders.add((Boulder) e);
            }
        }
        return boulders;
    }

    /**
     * Checks for all enemies left in the dungeon
     * @return Number of enemies remaining (int)
     */
    public int enemiesRemaining() {
        int count = 0;

        // Check all entities for enemies alive
        for (Entity e : entities) {
            if (e instanceof Enemy) {
                count++;
            }
        }

        return count;
    }

    /**
     * Obtain's the exit
     * @return Exit (Exit)
     */
    public Exit getExit() {
        // Check all entities for enemies alive
        for (Entity e : entities) {
            if (e instanceof Exit) {
                return (Exit) e;
            }
        }

        return null;
    }

    public void logDryFireRanged() {
        log.logDryFireRanged();
    }

    public boolean validArrowLocation(int x, int y) {
        for (Entity e : entities) {
            if (e.getX() == x && e.getY() == y && ! (e instanceof Enemy)) {
                return false;
            }
        }
        return true;
    }

    public Enemy getEnemy(int x, int y) {
        for (Entity e : entities) {
            if (e.getX() == x && e.getY() == y && e instanceof Enemy) {
                return (Enemy) e;
            }
        }
        return null;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
