package impl;

import exceptions.NDTransitionException;
import exceptions.BadInputException;
import interfaces.ITransition;
import interfaces.ITransitionTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a transition table for a Finite State Machine (FSM) using ArrayList.
 */
public class TransitionTable implements ITransitionTable {

    private List<ITransition> transitions = new ArrayList<>();

    /**
     * Adds a transition to the transition table.
     *
     * @param transition The transition to be added.
     * @throws NDTransitionException Thrown if a non-deterministic transition is detected.
     */
    @Override
    public void addTransition(ITransition transition) throws NDTransitionException {
        // Check for non-deterministic transition
        for (ITransition existingTransition : transitions) {
            if (existingTransition.getCurrentState() == transition.getCurrentState()
                    && existingTransition.getInput() == transition.getInput()) {
                throw new NDTransitionException();
            }
        }
        transitions.add(transition);
    }

    /**
     * Retrieves a transition from the transition table based on the current state and input.
     *
     * @param current_state The current state of the FSM.
     * @param input         The input symbol triggering the transition.
     * @return The transition associated with the current state and input.
     * @throws BadInputException Thrown if no matching transition is found for the given state and input.
     */
    @Override
    public ITransition getTransition(int current_state, char input) throws BadInputException {
        for (ITransition transition : transitions) {
            if (transition.getCurrentState() == current_state && transition.getInput() == input) {
                return transition;
            }
        }
        throw new BadInputException();
    }

    /**
     * Checks if the transition table contains transitions to illegal states.
     *
     * @return True if there are transitions to illegal states, false otherwise.
     */
    @Override
    public boolean hasTransitionsToIllegalStates() {
        boolean hasIllegalTransitions = false;

        for (ITransition transition : transitions) {
            int nextState = transition.getNextState();
            boolean nextStateFound = false;
            for (ITransition existingTransition : transitions) {
                if (existingTransition.getCurrentState() == nextState) {
                    nextStateFound = true;
                    break;
                }
            }
            if (!nextStateFound) {
                hasIllegalTransitions = true; // set to true if an illegal transition is found
            }
        }

        return hasIllegalTransitions;
    }

    /**
     * Checks if there are missing input symbols in the transition table.
     *
     * @return True if there are missing input symbols, false otherwise.
     */
    @Override
    public boolean hasMissingInputs() {
        // Create an input alphabet from all inputs from each transition collected
        char[] inputAlphabet = new char[transitions.size()];
        int counter = 0;

        // Loop through all transitions
        for (ITransition transition : transitions) {
            // Add the input to the input alphabet
            inputAlphabet[counter] = transition.getInput();
            counter++;
        }

        for (char input : inputAlphabet) {
            boolean inputFound = false;
            for (ITransition transition : transitions) {
                if (transition.getInput() == input) {
                    inputFound = true;
                    break;
                }
            }
            if (!inputFound) {
                return true; // Missing input found
            }
        }
        return false;
    }
}
