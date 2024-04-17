package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.BadTableException;
import exceptions.BadInputException;
import exceptions.NDTransitionException;
import impl.Factory;
import interfaces.IFactory;
import interfaces.IFiniteStateMachine;
import interfaces.ITransition;

/**
 * This is a JUnit test class for the FSM ADT.
 */
public class TestFiniteStateMachine {


    private static final int STATE1 = 1;

    private static final char INPUT1 = 'a';

    private static final char OUTPUT1 = 'e';

    private IFactory factory;
    private IFiniteStateMachine fsm;


    /**
     * JUnit setup method to run before every other test.
     */
    @BeforeEach
    public void setup() {
        factory = Factory.getInstance();
        fsm = factory.makeFiniteStateMachine();
    }

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of IFiniteStateMachine.
     */
    @Test
    public void fsmCreationNonNull() {
        assertNotNull(fsm);
    }


    /**
     * Checks whether a simple FSM with a single state and input works as expected when supplied with that input.
     * @throws BadTableException should not be thrown during this test
     * @throws BadInputException should not be thrown during this test
     * @throws NDTransitionException should not be thrown during this test
     */
    @Test
    public void singleStateFSMWorks() throws BadTableException, BadInputException, NDTransitionException {
        ITransition t1 = factory.makeTransition(STATE1, INPUT1, OUTPUT1, STATE1);
        fsm.addTransition(t1);
        assertEquals("eee", fsm.interpret("aaa"));
    }


    /**
     * Checks whether a simple FSM with two states and two inputs works as expected when supplied with valid inputs.
     * @throws BadTableException should not be thrown during this test
     * @throws BadInputException should not be thrown during this test
     * @throws NDTransitionException should not be thrown during this test
     */
    @Test
    public void simplTwoStateFSMWorks() throws BadTableException, BadInputException, NDTransitionException {
        fsm.addTransition(factory.makeTransition(1, 'a', 'e', 1));
        fsm.addTransition(factory.makeTransition(1, 'b', 'o', 2));
        fsm.addTransition(factory.makeTransition(2, 'a', 'o', 2));
        fsm.addTransition(factory.makeTransition(2, 'b', 'e', 1));
        assertEquals("eoo", fsm.interpret("aba"));
    }

    /**
     * Checks whether a slightly bigger FSM with three states and two inputs works as expected when supplied with valid inputs.
     * @throws BadTableException should not be thrown during this test
     * @throws BadInputException should not be thrown during this test
     * @throws NDTransitionException should not be thrown during this test
     */
    @Test
    public void simplThreeStateFSMWorks() throws BadTableException, BadInputException, NDTransitionException {
        fsm.addTransition(factory.makeTransition(7, '1', '1', 5));
        fsm.addTransition(factory.makeTransition(7, '2', '0', 7));
        fsm.addTransition(factory.makeTransition(5, '1', '2', 6));
        fsm.addTransition(factory.makeTransition(5, '2', '0', 6));
        fsm.addTransition(factory.makeTransition(6, '1', '3', 7));
        fsm.addTransition(factory.makeTransition(6, '2', '0', 6));
        assertEquals("12300", fsm.interpret("11122"));
    }

    /**
     * Checks whether an invalid input returns null.
     *
     * This test method adds valid transitions to the FSM and then attempts to interpret
     * an input string containing an invalid character. It ensures that the FSM correctly
     * returns null when there are no valid transitions defined for some characters in the input.
     *
     * @throws BadTableException If there is an issue with the transition table.
     * @throws BadInputException If there is an issue with the input string.
     * @throws NDTransitionException If a non-deterministic transition is encountered (should not occur in this test).
     */
    @Test
    public void invalidInputReturnsNull() throws BadTableException, BadInputException, NDTransitionException {
        // Add transitions for valid inputs
        fsm.addTransition(factory.makeTransition(1, 'a', 'e', 1));
        fsm.addTransition(factory.makeTransition(1, 'b', 'o', 2));
        fsm.addTransition(factory.makeTransition(2, 'a', 'o', 2));
        fsm.addTransition(factory.makeTransition(2, 'b', 'e', 1));

        // Interpret an input string with an invalid character 'c'
        assertNull(fsm.interpret("aca"));
    }


    /**
     * Checks whether an FSM with no transitions produces null output.
     *
     * This test method verifies that if an FSM has no transitions defined,
     * it should produce null output for any input.
     *
     * @throws BadTableException If there is an issue with the transition table.
     * @throws BadInputException If there is an issue with the input string.
     * @throws NDTransitionException If a non-deterministic transition is encountered (should not occur in this test).
     */
    @Test
    public void fsmWithNoTransitionsProducesNullOutput() throws BadTableException, BadInputException, NDTransitionException {
        // No transitions added to the FSM

        // Interpret an input string
        assertNull(fsm.interpret("abc"));
    }

}
