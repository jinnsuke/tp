package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The {@code PropertyList} class stores a list of properties available for sale.
 * It provides functionality to add new properties, retrieve all properties,
 * search for a specific property by index, and remove a property by its object.
 */
public class PropertyList {
    private final List<PropertyForSale> properties;

    /**
     * Constructs an empty {@code PropertyList}.
     */
    public PropertyList() {
        this.properties = new ArrayList<>();
    }

    /**
     * Constructs a {@code PropertyList} with a pre-existing list of properties.
     *
     * @param properties The initial list of properties to be added.
     */
    public PropertyList(List<PropertyForSale> properties) {
        this.properties = new ArrayList<>(properties); // Copy to avoid external modification
    }

    /**
     * Adds a new property to the list.
     *
     * @param property The {@code PropertyForSale} to be added.
     */
    public void addProperty(PropertyForSale property) {
        if (property == null) {
            throw new IllegalArgumentException("Property cannot be null.");
        }
        properties.add(property);
    }

    /**
     * Removes the specified property from the list.
     *
     * @param property The {@code PropertyForSale} to be removed.
     * @return {@code true} if the property was removed, {@code false} if it was not found.
     */
    public boolean removeProperty(PropertyForSale property) {
        if (property == null) {
            throw new IllegalArgumentException("Property cannot be null.");
        }
        if (!properties.contains(property)) {
            throw new IllegalArgumentException("Property not found in the list.");
        }
        return properties.remove(property);
    }

    /**
     * Retrieves the property at the specified index.
     *
     * @param index The index of the property to retrieve.
     * @return The {@code PropertyForSale} at the specified index.
     * @throws NoSuchElementException If the index is out of bounds.
     */
    public PropertyForSale getProperty(int index) {
        if (index < 0 || index >= properties.size()) {
            throw new NoSuchElementException("No property found at index: " + index);
        }
        return properties.get(index);
    }

    /**
     * Retrieves all properties in the list.
     *
     * @return A list of all {@code PropertyForSale} objects.
     */
    public List<PropertyForSale> getAllProperties() {
        return new ArrayList<>(properties); // Return a copy to prevent modification
    }

    /**
     * Returns the number of properties in the list.
     *
     * @return The size of the property list.
     */
    public int size() {
        return properties.size();
    }

    /**
     * Checks if the list of properties is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return properties.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PropertyList)) {
            return false;
        }
        PropertyList otherList = (PropertyList) other;
        return properties.equals(otherList.properties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Property List:\n");
        for (int i = 0; i < properties.size(); i++) {
            sb.append(i + 1).append(". ").append(properties.get(i)).append("\n");
        }
        return sb.toString();
    }
}
