package seedu.address.model.property;

import java.time.LocalDate;

/**
 * Represents a Property that is for sale.
 */
public class PropertyForSale extends Property {
    private final LocalDate listingDate; // The date the property was listed for sale

    /**
     * Constructs a {@code PropertyForSale} with the specified details.
     *
     * @param address The address of the property.
     * @param town The town where the property is located.
     * @param propertyType The type of the property (e.g., apartment, house).
     * @param size The size of the property in square meters.
     * @param numberOfBedrooms The number of bedrooms in the property.
     * @param numberOfBathrooms The number of bathrooms in the property.
     * @param price The price of the property.
     * @param listingDate The date the property was listed for sale.
     */
    public PropertyForSale(String address, String town, String propertyType, double size,
                           int numberOfBedrooms, int numberOfBathrooms, double price,
                           LocalDate listingDate) {
        super(address, town, propertyType, size, numberOfBedrooms, numberOfBathrooms, price);
        this.listingDate = listingDate;
    }

    public LocalDate getListingDate() {
        return listingDate;
    }

    @Override
    public String toString() {
        return String.format("%s, Listing Date: %s", super.toString(), listingDate);
    }
}
