package seedu.address.logic.parser;

import static seedu.address.logic.commands.AddPropertyForRentCommand.MESSAGE_MISSING_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyForRentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.PropertyForRent;

public class AddPropertyForRentCommandParserTest {

    private final AddPropertyForRentCommandParser parser = new AddPropertyForRentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        PropertyForRent expectedProperty = new PropertyForRent(
                "123 Main St", "Downtown", "Condo", 100.5, 2, 2,
                3000.0, Optional.of("Near park"), LocalDate.of(2024, 12, 1));

        String userInput = ADDRESS_DESC_AMY + TOWN_DESC_AMY + TYPE_DESC + SIZE_DESC +
                " b/2 b/2 " + PRICE_DESC + REMARK_DESC_AMY + " af/2024-12-01";

        assertParseSuccess(parser, userInput, new AddPropertyForRentCommand(expectedProperty));
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
    public void parse_invalidField_failure() {
        String userInput = ADDRESS_DESC_AMY + TOWN_DESC_AMY + TYPE_DESC + SIZE_DESC +
                " b/2 b/2 invalidPrice " + REMARK_DESC_AMY + " af/2024-12-01";

        assertParseFailure(parser, userInput, "Invalid price format.");
    }

    @Test
    public void parse_invalidDate_failure() {
        String userInput = ADDRESS_DESC_AMY + TOWN_DESC_AMY + TYPE_DESC + SIZE_DESC +
                " b/2 b/2 " + PRICE_DESC + REMARK_DESC_AMY + " af/2024-02-30"; // Invalid date

        assertParseFailure(parser, userInput, "Invalid date format.");
    }

    @Test
    public void parse_emptyInput_failure() {
        String userInput = "";

        assertParseFailure(parser, userInput, String.format(
                "The command requires mandatory fields to be filled in, missing: %s",
                "address, town, type, size, price"));
    }
}
