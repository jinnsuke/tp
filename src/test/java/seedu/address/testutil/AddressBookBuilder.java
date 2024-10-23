package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Adds a property for sale to the address book.
     *
     * @param propertyForSale The property for sale to be added.
     * @return The current instance of {@code AddressBookBuilder} for method chaining.
     */
    public AddressBookBuilder withPropertyForSale(PropertyForSale propertyForSale) {
        addressBook.addPropertyForSale(propertyForSale);
        return this;
    }

    /**
     * Adds a property for rent to the address book.
     *
     * @param propertyForRent The property for rent to be added.
     * @return The current instance of {@code AddressBookBuilder} for method chaining.
     */
    public AddressBookBuilder withPropertyForRent(PropertyForRent propertyForRent) {
        addressBook.addPropertyForRent(propertyForRent);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
