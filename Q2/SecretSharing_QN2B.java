package Q2;

import java.util.Arrays;

public class SecretSharing_QN2B {
    int individuals[];
    int intervals[][];

    // Constructor initializes individuals array and intervals matrix
    SecretSharing_QN2B(int noOfindividuals, int[][] intervalMatrix) {
        individuals = new int[noOfindividuals];
        // Initialize individuals array with unique identifiers
        for (int i = 0; i < noOfindividuals; i++) {
            individuals[i] = i;
        }
        intervals = intervalMatrix; // Set the intervals matrix
    }

    // Method to determine individuals who will know the secret
    int[] startSharing() {
        int secretKnowingIndividuals = 0;

        // Iterate through the intervals
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals[0].length; j++) {
                // Check if the current interval overlaps with the previous one
                if (i > 0 &&
                        intervals[i - 1][intervals[0].length - 1] > intervals[i][j]) {
                    continue; // Skip to the next interval if there is an overlap
                }
                // In each interval, only end-start+1 individuals will know the secret
                secretKnowingIndividuals++;
            }

            // If all individuals know the secret, exit the loop
            if (secretKnowingIndividuals >= individuals.length) {
                break;
            }
        }

        // Return an array of secret-knowing individuals
        return Arrays.copyOf(individuals, secretKnowingIndividuals);
    }

    public static void main(String[] args) {
        // Create an instance of SecretSharing with 5 individuals and intervals matrix
        SecretSharing_QN2B share = new SecretSharing_QN2B(5, new int[][] { { 0, 1 }, { 1, 3 }, { 2, 4 } });

        // Get the secret-knowing individuals and print the result
        int[] secretKnowingIndividuals = share.startSharing();
        for (int i : secretKnowingIndividuals) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
