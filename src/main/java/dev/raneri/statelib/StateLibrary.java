package dev.raneri.statelib;

public class StateLibrary {
	
	private State currentState;
	
	/**
	 * Initialize the state machine with an initial state.
	 *
	 * @param state The initial state of the state machine
	 */
	public void init(State state) {
		if (currentState != null) throw new IllegalStateException("State machine has already been initialized!");
		currentState = state;
		state.onEntry(null);
	}
	
	/**
	 * Loop the state machine. This should be called on every iteration.
	 */
	public void loop() {
		if (currentState == null) throw new IllegalStateException("State machine is not yet initialized!");
		State next = currentState.getNextState();
		if (next != null && next != currentState) {
			currentState.onExit(next);
			State oldState = currentState;
			currentState = next;
			currentState.onEntry(oldState);
		}
		
		currentState.loop();
	}
	
	/**
	 * Shutdown the state machine. Further calls to loop() will throw exceptions, until init() is called.
	 */
	public void shutdown() {
		if (currentState == null) throw new IllegalStateException("State machine is not initialized and cannot be shut down!");
		currentState.onExit(null);
		currentState = null;
	}
	
}
