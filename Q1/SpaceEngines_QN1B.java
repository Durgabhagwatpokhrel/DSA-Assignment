package Q1;

public class SpaceEngines_QN1B {
    /**
     * Main method to test the functionality of calculating the minimum time to repair engines.
     */
    public static void main(String[] args) {
        int[] engines = {1, 2, 3}; // Array representing time required to repair each engine
        int k = 1; // Maximum number of engineers available
        System.out.println(minimumTime(engines, k)); // Output the minimum time to repair all engines
    }

    /**
     * Method to calculate the minimum time required to repair all engines.
     * @param engines Array representing time required to repair each engine
     * @param k Maximum number of engineers available
     * @return Minimum time required to repair all engines
     */
    public static int minimumTime(int[] engines, int k) {
        int maxTime = 0; // Maximum time taken to repair any engine
        int engineers = 1; // Number of engineers currently available

        // Iterate through the engines starting from the last one
        for (int i = engines.length - 1; i >= 0; i--) {
            int time = engines[i]; // Time required to repair current engine

            // Continue until the required number of engineers is available
            while (engineers < time) {
                int splitTime = Math.min(time - engineers, k); // Time taken to repair with available engineers
                engineers += splitTime; // Allocate engineers for repair

                maxTime = Math.max(maxTime, time); // Update maximum time if needed
            }
            engineers--; // Reduce the number of engineers after repair
        }

        return maxTime + 1; // Add 1 to include the time for the last engine repair
    }
}

// Output: 4
