package impl;

import interfaces.IFactory;
import interfaces.IFiniteStateMachine;
import interfaces.ITransition;
import interfaces.ITransitionTable;

/**.
 * Factory implementation
 * @author (extended / edited by) 220019540
 * 05-10-2023
 */
 public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    //constructor
    private Factory() {

    }

    /**
     * Method which returns an instance of the Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public ITransition makeTransition(int current_state, char input, char output, int next_state) {
        return new Transition(current_state, input, output, next_state);
    }

    @Override
    public ITransitionTable makeTransitionTable() {
        ITransitionTable transitionTable = new TransitionTable();
        return transitionTable;
    }

    /**.
     * makeFiniteStateMachine()
     * creates an instance of the finitestatemachine class
     * @return instance of the finitestatemachine object
     */
    @Override
    public IFiniteStateMachine makeFiniteStateMachine() {
        return new FiniteStateMachine();
    }

}
