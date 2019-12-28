package com.kaushaltop;

public class BinarySearchTree {

    private Node root;

    public void insert(int key, String value) {

        Node newNode = new Node(key, value);

        if (root == null) {

            root = newNode;

        } else {

            Node currentNode = root;
            Node parent;

            while (true) {

                parent = currentNode;

                if (key < currentNode.getKey()) {

                    currentNode = currentNode.getLeftNode();

                    if (currentNode == null) {

                        parent.setLeftNode(newNode);
                        return;
                    }
                } else {

                    currentNode = currentNode.getRightNode();

                    if (currentNode == null) {

                        parent.setRightNode(newNode);
                        return;
                    }
                }
            } //loops breaks here
        }
    }

    public int findMin() {

        Node currentNode = root;
        Node last = null;

        while (currentNode != null) {

            last = currentNode;

            currentNode = currentNode.getLeftNode();
        }
        return last.getKey();
    }

    public int findMax() {

        Node currentNode = root;
        Node right = null;

        while (currentNode != null) {

            right = currentNode;

            currentNode = currentNode.getRightNode();
        }
        return right.getKey();
    }

    public boolean remove(int key) {

        /**
         * First search the key you wanna delete/remove
         * Then check for the three conditions below:
         * if node is leaf node
         * if node has only one child
         * if node has two children
         */

        Node currentNode = root;
        Node parentNode = root;

        boolean isLeftChild = false;

        while (currentNode.getKey() != key) {

            parentNode = currentNode;
            if (key < currentNode.getKey()) {

                isLeftChild = true;
                currentNode = currentNode.getLeftNode();

            } else {

                currentNode = currentNode.getRightNode();
                isLeftChild = false;
            }
            if (currentNode == null) return false;
        }
        //Found the key to be deleted
        Node deleteNode = currentNode;

        //Now check for the first condition
        //if node is leaf node
        //This condition is checks whether last node has no children
        if (deleteNode.getLeftNode() == null && deleteNode.getRightNode() == null) {

            //what if there is only one node in tree: root
            if (deleteNode == root) root = null;

            else if (isLeftChild) parentNode.setLeftNode(null);

            else parentNode.setRightNode(null);

            //Now check if the node to be deleted has only one child
        } else if (deleteNode.getRightNode() == null) {
            //This condition checks whether nodes rightChild is null and if it is then it has only one child
            //And that is leftChild
            if (deleteNode == root) root = deleteNode.getLeftNode();

            else if (isLeftChild) parentNode.setLeftNode(deleteNode.getLeftNode());

            else parentNode.setRightNode(deleteNode.getLeftNode());

        } else if (deleteNode.getLeftNode() == null) {
            //This condition checks whether nodes leftChild is null and if it is then it has only one child
            //And that is rightChild
            if (deleteNode == root) root = deleteNode.getRightNode();

            else if (isLeftChild) parentNode.setLeftNode(deleteNode.getRightNode());

            else parentNode.setRightNode(deleteNode.getRightNode());

        } //Now comes the tricky part, what if the node has two children
        else {

            Node successor = getSuccessor(deleteNode);

            if (deleteNode == root) root = successor;

            else if (isLeftChild) parentNode.setLeftNode(successor);

            else parentNode.setRightNode(successor);

            successor.setLeftNode(deleteNode.getLeftNode());
        }


        return true;
    }

    private Node getSuccessor(Node deleteNode) {

        //To get the successor simply get the greater key than the key to be deleted
        //To do this, get the right child of node to be deleted
        //Then start getting the min key from noe on
        //How to that? Keep on getting ot traversing the leftChild until its next child is null

        Node successorParent = deleteNode;
        Node successor = deleteNode;

        Node currentNode = deleteNode.getRightNode();

        while (currentNode != null) {

            successorParent = successor;
            successor = currentNode;
            currentNode = currentNode.getLeftNode();
        }

        if (successor != deleteNode.getRightNode()) {

            successorParent.setLeftNode(successor.getRightNode());
            successor.setRightNode(deleteNode.getRightNode());
        }

        return successor;
    }
}
