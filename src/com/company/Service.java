package com.company;

import java.util.List;

public interface Service {

    void printContacts();

    List<Person> printPersons();

    void addPerson();

    void deletePerson(int key);

    void editPerson(int key);

}