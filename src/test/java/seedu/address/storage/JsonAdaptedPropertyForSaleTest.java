package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPropertyForSale.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.PropertyForSale;

public class JsonAdaptedPropertyForSaleTest {

    private static final String VALID_ADDRESS = "123 Example Street";
    private static final String VALID_TOWN = "Example Town";
    private static final String VALID_PROPERTY_TYPE = "Condo";
    private static final double VALID_SIZE = 100.5;
    private static final int VALID_NUM_BEDROOMS = 3;
    private static final int VALID_NUM_BATHROOMS = 2;
    private static final double VALID_PRICE = 500000.00;
    private static final String VALID_LISTING_DATE = LocalDate.now().toString();

    private static final String INVALID_LISTING_DATE = "not-a-date";

    @Test
    public void toModelType_validPropertyDetails_returnsPropertyForSale() throws Exception {
        JsonAdaptedPropertyForSale property = new JsonAdaptedPropertyForSale(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_LISTING_DATE);

        PropertyForSale expectedProperty = new PropertyForSale(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, LocalDate.parse(VALID_LISTING_DATE));

        assertEquals(expectedProperty, property.toModelType());
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPropertyForSale property = new JsonAdaptedPropertyForSale(
                null, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_LISTING_DATE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "address");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullTown_throwsIllegalValueException() {
        JsonAdaptedPropertyForSale property = new JsonAdaptedPropertyForSale(
                VALID_ADDRESS, null, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_LISTING_DATE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "town");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_nullPropertyType_throwsIllegalValueException() {
        JsonAdaptedPropertyForSale property = new JsonAdaptedPropertyForSale(
                VALID_ADDRESS, VALID_TOWN, null, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, VALID_LISTING_DATE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "propertyType");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }

    @Test
    public void toModelType_invalidListingDate_throwsIllegalValueException() {
        JsonAdaptedPropertyForSale property = new JsonAdaptedPropertyForSale(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, INVALID_LISTING_DATE);

        assertThrows(IllegalValueException.class, property::toModelType);
    }

    @Test
    public void toModelType_nullListingDate_throwsIllegalValueException() {
        JsonAdaptedPropertyForSale property = new JsonAdaptedPropertyForSale(
                VALID_ADDRESS, VALID_TOWN, VALID_PROPERTY_TYPE, VALID_SIZE,
                VALID_NUM_BEDROOMS, VALID_NUM_BATHROOMS, VALID_PRICE, null);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "listingDate");
        assertThrows(IllegalValueException.class, expectedMessage, property::toModelType);
    }
}
