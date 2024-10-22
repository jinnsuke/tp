package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Test class for PropertyForSale.
 */
class PropertyForSaleTest {

    @Test
    void testPropertyForSaleCreation() {
        PropertyForSale propertyForSale = new PropertyForSale(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                500000.0,
                LocalDate.of(2024, 10, 1)
        );

        // Check if fields are correctly assigned
        assertEquals("123 Main St", propertyForSale.getAddress());
        assertEquals("Central Town", propertyForSale.getTown());
        assertEquals("Apartment", propertyForSale.getPropertyType());
        assertEquals(85.5, propertyForSale.getSize());
        assertEquals(3, propertyForSale.getNumberOfBedrooms());
        assertEquals(2, propertyForSale.getNumberOfBathrooms());
        assertEquals(500000.0, propertyForSale.getPrice());
        assertEquals(LocalDate.of(2024, 10, 1), propertyForSale.getListingDate());
    }

    @Test
    void testEqualsSameProperties() {
        PropertyForSale property1 = new PropertyForSale(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                500000.0,
                LocalDate.of(2024, 10, 1)
        );

        PropertyForSale property2 = new PropertyForSale(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                500000.0,
                LocalDate.of(2024, 10, 1)
        );

        // Ensure that two properties with identical values are considered equal
        assertEquals(property1, property2);
    }

    @Test
    void testEqualsDifferentProperties() {
        PropertyForSale property1 = new PropertyForSale(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                500000.0,
                LocalDate.of(2024, 10, 1)
        );

        PropertyForSale property2 = new PropertyForSale(
                "456 Side St",
                "West Town",
                "House",
                120.0,
                4,
                3,
                750000.0,
                LocalDate.of(2024, 10, 1)
        );

        // Ensure that two different properties are not equal
        assertNotEquals(property1, property2);
    }

    @Test
    void testToString() {
        PropertyForSale property = new PropertyForSale(
                "123 Main St",
                "Central Town",
                "Apartment",
                85.5,
                3,
                2,
                500000.0,
                LocalDate.of(2024, 10, 1)
        );

        String expected = "Property[address=123 Main St, town=Central Town, type=Apartment, "
                + "size=85.50, bedrooms=3, bathrooms=2, price=500000.00], Listing Date: 2024-10-01";

        assertEquals(expected, property.toString());
    }
}
