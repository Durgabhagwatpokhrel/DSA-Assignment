package Q5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyTSP_QN5A {
    private double[][] pheromoneMatrix;
    private int[][] distanceMatrix;
    private int numberOfNodes;
    private int numberOfAnts;
    private int sourceNode;
    private int destinationNode;
    private double alpha;
    private double beta;
    private double evaporationRate;
    private double initialPheromone;

    // Constructor to initialize parameters
    public AntColonyTSP_QN5A(int numberOfNodes, int numberOfAnts, int sourceNode, int destinationNode,
            double alpha, double beta, double evaporationRate, double initialPheromone) {
        this.numberOfNodes = numberOfNodes;
        this.numberOfAnts = numberOfAnts;
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.initialPheromone = initialPheromone;
        pheromoneMatrix = new double[numberOfNodes][numberOfNodes];
        distanceMatrix = new int[numberOfNodes][numberOfNodes];
    }

    // Initialize pheromone matrix with initial pheromone value
    public void initializePheromoneMatrix() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                pheromoneMatrix[i][j] = initialPheromone;
            }
        }
    }

    // Initialize distance matrix
    public void initializeDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    // Find shortest path using ant colony optimization
    public List<Integer> findShortestPath() {
        Random random = new Random();
        List<Integer> bestPath = null;
        int bestDistance = Integer.MAX_VALUE;
        // Iterate for each ant
        for (int iteration = 0; iteration < numberOfAnts; iteration++) {
            // Construct path for the ant
            List<Integer> antPath = constructAntPath(sourceNode, destinationNode, random);
            int antDistance = calculatePathDistance(antPath);
            // Update best path if current ant's path is shorter
            if (antDistance < bestDistance) {
                bestDistance = antDistance;
                bestPath = antPath;
            }
            // Update pheromone trail based on ant's path
            updatePheromoneTrail(antPath, antDistance);
        }
        return bestPath;
    }

    // Construct path for an ant
    private List<Integer> constructAntPath(int source, int destination, Random random) {
        List<Integer> antPath = new ArrayList<>();
        boolean[] visitedNodes = new boolean[numberOfNodes];
        int currentNode = source;
        antPath.add(currentNode);
        visitedNodes[currentNode] = true;
        // Build the path until the ant reaches the destination
        while (currentNode != destination) {
            int nextNode = selectNextNode(currentNode, visitedNodes, random);
            antPath.add(nextNode);
            visitedNodes[nextNode] = true;
            currentNode = nextNode;
        }
        return antPath;
    }

    // Select next node for an ant based on pheromone levels and distances
    private int selectNextNode(int currentNode, boolean[] visitedNodes, Random random) {
        double[] probabilities = new double[numberOfNodes];
        double probabilitiesSum = 0.0;
        // Calculate probabilities for unvisited nodes
        for (int node = 0; node < numberOfNodes; node++) {
            if (!visitedNodes[node]) {
                double pheromoneLevel = Math.pow(pheromoneMatrix[currentNode][node], alpha);
                double distance = 1.0 / Math.pow(distanceMatrix[currentNode][node], beta);
                probabilities[node] = pheromoneLevel * distance;
                probabilitiesSum += probabilities[node];
            }
        }
        // Select a random node based on probabilities
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int node = 0; node < numberOfNodes; node++) {
            if (!visitedNodes[node]) {
                double probability = probabilities[node] / probabilitiesSum;
                cumulativeProbability += probability;
                if (randomValue <= cumulativeProbability) {
                    return node;
                }
            }
        }
        return -1; // Unreachable code, should never happen
    }

    // Calculate total distance of a path
    private int calculatePathDistance(List<Integer> path) {
        int distance = 0;
        int pathSize = path.size();
        for (int i = 0; i < pathSize - 1; i++) {
            int currentNode = path.get(i);
            int nextNode = path.get(i + 1);
            distance += distanceMatrix[currentNode][nextNode];
        }
        return distance;
    }

    // Update pheromone trail based on ant's path
    private void updatePheromoneTrail(List<Integer> path, int distance) {
        double pheromoneDeposit = 1.0 / distance;
        for (int i = 0; i < path.size() - 1; i++) {
            int currentNode = path.get(i);
            int nextNode = path.get(i + 1);
            pheromoneMatrix[currentNode][nextNode] = (1 - evaporationRate) *
                    pheromoneMatrix[currentNode][nextNode] + evaporationRate * pheromoneDeposit;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example usage
        int[][] distanceMatrix = {
                { 0, 2, 3, 4 },
                { 2, 0, 6, 1 },
                { 3, 6, 0, 2 },
                { 4, 1, 2, 0 }
        };
        int numberOfNodes = 4;
        int numberOfAnts = 10;
        int sourceNode = 0;
        int destinationNode = 3;
        double alpha = 1.0;
        double beta = 2.0;
        double evaporationRate = 0.5;
        double initialPheromone = 0.1;
        AntColonyTSP_QN5A antColony = new AntColonyTSP_QN5A(numberOfNodes, numberOfAnts,
                sourceNode, destinationNode,
                alpha, beta, evaporationRate, initialPheromone);
        antColony.initializePheromoneMatrix();
        antColony.initializeDistanceMatrix(distanceMatrix);
        List<Integer> shortestPath = antColony.findShortestPath();
        System.out.println("Shortest path: " + shortestPath);
    }
}
