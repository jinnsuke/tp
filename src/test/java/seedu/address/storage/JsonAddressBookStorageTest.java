package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.property.PropertyForRent;
import seedu.address.model.property.PropertyForSale;
import seedu.address.testutil.TypicalProperties;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite existing file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }

    // New Tests for PropertyForRent and PropertyForSale

    @Test
    public void saveAndReadPropertiesForRent_success() throws Exception {
        Path filePath = testFolder.resolve("TempPropertiesForRent.json");
        JsonAddressBookStorage storage = new JsonAddressBookStorage(filePath);

        List<PropertyForRent> properties = TypicalProperties.getTypicalPropertiesForRent();

        // Save properties and read them back
        storage.savePropertiesForRent(properties);
        List<PropertyForRent> readProperties = storage.readPropertiesForRent();

        assertEquals(properties, readProperties);
    }

    @Test
    public void saveAndReadPropertiesForSale_success() throws Exception {
        Path filePath = testFolder.resolve("TempPropertiesForSale.json");
        JsonAddressBookStorage storage = new JsonAddressBookStorage(filePath);

        List<PropertyForSale> properties = TypicalProperties.getTypicalPropertiesForSale();

        // Save properties and read them back
        storage.savePropertiesForSale(properties);
        List<PropertyForSale> readProperties = storage.readPropertiesForSale();

        assertEquals(properties, readProperties);
    }

    @Test
    public void readPropertiesForRent_invalidProperty_throwsRuntimeException() {
        Path filePath = addToTestDataPathIfNotNull("invalidPropertiesForRent.json");
        JsonAddressBookStorage storage = new JsonAddressBookStorage(filePath);

        assertThrows(RuntimeException.class, storage::readPropertiesForRent);
    }

    @Test
    public void readPropertiesForSale_invalidProperty_throwsRuntimeException() {
        Path filePath = addToTestDataPathIfNotNull("invalidPropertiesForSale.json");
        JsonAddressBookStorage storage = new JsonAddressBookStorage(filePath);

        assertThrows(RuntimeException.class, storage::readPropertiesForSale);
    }
}
