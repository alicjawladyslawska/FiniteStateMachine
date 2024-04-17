package exceptions;

/**
 * Exception type indicating that a transition would lead to non-determinism (should it be added to the transition table) as there is already a transition from the same state with the same input in the table.
 *
 */
public class NDTransitionException extends Exception {

    private static final long serialVersionUID = 8136572467869000143L;

}
