package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.property.PropertyForRent;

/**
 * A utility class to help with building {@code PropertyForRent} objects.
 */
public class PropertyForRentBuilder {

    public static final String DEFAULT_ADDRESS = "123 Main St";
    public static final String DEFAULT_TOWN = "Jurong West";
    public static final String DEFAULT_PROPERTY_TYPE = "Apartment";
    public static final double DEFAULT_SIZE = 100.0;
    public static final int DEFAULT_BEDROOMS = 2;
    public static final int DEFAULT_BATHROOMS = 1;
    public static final double DEFAULT_PRICE = 1200.00;
    public static final LocalDate DEFAULT_AVAILABLE_FROM = LocalDate.now();

    private String address;
    private String town;
    private String propertyType;
    private double size;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private double price;
    private LocalDate availableFrom;

    /**
     * Creates a {@code PropertyForRentBuilder} with default property values.
     */
    public PropertyForRentBuilder() {
        address = DEFAULT_ADDRESS;
        town = DEFAULT_TOWN;
        propertyType = DEFAULT_PROPERTY_TYPE;
        size = DEFAULT_SIZE;
        numberOfBedrooms = DEFAULT_BEDROOMS;
        numberOfBathrooms = DEFAULT_BATHROOMS;
        price = DEFAULT_PRICE;
        availableFrom = DEFAULT_AVAILABLE_FROM;
    }

    /**
     * Sets the {@code address} of the {@code PropertyForRent} that is being built.
     *
     * @param address The property address.
     * @return This builder instance, with the address set.
     */
    public PropertyForRentBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Sets the {@code town} where the property is located.
     *
     * @param town The town name.
     * @return This builder instance, with the town set.
     */
    public PropertyForRentBuilder withTown(String town) {
        this.town = town;
        return this;
    }

    /**
     * Sets the {@code propertyType} of the property.
     *
     * @param propertyType The type of the property (e.g., apartment, house).
     * @return This builder instance, with the property type set.
     */
    public PropertyForRentBuilder withPropertyType(String propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    /**
     * Sets the {@code size} of the property in square meters.
     *
     * @param size The size of the property.
     * @return This builder instance, with the size set.
     */
    public PropertyForRentBuilder withSize(double size) {
        this.size = size;
        return this;
    }

    /**
     * Sets the number of {@code bedrooms} in the property.
     *
     * @param bedrooms The number of bedrooms.
     * @return This builder instance, with the number of bedrooms set.
     */
    public PropertyForRentBuilder withBedrooms(int bedrooms) {
        this.numberOfBedrooms = bedrooms;
        return this;
    }

    /**
     * Sets the number of {@code bathrooms} in the property.
     *
     * @param bathrooms The number of bathrooms.
     * @return This builder instance, with the number of bathrooms set.
     */
    public PropertyForRentBuilder withBathrooms(int bathrooms) {
        this.numberOfBathrooms = bathrooms;
        return this;
    }

    /**
     * Sets the {@code price} of the property.
     *
     * @param price The rental price of the property.
     * @return This builder instance, with the price set.
     */
    public PropertyForRentBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * Sets the {@code availableFrom} date when the property is available for rent.
     *
     * @param availableFrom The availability date.
     * @return This builder instance, with the availability date set.
     */
    public PropertyForRentBuilder withAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
        return this;
    }

    /**
     * Builds and returns a {@code PropertyForRent} object with the specified details.
     *
     * @return A {@code PropertyForRent} instance with the configured values.
     */
    public PropertyForRent build() {
        return new PropertyForRent(address, town, propertyType, size,
                numberOfBedrooms, numberOfBathrooms, price, availableFrom);
    }
}
