package unsw.dungeon;

import java.util.Random;

public class RandomMovement implements MovementType {
    private Dungeon dungeon;
    
    public RandomMovement(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public int[] move(int x, int y) {
        // Getting valid squares to move into
        Boolean[] moves = dungeon.validAdjacentMoves(x, y);

        // Counting number of valid moves
        int valid_moves = 0;
        for (Boolean m : moves) {
            if (m) {
                valid_moves++;
            }
        }

        int move = (int) (Math.random() * (valid_moves));

        // Getting index of where the selected move is
        int move_number = 0;
        for (Boolean m : moves) {
            if (m) {
                move--;
                if (move == -1) {
                    break;
                }
            }

            move_number++;
        }

        if (move_number == 0) {
            int[] result = {-1, 0};
            return result;
        } else if (move_number == 1) {
            int[] result = {1, 0};
            return result;
        } else if (move_number == 2) {
            int[] result = {0, -1};
            return result;
        } else if (move_number == 3) {
            int[] result = {0, 1};
            return result;
        } else {
            // Trapped
            int[] result = {0, 0};
            return result;
        }
    }
}
