package com.FamilyTree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Family Tree
 */
public class FamilyTreeTest {
    private static Relations relations;
    public FamilyTreeTest () {
        relations = new FamilyTree(new Relations()).getRelations();
    }

    @Test
    public void testFindBrothers() {
        Node<Person> ish = FamilyTree.getPersonNode("Ish");
        assertEquals(2, relations.findBrothers(ish).size());
    }

    @Test
    public void testFindSisters() {
        Node<Person> ish = FamilyTree.getPersonNode("Ish");
        assertEquals(1, relations.findSisters(ish).size());
    }

    @Test
    public void testFindFather() {
        Node<Person> ish = FamilyTree.getPersonNode("Ish");
        assertEquals("King Shan", relations.findFather(ish).get(0).getNode().getName());
    }

    @Test
    public void testFindMother() {
        Node<Person> ish = FamilyTree.getPersonNode("Ish");
        assertEquals("Queen Anga", relations.findMother(ish).get(0).getNode().getName());
    }

    @Test
    public void testFindChildren() {
        Node<Person> kingShan = FamilyTree.getPersonNode("King Shan");
        assertEquals(4, relations.findChildren(kingShan).size());
    }

    @Test
    public void testFindSon() {
        Node<Person> kingShan = FamilyTree.getPersonNode("King Shan");
        assertEquals(3, relations.findSon(kingShan).size());
    }

    @Test
    public void testFindDaughter() {
        Node<Person> kingShan = FamilyTree.getPersonNode("King Shan");
        assertEquals(1, relations.findDaughter(kingShan).size());
    }

    @Test
    public void testFindGrandDaughter() {
        Node<Person> kingShan = FamilyTree.getPersonNode("King Shan");
        assertEquals(2, relations.findGrandDaughter(kingShan).size());
    }

    @Test
    public void testFindCousins() {
        Node<Person> drita = FamilyTree.getPersonNode("Drita");
        assertEquals(5, relations.findCousins(drita).size());
    }

    @Test
    public void testFindBrotherInLaw() {
        Node<Person> ambi = FamilyTree.getPersonNode("Ambi");
        assertEquals(2, relations.findBrotherInLaw(ambi).size());

        Node<Person> jaya = FamilyTree.getPersonNode("Jaya");
        assertEquals(1, relations.findBrotherInLaw(jaya).size());
    }

    @Test
    public void testFindSisterInLaw() {
        Node<Person> ambi = FamilyTree.getPersonNode("Ambi");
        assertEquals(1, relations.findSisterInLaw(ambi).size());

        Node<Person> jaya = FamilyTree.getPersonNode("Jaya");
        assertEquals(0, relations.findSisterInLaw(jaya).size());
    }

    @Test
    public void testFindMaternalAunt() {
        Node<Person> lavnya = FamilyTree.getPersonNode("Lavnya");
        assertEquals(1, relations.findMaternalAunt(lavnya).size());

        Node<Person> jaya = FamilyTree.getPersonNode("Jaya");
        assertEquals(0, relations.findSisterInLaw(jaya).size());
    }

    @Test
    public void testFindPaternalAunt() {
        Node<Person> drita = FamilyTree.getPersonNode("Drita");
        assertEquals(2, relations.findPaternalAunt(drita).size());

        Node<Person> jaya = FamilyTree.getPersonNode("Jaya");
        assertEquals(2, relations.findPaternalAunt(jaya).size());
    }

    @Test
    public void testFindMaternalUncle() {
        Node<Person> satvy = FamilyTree.getPersonNode("Satvy");
        assertEquals(3, relations.findMaternalUncle(satvy).size());

        Node<Person> jaya = FamilyTree.getPersonNode("Jaya");
        assertEquals(2, relations.findMaternalUncle(jaya).size());
    }

    @Test
    public void testFindPaternalUncle() {
        Node<Person> satvy = FamilyTree.getPersonNode("Satvy");
        assertEquals(3, relations.findPaternalUncle(satvy).size());

        Node<Person> jaya = FamilyTree.getPersonNode("Jaya");
        assertEquals(3, relations.findPaternalUncle(jaya).size());
    }
}