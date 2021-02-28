package dev.raneri.statelib.event;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public interface EventHandler<T> {
	
	Logger logger = Logger.getLogger("StateLib");
	
	void onEvent(@NotNull T event);
	
	default void execute(T event) {
		try {
			onEvent(event);
		} catch (Throwable e) {
			logger.log(Level.SEVERE, "Failed to pass event", e);
		}
	}
}
