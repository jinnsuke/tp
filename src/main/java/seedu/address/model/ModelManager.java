package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<PropertyForSale> filteredPropertiesForSale;
    private final FilteredList<PropertyForRent> filteredPropertiesForRent;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredPropertiesForSale = new FilteredList<>(this.addressBook.getPropertyForSaleList());
        this.filteredPropertiesForRent = new FilteredList<>(this.addressBook.getPropertyForRentList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Sorting Methods ===========================================================================

    /**
     * Sorts the persons in the address book in ascending order based on their names.
     */
    public void sortPersonsAsc() {
        addressBook.sortPersonsAsc(); // Call the sortPersonsAsc method from AddressBook
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS); // Refresh the filtered list after sorting
    }

    /**
     * Sorts the persons in the address book in descending order based on their names.
     */
    public void sortPersonsDesc() {
        addressBook.sortPersonsDesc(); // Call the sortPersonsDesc method from AddressBook
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS); // Refresh the filtered list after sorting
    }

    //=========== Property Methods ===========================================================================

    @Override
    public boolean hasPropertyForSale(PropertyForSale propertyForSale) {
        requireNonNull(propertyForSale);
        return addressBook.hasPropertyForSale(propertyForSale);
    }

    @Override
    public void addPropertyForSale(PropertyForSale propertyForSale) {
        addressBook.addPropertyForSale(propertyForSale);
        updateFilteredPropertyForSaleList(PREDICATE_SHOW_ALL_PROPERTIES_FOR_SALE);
    }

    @Override
    public boolean hasPropertyForRent(PropertyForRent propertyForRent) {
        requireNonNull(propertyForRent);
        return addressBook.hasPropertyForRent(propertyForRent);
    }

    @Override
    public void addPropertyForRent(PropertyForRent propertyForRent) {
        addressBook.addPropertyForRent(propertyForRent);
        updateFilteredPropertyForRentList(PREDICATE_SHOW_ALL_PROPERTIES_FOR_RENT);
    }

    @Override
    public void updateFilteredPropertyForSaleList(Predicate<PropertyForSale> predicate) {
        requireNonNull(predicate);
        filteredPropertiesForSale.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPropertyForRentList(Predicate<PropertyForRent> predicate) {
        requireNonNull(predicate);
        filteredPropertiesForRent.setPredicate(predicate);
    }

    @Override
    public ObservableList<PropertyForSale> getFilteredPropertyForSaleList() {
        return filteredPropertiesForSale; // Assuming you have a field that stores filtered properties for sale
    }

    @Override
    public ObservableList<PropertyForRent> getFilteredPropertyForRentList() {
        return filteredPropertiesForRent; // Assuming you have a field that stores filtered properties for rent
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }
}
