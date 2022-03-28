package com.santhosh.alogrithms.datastructures.priorityqueue;

import java.util.Arrays;

public class BinaryHeap {

    private Integer[] heap;
    private int size;

    public BinaryHeap(int capacity) {
        heap = new Integer[capacity];
    }

    public int rootNode() {
        return heap[0];
    }

    public int lastNode() {
        return heap[heap.length - 1];
    }

    public int indexOfLeftChild(int index) {
        return (index * 2) + 1;
    }

    public int indexOfRightChild(int index) {
        return (index * 2) + 2;
    }

    public int parentIndex(int index) {
        return (index - 1) / 2; // integer division which removes the fraction part
    }

    public void insert(int value) {
        if (isFull()) {
            throw new IllegalArgumentException("Heap if full");
        }
        heap[size] = value;
        bubbleUpToCorrectPlace(size);
        size++;
    }

    public int poll() {
        /*
        Return the root node
        Replace last node at root node's position
        Heapify down the current root node to it's correct position
         */
        int result = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        bubbleDownToCorrectPlace(0);
        return result;
    }

    private void bubbleDownToCorrectPlace(int index) {
        while (hasGreaterChild(index)) {
            int largerChildIndex = calculateLargerChildIndex(index);
            int temp = heap[index];
            heap[index] = heap[largerChildIndex];
            heap[largerChildIndex] = temp;

            index = largerChildIndex;
        }
    }

    private int calculateLargerChildIndex(int index) {
        int rightChildIndex = indexOfRightChild(index);
        int leftChildIndex = indexOfLeftChild(index);
        // if there is no right child
        if (rightChildIndex > size - 1) {
            return leftChildIndex;
        }

        // if right child value is greater than left child value
        if (heap[rightChildIndex] > heap[leftChildIndex]) {
            return rightChildIndex;
        } else { // if left child value is greater or equal to right child value
            return leftChildIndex;
        }
    }

    private boolean hasGreaterChild(int index) {
        int leftIndex = indexOfLeftChild(index);
        int rightIndex = indexOfRightChild(index);
        return (leftIndex < size && heap[leftIndex] > heap[index])|| (rightIndex < size && heap[rightIndex] > heap[index]);
    }


    private void bubbleUpToCorrectPlace(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[parentIndex(index)]) {
            heap[index] = heap[parentIndex(index)];
            index = parentIndex(index);
        }
        heap[index] = newValue;
    }

    private boolean isFull() {
        return heap.length == size;
    }

    public static void main(String[] args) {
        BinaryHeap maxHeap = new BinaryHeap(10);
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(9);

        System.out.println(Arrays.toString(maxHeap.heap));
        System.out.println(maxHeap.size);
        System.out.println("----------------------");

        int result = maxHeap.poll();
        System.out.println("result = " + result);
        System.out.println(Arrays.toString(maxHeap.heap));

    }
}
