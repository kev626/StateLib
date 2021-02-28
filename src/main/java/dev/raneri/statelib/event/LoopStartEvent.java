package dev.raneri.statelib.event;

import dev.raneri.statelib.State;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LoopStartEvent {
	
	@Nullable private final State state;
	
	public LoopStartEvent(@NotNull State state) {
		this.state = state;
	}
	
	/**
	 * Gets the state the state machine is currently in
	 * @return the state the state machine is currently in
	 */
	public @NotNull State getState() {
		return state;
	}
}
