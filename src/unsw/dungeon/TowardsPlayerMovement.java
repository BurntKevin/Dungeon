package unsw.dungeon;

import java.util.ArrayList;

public class TowardsPlayerMovement implements MovementType {
    private Dungeon dungeon;
    
    public TowardsPlayerMovement(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

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
     */
    private int[] awayPlayer(int x, int y) {
        // Calculating all possible moves
        ArrayList<int[]> possibilities = new ArrayList<int[]>();
        possibilities.add(new int[] {x - 1, y});
        possibilities.add(new int[] {x + 1, y});
        possibilities.add(new int[] {x, y - 1});
        possibilities.add(new int[] {x, y + 1});

        for (int[] p : possibilities) {
            if (dungeon.validPlayerTile(p[0], p[1])) {
                int[] move = towardsPlayer(p[0], p[1]);
                if (move[0] + p[0] == x && move[1] + p[1] == y) {
                    return new int[] {-move[0], -move[1]};
                }
            }
        }

        // No movement which goes further away
        return new int[] {0, 0};
    }

    private int[] towardsPlayer(int x, int y) {
        // Setting up board
        Entity[][] boardStatus = dungeon.getBoard();
        int[] playerCoordinates = dungeon.getPlayerCoordinates();

        // Finding best move towards player - invariant: assumes player exists
        // Finds first best move rather than randomly choose
        ArrayList<int[]> queue = new ArrayList<int[]>();
        int[] source = {playerCoordinates[0], playerCoordinates[1], 0, 0};
        queue.add(source);

        int[] move = {0, 0};
        while (queue.size() != 0) {
            int[] node = queue.remove(0);

            if (0 <= node[0] && node[0] < dungeon.getWidth() && 0 <= node[1] && node[1] < dungeon.getHeight() && ! (boardStatus[node[0]][node[1]] instanceof Wall)) {
                if (node[0] == x && node[1] == y) {
                    move[0] = node[2];
                    move[1] = node[3];
                    break;
                }

                queue.add(new int[] {node[0] - 1, node[1], 1, 0}); // Left
                queue.add(new int[] {node[0] + 1, node[1], -1, 0}); // Right
                queue.add(new int[] {node[0], node[1] - 1, 0, 1}); // Up
                queue.add(new int[] {node[0], node[1] + 1, 0, -1}); // Down

                boardStatus[node[0]][node[1]] = new Wall(node[0], node[1]);
            }
        }
        return move;
    }
}
