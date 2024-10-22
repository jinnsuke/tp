package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyForRentCommand;
import seedu.address.model.property.PropertyForRent;

public class AddPropertyForRentCommandParserTest {

    private final AddPropertyForRentCommandParser parser = new AddPropertyForRentCommandParser();

    @Test
    public void parse_validInput_success() {
        // Arrange: Create the expected property object without a remark
        PropertyForRent expectedProperty = new PropertyForRent(
                "123 Main St", "Central Town", "Apartment", 120.5,
                2, 2, 3000.0,
                LocalDate.of(2024, 12, 1)
        );

        // User input matches the expected property without the remark
        String userInput = " a/123 Main St t/Central Town pt/Apartment s/120.5 b/2 br/2 p/3000.0 "
                + "af/2024-12-01";

        // Act & Assert: Ensure the parser returns the correct command
        assertParseSuccess(parser, userInput, new AddPropertyForRentCommand(expectedProperty));
    }
}
