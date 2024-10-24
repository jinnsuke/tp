package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOWN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEDROOMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BATHROOMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PropertyForSale;
import seedu.address.model.person.Person;
import seedu.address.model.person.PropertyList;

/**
 * Add a property that is listed for sale.
 */
public class AddPropertyForSaleCommand extends Command {
    public static final String COMMAND_WORD = "addProperty";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property for sale with the specified details of the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PROPERTY_ADDRESS + "ADDRESS "
            + PREFIX_TOWN + "TOWN "
            + PREFIX_TYPE + "PROPERTY_TYPE "
            + PREFIX_SIZE + "SIZE "
            + PREFIX_BEDROOMS + "NUMBER_OF_BEDROOMS"
            + PREFIX_BATHROOMS + "NUMBER_OF_BATHROOMS "
            + PREFIX_PRICE + "PRICE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PROPERTY_ADDRESS + "123 Main St "
            + PREFIX_TYPE + "Condo "
            + PREFIX_SIZE + "85 "
            + PREFIX_BEDROOMS + "2 "
            + PREFIX_BATHROOMS + "2 "
            + PREFIX_PRICE + "500000";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "AddPropertyForSale command not implemented yet";
    public static final String MESSAGE_ADD_PROPERTY_SUCCESS = "Added property to Person: %1$s";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Address: %2$s, Town: %3$s, Type: %4$s, Size: %.2f, Bedrooms: %5$d, Bathrooms: %6$d, Price: $%.2f";
    private final Index index;
    private final String address;
    private final String town;
    private final String propertyType;
    private final double size;
    private final int numberOfBedrooms;
    private final int numberOfBathrooms;
    private final double price;

    /**
     * Constructs an {@code AddPropertyForSaleCommand} with the specified details.
     *
     * @param address The address of the property.
     * @param town The town where the property is located.
     * @param propertyType The type of the property (e.g., condo, landed, HDB).
     * @param size The size of the property in square meters.
     * @param numberOfBedrooms The number of bedrooms.
     * @param numberOfBathrooms The number of bathrooms.
     * @param price The asking price of the property.
     */
    public AddPropertyForSaleCommand(Index index, String address, String town, String propertyType,
                                     double size, int numberOfBedrooms,
                                     int numberOfBathrooms, double price) {
        requireAllNonNull(index, address, town, propertyType);
        this.index = index;
        this.address = address;
        this.town = town;
        this.propertyType = propertyType;
        this.size = size;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.price = price;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        PropertyForSale newProperty = PropertyForSale.of(address, town, propertyType,
                size, numberOfBedrooms, numberOfBathrooms, price);

        Person personToEdit = lastShownList.get(index.getZeroBased());
        PropertyList propertyList = PropertyList.addProperty(newProperty);

        model.addPropertyForSale(newProperty);  // Assumes you have an addPropertyForSale method in your Model.
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);

        return new CommandResult(generateSuccessMessage(newProperty));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPropertyForSaleCommand)) {
            return false;
        }

        AddPropertyForSaleCommand e = (AddPropertyForSaleCommand) other;
        return address.equals(e.address)
                && town.equals(e.town)
                && propertyType.equals(e.propertyType)
                && size == e.size
                && numberOfBedrooms == e.numberOfBedrooms
                && numberOfBathrooms == e.numberOfBathrooms
                && price == e.price;
    }

    /**
     * Generates a command execution success message based on the added property.
     */
    public String generateSuccessMessage(PropertyForSale property) {
        return String.format(MESSAGE_ADD_PROPERTY_SUCCESS, property);
    }

    @Override
    public String toString() {
        return String.format("AddPropertyForSaleCommand: address=%s, town=%s, propertyType=%s, size=%.2f, "
                        + "bedrooms=%d, bathrooms=%d, price=%.2f",
                address, town, propertyType, size, numberOfBedrooms, numberOfBathrooms, price);
    }
}
