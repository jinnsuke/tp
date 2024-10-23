package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_RENT_AMY;
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_RENT_BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.testutil.PropertyForRentBuilder;

public class UniquePropertyForRentListTest {

    private final UniquePropertyForRentList uniquePropertyForRentList = new UniquePropertyForRentList();

    @Test
    public void contains_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForRentList.contains(null));
    }

    @Test
    public void contains_propertyNotInList_returnsFalse() {
        assertFalse(uniquePropertyForRentList.contains(PROPERTY_FOR_RENT_AMY));
    }

    @Test
    public void contains_propertyInList_returnsTrue() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        assertTrue(uniquePropertyForRentList.contains(PROPERTY_FOR_RENT_AMY));
    }

    @Test
    public void add_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForRentList.add(null));
    }

    @Test
    public void add_duplicateProperty_throwsDuplicatePropertyException() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        assertThrows(DuplicatePropertyException.class, () ->
                uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY));
    }

    @Test
    public void setProperty_nullTargetProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForRentList.setProperty(null, PROPERTY_FOR_RENT_AMY));
    }

    @Test
    public void setProperty_nullEditedProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForRentList.setProperty(PROPERTY_FOR_RENT_AMY, null));
    }

    @Test
    public void setProperty_targetPropertyNotInList_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyForRentList.setProperty(PROPERTY_FOR_RENT_AMY, PROPERTY_FOR_RENT_AMY));
    }

    @Test
    public void setProperty_editedPropertyIsSameProperty_success() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        uniquePropertyForRentList.setProperty(PROPERTY_FOR_RENT_AMY, PROPERTY_FOR_RENT_AMY);
        UniquePropertyForRentList expectedUniquePropertyForRentList = new UniquePropertyForRentList();
        expectedUniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        assertEquals(expectedUniquePropertyForRentList, uniquePropertyForRentList);
    }

    @Test
    public void setProperty_editedPropertyHasSameIdentity_success() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        PropertyForRent editedPropertyForRentAmy =
                new PropertyForRentBuilder(PROPERTY_FOR_RENT_AMY)
                        .withAddress("New Address")
                        .build();
        uniquePropertyForRentList.setProperty(PROPERTY_FOR_RENT_AMY, editedPropertyForRentAmy);
        UniquePropertyForRentList expectedUniquePropertyForRentList = new UniquePropertyForRentList();
        expectedUniquePropertyForRentList.add(editedPropertyForRentAmy);
        assertEquals(expectedUniquePropertyForRentList, uniquePropertyForRentList);
    }

    @Test
    public void setProperty_editedPropertyHasDifferentIdentity_success() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        uniquePropertyForRentList.setProperty(PROPERTY_FOR_RENT_AMY, PROPERTY_FOR_RENT_BOB);
        UniquePropertyForRentList expectedUniquePropertyForRentList = new UniquePropertyForRentList();
        expectedUniquePropertyForRentList.add(PROPERTY_FOR_RENT_BOB);
        assertEquals(expectedUniquePropertyForRentList, uniquePropertyForRentList);
    }

    @Test
    public void setProperty_editedPropertyHasNonUniqueIdentity_throwsDuplicatePropertyException() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_BOB);
        assertThrows(DuplicatePropertyException.class, () ->
                uniquePropertyForRentList.setProperty(PROPERTY_FOR_RENT_AMY, PROPERTY_FOR_RENT_BOB));
    }

    @Test
    public void remove_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForRentList.remove(null));
    }

    @Test
    public void remove_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () ->
                uniquePropertyForRentList.remove(PROPERTY_FOR_RENT_AMY));
    }

    @Test
    public void remove_existingProperty_removesProperty() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        uniquePropertyForRentList.remove(PROPERTY_FOR_RENT_AMY);
        UniquePropertyForRentList expectedUniquePropertyForRentList = new UniquePropertyForRentList();
        assertEquals(expectedUniquePropertyForRentList, uniquePropertyForRentList);
    }

    @Test
    public void setProperties_nullUniquePropertyForRentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForRentList.setProperties((UniquePropertyForRentList) null));
    }

    @Test
    public void setProperties_uniquePropertyForRentList_replacesOwnListWithProvidedUniquePropertyForRentList() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        UniquePropertyForRentList expectedUniquePropertyForRentList = new UniquePropertyForRentList();
        expectedUniquePropertyForRentList.add(PROPERTY_FOR_RENT_BOB);
        uniquePropertyForRentList.setProperties(expectedUniquePropertyForRentList);
        assertEquals(expectedUniquePropertyForRentList, uniquePropertyForRentList);
    }

    @Test
    public void setProperties_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePropertyForRentList.setProperties((List<PropertyForRent>) null));
    }

    @Test
    public void setProperties_list_replacesOwnListWithProvidedList() {
        uniquePropertyForRentList.add(PROPERTY_FOR_RENT_AMY);
        List<PropertyForRent> propertyList = Collections.singletonList(PROPERTY_FOR_RENT_BOB);
        uniquePropertyForRentList.setProperties(propertyList);
        UniquePropertyForRentList expectedUniquePropertyForRentList = new UniquePropertyForRentList();
        expectedUniquePropertyForRentList.add(PROPERTY_FOR_RENT_BOB);
        assertEquals(expectedUniquePropertyForRentList, uniquePropertyForRentList);
    }

    @Test
    public void setProperties_listWithDuplicateProperties_throwsDuplicatePropertyException() {
        List<PropertyForRent> listWithDuplicateProperties = Arrays.asList(
                PROPERTY_FOR_RENT_AMY, PROPERTY_FOR_RENT_AMY);
        assertThrows(DuplicatePropertyException.class, () ->
                uniquePropertyForRentList.setProperties(listWithDuplicateProperties));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniquePropertyForRentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePropertyForRentList.asUnmodifiableObservableList().toString(),
                uniquePropertyForRentList.toString());
    }
}
