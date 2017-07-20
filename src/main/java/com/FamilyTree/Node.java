package com.FamilyTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class
 * @param <T>
 */
public class Node<T> {
    private T node;
    private Node<T> parentNode;
    private Node<T> partnerNode;
    private List<Node<T>> children = new ArrayList<>();

    /**
     * Constructor
     * @param node
     */
    public Node (T node) {
        this.node = node;
    }

    /**
     * Getter for partner node
     * @return
     */
    public Node<T> getPartnerNode () {
        return partnerNode;
    }

    /**
     * Getter for parent
     * @return
     */
    public Node<T> getParentNode () {
        return parentNode;

    }

    /**
     * Get the current node
     * @return
     */
    public T getNode () {
        return node;
    }

    /**
     * Setter for parent
     * @param parent
     */
    public void setParent (Node parent) {
        this.parentNode = parent;
    }

    /**
     * Setter for partner
     * @param partner
     */
    public void setPartner (Node partner) {
        this.partnerNode = partner;
    }

    /**
     * Getter for children
     * @return
     */
    public List<Node<T>> getChildren () {
        return children;
    }

    /**
     * Add children
     * @param node
     */
    public void addChildren(Node<T> node) {
        this.children.add(node);
    }
}