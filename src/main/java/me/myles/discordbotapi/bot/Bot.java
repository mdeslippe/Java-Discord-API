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

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.myles.discordbotapi.data.file.FileManager;
import me.myles.discordbotapi.event.EventManager;
import me.myles.discordbotapi.event.command.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * A Discord Bot.
 * 
 * @author Myles Deslippe
 */
public class Bot {

	/**
	 * The Bot's token.
	 */
	private final String token;

	/**
	 * The Bot's Discord API instance.
	 */
	@Getter
	private JDA discordAPI;

	/**
	 * The Bot's file manager.
	 */
	@Getter
	private final FileManager fileManager;

	/**
	 * The Bot's event manager.
	 */
	@Getter
	private final EventManager eventManager;

	/**
	 * The Bot's command manager.
	 */
	@Getter
	private final CommandManager commandManager;

	/**
	 * The Bot's internal command listener.
	 */
	@Getter(value = AccessLevel.PROTECTED)
	private final InternalCommandListener internalCommandListener;

	/**
	 * The Bot's internal event listener.
	 */
	@Getter(value = AccessLevel.PROTECTED)
	private final InternalEventListener internalEventListener;

	/**
	 * The Bot's command prefix.
	 */
	@Getter
	@Setter
	private String prefix;

	/**
	 * Create a new Discord Bot.
	 * 
	 * @param token The bot's token.
	 */
	public Bot(@Nonnull String token) {

		this.token = token;
		this.prefix = "";
		this.fileManager = new FileManager();
		this.eventManager = new EventManager();
		this.commandManager = new CommandManager();
		this.internalEventListener = new InternalEventListener(this);
		this.internalCommandListener = new InternalCommandListener(this);

	}

	/**
	 * Create a new Discord Bot.
	 * 
	 * @param token  The bot's token.
	 * @param prefix The bot's command prefix.
	 */
	public Bot(@Nonnull String token, @Nonnull String prefix) {

		this.token = token;
		this.prefix = prefix;
		this.fileManager = new FileManager();
		this.eventManager = new EventManager();
		this.commandManager = new CommandManager();
		this.internalEventListener = new InternalEventListener(this);
		this.internalCommandListener = new InternalCommandListener(this);

	}

	/**
	 * Start the Bot.
	 * 
	 * @throws LoginException If there was an issue logging into the Bot.
	 */
	public synchronized void start() throws LoginException {

		JDABuilder builder = JDABuilder.createDefault(token);
		builder.addEventListeners(this.getInternalEventListener());
		this.discordAPI = builder.build();

	}

	/**
	 * Stop the Bot.
	 */
	public synchronized void stop() {

		this.getDiscordAPI().shutdown();

	}

}
