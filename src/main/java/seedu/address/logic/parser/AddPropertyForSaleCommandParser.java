package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BATHROOMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEDROOMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOWN;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddPropertyForSaleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.PropertyForSale;

/**
 * Parses input arguments and creates a new AddPropertyForSaleCommand object.
 */
public class AddPropertyForSaleCommandParser implements Parser<AddPropertyForSaleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPropertyForSaleCommand
     * and returns an AddPropertyForSaleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddPropertyForSaleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_ADDRESS, PREFIX_TOWN, PREFIX_PROPERTY_TYPE, PREFIX_SIZE,
                PREFIX_BEDROOMS, PREFIX_BATHROOMS, PREFIX_PRICE, PREFIX_LISTING_DATE
        );

        if (!arePrefixesPresent(argMultimap, PREFIX_ADDRESS, PREFIX_TOWN, PREFIX_PROPERTY_TYPE,
                PREFIX_SIZE, PREFIX_BEDROOMS, PREFIX_BATHROOMS, PREFIX_PRICE, PREFIX_LISTING_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyForSaleCommand.MESSAGE_USAGE)
            );
        }

        String address = argMultimap.getValue(PREFIX_ADDRESS).get();
        String town = argMultimap.getValue(PREFIX_TOWN).get();
        String propertyType = argMultimap.getValue(PREFIX_PROPERTY_TYPE).get();
        double size = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_SIZE).get());
        int bedrooms = ParserUtil.parseInt(argMultimap.getValue(PREFIX_BEDROOMS).get());
        int bathrooms = ParserUtil.parseInt(argMultimap.getValue(PREFIX_BATHROOMS).get());
        double price = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_PRICE).get());
        LocalDate listingDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_LISTING_DATE).get());

        PropertyForSale propertyForSale = new PropertyForSale(
                address, town, propertyType, size, bedrooms, bathrooms, price, listingDate
        );

        return new AddPropertyForSaleCommand(propertyForSale);
    }

    /**
     * Checks if the necessary prefixes are present in the user input.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
