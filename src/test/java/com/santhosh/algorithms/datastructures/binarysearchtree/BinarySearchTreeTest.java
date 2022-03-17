package com.santhosh.algorithms.datastructures.binarysearchtree;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTreeTest {

    @Test
    void binarySearchTreeShouldBeEmpty() throws Exception {
        BinarySearchTree<String> tree = new BinarySearchTree();

        assertThat(tree.isEmpty()).isTrue();

        tree.add("Hello World");
        assertThat(tree.isEmpty()).isFalse();
    }

    @Test
    void testHeight() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        // Tree should look like
        //              M
        //          J       S
        //      B       N       z
        //  A

        // No Tree
        assertThat(tree.height()).isEqualTo(0);

        // Layer one
        tree.add("M");
        assertThat(tree.height()).isEqualTo(1);

        // Layer two
        tree.add("J");
        assertThat(tree.height()).isEqualTo(2);

        tree.add("S");
        assertThat(tree.height()).isEqualTo(2);

        // Layer three
        tree.add("B");
        assertThat(tree.height()).isEqualTo(3);
        tree.add("N");
        assertThat(tree.height()).isEqualTo(3);
        tree.add("Z");
        assertThat(tree.height()).isEqualTo(3);

        // Layer four
        tree.add("A");
        assertThat(tree.height()).isEqualTo(4);
    }


    @Test
    void testAdd() {
        BinarySearchTree<Character> tree = new BinarySearchTree<>();

        // Add element which does not exist
        assertThat(tree.add('A')).isTrue();

        // Add duplicate element
        assertThat(tree.add('A')).isFalse();

        // Add a second element which is not a duplicate
        assertThat(tree.add('B')).isTrue();
    }



}