package dev.raneri.statelib.event;

import dev.raneri.statelib.State;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StateTransitionEvent {
	
	@NotNull private final State initialState;
	@Nullable private State finalState;
	
	public StateTransitionEvent(@NotNull State initialState, @Nullable State finalState) {
		this.initialState = initialState;
		this.finalState = finalState;
	}
	
	/**
	 * Gets the state the state machine was in before transitioning
	 * @return the state the state machine was in before transitioning
	 */
	public @NotNull State getInitialState() {
		return initialState;
	}
	
	/**
	 * Gets the state the state machine will be in after transitioning
	 * @return the state the state machine will be in after transitioning
	 */
	public @Nullable State getFinalState() {
		return finalState;
	}
	
	/**
	 * Changes the state the state machine will be in after transitioning
	 * @param finalState The new state
	 */
	public void setFinalState(@Nullable State finalState) {
		this.finalState = finalState;
	}
}
