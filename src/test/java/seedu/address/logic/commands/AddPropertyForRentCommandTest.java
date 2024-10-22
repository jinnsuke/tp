package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.property.PropertyForRent;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PropertyForRentBuilder;

public class AddPropertyForRentCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPropertyForRentCommand(null));
    }

    @Test
    public void execute_propertyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPropertyForRentAdded modelStub = new ModelStubAcceptingPropertyForRentAdded();
        PropertyForRent validProperty = new PropertyForRentBuilder().build();

        CommandResult commandResult = new AddPropertyForRentCommand(validProperty).execute(modelStub);

        assertEquals(String.format(AddPropertyForRentCommand.MESSAGE_SUCCESS, Messages.format(validProperty)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProperty), modelStub.propertiesAdded);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        PropertyForRent validProperty = new PropertyForRentBuilder().build();
        AddPropertyForRentCommand command = new AddPropertyForRentCommand(validProperty);
        ModelStub modelStub = new ModelStubWithPropertyForRent(validProperty);

        assertThrows(CommandException.class,
                AddPropertyForRentCommand.MESSAGE_DUPLICATE_PROPERTY, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        PropertyForRent property1 = new PropertyForRentBuilder().withAddress("123 Main St").build();
        PropertyForRent property2 = new PropertyForRentBuilder().withAddress("456 Elm St").build();
        AddPropertyForRentCommand command1 = new AddPropertyForRentCommand(property1);
        AddPropertyForRentCommand command2 = new AddPropertyForRentCommand(property2);

        // Same object -> returns true
        assertTrue(command1.equals(command1));

        // Same values -> returns true
        AddPropertyForRentCommand command1Copy = new AddPropertyForRentCommand(property1);
        assertTrue(command1.equals(command1Copy));

        // Different types -> returns false
        assertFalse(command1.equals(1));

        // Null -> returns false
        assertFalse(command1.equals(null));

        // Different property -> returns false
        assertFalse(command1.equals(command2));
    }
}

class ModelStubAcceptingPropertyForRentAdded extends ModelStub {
    final List<PropertyForRent> propertiesAdded = new ArrayList<>();

    @Override
    public void addPropertyForRent(PropertyForRent property) {
        requireNonNull(property);
        propertiesAdded.add(property);
    }

    @Override
    public boolean hasPropertyForRent(PropertyForRent property) {
        requireNonNull(property);
        return propertiesAdded.stream().anyMatch(property::equals);
    }
}

class ModelStubWithPropertyForRent extends ModelStub {
    private final PropertyForRent property;

    ModelStubWithPropertyForRent(PropertyForRent property) {
        requireNonNull(property);
        this.property = property;
    }

    @Override
    public boolean hasPropertyForRent(PropertyForRent property) {
        requireNonNull(property);
        return this.property.equals(property);
    }
}

