package com.FamilyTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Relations class that uses the tree structure created using Node to find relations for the given node.
 */
class Relations {

    /**
     * Find brothers for the given node.
     * @param node
     * @return
     */
    public List<Node<Person>> findBrothers(Node<Person> node) {
        if (node.getParentNode() != null) {
            List<Node<Person>> children = node.getParentNode().getChildren();
            return children.stream()
                           .filter( c -> c.getNode().getName() != node.getNode().getName() && c.getNode().getSex().equalsIgnoreCase("male"))
                           .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Find sisters for the given node.
     * @param node
     * @return
     */
    public List<Node<Person>> findSisters(Node<Person> node) {
        if (node.getParentNode() != null) {
            List<Node<Person>> children = node.getParentNode().getChildren();
            return children.stream()
                    .filter( c -> c.getNode().getName() != node.getNode().getName() && c.getNode().getSex().equalsIgnoreCase("female"))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Find father for the given node.
     * @param node
     * @return
     */
    public List<Node<Person>> findFather(Node<Person> node) {
        List<Node<Person>> father = new ArrayList<>();
        Node<Person> parentNode = node.getParentNode();
        if (parentNode == null) {
            parentNode = node.getPartnerNode().getParentNode();
        }

        if (parentNode.getNode().getSex().equalsIgnoreCase("male")) {
            father.add(parentNode);
        } else {
            father.add(parentNode.getPartnerNode());
        }
        return father;
    }

    /**
     * Find mother for the given node.
     * @param node
     * @return
     */
    public List<Node<Person>> findMother(Node<Person> node) {
        List<Node<Person>> mother = new ArrayList<>();
        Node<Person> motherNode = node.getParentNode();
        if (motherNode == null) {
            motherNode = node.getPartnerNode().getParentNode();
        }
        if (motherNode.getNode().getSex().equalsIgnoreCase("female")) {
            mother.add(motherNode);
        } else {
            mother.add(motherNode.getPartnerNode());
        }
        return mother;
    }

    /**
     * Find children for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findChildren(Node<Person> node) {
        return node.getChildren();
    }

    /**
     * Find son of the given node.
     * @param node
     * @return
     */
    public List<Node<Person>> findSon(Node<Person> node) {
        return findChildren(node).stream()
                .filter(c -> c.getNode().getSex().equalsIgnoreCase("male"))
                .collect(Collectors.toList());
    }

    /**
     * Find daughter for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findDaughter(Node<Person> node) {
        return findChildren(node).stream()
                .filter(c -> c.getNode().getSex().equalsIgnoreCase("female"))
                .collect(Collectors.toList());
    }

    /**
     * Find grand daughter for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findGrandDaughter(Node<Person> node) {
        List<Node<Person>> daughters = new ArrayList<>();
        for (Node<Person> children : findChildren(node)) {
            daughters.addAll(
                    findChildren(children).stream()
                                          .filter(c -> c.getNode().getSex().equalsIgnoreCase("female"))
                                          .collect(Collectors.toList())
            );
        }
        return daughters;
    }

    /**
     * Find cousins for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findCousins(Node<Person> node) {
        List<Node<Person>> cousins = new ArrayList<>();
        for (Node brother : findBrothers(node.getParentNode())) {
            cousins.addAll(findChildren(brother));
        }

        for (Node sister : findSisters(node.getParentNode())) {
            cousins.addAll(findChildren(sister));
        }
        return cousins;
    }

    /**
     * Find brother in law for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findBrotherInLaw(Node<Person> node) {
        List<Node<Person>> brotherInLaws = new ArrayList<>();
        //Spouse’s brothers
        if (node.getPartnerNode() != null) {
            brotherInLaws.addAll(findBrothers(node.getPartnerNode()));
        }

        //Husbands of siblings
        for (Node<Person> sibling : findSisters(node)) {
            if (sibling.getPartnerNode() != null) {
                brotherInLaws.add(sibling.getPartnerNode());
            }
        }
        return brotherInLaws;
    }

    /**
     * Find sister in law for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findSisterInLaw(Node<Person> node) {
        List<Node<Person>> sisterInLaws = new ArrayList<>();
        //Spouse’s sisters,
        if (node.getPartnerNode() != null) {
            sisterInLaws.addAll(findSisters(node.getPartnerNode()));
        }

        //Wives of siblings
        for (Node<Person> sibling : findBrothers(node)) {
            if (sibling.getPartnerNode() != null) {
                sisterInLaws.add(sibling.getPartnerNode());
            }
        }
        return sisterInLaws;
    }

    /**
     * Find maternal aunt for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findMaternalAunt(Node<Person> node) {
        List<Node<Person>> maternalAunts = new ArrayList<>();
        Node<Person> mother = findMother(node).get(0);
        //Mother’s sister
        maternalAunts.addAll(findSisters(mother));

        //Mother’s sister-in-laws
        maternalAunts.addAll(findSisterInLaw(mother));
        return maternalAunts;
    }

    /**
     * Find paternal aunt for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findPaternalAunt(Node<Person> node) {
        List<Node<Person>> paternalAunts = new ArrayList<>();
        if (!findFather(node).isEmpty()) {
            Node<Person> father = findFather(node).get(0);
            //Father’ sister
            paternalAunts.addAll(findSisters(father));

            //Father’s sister-in-laws
            paternalAunts.addAll(findSisterInLaw(father));
        }
        return paternalAunts;
    }

    /**
     * Find maternal uncle for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findMaternalUncle(Node<Person> node) {
        List<Node<Person>> maternalUncle = new ArrayList<>();
        if (!findMother(node).isEmpty()) {
            Node<Person> mother = findMother(node).get(0);
            //Mother’s brother
            maternalUncle.addAll(findBrothers(mother));

            //Mother’s brother-in-laws
            maternalUncle.addAll(findBrotherInLaw(mother));
        }
        return maternalUncle;
    }

    /**
     * Find paternal uncle for the given node
     * @param node
     * @return
     */
    public List<Node<Person>> findPaternalUncle(Node<Person> node) {
        List<Node<Person>> paternalUncle = new ArrayList<>();
        Node<Person> father = findFather(node).get(0);
        //Father’s brothers
        paternalUncle.addAll(findBrothers(father));

        //Father’s brother-inlaws
        paternalUncle.addAll(findBrotherInLaw(father));
        return paternalUncle;
    }
}