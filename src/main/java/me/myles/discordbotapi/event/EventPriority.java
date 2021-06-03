package me.myles.discordbotapi.event;

/**
 * 
 * This these priorities to modify the order events are dispatched by the
 * {@link me.myles.discordbotapi.event.EventManager EventManager}.
 * 
 * @author Myles Deslippe
 *
 */
public enum EventPriority {

	/**
	 * The highest priority possible.
	 */
	HIGHEST,

	/**
	 * A priority higher then normal.
	 */
	HIGH,

	/**
	 * The default priority.
	 */
	NORMAL,

	/**
	 * A priority lower then normal.
	 */
	LOW,

	/**
	 * The lowest priority possible.
	 */
	LOWEST;

	/**
	 * Get the event priorities ordered from highest to lowest.
	 * 
	 * @return The event priorities in order.
	 */
	public static EventPriority[] getPrioritiesInOrder() {

		EventPriority[] priorities = { EventPriority.HIGHEST, EventPriority.HIGH, EventPriority.NORMAL, EventPriority.LOW, EventPriority.LOWEST };
		return priorities;

	}

}