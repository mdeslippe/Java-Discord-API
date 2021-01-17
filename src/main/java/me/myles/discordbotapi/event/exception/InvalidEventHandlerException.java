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
package me.myles.discordbotapi.event.exception;

import javax.annotation.Nullable;

/**
 * The InvalidEventHandlerException.
 * 
 * <p>
 * This exception will be thrown when an event handler is registered without
 * specifying the type of event it intended to handle.
 * </p>
 * 
 * @author Myles Deslippe
 */
public class InvalidEventHandlerException extends RuntimeException {

	/**
	 * The InvalidEventHandlerException serial version UID.
	 */
	private static final long serialVersionUID = 5159848849853387893L;

	/**
	 * The Invalid Event Handler Exception constructor.
	 * 
	 * @param reason The reason for throwing the exception.
	 */
	public InvalidEventHandlerException(@Nullable final String reason) {

		super(reason);

	}

	/**
	 * The Invalid Event Handler Exception constructor.
	 * 
	 * @param exception The reason for the exception.
	 */
	public InvalidEventHandlerException(Exception exception) {

		super(exception);

	}

}