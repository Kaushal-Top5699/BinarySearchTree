package com.kaushaltop;

public class Main {

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(10, "Ten");
        tree.insert(20, "Twenty");
        tree.insert(15, "Fifteen");
        tree.insert(30, "Thirty");
        tree.insert(33, "ThirtyThree");
        tree.insert(48, "FortyEight");
        tree.insert(87, "EightySeven");
        tree.insert(5, "Five");

        System.out.println(tree.findMin());
        System.out.println(tree.findMax());

        System.out.println(tree.remove(33));
        System.out.println("Now min is: "+tree.findMin());

    }
}
