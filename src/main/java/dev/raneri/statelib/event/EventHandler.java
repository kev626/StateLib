package dev.raneri.statelib.event;

import org.jetbrains.annotations.NotNull;

public interface EventHandler<T> {
	
	void onEvent(@NotNull T event);
	
}
