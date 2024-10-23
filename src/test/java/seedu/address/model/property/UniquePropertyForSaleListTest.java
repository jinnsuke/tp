package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_SALE_AMY; // Update with your actual property references
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_SALE_BOB; // Update with your actual property references

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.testutil.PropertyForSaleBuilder; // Update this if necessary

public class UniquePropertyForSaleListTest {

    private final UniquePropertyForSaleList uniquePropertyForSaleList =
            new UniquePropertyForSaleList();

    @Test
    public void contains_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForSaleList.contains(null));
    }

    @Test
    public void contains_propertyNotInList_returnsFalse() {
        assertFalse(uniquePropertyForSaleList.contains(PROPERTY_FOR_SALE_AMY));
    }

    @Test
    public void contains_propertyInList_returnsTrue() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        assertTrue(uniquePropertyForSaleList.contains(PROPERTY_FOR_SALE_AMY));
    }

    @Test
    public void add_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForSaleList.add(null));
    }

    @Test
    public void add_duplicateProperty_throwsDuplicatePropertyException() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        assertThrows(DuplicatePropertyException.class, () ->
                uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY));
    }

    @Test
    public void setProperty_nullTargetProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForSaleList.setProperty(null,
                        PROPERTY_FOR_SALE_AMY));
    }

    @Test
    public void setProperty_nullEditedProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForSaleList.setProperty(
                        PROPERTY_FOR_SALE_AMY, null));
    }

    @Test
    public void setProperty_targetPropertyNotInList_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyForSaleList.setProperty(
                        PROPERTY_FOR_SALE_AMY, PROPERTY_FOR_SALE_AMY));
    }

    @Test
    public void setProperty_editedPropertyIsSameProperty_success() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        uniquePropertyForSaleList.setProperty(PROPERTY_FOR_SALE_AMY,
                PROPERTY_FOR_SALE_AMY);
        UniquePropertyForSaleList expectedUniquePropertyForSaleList =
                new UniquePropertyForSaleList();
        expectedUniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        assertEquals(expectedUniquePropertyForSaleList, uniquePropertyForSaleList);
    }

    @Test
    public void setProperty_editedPropertyHasSameIdentity_success() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        PropertyForSale editedPropertyForSale =
                new PropertyForSaleBuilder(PROPERTY_FOR_SALE_AMY)
                        .withAddress("New Address")
                        .build();
        uniquePropertyForSaleList.setProperty(PROPERTY_FOR_SALE_AMY,
                editedPropertyForSale);
        UniquePropertyForSaleList expectedUniquePropertyForSaleList =
                new UniquePropertyForSaleList();
        expectedUniquePropertyForSaleList.add(editedPropertyForSale);
        assertEquals(expectedUniquePropertyForSaleList, uniquePropertyForSaleList);
    }

    @Test
    public void setProperty_editedPropertyHasDifferentIdentity_success() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        uniquePropertyForSaleList.setProperty(PROPERTY_FOR_SALE_AMY,
                PROPERTY_FOR_SALE_BOB);
        UniquePropertyForSaleList expectedUniquePropertyForSaleList =
                new UniquePropertyForSaleList();
        expectedUniquePropertyForSaleList.add(PROPERTY_FOR_SALE_BOB);
        assertEquals(expectedUniquePropertyForSaleList, uniquePropertyForSaleList);
    }

    @Test
    public void setProperty_editedPropertyHasNonUniqueIdentity_throwsDuplicatePropertyException() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_BOB);
        assertThrows(DuplicatePropertyException.class, () ->
                uniquePropertyForSaleList.setProperty(
                        PROPERTY_FOR_SALE_AMY, PROPERTY_FOR_SALE_BOB));
    }

    @Test
    public void remove_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForSaleList.remove(null));
    }

    @Test
    public void remove_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyForSaleList.remove(PROPERTY_FOR_SALE_AMY));
    }

    @Test
    public void remove_existingProperty_removesProperty() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        uniquePropertyForSaleList.remove(PROPERTY_FOR_SALE_AMY);
        UniquePropertyForSaleList expectedUniquePropertyForSaleList =
                new UniquePropertyForSaleList();
        assertEquals(expectedUniquePropertyForSaleList, uniquePropertyForSaleList);
    }

    @Test
    public void setProperties_nullUniquePropertyForSaleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForSaleList.setProperties(
                        (UniquePropertyForSaleList) null));
    }

    @Test
    public void setProperties_uniquePropertyForSaleList_replacesOwnListWithProvidedUniquePropertyForSaleList() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        UniquePropertyForSaleList expectedUniquePropertyForSaleList =
                new UniquePropertyForSaleList();
        expectedUniquePropertyForSaleList.add(PROPERTY_FOR_SALE_BOB);
        uniquePropertyForSaleList.setProperties(expectedUniquePropertyForSaleList);
        assertEquals(expectedUniquePropertyForSaleList, uniquePropertyForSaleList);
    }

    @Test
    public void setProperties_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForSaleList.setProperties(
                        (List<PropertyForSale>) null));
    }

    @Test
    public void setProperties_list_replacesOwnListWithProvidedList() {
        uniquePropertyForSaleList.add(PROPERTY_FOR_SALE_AMY);
        List<PropertyForSale> propertyList =
                Collections.singletonList(PROPERTY_FOR_SALE_BOB);
        uniquePropertyForSaleList.setProperties(propertyList);
        UniquePropertyForSaleList expectedUniquePropertyForSaleList =
                new UniquePropertyForSaleList();
        expectedUniquePropertyForSaleList.add(PROPERTY_FOR_SALE_BOB);
        assertEquals(expectedUniquePropertyForSaleList, uniquePropertyForSaleList);
    }

    @Test
    public void setProperties_listWithDuplicateProperties_throwsDuplicatePropertyException() {
        List<PropertyForSale> listWithDuplicateProperties =
                Arrays.asList(PROPERTY_FOR_SALE_AMY, PROPERTY_FOR_SALE_AMY);
        assertThrows(DuplicatePropertyException.class, () ->
                uniquePropertyForSaleList.setProperties(listWithDuplicateProperties));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniquePropertyForSaleList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePropertyForSaleList.asUnmodifiableObservableList().toString(),
                uniquePropertyForSaleList.toString());
    }
}
