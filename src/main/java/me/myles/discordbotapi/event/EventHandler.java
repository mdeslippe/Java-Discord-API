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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add this annotation to methods that will handle events.
 * 
 * <p>
 * <strong>Note:</strong> The class containing the event handling method must
 * implement {@link me.myles.discordbotapi.event.Listener Listener}, and must be
 * registered with the Bot in order for events to be dispatched to this event
 * handler.
 * </p>
 * 
 * @author Myles Deslippe
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

	/**
	 * Get the priority of the event handler.
	 * 
	 * @return The priority of the event handler.
	 */
	public EventPriority priority() default EventPriority.NORMAL;

}
