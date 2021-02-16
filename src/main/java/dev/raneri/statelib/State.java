package dev.raneri.statelib;

import org.jetbrains.annotations.Nullable;

public abstract class State {
	
	/**
	 * Called when the state machine transitions into this state
	 * This is called immediately after onExit
	 *
	 * @param previousState The state the machine is transitioning from, or null if this is the first state
	 */
	public void onEntry(@Nullable State previousState) {}
	
	/**
	 * Called when the state machine transitions to a new state from this one
	 * This is called immediately prior to onEntry
	 *
	 * @param nextState the state the machine is transitioning to, or null if the state machine is exiting
	 */
	public void onExit(@Nullable State nextState) {}
	
	/**
	 * Gets the next state for this state machine.
	 *
	 * @return The new state, or null/this to indicate that no transition should take place
	 */
	public abstract @Nullable State getNextState();
	
	/**
	 * Called every loop iteration while this state is active
	 */
	public void loop() {}
	
}
