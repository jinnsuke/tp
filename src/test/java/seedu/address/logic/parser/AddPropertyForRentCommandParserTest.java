package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.AVAILABLE_FROM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BATHROOM_COUNT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PROPERTY_ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.RENT_PRICE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_COUNT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SIZE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TOWN_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_RENT_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyForRentCommand;
import seedu.address.model.property.PropertyForRent;
import seedu.address.testutil.PropertyForRentBuilder;

public class AddPropertyForRentCommandParserTest {
    private final AddPropertyForRentCommandParser parser = new AddPropertyForRentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        PropertyForRent expectedProperty = new PropertyForRentBuilder(PROPERTY_FOR_RENT_BOB).build();

        assertParseSuccess(parser, PROPERTY_ADDRESS_DESC_BOB + TOWN_DESC_BOB
                        + TYPE_DESC_BOB + SIZE_DESC_BOB
                        + ROOM_COUNT_DESC_BOB + BATHROOM_COUNT_DESC_BOB
                        + RENT_PRICE_DESC_BOB + AVAILABLE_FROM_DESC_BOB,
                new AddPropertyForRentCommand(expectedProperty));
    }
}
