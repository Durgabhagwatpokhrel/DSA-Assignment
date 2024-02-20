package Q3;

import java.util.ArrayList;

public class PriorityQueueu_QN3B {
    MinHeap_QN3B heap; // Priority queue implemented using a MinHeap

    // Constructor initializes the priority queue with a MinHeap
    PriorityQueueu_QN3B() {
        heap = new MinHeap_QN3B();
    }

    // Method to dequeue (remove and return) the element with the highest priority
    int dequeue() {
        return heap.deleteHeap(0); // The root of the MinHeap contains the element with the highest priority
    }

    // Method to enqueue (insert) a new element with a given priority
    void queue(int element) {
        heap.insertHeap(element); // Insert the element into the MinHeap
    }

    // Main method for testing the PriorityQueueu_QN3B class
    public static void main(String[] args) {
        PriorityQueueu_QN3B pq = new PriorityQueueu_QN3B();

        // Enqueue elements with priorities
        pq.queue(12);
        pq.queue(213);
        pq.queue(99);
        pq.queue(21);
        pq.queue(143);

        // Dequeue an element (highest priority)
        pq.dequeue();

        // Print the current state of the priority queue
        System.out.println(pq);
    }

    // Override toString method to display the current state of the priority queue
    @Override
    public String toString() {
        return heap.heapList.toString();
    }
}
