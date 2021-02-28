package dev.raneri.statelib.event;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public abstract class EventHandler<T> {
	
	private static Logger logger = Logger.getLogger("StateLib");
	
	public abstract void onEvent(@NotNull T event);
	
	public void execute(T event) {
		try {
			onEvent(event);
		} catch (Throwable e) {
			logger.log(Level.SEVERE, "Failed to pass event", e);
		}
	}
}
