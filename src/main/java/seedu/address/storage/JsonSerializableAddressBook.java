package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.property.PropertyForRent; // Import your PropertyForRent model
import seedu.address.model.property.PropertyForSale; // Import your PropertyForSale model

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "Properties list contains duplicate property(ies).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedPropertyForSale> propertiesForSale = new ArrayList<>();
    private final List<JsonAdaptedPropertyForRent> propertiesForRent = new ArrayList<>();

    // Default constructor for creating an empty instance
    public JsonSerializableAddressBook() {
        // No-arg constructor for Jackson to create an empty object
    }

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        // Initialize persons
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));

        // Initialize properties for sale
        propertiesForSale.addAll(source.getPropertyForSaleList().stream()
                .map(JsonAdaptedPropertyForSale::new)
                .collect(Collectors.toList()));

        // Initialize properties for rent
        propertiesForRent.addAll(source.getPropertyForRentList().stream()
                .map(JsonAdaptedPropertyForRent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        // Convert persons
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        // Convert properties for sale
        for (JsonAdaptedPropertyForSale jsonAdaptedProperty : propertiesForSale) {
            PropertyForSale property = jsonAdaptedProperty.toModelType();
            if (addressBook.hasPropertyForSale(property)) { // Ensure you have this method
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROPERTY);
            }
            addressBook.addPropertyForSale(property); // Ensure you have this method
        }

        // Convert properties for rent
        for (JsonAdaptedPropertyForRent jsonAdaptedProperty : propertiesForRent) {
            PropertyForRent property = jsonAdaptedProperty.toModelType();
            if (addressBook.hasPropertyForRent(property)) { // Ensure you have this method
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROPERTY);
            }
            addressBook.addPropertyForRent(property); // Ensure you have this method
        }

        return addressBook;
    }

    // Getters for properties
    public List<JsonAdaptedPropertyForRent> getPropertiesForRent() {
        return new ArrayList<>(propertiesForRent); // Return a copy to avoid external modification
    }

    public List<JsonAdaptedPropertyForSale> getPropertiesForSale() {
        return new ArrayList<>(propertiesForSale); // Return a copy to avoid external modification
    }

    // Setters for properties
    public void setPropertiesForRent(List<JsonAdaptedPropertyForRent> properties) {
        propertiesForRent.clear(); // Clear existing properties
        if (properties != null) {
            propertiesForRent.addAll(properties); // Add all new properties
        }
    }

    public void setPropertiesForSale(List<JsonAdaptedPropertyForSale> properties) {
        propertiesForSale.clear(); // Clear existing properties
        if (properties != null) {
            propertiesForSale.addAll(properties); // Add all new properties
        }
    }
}
