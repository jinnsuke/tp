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
import seedu.address.model.property.PropertyForSale;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PropertyForSaleBuilder;

public class AddPropertyForSaleCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPropertyForSaleCommand(null));
    }

    @Test
    public void execute_propertyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPropertyForSaleAdded modelStub = new ModelStubAcceptingPropertyForSaleAdded();
        PropertyForSale validProperty = new PropertyForSaleBuilder().build();

        CommandResult commandResult = new AddPropertyForSaleCommand(validProperty).execute(modelStub);

        assertEquals(String.format(AddPropertyForSaleCommand.MESSAGE_SUCCESS, Messages.format(validProperty)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProperty), modelStub.propertiesAdded);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        PropertyForSale validProperty = new PropertyForSaleBuilder().build();
        AddPropertyForSaleCommand command = new AddPropertyForSaleCommand(validProperty);
        ModelStub modelStub = new ModelStubWithPropertyForSale(validProperty);

        assertThrows(CommandException.class,
                AddPropertyForSaleCommand.MESSAGE_DUPLICATE_PROPERTY, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        PropertyForSale property1 = new PropertyForSaleBuilder().withAddress("123 Main St").build();
        PropertyForSale property2 = new PropertyForSaleBuilder().withAddress("456 Elm St").build();
        AddPropertyForSaleCommand command1 = new AddPropertyForSaleCommand(property1);
        AddPropertyForSaleCommand command2 = new AddPropertyForSaleCommand(property2);

        // Same object -> returns true
        assertTrue(command1.equals(command1));

        // Same values -> returns true
        AddPropertyForSaleCommand command1Copy = new AddPropertyForSaleCommand(property1);
        assertTrue(command1.equals(command1Copy));

        // Different types -> returns false
        assertFalse(command1.equals(1));

        // Null -> returns false
        assertFalse(command1.equals(null));

        // Different property -> returns false
        assertFalse(command1.equals(command2));
    }
}

class ModelStubAcceptingPropertyForSaleAdded extends ModelStub {
    final List<PropertyForSale> propertiesAdded = new ArrayList<>();

    @Override
    public void addPropertyForSale(PropertyForSale property) {
        requireNonNull(property);
        propertiesAdded.add(property);
    }

    @Override
    public boolean hasPropertyForSale(PropertyForSale property) {
        requireNonNull(property);
        return propertiesAdded.stream().anyMatch(property::equals);
    }
}

class ModelStubWithPropertyForSale extends ModelStub {
    private final PropertyForSale property;

    ModelStubWithPropertyForSale(PropertyForSale property) {
        requireNonNull(property);
        this.property = property;
    }

    @Override
    public boolean hasPropertyForSale(PropertyForSale property) {
        requireNonNull(property);
        return this.property.equals(property);
    }
}
