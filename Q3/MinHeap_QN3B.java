package Q3;

import java.util.ArrayList;

public class MinHeap_QN3B {
    // Min heap is a binary tree where the parent node is less than or equal to its child nodes
    ArrayList<Integer> heapList;

    // Constructor initializes the heapList
    MinHeap_QN3B() {
        heapList = new ArrayList<>();
    }

    // Method to swap two elements in the heapList
    void swapheapListitems(int index1, int index2) {
        // Swap elements in the heapList
        heapList.add(index1, heapList.get(index2));
        heapList.add(index2, heapList.get(index1 + 1));
        heapList.remove(index2 + 1);
        heapList.remove(index1 + 1);
    }

    // Method to insert a new element into the min heap
    void insertHeap(int data) {
        heapList.add(data);
        int childIndex = heapList.size() - 1;
        while (childIndex > 0) {
            // Continue swapping until the inserted data is at the top or in the correct position
            int parentIndex = (childIndex - 1) / 2;
            if (heapList.get(childIndex) < heapList.get(parentIndex)) {
                swapheapListitems(childIndex, parentIndex);
                childIndex = parentIndex; // Update childIndex after swapping
            } else {
                return; // If the inserted data is greater than its parent, stop swapping
            }
        }
    }

    // Method to delete and return the root element from the min heap
    int deleteHeap(int n) {
        int temp = heapList.remove(0); // Remove the root element
        heapList.add(0, heapList.get(n)); // Replace the root with the last element
        int index = heapList.size();

        // Heapify the heap after removing the root
        while (index <= heapList.size()) {
            int largestIndex = index;
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            // Find the largest among the root, left child, and right child
            if (leftChild <= heapList.size() && heapList.get(leftChild) > heapList.get(largestIndex)) {
                largestIndex = leftChild;
            }
            if (rightChild <= heapList.size() && heapList.get(rightChild) > heapList.get(largestIndex)) {
                largestIndex = rightChild;
            }

            // Swap the root with the largest child if necessary
            if (largestIndex != index) {
                swapheapListitems(largestIndex, index);
                index = largestIndex; // Update index after swapping
            } else {
                return temp; // If no swapping needed, return the removed element
            }
        }
        return temp; // Return the removed element
    }

    // Main method for testing the MinHeap_QN3B class
    public static void main(String[] args) {
        MinHeap_QN3B hp = new MinHeap_QN3B();
        hp.insertHeap(12);
        hp.insertHeap(22);
        hp.insertHeap(76);
        hp.insertHeap(2);
        hp.insertHeap(931);
        hp.insertHeap(24);
        System.out.println(hp.heapList);
    }
}
