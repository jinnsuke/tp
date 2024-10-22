package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.property.PropertyForSale;

/**
 * A utility class to help with building {@code PropertyForSale} objects.
 */
public class PropertyForSaleBuilder {

    public static final String DEFAULT_ADDRESS = "456 Orchard Rd";
    public static final String DEFAULT_TOWN = "Orchard";
    public static final String DEFAULT_PROPERTY_TYPE = "Condominium";
    public static final double DEFAULT_SIZE = 200.0;
    public static final int DEFAULT_BEDROOMS = 3;
    public static final int DEFAULT_BATHROOMS = 2;
    public static final double DEFAULT_PRICE = 1500000.00;
    public static final LocalDate DEFAULT_LISTING_DATE = LocalDate.now();

    private String address;
    private String town;
    private String propertyType;
    private double size;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private double price;
    private LocalDate listingDate;

    /**
     * Creates a {@code PropertyForSaleBuilder} with default property values.
     */
    public PropertyForSaleBuilder() {
        address = DEFAULT_ADDRESS;
        town = DEFAULT_TOWN;
        propertyType = DEFAULT_PROPERTY_TYPE;
        size = DEFAULT_SIZE;
        numberOfBedrooms = DEFAULT_BEDROOMS;
        numberOfBathrooms = DEFAULT_BATHROOMS;
        price = DEFAULT_PRICE;
        listingDate = DEFAULT_LISTING_DATE;
    }

    /**
     * Sets the {@code address} of the {@code PropertyForSale} that is being built.
     *
     * @param address The property address.
     * @return This builder instance, with the address set.
     */
    public PropertyForSaleBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Sets the {@code town} where the property is located.
     *
     * @param town The town name.
     * @return This builder instance, with the town set.
     */
    public PropertyForSaleBuilder withTown(String town) {
        this.town = town;
        return this;
    }

    /**
     * Sets the {@code propertyType} of the property.
     *
     * @param propertyType The type of the property (e.g., condominium, house).
     * @return This builder instance, with the property type set.
     */
    public PropertyForSaleBuilder withPropertyType(String propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    /**
     * Sets the {@code size} of the property in square meters.
     *
     * @param size The size of the property.
     * @return This builder instance, with the size set.
     */
    public PropertyForSaleBuilder withSize(double size) {
        this.size = size;
        return this;
    }

    /**
     * Sets the number of {@code bedrooms} in the property.
     *
     * @param bedrooms The number of bedrooms.
     * @return This builder instance, with the number of bedrooms set.
     */
    public PropertyForSaleBuilder withBedrooms(int bedrooms) {
        this.numberOfBedrooms = bedrooms;
        return this;
    }

    /**
     * Sets the number of {@code bathrooms} in the property.
     *
     * @param bathrooms The number of bathrooms.
     * @return This builder instance, with the number of bathrooms set.
     */
    public PropertyForSaleBuilder withBathrooms(int bathrooms) {
        this.numberOfBathrooms = bathrooms;
        return this;
    }

    /**
     * Sets the {@code price} of the property.
     *
     * @param price The selling price of the property.
     * @return This builder instance, with the price set.
     */
    public PropertyForSaleBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * Sets the {@code listingDate} when the property was listed for sale.
     *
     * @param listingDate The date the property was listed.
     * @return This builder instance, with the listing date set.
     */
    public PropertyForSaleBuilder withListingDate(LocalDate listingDate) {
        this.listingDate = listingDate;
        return this;
    }

    /**
     * Builds and returns a {@code PropertyForSale} object with the specified details.
     *
     * @return A {@code PropertyForSale} instance with the configured values.
     */
    public PropertyForSale build() {
        return new PropertyForSale(address, town, propertyType, size,
                numberOfBedrooms, numberOfBathrooms, price, listingDate);
    }
}
