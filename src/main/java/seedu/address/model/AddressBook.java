package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;
import seedu.address.model.property.UniquePropertyForRentList;
import seedu.address.model.property.UniquePropertyForSaleList;

/**
 * Wraps all data at the address-book level.
 * Duplicates are not allowed (by .isSamePerson comparison).
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniquePropertyForSaleList propertiesForSale; // New list for properties for sale
    private final UniquePropertyForRentList propertiesForRent; // New list for properties for rent
        /*
         * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
         * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
         *
         * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
         *   among constructors.
         */
        {
            persons = new UniquePersonList();
            propertiesForSale = new UniquePropertyForSaleList();
            propertiesForRent = new UniquePropertyForRentList();
        }

        public AddressBook() {}

        /**
         * Creates an AddressBook using the Persons and Properties in the {@code toBeCopied}.
         */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Replaces the contents of the property list for sale with {@code properties}.
     * {@code properties} must not contain duplicate properties for sale.
     */
    public void setPropertiesForSale(List<PropertyForSale> properties) {
        this.propertiesForSale.setProperties(properties);
    }

    /**
     * Replaces the contents of the property list for rent with {@code properties}.
     * {@code properties} must not contain duplicate properties for rent.
     */
    public void setPropertiesForRent(List<PropertyForRent> properties) {
        this.propertiesForRent.setProperties(properties);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPropertiesForSale(newData.getPropertyForSaleList());
        setPropertiesForRent(newData.getPropertyForRentList());
    }

    /**
     * Returns true if a property for sale with the same identity as {@code propertyForSale} exists in the address book.
     */
    public boolean hasPropertyForSale(PropertyForSale propertyForSale) {
        requireNonNull(propertyForSale);
        return propertiesForSale.contains(propertyForSale);
    }

    /**
     * Adds a property for sale to the address book.
     * The property must not already exist in the address book.
     */
    public void addPropertyForSale(PropertyForSale propertyForSale) {
        propertiesForSale.add(propertyForSale);
    }

    /**
     * Returns true if a property for rent with the same identity as {@code propertyForRent} exists in the address book.
     */
    public boolean hasPropertyForRent(PropertyForRent propertyForRent) {
        requireNonNull(propertyForRent);
        return propertiesForRent.contains(propertyForRent);
    }

    /**
     * Adds a property for rent to the address book.
     * The property must not already exist in the address book.
     */
    public void addPropertyForRent(PropertyForRent propertyForRent) {
        propertiesForRent.add(propertyForRent);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("propertiesForSale", propertiesForSale)
                .add("propertiesForRent", propertiesForRent)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    // New methods to retrieve property lists
    public ObservableList<PropertyForSale> getPropertyForSaleList() {
        return propertiesForSale.asUnmodifiableObservableList();
    }

    public ObservableList<PropertyForRent> getPropertyForRentList() {
        return propertiesForRent.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && propertiesForSale.equals(otherAddressBook.propertiesForSale)
                && propertiesForRent.equals(otherAddressBook.propertiesForRent);
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + propertiesForSale.hashCode() + propertiesForRent.hashCode();
    }

    /**
     * Sorts the persons in the address book in ascending order based on their names.
     */
    public void sortPersonsAsc() {
        persons.setAsc(); // Call the setAsc method from UniquePersonList
    }

    /**
     * Sorts the persons in the address book in descending order based on their names.
     */
    public void sortPersonsDesc() {
        persons.setDesc(); // Call the setDesc method from UniquePersonList
    }
}
