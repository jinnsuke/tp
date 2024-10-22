package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    /* Prefix definitions for Person-related Commands */
    public static final Prefix PREFIX_NAME = new Prefix("n/"); // Name of person
    public static final Prefix PREFIX_PHONE = new Prefix("p/"); // Phone number
    public static final Prefix PREFIX_EMAIL = new Prefix("e/"); // Email address
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/"); // Address
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/"); // Birthday
    public static final Prefix PREFIX_TAG = new Prefix("t/"); // Tag for a person (e.g., friend)
    public static final Prefix PREFIX_REMARK = new Prefix("r/"); // Remarks
    public static final Prefix PREFIX_DATE = new Prefix("d/"); // Date (for any purpose)
    public static final Prefix PREFIX_LOG = new Prefix("l/"); // Logs or notes

    /* Prefix definitions for Property-related Commands */
    public static final Prefix PREFIX_LISTING_DATE = new Prefix("ld/"); // Listing date for property sale
    public static final Prefix PREFIX_PRICE = new Prefix("pr/"); // Price of property
    public static final Prefix PREFIX_PROPERTY_TYPE = new Prefix("pt/"); // Type of property (e.g., Apartment)
    public static final Prefix PREFIX_SIZE = new Prefix("s/"); // Size in square meters or feet
    public static final Prefix PREFIX_TOWN = new Prefix("t/"); // Town or locality
    public static final Prefix PREFIX_BEDROOMS = new Prefix("br/"); // Number of bedrooms
    public static final Prefix PREFIX_BATHROOMS = new Prefix("ba/"); // Number of bathrooms
    public static final Prefix PREFIX_AVAILABLE_FROM = new Prefix("af/"); // Availability date for rental
}
