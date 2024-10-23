package seedu.address.logic.parser;

//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.*;
        import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProperties.PROPERTY_FOR_SALE_BOB; // Property for sale

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyForSaleCommand;
import seedu.address.model.property.PropertyForSale;
import seedu.address.testutil.PropertyForSaleBuilder;

public class AddPropertyForSaleCommandParserTest {
    private final AddPropertyForSaleCommandParser parser = new AddPropertyForSaleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        PropertyForSale expectedProperty = new PropertyForSaleBuilder(PROPERTY_FOR_SALE_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PROPERTY_ADDRESS_DESC_BOB + TOWN_DESC_BOB
                        + TYPE_DESC_BOB + SIZE_DESC_BOB
                        + ROOM_COUNT_DESC_BOB + BATHROOM_COUNT_DESC_BOB
                        + PRICE_DESC_BOB + LISTING_DATE_DESC_BOB,
                new AddPropertyForSaleCommand(expectedProperty));
    }
}
