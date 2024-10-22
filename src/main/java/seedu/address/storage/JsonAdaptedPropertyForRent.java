package seedu.address.storage;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.PropertyForRent;

/**
 * Jackson-friendly version of {@link PropertyForRent}.
 */
class JsonAdaptedPropertyForRent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";

    private final String address;
    private final String town;
    private final String propertyType;
    private final double size;
    private final int numberOfBedrooms;
    private final int numberOfBathrooms;
    private final double price;
    private final String remark;
    private final String availableFrom;

    /**
     * Constructs a {@code JsonAdaptedPropertyForRent} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedPropertyForRent(
            @JsonProperty("address") String address,
            @JsonProperty("town") String town,
            @JsonProperty("propertyType") String propertyType,
            @JsonProperty("size") double size,
            @JsonProperty("numberOfBedrooms") int numberOfBedrooms,
            @JsonProperty("numberOfBathrooms") int numberOfBathrooms,
            @JsonProperty("price") double price,
            @JsonProperty("remark") String remark,
            @JsonProperty("availableFrom") String availableFrom) {
        this.address = address;
        this.town = town;
        this.propertyType = propertyType;
        this.size = size;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.price = price;
        this.remark = remark;
        this.availableFrom = availableFrom;
    }

    /**
     * Converts a given {@code PropertyForRent} into this class for Jackson use.
     */
    public JsonAdaptedPropertyForRent(PropertyForRent source) {
        address = source.getAddress();
        town = source.getTown();
        propertyType = source.getPropertyType();
        size = source.getSize();
        numberOfBedrooms = source.getNumberOfBedrooms();
        numberOfBathrooms = source.getNumberOfBathrooms();
        price = source.getPrice();
        remark = source.getRemark().orElse(null);
        availableFrom = source.getAvailableFrom().toString();
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code PropertyForRent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public PropertyForRent toModelType() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "address"));
        }

        if (town == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "town"));
        }

        if (propertyType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "propertyType"));
        }

        if (size <= 0) {
            throw new IllegalValueException("Size must be greater than zero.");
        }

        if (numberOfBedrooms < 0) {
            throw new IllegalValueException("Number of bedrooms cannot be negative.");
        }

        if (numberOfBathrooms < 0) {
            throw new IllegalValueException("Number of bathrooms cannot be negative.");
        }

        if (price < 0) {
            throw new IllegalValueException("Price cannot be negative.");
        }

        Optional<String> modelRemark = Optional.ofNullable(remark);
        LocalDate modelAvailableFrom;

        try {
            modelAvailableFrom = LocalDate.parse(availableFrom);
        } catch (Exception e) {
            throw new IllegalValueException("Invalid date format for availableFrom.");
        }

        return new PropertyForRent(address, town, propertyType, size,
                numberOfBedrooms, numberOfBathrooms, price,
                modelRemark, modelAvailableFrom);
    }
}
