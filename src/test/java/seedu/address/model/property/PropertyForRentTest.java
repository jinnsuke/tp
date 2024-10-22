package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * Test class for PropertyForRent.
 */
class PropertyForRentTest {

    @Test
    void testPropertyForRentCreation() {
        PropertyForRent propertyForRent = new PropertyForRent(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                2000.0,
                Optional.of("Great view"),
                LocalDate.of(2024, 10, 1)
        );

        // Check if fields are correctly assigned
        assertEquals("123 Main St", propertyForRent.getAddress());
        assertEquals("Central Town", propertyForRent.getTown());
        assertEquals("Apartment", propertyForRent.getPropertyType());
        assertEquals(85.5, propertyForRent.getSize());
        assertEquals(3, propertyForRent.getNumberOfBedrooms());
        assertEquals(2, propertyForRent.getNumberOfBathrooms());
        assertEquals(2000.0, propertyForRent.getPrice());
        assertEquals(Optional.of("Great view"), propertyForRent.getRemark());
        assertEquals(LocalDate.of(2024, 10, 1), propertyForRent.getAvailableFrom());
    }

    @Test
    void testEqualsSameProperties() {
        PropertyForRent property1 = new PropertyForRent(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                2000.0,
                Optional.of("Great view"),
                LocalDate.of(2024, 10, 1)
        );

        PropertyForRent property2 = new PropertyForRent(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                2000.0,
                Optional.of("Great view"),
                LocalDate.of(2024, 10, 1)
        );

        // Ensure that two properties with identical values are considered equal
        assertEquals(property1, property2);
    }

    @Test
    void testEqualsDifferentProperties() {
        PropertyForRent property1 = new PropertyForRent(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                2000.0,
                Optional.of("Great view"),
                LocalDate.of(2024, 10, 1)
        );

        PropertyForRent property2 = new PropertyForRent(
                "456 Side St",
                "West Town",
                "House",
                120.0,
                4,
                3,
                3000.0,
                Optional.empty(),
                LocalDate.of(2024, 10, 1)
        );

        // Ensure that two different properties are not equal
        assertNotEquals(property1, property2);
    }

    @Test
    void testToString() {
        PropertyForRent property = new PropertyForRent(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                2000.0,
                Optional.of("Great view"),
                LocalDate.of(2024, 10, 1)
        );

        String expected = "Property[address=123 Main St, town=Central Town, type=Apartment, "
                + "size=85.50, bedrooms=3, bathrooms=2, price=2000.00, remark=Great view], Available From: 2024-10-01";

        assertEquals(expected, property.toString());
    }

    @Test
    void testToStringWithEmptyRemark() {
        PropertyForRent property = new PropertyForRent(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                2000.0,
                Optional.empty(),
                LocalDate.of(2024, 10, 1)
        );

        String expected = "Property[address=123 Main St, town=Central Town, type=Apartment, "
                + "size=85.50, bedrooms=3, bathrooms=2, price=2000.00, remark=No remark], Available From: 2024-10-01";

        assertEquals(expected, property.toString());
    }
}
