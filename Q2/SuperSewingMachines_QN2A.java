package Q2;

public class SuperSewingMachines_QN2A {
    int dresses[];
    final int noOfSewingMachines;
    final int totalDressCount, idealDressCount;
    final boolean isEquilizable;

    // Constructor initializes the SuperSewingMachines with an array of dresses
    SuperSewingMachines_QN2A(int[] dresses) {
        this.dresses = dresses;
        noOfSewingMachines = dresses.length;
        int[] totalCount_solvable = checkEquilizabililty();
        isEquilizable = totalCount_solvable[1] == 1 ? true : false;
        totalDressCount = totalCount_solvable[0];
        idealDressCount = totalDressCount / noOfSewingMachines;
    }

    // Method to check if the total number of dresses is equilizable among sewing machines
    int[] checkEquilizabililty() {
        int totalDresses = 0;
        // Calculate the total number of dresses
        for (int sewingMachine = 0; sewingMachine < noOfSewingMachines; sewingMachine++) {
            totalDresses += dresses[sewingMachine];
        }
        // Check if the total number of dresses is divisible by the number of sewing machines
        if (totalDresses % noOfSewingMachines == 0) {
            return new int[] { totalDresses, 1 }; // Equilizable
        }
        return new int[] { totalDresses, 0 }; // Not equilizable
    }

    // Method to find the sewing machine with the least and most dresses
    int[] minDress_maxDress() {
        int min = 0;
        int max = 0;
        // Iterate through sewing machines to find the one with the least and most dresses
        for (int sewingMachine = 1; sewingMachine < noOfSewingMachines; sewingMachine++) {
            if (dresses[sewingMachine] < dresses[min]) {
                min = sewingMachine;
            }
            if (dresses[sewingMachine] > dresses[max]) {
                max = sewingMachine;
            }
        }
        return new int[] { min, max };
    }

    // Method to check if all sewing machines have an equal number of dresses
    boolean ifEqualized() {
        // Check if each sewing machine has the ideal number of dresses
        for (int sewingMachine = 1; sewingMachine < noOfSewingMachines; sewingMachine++) {
            if (dresses[sewingMachine] != idealDressCount) {
                return false; // Not equalized
            }
        }
        return true; // Equalized
    }

    // Method to equalize the number of dresses among sewing machines
    int equalize() {
        if (!isEquilizable) {
            return -1; // Return -1 if not equilizable
        }

        int moveCounter = 0; // Counter to track the number of moves
        // Iterate until all sewing machines have an equal number of dresses
        while (!ifEqualized()) {
            int[] min_max = minDress_maxDress(); // Get the sewing machine with the least and most dresses

            // If there is a difference in the number of dresses, perform the equalization
            if (min_max[1] - min_max[0] != 0) {
                while (dresses[min_max[1]] != idealDressCount && dresses[min_max[0]] != idealDressCount) {
                    // Redistribute dresses to equalize the counts
                    if (min_max[1] - min_max[0] > 0) {
                        dresses[min_max[1]]--;
                        dresses[min_max[1] - 1]++;
                    } else {
                        dresses[min_max[1]]--;
                        dresses[min_max[1] + 1]++;
                    }
                    moveCounter++; // Increment move counter
                }
            } else {
                return moveCounter; // If already equalized, return the move counter
            }
        }
        return moveCounter; // Return the total number of moves
    }

    // Method to print the current state of the dresses array
    void printer() {
        System.out.println();
        for (int sewingMachine = 0; sewingMachine < noOfSewingMachines; sewingMachine++) {
            System.out.print(dresses[sewingMachine] + "\t");
        }
        System.out.println();
    }

    // Main method for testing the SuperSewingMachines class
    public static void main(String[] args) {
        SuperSewingMachines_QN2A sp = new SuperSewingMachines_QN2A(new int[] { 2, 1, 3, 0, 2 });
        System.out.println(sp.noOfSewingMachines);
        System.out.println(sp.equalize());
        sp.printer();
    }
}
