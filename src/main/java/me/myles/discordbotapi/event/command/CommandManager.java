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
package me.myles.discordbotapi.event.command;

import java.util.ArrayList;

/**
 * The command manager for Discord {@link me.myles.discordbotapi.bot.Bot Bot}'s.
 * 
 * @author Myles Deslippe
 */
public class CommandManager {

	/**
	 * The registered commands.
	 */
	private final ArrayList<Command> commands;

	/**
	 * Create a Discord Bot command manager.
	 */
	public CommandManager() {

		this.commands = new ArrayList<Command>();

	}

	/**
	 * Register a command with the command manager.
	 * 
	 * @param command The command to register.
	 */
	public void registerCommand(Command command) {

		this.commands.add(command);

	}

	/**
	 * Unregister a command with the command manager.
	 * 
	 * @param command The command to unregister.
	 */
	public void unregisterCommand(Command command) {

		this.commands.remove(command);

	}

	/**
	 * Check if a command is registered with the command manager.
	 * 
	 * @param command The command to check for.
	 * 
	 * @return The truth value associated with the command being registered with the
	 *         command manager.
	 */
	public boolean containsCommand(Command command) {

		return this.commands.contains(command);

	}

	/**
	 * Get all of the registered commands.
	 * 
	 * @return An array of the registered commands.
	 */
	public Command[] getAllCommands() {

		return commands.toArray(new Command[0]);

	}

	/**
	 * Unregister all of the commands.
	 */
	public void unregisterAllCommands() {

		this.commands.clear();

	}

}