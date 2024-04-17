package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import impl.Factory;
import interfaces.IFactory;
import interfaces.ITransition;

/**
 * This is a JUnit test class for the Transition ADT.
 */
public class TestTransition {


    private static final int CURRENT_STATE = 1;
    private static final char INPUT = 'a';
    private static final char OUTPUT = '.';
    private static final int NEXT_STATE = 2;

    private IFactory factory;


    /**
     * JUnit setup method to run before every other test.
     */
    @BeforeEach
    public void setup() {
        factory = Factory.getInstance();

        //create an FSM from the factry (???)
    }

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ITransition.
     */
    @Test
    public void transitionCreationNonNull() {
        ITransition transition = factory.makeTransition(CURRENT_STATE, INPUT, OUTPUT, NEXT_STATE);
        assertNotNull(transition);
    }


    /**
     * Here I am testing each individual method in Transition
     */
    @Test
    public void getCurrentState() {
        ITransition transition = factory.makeTransition(CURRENT_STATE, INPUT, OUTPUT, NEXT_STATE);
        assertEquals(CURRENT_STATE, transition.getCurrentState());
    }

    @Test
    public void getInput() {
        ITransition transition = factory.makeTransition(CURRENT_STATE, INPUT, OUTPUT, NEXT_STATE);
        assertEquals(INPUT, transition.getInput());
    }

    @Test
    public void getOutput() {
        ITransition transition = factory.makeTransition(CURRENT_STATE, INPUT, OUTPUT, NEXT_STATE);
        assertEquals(OUTPUT, transition.getOutput());
    }

    @Test
    public void getNextState() {
        ITransition transition = factory.makeTransition(CURRENT_STATE, INPUT, OUTPUT, NEXT_STATE);
        assertEquals(NEXT_STATE, transition.getNextState());
    }

}
