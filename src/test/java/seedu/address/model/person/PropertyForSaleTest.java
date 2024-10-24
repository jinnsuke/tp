package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@code PropertyForSale}.
 */
public class PropertyForSaleTest {

    @Test
    public void of_validAttributes_success() {
        PropertyForSale property = PropertyForSale.of(
                "123 Main Street", "Springfield", "Condo",
                150.75, 3, 2, 500000.00
        );

        String expected = "Property at 123 Main Street, Springfield (Condo): "
                + "150.75 sqm, 3 bed, 2 bath - $500000.00";
        assertEquals(expected, property.toString());
    }

    @Test
    public void of_nullAddress_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                PropertyForSale.of(null, "Springfield", "Condo", 150.75, 3, 2, 500000.00)
        );
    }

    @Test
    public void of_nullTown_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                PropertyForSale.of("123 Main Street", null, "Condo", 150.75, 3, 2, 500000.00)
        );
    }

    @Test
    public void of_nullPropertyType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                PropertyForSale.of("123 Main Street", "Springfield", null, 150.75, 3, 2, 500000.00)
        );
    }

    @Test
    public void of_zeroSize_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                PropertyForSale.of("123 Main Street", "Springfield", "Condo", 0, 3, 2, 500000.00)
        );
    }

    @Test
    public void of_negativeBedrooms_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                PropertyForSale.of("123 Main Street", "Springfield", "Condo", 150.75, -1, 2, 500000.00)
        );
    }

    @Test
    public void of_negativeBathrooms_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                PropertyForSale.of("123 Main Street", "Springfield", "Condo", 150.75, 3, -1, 500000.00)
        );
    }

    @Test
    public void of_negativePrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                PropertyForSale.of("123 Main Street", "Springfield", "Condo", 150.75, 3, 2, -500000.00)
        );
    }
}
