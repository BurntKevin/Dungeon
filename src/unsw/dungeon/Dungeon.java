/**
 *
 */
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

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        // Todo - Remove from front end interface
        entities.remove(entity);
    }

    public Entity getItem(int x, int y) {
        for (Entity entity : entities) {
            if (entity != null && entity.getX() == x && entity.getY() == y) {
                // Found neighbouring item
                return entity;
            }
        }

        return null;
    }

    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        for (Entity e : entities) {
            if (e instanceof Enemy) {
                enemies.add( (Enemy) e);
            }
        }

        return enemies;
    }

    public int[] getPlayerCoordinates() {
        int[] coordinates = {player.getX(), player.getY()};

        return coordinates;
    }

    public Boolean[] validAdjacentMoves(int x, int y) {
        // Null is added for type safety as there are cases when entity is null - undeveloped item
        Boolean[] result = {x - 1 >= 0, x + 1 < width, y - 1 >= 0, y + 1 < height};

        for (Entity entity : entities) {
            if (entity != null && entity.getX() == x - 1 && entity.getY() == y && entity instanceof Wall) { 
                result[0] = false;
            } else if (entity != null && entity.getX() == x + 1 && entity.getY() == y && entity instanceof Wall) {
                result[1] = false;
            } else if (entity != null && entity.getX() == x && entity.getY() == y - 1 && entity instanceof Wall) {
                result[2] = false;
            } else if (entity != null && entity.getX() == x && entity.getY() == y + 1 && entity instanceof Wall) {
                result[3] = false;
            }
        }

        return result;
    }

    public Entity[][] getBoard() {
        // Creating a board where every tile can be walked through
        Entity[][] board = new Entity[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = null;
            }
        }

        // Finding locations which cannot be walked through - current issues: unable to walk through stuff like treasure and switches and other
        for (Entity e: entities) {
            board[e.getX()][e.getY()] = e;
        }

        return board;
    }
}
