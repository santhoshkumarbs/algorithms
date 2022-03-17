package com.santhosh.algorithms.datastructures.binarysearchtree;

//https://github.com/williamfiset/Algorithms/blob/master/src/test/java/com/williamfiset/algorithms/datastructures/binarysearchtree/BinarySearchTreeTest.java
public class BinarySearchTree<T extends Comparable<T>> {
    // Tracks number of nodes in this Binary Search Tree
    private int nodeCount = 0;

    // Root node of this Binary Search Tree
    private Node root = null;

    // Add an element to this binary tree.
    // Return true if insertion is successful
    public boolean add(T element) {
        if (contains(element)) {
            return false;
        } else {
            root = add(root, element);
            nodeCount++;
            return true;
        }
    }

    // Adds element to node recursively
    private Node add(Node node, T element) {
        // Base case: Reached leaf node
        if (node == null) {
            node = new Node(element, null, null);
        } else {
            int cmp = element.compareTo(node.data);
            if (cmp < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }
        return node;
    }

    // check if element exists in the Binary Search Tree
    private boolean contains(T element) {
        return contains(root, element);
    }

    // private recursive method to check
    private boolean contains(Node node, T element) {
        // Base Case: Reached last node but couldn't find a match
        if (node == null) {
            return false;
        } else {
            int cmp = element.compareTo(node.data);
            if (cmp < 0) {
                // dig in left sub tree
                return contains(node.left, element);
            } else if (cmp > 0) {
                // dig in right subtree
                return contains(node.right, element);
            } else {
                // both are equal
                return true;
            }
        }
    }

    // Computes the height of the tree
    public int height() {
        return height(root);
    }

    // private recursive method to determine the height of the tree
    private int height(Node node) {
        // Base case: Reached leaf node
        if (node == null) {
            return 0;
        } else {
            int height = Math.max(height(node.left), height(node.right)) + 1;
            return height;
        }

    }

    public boolean isEmpty() {
        return nodeCount == 0;
    }

    private class Node {
        T data; // Holds data
        Node left, right; // Refers to left & right node

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
