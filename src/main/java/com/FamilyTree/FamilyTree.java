package com.FamilyTree;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class
 */
class FamilyTree {
    private final static String FAMILY_TREE_FILE_PATH = "src/main/java/json/ShanFamilyTree.json";
    private static Map<String, Node> nodeMap = new HashMap<>();
    private static Relations relations;

    /**
     * Constructor
     * @param relations relations
     */
    public FamilyTree (Relations relations) {
        FamilyTree.relations = relations;
        try {
            createFamilyTree();
        } catch (IOException ex) {
            System.out.println("Not able to read the family tree JSON file.");
        }
    }

    /**
     * Getter for relations.
     * @return relations
     */
    public Relations getRelations() {
        return FamilyTree.relations;
    }

    /**
     * Main method
     * @param args arguments
     * @throws IOException
     */
    public static void main (String[] args) throws IOException {
        InputListener inputListener = new InputListener(new Scanner(System.in), new FamilyTree(new Relations()));
        inputListener.parseInput();
    }

    /**
     * Returns Node object for the person name
     * @param person person name
     * @return Node<Person>
     */
    public static Node getPersonNode(String person) {
        return FamilyTree.nodeMap.get(person);
    }

    /**
     * Method to create Family Tree
     * @throws IOException
     */
    private void createFamilyTree() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonRootNode = mapper.readTree(new File(FAMILY_TREE_FILE_PATH));
        populateTree(null, jsonRootNode);
    }

    /**
     * Internal utility method to populate the tree structure.
     * @param parentNode
     * @param jsonNode
     * @return
     */
    private Node populateTree(Node parentNode, JsonNode jsonNode) {
        Node<Person> node = new Node(new Person(jsonNode.path("name").asText(), jsonNode.path("gender").asText()));
        nodeMap.putIfAbsent(jsonNode.path("name").asText(), node);
        JsonNode partnerJsonNode = jsonNode.path("partner");
        if (!partnerJsonNode.isMissingNode()) {
            Node partnerNode = new Node(new Person(partnerJsonNode.path("name").asText(), partnerJsonNode.path("gender").asText()));
            node.setPartner(partnerNode);
            partnerNode.setPartner(node);
            nodeMap.putIfAbsent(partnerJsonNode.path("name").asText(), partnerNode);
        }
        if (jsonNode.path("children").size() > 0) {
            for (JsonNode childNode : jsonNode.path("children")) {
                populateTree(node, childNode);
            }
        }
        node.setParent(parentNode);
        if (parentNode != null) {
            parentNode.addChildren(node);
        }
        return node;
    }
}