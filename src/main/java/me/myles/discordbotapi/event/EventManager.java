/*
 * 
 * Copyright 2021 Myles Deslippe
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package me.myles.discordbotapi.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import me.myles.discordbotapi.event.exception.InvalidEventHandlerException;
import net.dv8tion.jda.api.events.GenericEvent;

/**
 * The event manager for Discord {@link me.myles.discordbotapi.bot.Bot Bot}'s.
 * 
 * @author Myles Deslippe
 */
public class EventManager {

	/**
	 * The registered listeners.
	 */
	private final ArrayList<Listener> listeners;

	/**
	 * Create a Discord Bot event manager.
	 */
	public EventManager() {

		this.listeners = new ArrayList<Listener>();

	}

	/**
	 * Register a listener with the event manager.
	 * 
	 * @param listener The listener to register.
	 */
	public void registerListener(Listener listener) {

		this.listeners.add(listener);

	}

	/**
	 * Unregister a listener with the event manager.
	 * 
	 * @param listener The listener to unregister.
	 */
	public void unregisterListener(Listener listener) {

		this.listeners.remove(listener);

	}

	/**
	 * Check if a listener is registered with the event manager.
	 * 
	 * @param listener The listener to check for.
	 * 
	 * @return The truth value associated with the listener being registered with
	 *         the event manager.
	 */
	public boolean containsListener(Listener listener) {

		return this.listeners.contains(listener);

	}

	/**
	 * Get all of the registered listeners.
	 * 
	 * @return An array of the registered listeners.
	 */
	public Listener[] getAllListeners() {

		return listeners.toArray(new Listener[0]);

	}

	/**
	 * Unregister all of the listeners.
	 */
	public void unregisterAllListeners() {

		this.listeners.clear();

	}

	/**
	 * Dispatch an event.
	 * 
	 * @param event The event to dispatch.
	 */
	public void dispatchEvent(@Nonnull final GenericEvent event) {

		for (Listener index : listeners)
			this.executeEvent(index.getClass(), index, event);

	}

	/**
	 * Execute an event.
	 * 
	 * @param clazz    The listener class.
	 * @param executor The class executing the event.
	 * @param args     The event arguments.
	 */
	private void executeEvent(@Nonnull Class<?> clazz, @Nonnull Object executor, @Nonnull final Object... args) {

		for (Method index : clazz.getDeclaredMethods()) {

			if (index.getDeclaredAnnotation(EventHandler.class) != null) {

				if (index.getParameterCount() == 0)
					throw new InvalidEventHandlerException("Error: No paramater was specified!");

				// Define variables that will be used for readability purposes.
				String paramaterType = index.getParameterTypes()[0].getTypeName();
				String argumentType = args[0].getClass().getTypeName();
				String genericType = GenericEvent.class.getTypeName();

				if (paramaterType.equals(argumentType) || paramaterType.equals(genericType)) {

					try {

						index.invoke(executor, args);

					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

						throw new InvalidEventHandlerException(e);

					}

				}
			}

		}

	}

}
