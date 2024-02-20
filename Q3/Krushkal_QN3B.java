package Q3;

// Importing necessary library for Priority Queue
import java.util.PriorityQueue;

public class Krushkal_QN3B {

    // Inner class representing an Edge with source, destination, and weight
    public static class Edge implements Comparable<Edge> {
        int s; // source
        int d; // destination
        int w; // weight

        Edge(int s, int d, int w) {
            this.s = s;
            this.d = d;
            this.w = w;
        }

        // Override compareTo method to compare edges based on weight
        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    int v; // Number of vertices
    PriorityQueue<Edge> pq; // Priority Queue to store edges in minimum heap order

    // Constructor to initialize the number of vertices and priority queue
    Krushkal_QN3B(int v) {
        this.v = v;
        this.pq = new PriorityQueue<>();
    }

    // Method to add an edge to the priority queue
    void addEdge(int s, int d, int w) {
        pq.add(new Edge(s, d, w));
    }

    // Kruskal's algorithm to find Minimum Spanning Tree
    void kruskal() {
        // Array to keep track of the parent of each node for disjoint set
        int[] parent = new int[v];

        // Initialize all vertices as belonging to different sets
        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }

        // Process edges in ascending order of weight
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int x = find(e.s, parent);
            int y = find(e.d, parent);

            // If including this edge doesn't cause a cycle,
            // include it in the result and union the two vertices.
            if (x != y) {
                System.out.println(e.s + " - " + e.d + " : " + e.w);
                union(x, y, parent);
            }
        }
    }

    // Find operation for disjoint set, with path compression
    int find(int i, int[] parent) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i], parent);
    }

    // Union operation for disjoint set
    void union(int x, int y, int[] parent) {
        int xset = find(x, parent);
        int yset = find(y, parent);
        if (xset != yset) {
            parent[xset] = yset;
        }
    }

    // Main method for testing Kruskal's algorithm
    public static void main(String[] args) {
        Krushkal_QN3B graph = new Krushkal_QN3B(4);

        // Adding edges to the graph
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // Running Kruskal's algorithm to find Minimum Spanning Tree
        graph.kruskal();
    }
}
