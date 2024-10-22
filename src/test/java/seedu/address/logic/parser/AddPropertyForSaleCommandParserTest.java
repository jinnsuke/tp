package seedu.address.logic.parser;

import static seedu.address.logic.commands.AddPropertyForSaleCommand.MESSAGE_MISSING_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.LISTING_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SIZE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TOWN_DESC_BOB; // Updated from TOWN_DESC
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyForSaleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.PropertyForSale;

public class AddPropertyForSaleCommandParserTest {

    private final AddPropertyForSaleCommandParser parser = new AddPropertyForSaleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        PropertyForSale expectedProperty = new PropertyForSale(
                "456 Elm St", "Uptown", "Apartment", 85.0, 1, 1,
                450000.0, Optional.of("Newly renovated"), LocalDate.of(2024, 11, 15));

        String userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + TYPE_DESC + SIZE_DESC
                + " b/1 b/1 " + PRICE_DESC + REMARK_DESC_BOB + LISTING_DATE_DESC;

        assertParseSuccess(parser, userInput, new AddPropertyForSaleCommand(expectedProperty));
    }

    @Test
    public void parse_missingMandatoryField_failure() {
        // Test case where the town is missing
        String userInput = ADDRESS_DESC_BOB + TYPE_DESC + SIZE_DESC + PRICE_DESC + REMARK_DESC_BOB + LISTING_DATE_DESC;
        assertParseFailure(parser, userInput, String.format(MESSAGE_MISSING_FIELD, "town"));

        // Test case where the address is missing
        userInput = TOWN_DESC_BOB + TYPE_DESC + SIZE_DESC + PRICE_DESC + REMARK_DESC_BOB + LISTING_DATE_DESC;
        assertParseFailure(parser, userInput, String.format(MESSAGE_MISSING_FIELD, "address"));

        // Test case where the type is missing
        userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + SIZE_DESC + PRICE_DESC + REMARK_DESC_BOB + LISTING_DATE_DESC;
        assertParseFailure(parser, userInput, String.format(MESSAGE_MISSING_FIELD, "type"));

        // Test case where the size is missing
        userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + TYPE_DESC + PRICE_DESC + REMARK_DESC_BOB + LISTING_DATE_DESC;
        assertParseFailure(parser, userInput, String.format(MESSAGE_MISSING_FIELD, "size"));

        // Test case where the price is missing
        userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + TYPE_DESC + SIZE_DESC + REMARK_DESC_BOB + LISTING_DATE_DESC;
        assertParseFailure(parser, userInput, String.format(MESSAGE_MISSING_FIELD, "price"));

        // Test case where the listing date is missing
        userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + TYPE_DESC + SIZE_DESC + PRICE_DESC + REMARK_DESC_BOB;
        assertParseFailure(parser, userInput, String.format(MESSAGE_MISSING_FIELD, "listing date"));
    }

    @Test
    public void parse_invalidValue_failure() {
        // Example of invalid size
        String userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + TYPE_DESC + " s/invalidSize "
                + PRICE_DESC + REMARK_DESC_BOB + LISTING_DATE_DESC;
        assertParseFailure(parser, userInput, "Invalid size format");

        // Example of invalid price
        userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + TYPE_DESC + SIZE_DESC
                + " p/invalidPrice " + REMARK_DESC_BOB + LISTING_DATE_DESC;
        assertParseFailure(parser, userInput, "Invalid price format");

        // Example of invalid listing date
        userInput = ADDRESS_DESC_BOB + TOWN_DESC_BOB + TYPE_DESC
                + SIZE_DESC + PRICE_DESC + REMARK_DESC_BOB + " d/invalidDate";
        assertParseFailure(parser, userInput, "Invalid listing date format");
    }
}
