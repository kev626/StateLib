package dev.raneri.statelib.event;

import org.jetbrains.annotations.NotNull;

public interface StateTransitionEventHandler {
	
	void onTransition(@NotNull StateTransitionEvent event);
	
}
