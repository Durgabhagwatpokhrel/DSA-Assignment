import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class VirtualMazeGames_QN4A {

    // State class represents the current state of the player in the maze
    static class State {
        int x, y, keys, steps;

        State(int x, int y, int keys, int steps) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.steps = steps;
        }
    }

    // Method to find the minimum steps required to collect all keys
    public static int collectAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int allKeys = 0; // Represents all the keys that need to be collected

        // Find starting point and total number of keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = grid[i].charAt(j);
                if (cell == 'S') {
                    // Add the starting state to the queue and mark it as visited
                    queue.offer(new State(i, j, 0, 0));
                    visited.add(i + "," + j + ",0");
                } else if (cell >= 'a' && cell <= 'f') {
                    // Update allKeys with the bit corresponding to the current key
                    allKeys |= (1 << (cell - 'a'));
                }
            }
        }

        // Directions: up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // Breadth-first search
        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Check if we've collected all keys
            if (current.keys == allKeys)
                return current.steps;

            // Explore possible moves in all directions
            for (int[] dir : directions) {
                int newX = current.x + dir[0], newY = current.y + dir[1];
                int newKeys = current.keys;

                // Check if the new position is within the bounds of the maze
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    char cell = grid[newX].charAt(newY);

                    // Check Wall
                    if (cell == 'W')
                        continue;

                    // Check Door & Key
                    if (cell >= 'A' && cell <= 'F' && (newKeys & (1 << (cell - 'A'))) == 0)
                        continue;

                    // Collect Key (If Found)
                    if (cell >= 'a' && cell <= 'f')
                        newKeys |= (1 << (cell - 'a'));

                    // Create a unique identifier for the new state
                    String newState = newX + "," + newY + "," + newKeys;

                    // Check if the new state has not been visited
                    if (!visited.contains(newState)) {
                        visited.add(newState);
                        // Add the new state to the queue with updated parameters
                        queue.offer(new State(newX, newY, newKeys, current.steps + 1));
                    }
                }
            }
        }

        // If we exit the loop, no solution was found
        return -1;
    }

    // Main method for testing
    public static void main(String[] args) {
        String[] grid1 = { "SPaPP", "WWWPW", "bPAPB" };
        System.out.println("Minimum Steps: " + collectAllKeys(grid1));
    }
}
