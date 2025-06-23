package Model;

import java.util.ArrayList;
import java.util.List;
/** Класс список для контактов */
public  class AddressBook {
    private List<Person> persons;
    /** создание списка */
    public AddressBook() {
        this.persons = new ArrayList<>();
    }
    /** Добавление контакта */
    public void addPerson(Person person) {
        persons.add(person);
    }
    /** получение списка контактов */
    public List<Person> getPersons() {
        return persons;
    }
}


