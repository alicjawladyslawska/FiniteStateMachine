package interfaces;

import java.io.FileNotFoundException;

/**
 * Interface for a factory abstracting over instantiation of other interface types.
 *
 */
public interface IFactory {


    /**
     * Creates an instance of ITransition for use in an FSM.
     * @param current_state the current state for this FSM transition
     * @param input the input for this FSM transition
     * @param output the output for this FSM transition
     * @param next_state the next state for this FSM transition
     * @return the Transition
     * 
     */
    ITransition makeTransition(int current_state, char input, char output, int next_state);


    /**
     * Creates an instance of ITransitionTable for use in a FSM.
     * @return the new empty TransitionTable
     * 
     */
    ITransitionTable makeTransitionTable();


    /**
     * Creates an instance of IFiniteStateMachine.
     * @return the new FiniteStateMachine
     */
    IFiniteStateMachine makeFiniteStateMachine();

}
