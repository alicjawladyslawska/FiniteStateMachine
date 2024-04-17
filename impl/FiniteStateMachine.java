package impl;

import exceptions.BadTableException;
import exceptions.BadInputException;
import exceptions.NDTransitionException;
import interfaces.IFiniteStateMachine;
import interfaces.ITransition;
import interfaces.ITransitionTable;

//own import
import java.util.ArrayList;

/**
 * Class representing a finite state machine.
 */
public class FiniteStateMachine implements IFiniteStateMachine {

    ArrayList<ITransition> transitionList = new ArrayList<>();

    @Override
    public void addTransition(ITransition transition) throws NDTransitionException {
        transitionList.add(transition);
    }

    /**
     * Interprets an input string based on the defined transitions.
     *
     * @param input The input string to be interpreted.
     * @return The output string produced by applying transitions.
     * @throws BadTableException If there is an issue with the transition table.
     * @throws BadInputException If there is an issue with the input string.
     */
    @Override
    public String interpret(String input) throws BadTableException, BadInputException {
        // Check if there are no transitions provided before interperting
        if (transitionList.isEmpty()) {
            return null;
        }

        String output = "";
        int machineState = transitionList.get(0).getCurrentState();
        char[] characters = input.toCharArray();

        for (char c : characters) {
            boolean transitionFound = false;
            for (ITransition selectedTransition : transitionList) {
                if (selectedTransition.getInput() == c && selectedTransition.getCurrentState() == machineState) {
                    output += selectedTransition.getOutput();
                    machineState = selectedTransition.getNextState();
                    transitionFound = true;
                    break;
                }
            }

            // If no transition is found for the character, set output to null
            if (!transitionFound) {
                return null;
            }
        }
        return output;
    }

}
