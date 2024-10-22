package seedu.address.model.property;

import java.time.LocalDate;

/**
 * Represents a Property that is for rent.
 */
public class PropertyForRent extends Property {
    private final LocalDate availableFrom; // The date the property is available for rent

    /**
     * Constructs a {@code PropertyForRent} with the specified details.
     *
     * @param address The address of the property.
     * @param town The town where the property is located.
     * @param propertyType The type of the property (e.g., apartment, house).
     * @param size The size of the property in square meters.
     * @param numberOfBedrooms The number of bedrooms in the property.
     * @param numberOfBathrooms The number of bathrooms in the property.
     * @param price The price of the property.
     * @param availableFrom The date the property is available for rent.
     */
    public PropertyForRent(String address, String town, String propertyType, double size,
                           int numberOfBedrooms, int numberOfBathrooms, double price,
                           LocalDate availableFrom) {
        super(address, town, propertyType, size, numberOfBedrooms, numberOfBathrooms, price);
        this.availableFrom = availableFrom;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    @Override
    public String toString() {
        return String.format("%s, Available From: %s", super.toString(), availableFrom);
    }
}
