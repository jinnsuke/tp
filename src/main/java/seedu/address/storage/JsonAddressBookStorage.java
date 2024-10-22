package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;

/**
 * JsonAddressBookStorage class.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);
    private Path filePath;

    /**
     * Constructs a JsonAddressBookStorage with the specified file path.
     *
     * @param filePath Path to the file where the address book is stored.
     */
    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the address book.
     *
     * @return the file path of the address book.
     */
    @Override
    public Path getAddressBookFilePath() {
        return filePath;
    }

    /**
     * Reads the address book from the file path and returns it as an Optional.
     *
     * @return an Optional containing the ReadOnlyAddressBook if it exists, or an empty Optional if not.
     * @throws DataLoadingException if there is an error loading the address book.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Reads the address book from the specified file path.
     *
     * @param filePath the path of the file to read from.
     * @return an Optional containing the ReadOnlyAddressBook if it exists, or an empty Optional if not.
     * @throws DataLoadingException if there is an error loading the address book.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        // Attempts to read the JSON file and convert it to a JsonSerializableAddressBook object.
        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            // Converts the JsonSerializableAddressBook to a ReadOnlyAddressBook.
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    /**
     * Saves the provided ReadOnlyAddressBook to the file path.
     *
     * @param addressBook the ReadOnlyAddressBook to save.
     * @throws IOException if there is an error saving the address book.
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Saves the provided ReadOnlyAddressBook to the specified file path.
     *
     * @param addressBook the ReadOnlyAddressBook to save.
     * @param filePath the path of the file to save to.
     * @throws IOException if there is an error saving the address book.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        // Creates the file if it does not exist.
        FileUtil.createIfMissing(filePath);
        // Serializes the address book and saves it to the file.
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

    /**
     * Saves the provided list of PropertyForRent to the file path.
     *
     * @param properties the list of PropertyForRent to save.
     * @throws IOException if there is an error saving the properties.
     */
    @Override
    public void savePropertiesForRent(List<PropertyForRent> properties) throws IOException {
        requireNonNull(properties);
        // Converts the list of PropertyForRent to a list of JsonAdaptedPropertyForRent.
        List<JsonAdaptedPropertyForRent> jsonProperties = properties.stream()
                .map(JsonAdaptedPropertyForRent::new)
                .collect(Collectors.toList());
        JsonSerializableAddressBook jsonAddressBook = new JsonSerializableAddressBook();
        // Sets the properties for rent in the JsonSerializableAddressBook.
        jsonAddressBook.setPropertiesForRent(jsonProperties);
        // Saves the JsonSerializableAddressBook to the file.
        JsonUtil.saveJsonFile(jsonAddressBook, filePath);
    }

    /**
     * Saves the provided list of PropertyForSale to the file path.
     *
     * @param properties the list of PropertyForSale to save.
     * @throws IOException if there is an error saving the properties.
     */
    @Override
    public void savePropertiesForSale(List<PropertyForSale> properties) throws IOException {
        requireNonNull(properties);
        // Converts the list of PropertyForSale to a list of JsonAdaptedPropertyForSale.
        List<JsonAdaptedPropertyForSale> jsonProperties = properties.stream()
                .map(JsonAdaptedPropertyForSale::new)
                .collect(Collectors.toList());
        JsonSerializableAddressBook jsonAddressBook = new JsonSerializableAddressBook();
        // Sets the properties for sale in the JsonSerializableAddressBook.
        jsonAddressBook.setPropertiesForSale(jsonProperties);
        // Saves the JsonSerializableAddressBook to the file.
        JsonUtil.saveJsonFile(jsonAddressBook, filePath);
    }

    /**
     * Reads properties for rent from the JSON file and returns a list of PropertyForRent.
     *
     * @return a list of PropertyForRent read from the file.
     * @throws DataLoadingException if there is an error loading properties for rent.
     */
    public List<PropertyForRent> readPropertiesForRent() throws DataLoadingException {
        JsonSerializableAddressBook jsonAddressBook = JsonUtil.readJsonFile(filePath, JsonSerializableAddressBook.class)
                .orElseThrow(() -> new RuntimeException("Failed to load properties for rent"));

        List<PropertyForRent> properties = new ArrayList<>();
        // Converts each JsonAdaptedPropertyForRent to a PropertyForRent.
        for (JsonAdaptedPropertyForRent adaptedProperty : jsonAddressBook.getPropertiesForRent()) {
            try {
                properties.add(adaptedProperty.toModelType());
            } catch (IllegalValueException e) {
                logger.warning("Illegal value found in property for rent: " + e.getMessage());
                throw new RuntimeException("Failed to load properties for rent due to illegal value", e);
            }
        }
        return properties;
    }

    /**
     * Reads properties for sale from the JSON file and returns a list of PropertyForSale.
     *
     * @return a list of PropertyForSale read from the file.
     * @throws DataLoadingException if there is an error loading properties for sale.
     */
    public List<PropertyForSale> readPropertiesForSale() throws DataLoadingException {
        JsonSerializableAddressBook jsonAddressBook = JsonUtil.readJsonFile(filePath, JsonSerializableAddressBook.class)
                .orElseThrow(() -> new RuntimeException("Failed to load properties for sale"));

        List<PropertyForSale> properties = new ArrayList<>();
        // Converts each JsonAdaptedPropertyForSale to a PropertyForSale.
        for (JsonAdaptedPropertyForSale adaptedProperty : jsonAddressBook.getPropertiesForSale()) {
            try {
                properties.add(adaptedProperty.toModelType());
            } catch (IllegalValueException e) {
                logger.warning("Illegal value found in property for sale: " + e.getMessage());
                throw new RuntimeException("Failed to load properties for sale due to illegal value", e);
            }
        }
        return properties;
    }

    /**
     * Helper method to get the file path for properties for rent.
     *
     * @return the file path for properties for rent.
     */
    private Path getPropertiesForRentFilePath() {
        return filePath.resolve("propertiesForRent.json");
    }

    /**
     * Helper method to get the file path for properties for sale.
     *
     * @return the file path for properties for sale.
     */
    private Path getPropertiesForSaleFilePath() {
        return filePath.resolve("propertiesForSale.json");
    }
}
