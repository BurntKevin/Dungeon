package unsw.dungeon;

import java.util.ArrayList;

/**
 * Movement of enemy which goes for player unless they are buffed with a potion
 */
public class TowardsPlayerMovement implements MovementType {
    private Dungeon dungeon;

    /**
     * Initalise enemy movement
     * @param dungeon Dungeon which enemy is at
     */
    public TowardsPlayerMovement(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    /**
     * Returns a movement for the enemy
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     */
    public int[] move(int x, int y) {
        if (dungeon.buffedPlayer()) {
            return awayPlayer(x, y);
        } else {
            return towardsPlayer(x, y);
        }
    }

    /**
     * Moves away from the player in a greedy fashion where it does not predict
     * future best placements but rather instantaneous ones
     * @param x Starting x-coordinate
     * @param y Starting y-coordinate
     * @return An array where the enemy should shift [x, y]
     */
    private int[] awayPlayer(int x, int y) {
        // Calculating all possible moves
        ArrayList<int[]> possibilities = new ArrayList<int[]>();
        possibilities.add(new int[] {-1, 0});
        possibilities.add(new int[] {1, 0});
        possibilities.add(new int[] {0, -1});
        possibilities.add(new int[] {0, 1});
        possibilities.add(new int[] {0, 0});

        // For all possibilities
        int furthest = 0;
        int[] bestEscape = new int[] {0, 0};
        for (int[] p : possibilities) {
            // Check if it is a possible location to be at
            if (dungeon.validPlayerTile(x + p[0], y + p[1])) {
                int steps = stepsToPlayer(x + p[0], y + p[1]);
                if (steps >= furthest) {
                    bestEscape = p;
                    furthest = steps;
                }
            }
        }

        // No movement which goes further away
        return bestEscape;
    }

    public int stepsToPlayer(int x, int y) {
        // Setting up board
        Entity[][] boardStatus = dungeon.getBoard();
        int[] playerCoordinates = dungeon.getPlayerCoordinates();

        // Finding best move towards player
        ArrayList<int[]> queue = new ArrayList<int[]>();
        int[] source = {x, y, 0};
        queue.add(source);

        while (queue.size() != 0) {
            int[] node = queue.remove(0);

            // Checking if the tile can be moved upon
            if (!(boardStatus[node[0]][node[1]] instanceof Wall)) {
                // Checking if the player has been reached
                if (node[0] == playerCoordinates[0] && node[1] == playerCoordinates[1]) {
                    return node[2];
                }

                // Finding next set of possible moves
                queue.add(new int[] {node[0] - 1, node[1], node[2] + 1}); // Left
                queue.add(new int[] {node[0] + 1, node[1], node[2] + 1}); // Right
                queue.add(new int[] {node[0], node[1] - 1, node[2] + 1}); // Up
                queue.add(new int[] {node[0], node[1] + 1, node[2] + 1}); // Down

                // Marking node as visited
                boardStatus[node[0]][node[1]] = new Wall(node[0], node[1]);
            }
        }

        return -1;
    }
    
    /**
     * Moves towards the place
     * @param x x-coordinate
     * @param y y-coordinate
     * @return An array where the enemy should shift [x, y]
     */
    private int[] towardsPlayer(int x, int y) {
        // Setting up board
        Entity[][] boardStatus = dungeon.getBoard();
        int[] playerCoordinates = dungeon.getPlayerCoordinates();

        // Finding best move towards player
        // Finds first best move rather than randomly choose
        ArrayList<int[]> queue = new ArrayList<int[]>();
        int[] source = {playerCoordinates[0], playerCoordinates[1], 0, 0};
        queue.add(source);

        // Calculating best move
        int[] move = {0, 0};
        while (queue.size() != 0) {
            int[] node = queue.remove(0);

            // Checking if the current node is a valid location
            if (0 <= node[0] && node[0] < dungeon.getWidth() && 0 <= node[1] && node[1] < dungeon.getHeight() && ! (boardStatus[node[0]][node[1]] instanceof Obstacle || boardStatus[node[0]][node[1]] instanceof Door)) {
                if (node[0] == x && node[1] == y) {
                    move[0] = node[2];
                    move[1] = node[3];
                    break;
                }

                // Finding next set of possible moves
                if (! (boardStatus[node[0]][node[1]] instanceof Enemy) || node.equals(new int[] {0, 1})) {
                    queue.add(new int[] {node[0] - 1, node[1], 1, 0}); // Left
                    queue.add(new int[] {node[0] + 1, node[1], -1, 0}); // Right
                    queue.add(new int[] {node[0], node[1] - 1, 0, 1}); // Up
                    queue.add(new int[] {node[0], node[1] + 1, 0, -1}); // Down
                }

                // Marking node as visited
                boardStatus[node[0]][node[1]] = new Wall(node[0], node[1]);
            }
        }
        return move;
    }
}
