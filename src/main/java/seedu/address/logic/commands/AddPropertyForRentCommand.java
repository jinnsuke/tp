package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BATHROOMS; // Move this above PREFIX_BEDROOMS
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEDROOMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.PropertyForRent;

/**
 * Adds a property for rent to the property list.
 */
public class AddPropertyForRentCommand extends Command {

    public static final String COMMAND_WORD = "add-rent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property for rent to the list. "
            + "Parameters: "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_PROPERTY_TYPE + "TYPE "
            + PREFIX_SIZE + "SIZE "
            + PREFIX_BEDROOMS + "BEDROOMS "
            + PREFIX_BATHROOMS + "BATHROOMS "
            + PREFIX_PRICE + "PRICE "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ADDRESS + "456 Elm St "
            + PREFIX_PROPERTY_TYPE + "Apartment "
            + PREFIX_SIZE + "100.0 "
            + PREFIX_BEDROOMS + "2 "
            + PREFIX_BATHROOMS + "1 "
            + PREFIX_PRICE + "1500.0 "
            + PREFIX_REMARK + "Close to metro";

    public static final String MESSAGE_SUCCESS = "New property for rent added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in the list";

    private final PropertyForRent toAdd;

    /**
     * Creates an AddPropertyForRentCommand to add the specified {@code PropertyForRent}
     */
    public AddPropertyForRentCommand(PropertyForRent propertyForRent) {
        requireNonNull(propertyForRent);
        toAdd = propertyForRent;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPropertyForRent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.addPropertyForRent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPropertyForRentCommand)) {
            return false;
        }

        AddPropertyForRentCommand otherAddCommand = (AddPropertyForRentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
