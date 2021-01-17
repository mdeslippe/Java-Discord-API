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

import java.util.Arrays;

import me.myles.discordbotapi.event.command.Command;
import me.myles.discordbotapi.utils.Utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * An internal command listener that is automatically bound to Bots.
 * 
 * @author Myles Deslippe
 */
public class InternalCommandListener {

	/**
	 * The Bot the internal command listener is bound to.
	 */
	private final Bot bot;

	/**
	 * Create an internal command listener.
	 * 
	 * @param bot The Bot to bind the command listener to.
	 */
	protected InternalCommandListener(Bot bot) {

		this.bot = bot;

	}

	/**
	 * Dispatch a command.
	 * 
	 * <p>
	 * <strong>Note:</strong> This method will not validate the command prefix.
	 * </p>
	 * 
	 * @param event The event containing the command.
	 */
	protected void dispatchCommand(MessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		if (!args[0].startsWith(this.bot.getPrefix()))
			return;
		else
			args[0] = args[0].substring(bot.getPrefix().length());

		for (Command command : bot.getCommandManager().getAllCommands()) {

			if (command.getName().equalsIgnoreCase(args[0]) || Utils.strArrContainsStr(command.getAliases(), args[0])) {

				command.executeCommand(
						event.isFromGuild() ? event.getGuild() : null,
						event.getMessage().getChannel(),
						event.getAuthor(),
						args[0],
						Arrays.copyOfRange(args, 1, args.length)
						);
				
				continue;

			}

		}

	}

}
