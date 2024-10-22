package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;

/**
 * Unmodifiable view of an address book.
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the properties list for sale.
     * This list will not contain any duplicate properties for sale.
     */
    ObservableList<PropertyForSale> getPropertyForSaleList();

    /**
     * Returns an unmodifiable view of the properties list for rent.
     * This list will not contain any duplicate properties for rent.
     */
    ObservableList<PropertyForRent> getPropertyForRentList();
}
