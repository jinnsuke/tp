package seedu.address.testutil;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;

/**
 * A default model stub that has all of the methods failing.
 *
 * <p>This class is used for testing purposes to simulate the behavior of a model.
 * It provides a stub implementation for the {@link Model} interface, allowing for
 * the addition of mock properties for sale and rent while throwing errors for all other operations.</p>
 */
public class ModelStub implements Model {
    private final List<PropertyForSale> propertiesForSale;
    private final List<PropertyForRent> propertiesForRent;

    /**
     * Constructs a {@code ModelStub} with empty lists for properties for sale and rent.
     */
    public ModelStub() {
        propertiesForSale = new ArrayList<>();
        propertiesForRent = new ArrayList<>();
        // You can add mock properties here for testing if necessary
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortPersonsDesc() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortPersonsAsc() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPropertyForSale(PropertyForSale propertyForSale) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPropertyForSale(PropertyForSale propertyForSale) {
        propertiesForSale.add(propertyForSale); // Store mock property for sale
    }

    @Override
    public boolean hasPropertyForRent(PropertyForRent propertyForRent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPropertyForRent(PropertyForRent propertyForRent) {
        propertiesForRent.add(propertyForRent); // Store mock property for rent
    }

    @Override
    public void updateFilteredPropertyForSaleList(Predicate<PropertyForSale> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPropertyForRentList(Predicate<PropertyForRent> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns an observable list of properties for sale in the address book.
     *
     * @return An observable list of {@code PropertyForSale} objects.
     */
    @Override
    public ObservableList<PropertyForSale> getFilteredPropertyForSaleList() {
        return FXCollections.observableArrayList(propertiesForSale);
    }

    /**
     * Returns an observable list of properties for rent in the address book.
     *
     * @return An observable list of {@code PropertyForRent} objects.
     */
    @Override
    public ObservableList<PropertyForRent> getFilteredPropertyForRentList() {
        return FXCollections.observableArrayList(propertiesForRent);
    }
}
