package dev.raneri.statelib;

import dev.raneri.statelib.event.LoopEndEvent;
import dev.raneri.statelib.event.LoopStartEvent;
import dev.raneri.statelib.event.StateTransitionEvent;
import dev.raneri.statelib.event.EventHandler;
import java.util.ArrayList;
import java.util.List;

public class StateLibrary {
	
	private State currentState;
	
	private List<EventHandler<StateTransitionEvent>> transitionHandlerList = new ArrayList<>();
	private List<EventHandler<LoopStartEvent>> loopStartHandlerList = new ArrayList<>();
	private List<EventHandler<LoopEndEvent>> loopEndHandlerList = new ArrayList<>();
	
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
		LoopStartEvent startEvent = new LoopStartEvent(currentState);
		loopStartHandlerList.forEach(h -> h.onEvent(startEvent));
		State next = currentState.getNextState();
		if (next != null && next != currentState) {
			currentState.onExit(next);
			State oldState = currentState;
			StateTransitionEvent transitionEvent = new StateTransitionEvent(oldState, next);
			transitionHandlerList.forEach(h -> h.onEvent(transitionEvent));
			next = transitionEvent.getFinalState();
			currentState = next;
			currentState.onEntry(oldState);
		}
		
		currentState.loop();
		
		LoopEndEvent endEvent = new LoopEndEvent(currentState);
		loopEndHandlerList.forEach(h -> h.onEvent(endEvent));
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
	public void addStateTransitionHandler(EventHandler<StateTransitionEvent> handler) {
		if (handler == null) throw new NullPointerException("handler");
		transitionHandlerList.add(handler);
	}
	
	/**
	 * Attach a handler to run when the loop begins execution
	 * @param handler the handler to run when the loop begins execution
	 */
	public void addLoopStartHandler(EventHandler<LoopStartEvent> handler) {
		if (handler == null) throw new NullPointerException("handler");
		loopStartHandlerList.add(handler);
	}
	
	/**
	 * Attach a handler to run when the loop finishes
	 * @param handler the handler to run when the loop finishes
	 */
	public void addLoopEndHandler(EventHandler<LoopEndEvent> handler) {
		if (handler == null) throw new NullPointerException("handler");
		loopEndHandlerList.add(handler);
	}
	
}
