package Q1;

import java.util.Scanner;

public class CostEvent_QN1A {
    int costMatrix[][];

    // Method to insert cost matrix values
    int[][] insertCostMatrix(int venues, int themes) {
        costMatrix = new int[venues][themes];
        Scanner sc = new Scanner(System.in);
        for (int n = 0; n < venues; n++) {
            for (int k = 0; k < themes; k++) {
                System.out.print(n + " " + k + " :");
                costMatrix[n][k] = sc.nextInt();
            }
        }
        sc.close();
        return costMatrix;
    }

    // Method to print the cost matrix
    void printCostMatrix(int[][] matrix) {
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Method to calculate the minimum cost of events
    String[] minimumCost(int[][] costMatrix) {
        StringBuilder n_k = new StringBuilder(); // 0=sum,1=n,k
        int prevVenue_theme = 0;
        int sum = 0;
        for (int n = 0; n < costMatrix.length; n++) {
            int smallest = costMatrix[n][0];
            n_k.append(n);
            n_k.append(" ");
            n_k.append(0);

            // can use do-while maybe??
            for (int k = 1; k < costMatrix[0].length; k++) {
                if (n == 0) {
                    smallest = costMatrix[n][k];
                    n_k.replace(n_k.length() - 1, n_k.length(), Integer.toString(k));
                    prevVenue_theme = k;
                    continue;
                } else if (costMatrix[n][k] < smallest && prevVenue_theme != k) {
                    smallest = costMatrix[n][k];
                    prevVenue_theme = k;
                }
            }

            n_k.append(" + \t");
            sum = sum + smallest;
        }
        return new String[] { Integer.toString(sum), n_k.toString() };
    }

    public static void main(String[] args) {
        CostEvent_QN1A event = new CostEvent_QN1A();
        event.insertCostMatrix(3, 3);
        event.printCostMatrix(event.costMatrix);
        System.out.println("\nThe minimum cost of events is:");
        String[] result = event.minimumCost(event.costMatrix);
        System.out.println(result[0]);
        System.out.println("And it's made from");
        System.out.println(result[1]);
    }
}
