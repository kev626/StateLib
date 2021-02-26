package dev.raneri.statelib;

import dev.raneri.statelib.event.StateTransitionEvent;
import dev.raneri.statelib.event.StateTransitionEventHandler;
import java.util.List;

public class StateLibrary {
	
	private State currentState;
	
	private List<StateTransitionEventHandler> transitionHandlerList;
	
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
			StateTransitionEvent event = new StateTransitionEvent(oldState, next);
			transitionHandlerList.forEach(h -> h.onTransition(event));
			next = event.getFinalState();
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
	
	/**
	 * Attach a handler to run when a state transition occurs
	 * @param handler the handler to run when a state transition occurs
	 */
	public void addStateTransitionHandler(StateTransitionEventHandler handler) {
		if (handler == null) throw new NullPointerException("handler");
		transitionHandlerList.add(handler);
	}
	
}
