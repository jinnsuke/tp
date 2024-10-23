package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPropertyForRent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.PropertyForRent;

public class JsonAdaptedPropertyForRentTest {

    private static final String VALID_ADDRESS = "456 Rental Avenue";
    private static final String VALID_TOWN = "Rental Town";
    private static final String VALID_PROPERTY_TYPE = "Apartment";
    private static final double VALID_SIZE = 80.0;
    private static final int VALID_NUM_BEDROOMS = 2;
    private static final int VALID_NUM_BATHROOMS = 1;
    private static final double VALID_PRICE = 1500.00;
    private static final String VALID_AVAILABLE_FROM = LocalDate.now().toString();

    private static final String INVALID_AVAILABLE_FROM = "not-a-date";

    @Test
    public void toModelType_validPropertyDetails_returnsPropertyForRent() throws Exception {
        JsonAdaptedPropertyForRent property = new JsonAdaptedPropertyForRent(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_AVAILABLE_FROM);

        PropertyForRent expectedProperty = new PropertyForRent(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, LocalDate.parse(VALID_AVAILABLE_FROM));

        assertEquals(expectedProperty, property.toModelType());
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPropertyForRent property = new JsonAdaptedPropertyForRent(
                null, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_AVAILABLE_FROM);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "address");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullTown_throwsIllegalValueException() {
        JsonAdaptedPropertyForRent property = new JsonAdaptedPropertyForRent(
                VALID_ADDRESS, null, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_AVAILABLE_FROM);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "town");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullPropertyType_throwsIllegalValueException() {
        JsonAdaptedPropertyForRent property = new JsonAdaptedPropertyForRent(
                VALID_ADDRESS, VALID_TOWN, null, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_AVAILABLE_FROM);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "propertyType");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidAvailableFrom_throwsIllegalValueException() {
        JsonAdaptedPropertyForRent property = new JsonAdaptedPropertyForRent(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, INVALID_AVAILABLE_FROM);

        assertThrows(IllegalValueException.class, property::toModelType);
    }

    @Test
    public void toModelType_nullAvailableFrom_throwsIllegalValueException() {
        JsonAdaptedPropertyForRent property = new JsonAdaptedPropertyForRent(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, null);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "availableFrom");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }
}
