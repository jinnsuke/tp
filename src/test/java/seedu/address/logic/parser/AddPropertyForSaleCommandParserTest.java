package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyForSaleCommand;
import seedu.address.model.property.PropertyForSale;

public class AddPropertyForSaleCommandParserTest {

    private final AddPropertyForSaleCommandParser parser = new AddPropertyForSaleCommandParser();

    @Test
    public void parse_validInput_success() {
        // Arrange: Create the expected property object without a remark
        PropertyForSale expectedProperty = new PropertyForSale(
                "456 Oak St", "West Town", "House", 300.0,
                4, 3, 800000.0,
                LocalDate.of(2024, 11, 15)
        );

        // User input matches the expected property without the remark
        String userInput = " a/456 Oak St t/West Town pt/House s/300.0 b/4 br/3 p/800000.0 "
                + "d/2024-11-15";

        // Act & Assert: Ensure the parser returns the correct command
        assertParseSuccess(parser, userInput, new AddPropertyForSaleCommand(expectedProperty));
    }
}
