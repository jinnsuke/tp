package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;

/**
 * Manages storage of AddressBook data and user preferences in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefsStorage}.
     *
     * @param addressBookStorage The storage for the address book.
     * @param userPrefsStorage The storage for user preferences.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    /**
     * Returns the file path of the user preferences file.
     *
     * @return The file path of the user preferences.
     */
    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    /**
     * Reads user preferences from the storage.
     *
     * @return An optional containing user preferences if found, otherwise empty.
     * @throws DataLoadingException If there is an error loading the user preferences.
     */
    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    /**
     * Saves the given user preferences to the storage.
     *
     * @param userPrefs The user preferences to save.
     * @throws IOException If there is an error saving the user preferences.
     */
    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ AddressBook methods ==============================

    /**
     * Returns the file path of the address book file.
     *
     * @return The file path of the address book.
     */
    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    /**
     * Reads the address book from the storage.
     *
     * @return An optional containing the address book if found, otherwise empty.
     * @throws DataLoadingException If there is an error loading the address book.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    /**
     * Reads the address book from the specified file path.
     *
     * @param filePath The file path to read the address book from.
     * @return An optional containing the address book if found, otherwise empty.
     * @throws DataLoadingException If there is an error loading the address book.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    /**
     * Saves the given address book to the storage.
     *
     * @param addressBook The address book to save.
     * @throws IOException If there is an error saving the address book.
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    /**
     * Saves the given address book to the specified file path.
     *
     * @param addressBook The address book to save.
     * @param filePath The file path to save the address book to.
     * @throws IOException If there is an error saving the address book.
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Property methods ==============================

    /**
     * Reads properties available for rent from the storage.
     *
     * @return A list of properties for rent.
     * @throws DataLoadingException If there is an error loading properties for rent.
     */
    @Override
    public List<PropertyForRent> readPropertiesForRent() throws DataLoadingException {
        logger.fine("Attempting to read properties for rent.");
        return addressBookStorage.readPropertiesForRent();
    }

    /**
     * Reads properties available for sale from the storage.
     *
     * @return A list of properties for sale.
     * @throws DataLoadingException If there is an error loading properties for sale.
     */
    @Override
    public List<PropertyForSale> readPropertiesForSale() throws DataLoadingException {
        logger.fine("Attempting to read properties for sale.");
        return addressBookStorage.readPropertiesForSale();
    }

    /**
     * Saves the given properties for rent to the storage.
     *
     * @param properties The properties for rent to save.
     * @throws IOException If there is an error saving the properties for rent.
     */
    public void savePropertiesForRent(List<PropertyForRent> properties) throws IOException {
        logger.fine("Attempting to save properties for rent.");
        addressBookStorage.savePropertiesForRent(properties);
    }

    /**
     * Saves the given properties for sale to the storage.
     *
     * @param properties The properties for sale to save.
     * @throws IOException If there is an error saving the properties for sale.
     */
    public void savePropertiesForSale(List<PropertyForSale> properties) throws IOException {
        logger.fine("Attempting to save properties for sale.");
        addressBookStorage.savePropertiesForSale(properties);
    }
}
