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
package me.myles.discordbotapi.bot;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

/**
 * An internal event listener that is automatically bound to Bots.
 * 
 * @author Myles Deslippe
 */
public class InternalEventListener implements EventListener {

	/**
	 * The Bot the internal event listener is bound to.
	 */
	private final Bot bot;

	/**
	 * Create an internal event listener.
	 * 
	 * @param bot The Bot to bind the event listener to.
	 */
	protected InternalEventListener(Bot bot) {

		this.bot = bot;

	}

	/**
	 * Dispatch in-bound events.
	 */
	public void onEvent(GenericEvent event) {

		new Thread(() -> {

			if (event instanceof MessageReceivedEvent)
				bot.getInternalCommandListener().dispatchCommand((MessageReceivedEvent) event);

			bot.getEventManager().dispatchEvent(event);

		}).start();

	}

}
