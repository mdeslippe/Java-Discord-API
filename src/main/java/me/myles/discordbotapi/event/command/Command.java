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
import java.util.Arrays;

import lombok.Getter;

import me.myles.discordbotapi.utils.Utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

/**
 * Extend this class in classes you wish to make commands.
 * 
 * <p>
 * <strong>Note:</strong> The command you are creating must be registered with
 * the Bot for it to be dispatched.
 * </p>
 * 
 * @author Myles Deslippe
 */
public abstract class Command {

	/**
	 * The command's name.
	 */
	@Getter
	private final String name;

	/**
	 * The command's permission.
	 */
	@Getter
	private final Permission permission;

	/**
	 * The command's aliases.
	 */
	@Getter
	private final String[] aliases;

	/**
	 * The command's sub commands.
	 */
	private final ArrayList<Command> subCommands;

	/**
	 * Create a new command.
	 * 
	 * @param name The name of the command.
	 */
	public Command(String name) {

		this.name = name;
		this.permission = Permission.MESSAGE_WRITE;
		this.aliases = null;
		this.subCommands = new ArrayList<Command>();

	}

	/**
	 * Create a new command.
	 * 
	 * @param name       The name of the command.
	 * @param permission The permission required to use the command.
	 */
	public Command(String name, Permission permission) {

		this.name = name;
		this.permission = permission;
		this.aliases = null;
		this.subCommands = new ArrayList<Command>();

	}

	/**
	 * Create a new command.
	 * 
	 * @param name    The name of the command.
	 * @param aliases The command's aliases.
	 */
	public Command(String name, String[] aliases) {

		this.name = name;
		this.permission = Permission.MESSAGE_WRITE;
		this.aliases = aliases;
		this.subCommands = new ArrayList<Command>();

	}

	/**
	 * Create a new command.
	 * 
	 * @param name       The name of the command.
	 * @param permission The permission required to use the command.
	 * @param aliases    The command's aliases.
	 */
	public Command(String name, Permission permission, String[] aliases) {

		this.name = name;
		this.permission = permission;
		this.aliases = aliases;
		this.subCommands = new ArrayList<Command>();

	}

	/**
	 * Add a sub-command.
	 * 
	 * @param command The sub-command to add.
	 */
	public void addSubCommand(Command command) {

		this.subCommands.add(command);

	}

	/**
	 * Remove a sub-command.
	 * 
	 * @param command The sub-command to remove.
	 */
	public void removeSubCommand(Command command) {

		this.subCommands.remove(command);

	}

	/**
	 * Check if a sub-command exists.
	 * 
	 * @param command The sub-command to check for.
	 * 
	 * @return The truth value associated with the sub-command existing.
	 */
	public boolean containsSubCommand(Command command) {

		return this.subCommands.contains(command);

	}

	/**
	 * Get all of the sub-commands.
	 * 
	 * @return An array containing all of the sub-commands.
	 */
	public Command[] getSubCommands() {

		return this.subCommands.toArray(new Command[0]);

	}

	/**
	 * Remove all of the sub-commands.
	 */
	public void removeAllSubCommands() {

		this.subCommands.clear();

	}

	/**
	 * Execute a command.
	 * 
	 * <p>
	 * This method will cycle through the sub-commands, and if a match is found will
	 * execute the sub-command, otherwise it will run the command.
	 * </p>
	 * 
	 * @param guild    The guild the command was executed in.
	 * @param channel  The channel the message was sent in.
	 * @param executor The user that executed the command.
	 * @param label    The label that was used to execute the command.
	 * @param args     The arguments that were passed in with the command.
	 */
	public void executeCommand(Guild guild, MessageChannel channel, User executor, String label, String[] args) {

		if(args.length > 0) {
		
			for (Command subCmd : subCommands) {
	
				if (subCmd.getName().equalsIgnoreCase(args[0]) || Utils.strArrContainsStr(subCmd.getAliases(), args[0])) {
					
					subCmd.executeCommand(
							guild, 
							channel, 
							executor, 
							args[0], 
							Arrays.copyOfRange(args, 1, args.length));
					return;
	
				}
	
			}
			
		}

		// The quild will be null if the command was executed in a channel that is not a
		// guild, for example a private messageing channel.
		if (guild == null || guild.getMember(executor).hasPermission(this.getPermission()))
			this.onCommand(guild, channel, executor, label, args);
		else
			this.onPermissionDenied(guild, channel, executor, label, args);

	}

	/**
	 * This method will be called when the command is executed.
	 * 
	 * <p>
	 * <strong>Note:</strong> If the message was not sent in a guild it will be
	 * null.
	 * </p>
	 * 
	 * @param guild    The guild the command was executed in.
	 * @param channel  The channel the message was sent in.
	 * @param executor The user that executed the command.
	 * @param label    The label that was used to execute the command.
	 * @param args     The arguments that were passed in with the command.
	 */
	public abstract void onCommand(Guild guild, MessageChannel channel, User executor, String label, String[] args);

	/**
	 * This method will be called when the command is executed but the executor does
	 * not have permission to use the command.
	 * 
	 * <p>
	 * <strong>Note:</strong> If the message was not sent in a guild it will be
	 * null.
	 * </p>
	 * 
	 * @param guild    The guild the command was executed in.
	 * @param channel  The channel the message was sent in.
	 * @param executor The user that executed the command.
	 * @param label    The label that was used to execute the command.
	 * @param args     The arguments that were passed in with the command.
	 */
	public abstract void onPermissionDenied(Guild guild, MessageChannel channel, User executor, String label,
			String[] args);

}
