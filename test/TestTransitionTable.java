package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import impl.Factory;
import exceptions.NDTransitionException;
import exceptions.BadInputException;
import interfaces.IFactory;
import interfaces.ITransition;
import interfaces.ITransitionTable;

/**
 * This class contains tests for the TransitionTable ADT.
 */
public class TestTransitionTable {

    private IFactory factory;
    private ITransitionTable transitionTable;

    /**
     * Initializes the factory and transitionTable before each test.
     */
    @BeforeEach
    public void setup() {
        factory = Factory.getInstance();
        transitionTable = factory.makeTransitionTable();
    }

    /**
     * Test the creation of a non-null transitionTable.
     */
    @Test
    public void transitionTableCreationNonNull() {
        assertNotNull(transitionTable);
    }

    /**
     * Test adding a valid transition to the transitionTable.
     *
     * @throws NDTransitionException if a non-deterministic transition is added.
     * @throws BadInputException     if bad input is encountered.
     */
    @Test
    public void addValidTransition() throws NDTransitionException, BadInputException {
        ITransition transition = factory.makeTransition(1, 'a', 'b', 2);
        transitionTable.addTransition(transition);
        try {
            assertEquals(transition, transitionTable.getTransition(1, 'a'));
        } catch (BadInputException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test adding a duplicate transition to the transitionTable,
     * expecting an NDTransitionException to be thrown.
     *
     * @throws NDTransitionException if a non-deterministic transition is added.
     */
    @Test
    public void addDuplicateTransitionThrowsNDTransitionException() throws NDTransitionException {
        ITransition transition1 = factory.makeTransition(1, 'a', 'b', 2);
        ITransition transition2 = factory.makeTransition(1, 'a', 'c', 3);
        transitionTable.addTransition(transition1);
        assertThrows(NDTransitionException.class, () -> transitionTable.addTransition(transition2));
    }

    /**
     * Test getting a non-existing transition from the transitionTable,
     * expecting a BadInputException to be thrown.
     */
    @Test
    public void getNonExistingTransitionThrowsBadInputException() {
        assertThrows(BadInputException.class, () -> transitionTable.getTransition(1, 'a'));
    }

    /**
     * Test checking if the transitionTable has transitions to illegal states.
     *
     * @throws NDTransitionException if a non-deterministic transition is added.
     */
    @Test
    public void hasTransitionsToIllegalStates() throws NDTransitionException {
        ITransition transition1 = factory.makeTransition(1, 'a', 'b', 2);
        ITransition transition2 = factory.makeTransition(2, 'a', 'b', 3);
        transitionTable.addTransition(transition1);
        transitionTable.addTransition(transition2);
        assertTrue(transitionTable.hasTransitionsToIllegalStates());
    }

    /**
     * Test checking if the transitionTable has no missing inputs.
     *
     * @throws NDTransitionException if a non-deterministic transition is added.
     */
    @Test
    public void hasNoMissingInputs() throws NDTransitionException {
        ITransition transition1 = factory.makeTransition(1, 'a', 'b', 2);
        ITransition transition2 = factory.makeTransition(1, 'b', 'c', 2);
        transitionTable.addTransition(transition1);
        transitionTable.addTransition(transition2);
        assertFalse(transitionTable.hasMissingInputs());
    }
}
