

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;

    // Constructor to initialize TreeNode with a value
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class BinarySearchTree_QN4A {

    // Method to find the x closest values to a target in a binary search tree
    public static List<Integer> closestValues(TreeNode root, double target, int x) {
        List<Integer> result = new ArrayList<>();
        closestValuesHelper(root, target, x, result); // Call helper method to perform the traversal
        return result;
    }

    // Helper method to perform inorder traversal and find the x closest values
    private static void closestValuesHelper(TreeNode node, double target, int x, List<Integer> result) {
        if (node == null) {
            return; // Base case: If node is null, return
        }

        closestValuesHelper(node.left, target, x, result); // Traverse left subtree

        if (result.size() < x) {
            result.add(node.val); // If result list is not full, add current node's value
        } else {
            double currentDiff = Math.abs(node.val - target); // Calculate difference between current node's value and target
            double maxDiff = Math.abs(result.get(0) - target); // Calculate difference between first value in result list and target

            if (currentDiff < maxDiff) {
                result.remove(0); // Remove first value in result list
                result.add(node.val); // Add current node's value to result list
            } else {
                // Since the tree is balanced, if we encounter a larger difference, we can stop searching in the right subtree
                return;
            }
        }

        closestValuesHelper(node.right, target, x, result); // Traverse right subtree
    }

    public static void main(String[] args) {
        // Create the binary search tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        double target = 3.8;
        int x = 2; // Number of closest values to find

        // Find the closest values to the target in the binary search tree
        List<Integer> closestValues = closestValues(root, target, x);
        System.out.println("Closest values to " + target + " are: " + closestValues); // Print the closest values
    }
}
