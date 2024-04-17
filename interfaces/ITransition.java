package interfaces;

/**
 * Interface representing a single FSM transition from a current state, upon receiving the given input, resulting in the given output and transition to the given next state.
 *
 */
public interface ITransition {

    /**
     * Returns the current state for this transition.
     * @return the current state to return
     */
    int getCurrentState();

    /**
     * Returns the input for this transition.
     * @return the input to return
     */
    char getInput();

    /**
     * Returns the output for this transition.
     * @return the output to return
     */
    char getOutput();

    /**
     * Returns the next state for this transition.
     * @return the next state to return
     */
    int getNextState();
}
