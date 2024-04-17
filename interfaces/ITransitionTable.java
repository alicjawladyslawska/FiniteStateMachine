package interfaces;

import exceptions.BadInputException;
import exceptions.NDTransitionException;

/**
 * Interface representing a transition table for an FSM.
 *
 */
public interface ITransitionTable {

    /**
     * Adds the given transition to the transition table.
     * @param transition the transition to add
     * @throws NDTransitionException when the transition table already contains another transition with the same current_state and input pair
     */
    void addTransition(ITransition transition) throws NDTransitionException;

    /**
     * Retrieves the ITransition object from the transition table for the given current state and input.
     * @param current_state the current state to use
     * @param input the input to use
     * @return the ITransition object for the given state and input
     * @throws BadInputException if the given current_state is not in the table or the given input character is not an element of the input alphabet
     */
    ITransition getTransition(int current_state, char input) throws BadInputException;

    /**
     * Check whether the transition table contains transitions to illegal (non-existent) states.
     * @return true if the transition table contains transitions to illegal (non-existent) state (i.e. next_states that are not in the table as a current_state) and false otherwise
     * 
     */
    boolean hasTransitionsToIllegalStates();

    /**
     * Checks whether the transition table is missing one or more transitions from states for valid inputs in the input alphabet.
     * @return true if the table is missing one or more transitions from states for valid inputs and false otherwise.
     */
    boolean hasMissingInputs();

}
