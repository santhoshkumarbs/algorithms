package com.santhosh.algorithms.datastructures.binarysearchtree;

//https://github.com/williamfiset/Algorithms/blob/master/src/test/java/com/williamfiset/algorithms/datastructures/binarysearchtree/BinarySearchTreeTest.java
public class BinarySearchTree<T extends Comparable<T>> {
    // Track number of nodes in this BST
    private int nodeCount = 0;
    private Node root;

    // Add an element to this binary tree.
    // Return true if insertion is successful
    public boolean add(T element) {
        if(contains(element)) {
            return false;
            // otherwise, add element in binary tree
        } else {
            root = add(root, element);
            nodeCount++;
            return true;
        }
    }

    // recursively add a value in binary tree
    private Node add(Node node, T element) {

        //Base Case: found a leaf node
        if (node == null) {
            node = new Node(element, null, null);
            return node;
        } else {
            // Pick a subtree to insert element
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
                return node;
            } else {
                node.right = add(node.right, element);
                return node;
            }
        }
    }

    // returns true if the element contains in the tree
    private boolean contains(T element) {
        return contains(root, element);
    }

    // recursive method to find element in the tree
    private boolean contains(Node node, T element) {

        // Base case: Reached bottom value not found
        if (node == null) {
            return false;
        }

        // compare element with the current value
        int cmp = element.compareTo(node.data);

        // value we are looking for is less than the current node's value
        // so look in left subtree
        if (cmp < 0) {
            return contains(node.left, element);
        } else if (cmp > 0) { // value we are looking for is greater than the current node's value
            return contains(node.right, element);
        } else { // we have found the value
            return true;
        }
    }

    // Computes the height of the tree
    public int height() {
        return height(root);
    }

    // Recursive helper method to compute height of the tree
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        int height = Math.max(height(node.left), height(node.right)) + 1;
        return height;
    }

    public int size() {
        return nodeCount;
    }

    // Remove value from this binary tree if it exists, O(n)?
    public boolean remove(T element) {
        // make sure node we want to remove actually exists before we remove it
        if (contains(element)) {
            root = remove(root, element);
            nodeCount--;
            return true;
        }
       return false;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }

        int cmp = element.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, element);
        } else if (cmp > 0) {
            node.right = remove(node.right, element);
        } else {
            // found the node
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node tmp = findMin(node.right);

                node.data = tmp.data;

                node.right = remove(node.right, tmp.data);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    // It contains actual data & references of left & right node
    private class Node {
        T data;
        Node left, right;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
