package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;

/**
 * A list of properties for sale that enforces uniqueness between its elements and does not allow nulls.
 * A property is considered unique by comparing using {@code PropertyForSale#isSameProperty(PropertyForSale)}.
 * Supports a minimal set of list operations.
 */
public class UniquePropertyForSaleList implements Iterable<PropertyForSale> {

    private final ObservableList<PropertyForSale> internalList = FXCollections.observableArrayList();
    private final ObservableList<PropertyForSale> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains an equivalent property as the given argument.
     */
    public boolean contains(PropertyForSale toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a property to the list.
     * The property must not already exist in the list.
     */
    public void add(PropertyForSale toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePropertyException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the list.
     * The property identity of {@code editedProperty} must not be the same as another existing property in the list.
     */
    public void setProperty(PropertyForSale target, PropertyForSale editedProperty) {
        requireAllNonNull(target, editedProperty);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PropertyNotFoundException();
        }

        if (!target.equals(editedProperty) && contains(editedProperty)) {
            throw new DuplicatePropertyException();
        }

        internalList.set(index, editedProperty);
    }

    /**
     * Removes the equivalent property from the list.
     * The property must exist in the list.
     */
    public void remove(PropertyForSale toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PropertyNotFoundException();
        }
    }

    public void setProperties(UniquePropertyForSaleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code properties}.
     * {@code properties} must not contain duplicate properties.
     */
    public void setProperties(List<PropertyForSale> properties) {
        requireAllNonNull(properties);
        if (!propertiesAreUnique(properties)) {
            throw new DuplicatePropertyException();
        }

        internalList.setAll(properties);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PropertyForSale> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PropertyForSale> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePropertyForSaleList)) {
            return false;
        }

        UniquePropertyForSaleList otherUniquePropertyList = (UniquePropertyForSaleList) other;
        return internalList.equals(otherUniquePropertyList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code properties} contains only unique properties.
     */
    private boolean propertiesAreUnique(List<PropertyForSale> properties) {
        for (int i = 0; i < properties.size() - 1; i++) {
            for (int j = i + 1; j < properties.size(); j++) {
                if (properties.get(i).equals(properties.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
