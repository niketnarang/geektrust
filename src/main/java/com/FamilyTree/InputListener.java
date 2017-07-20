package com.FamilyTree;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Input Listener class
 */
class InputListener {
    private static final String EXIT = "Exit";
    private static final String BROTHERS = "BROTHERS";
    private static final String SISTERS = "SISTERS";
    private static final String GRAND_DAUGHTER = "GRAND-DAUGHTER";
    private static final String SON = "SON";
    private static final String DAUGHTER = "DAUGHTER";
    private static final String CHILDREN = "CHILDREN";
    private static final String FATHER = "FATHER";
    private static final String MOTHER = "MOTHER";
    private static final String COUSINS = "COUSINS";
    private static final String BROTHER_IN_LAW = "BROTHER-IN-LAW";
    private static final String SISTER_IN_LAW = "SISTER-IN-LAW";
    private static final String MATERNAL_AUNT = "MATERNAL-AUNT";
    private static final String PATERNAL_AUNT =  "PATERNAL-AUNT";
    private static final String MATERNAL_UNCLE = "MATERNAL-UNCLE";
    private static final String PATERNAL_UNCLE = "PATERNAL-UNCLE";

    private static Scanner scanner;
    private static Relations relations;

    /**
     * Constructor
     * @param scanner
     * @param familyTree
     */
    public InputListener (Scanner scanner, FamilyTree familyTree) {
        InputListener.scanner = scanner;
        InputListener.relations = familyTree.getRelations();
    }

    /**
     * Method to parse input message and do action accordingly
     */
    public void parseInput() {
        printWelcomeMessage();
        String input = scanner.nextLine();
        while (!(input).equalsIgnoreCase(EXIT)) {
            String[] inputArray = input.split(";");
            String person = parsePerson(inputArray);
            String relation = parseRelation(inputArray);
            findRelations(FamilyTree.getPersonNode(person), relation.toLowerCase());
            System.out.print("Input: ");
            input = scanner.nextLine();
        }
    }

    /**
     * Parse the input message to get Person
     * @param inputArray input
     * @return Person
     */
    private String parsePerson(String[] inputArray) {
        return (inputArray[0].split("=")[1]).trim();
    }

    /**
     * parse the input message to get Relation
     * @param inputArray input
     * @return Relation
     */
    private String parseRelation(String[] inputArray) {
        return (inputArray[1].split("=")[1]).trim();
    }

    /**
     * Based on the parsed input, this class retrieves the relations for the given input.
     * @param person Node<Person>
     * @param relation relation
     */
    private void findRelations(Node person, String relation) {
        switch (relation.toUpperCase()) {
            case CHILDREN:
                prettyPrint(relations.findChildren(person));
                break;
            case BROTHERS:
                prettyPrint(relations.findBrothers(person));
                break;
            case SISTERS:
                prettyPrint(relations.findSisters(person));
                break;
            case FATHER:
                prettyPrint(relations.findFather(person));
                break;
            case MOTHER:
                prettyPrint(relations.findMother(person));
                break;
            case SON:
                prettyPrint(relations.findSon(person));
                break;
            case DAUGHTER:
                prettyPrint(relations.findDaughter(person));
                break;
            case GRAND_DAUGHTER:
                prettyPrint(relations.findGrandDaughter(person));
                break;
            case COUSINS:
                prettyPrint(relations.findCousins(person));
                break;
            case BROTHER_IN_LAW:
                prettyPrint(relations.findBrotherInLaw(person));
                break;
            case SISTER_IN_LAW:
                prettyPrint(relations.findSisterInLaw(person));
                break;
            case MATERNAL_AUNT:
                prettyPrint(relations.findMaternalAunt(person));
                break;
            case PATERNAL_AUNT:
                prettyPrint(relations.findPaternalAunt(person));
                break;
            case MATERNAL_UNCLE:
                prettyPrint(relations.findMaternalUncle(person));
                break;
            case PATERNAL_UNCLE:
                prettyPrint(relations.findPaternalUncle(person));
                break;
            default:
                System.out.println("Not a valid Input");
        }
    }

    /**
     * Utility method to print the relations
     * @param nodes List of nodes.
     */
    private void prettyPrint(List<Node<Person>> nodes) {
        System.out.println(
                nodes.stream()
                     .map(c-> c.getNode().getName())
                     .collect(Collectors.joining(", "))
        );
    }

    /**
     * Utility method to print welcome message.
     */
    private void printWelcomeMessage() {
        System.out.println("Welcome to King Shan Family Tree !!!");
        System.out.println("(Type 'exit' to end the program)");
        System.out.println("Input: ");
    }
}