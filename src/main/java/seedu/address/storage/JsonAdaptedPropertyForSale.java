package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.PropertyForSale;

/**
 * Jackson-friendly version of {@link PropertyForSale}.
 */
class JsonAdaptedPropertyForSale {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PropertyForSale's %s field is missing!";
    private final String address;
    private final String town;
    private final String propertyType;
    private final double size;
    private final int numberOfBedrooms;
    private final int numberOfBathrooms;
    private final double price;
    private final String listingDate; // String representation of the LocalDate

    /**
     * Constructs a {@code JsonAdaptedPropertyForSale} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedPropertyForSale(@JsonProperty("address") String address,
                                      @JsonProperty("town") String town,
                                      @JsonProperty("propertyType") String propertyType,
                                      @JsonProperty("size") double size,
                                      @JsonProperty("numberOfBedrooms") int numberOfBedrooms,
                                      @JsonProperty("numberOfBathrooms") int numberOfBathrooms,
                                      @JsonProperty("price") double price,
                                      @JsonProperty("listingDate") String listingDate) {
        this.address = address;
        this.town = town;
        this.propertyType = propertyType;
        this.size = size;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.price = price;
        this.listingDate = listingDate;
    }

    /**
     * Converts a given {@code PropertyForSale} into this class for Jackson use.
     */
    public JsonAdaptedPropertyForSale(PropertyForSale source) {
        address = source.getAddress();
        town = source.getTown();
        propertyType = source.getPropertyType();
        size = source.getSize();
        numberOfBedrooms = source.getNumberOfBedrooms();
        numberOfBathrooms = source.getNumberOfBathrooms();
        price = source.getPrice();
        listingDate = source.getListingDate().toString(); // Convert LocalDate to String
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code PropertyForSale} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public PropertyForSale toModelType() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "address"));
        }
        if (town == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "town"));
        }
        if (propertyType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "propertyType"));
        }

        // Validate the listing date
        if (listingDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "listingDate"));
        }

        LocalDate modelListingDate = LocalDate.parse(listingDate);
        // Create a new PropertyForSale object without the remark
        return new PropertyForSale(address, town, propertyType, size,
                numberOfBedrooms, numberOfBathrooms, price, modelListingDate);
    }
}
