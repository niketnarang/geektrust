package com.FamilyTree;

/**
 * Person class that represents each entity in the tree
 */
class Person {
    private final String name;
    private final String sex;

    public String getName () {
        return name;
    }

    public String getSex () {
        return sex;
    }

    public Person (String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
