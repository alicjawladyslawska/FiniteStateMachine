package impl;

import interfaces.ITransition;

/**
 * Class representing a single transition for an FSM, equivalent to a row in a transition table.
 *
 */
public class Transition implements ITransition {
    private int currentState;
    private char input;
    private char output;
    private int nextState;

    /*
     * CONSTRUCTOR
     */

    public Transition(int currentState, char input, char output, int nextState) {
        this.currentState = currentState;
        this.input = input;
        this.output = output;
        this.nextState = nextState;
    }

    @Override
    public int getCurrentState() {
        return currentState;
    }

    @Override
    public char getInput() {
        return input;
    }

    @Override
    public char getOutput() {
        return output;
    }

    @Override
    public int getNextState() {
        return nextState;
    }

}
