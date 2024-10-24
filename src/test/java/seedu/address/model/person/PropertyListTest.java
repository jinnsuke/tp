package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PropertyListTest {

    private PropertyList propertyList;

    @BeforeEach
    public void setUp() {
        propertyList = new PropertyList();
    }

    @Test
    public void addProperty_validProperty_success() {
        PropertyForSale property = PropertyForSale.of("123 Main St", "Townsville", "Condo", 50.0, 2, 1, 300000.0);
        propertyList.addProperty(property);

        // Verify the property was added
        List<PropertyForSale> properties = propertyList.getAllProperties();
        assertEquals(1, properties.size());
        assertEquals(property, properties.get(0));
    }

    @Test
    public void addProperty_nullProperty_throwsNullPointerException() {
        // Expect NullPointerException when adding null property
        assertThrows(IllegalArgumentException.class, () -> propertyList.addProperty(null));
    }

    @Test
    public void getAllProperties_noProperties_returnsEmptyList() {
        // Verify that no properties return an empty list
        List<PropertyForSale> properties = propertyList.getAllProperties();
        assertTrue(properties.isEmpty());
    }

    @Test
    public void getAllProperties_withProperties_success() {
        PropertyForSale property1 = PropertyForSale.of("123 Main St", "Townsville", "Condo", 50.0, 2, 1, 300000.0);
        PropertyForSale property2 = PropertyForSale.of("456 Side St", "Townsville", "HDB", 70.0, 3, 2, 500000.0);

        propertyList.addProperty(property1);
        propertyList.addProperty(property2);

        // Verify that both properties are returned
        List<PropertyForSale> properties = propertyList.getAllProperties();
        assertEquals(2, properties.size());
        assertEquals(property1, properties.get(0));
        assertEquals(property2, properties.get(1));
    }

    @Test
    public void getAllProperties_afterAddingProperties_success() {
        PropertyForSale property = PropertyForSale.of("123 Main St", "Townsville", "Condo", 50.0, 2, 1, 300000.0);
        propertyList.addProperty(property);

        // Verify the properties are as expected
        List<PropertyForSale> properties = propertyList.getAllProperties();
        assertEquals(1, properties.size());
        assertEquals(property, properties.get(0));
    }

    @Test
    public void removeProperty_validProperty_success() {
        PropertyForSale property = PropertyForSale.of("123 Main St", "Townsville", "Condo", 50.0, 2, 1, 300000.0);
        propertyList.addProperty(property);
        propertyList.removeProperty(property);

        // Verify the property was removed
        List<PropertyForSale> properties = propertyList.getAllProperties();
        assertTrue(properties.isEmpty());
    }

    @Test
    public void removeProperty_propertyNotInList_throwsIllegalArgumentException() {
        PropertyForSale property = PropertyForSale.of("123 Main St", "Townsville", "Condo", 50.0, 2, 1, 300000.0);

        // Expect IllegalArgumentException when removing a property that is not in the list
        assertThrows(IllegalArgumentException.class, () -> propertyList.removeProperty(property));
    }
}
