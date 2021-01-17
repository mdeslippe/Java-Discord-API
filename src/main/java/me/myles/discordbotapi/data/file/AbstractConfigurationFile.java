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
package me.myles.discordbotapi.data.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import lombok.Getter;
import lombok.Setter;

/**
 * A file configuration outline.
 * 
 * <p>
 * Extend this in classes that are going to be configuration files.
 * </p>
 * 
 * @author Myles Deslippe
 */
public abstract class AbstractConfigurationFile extends File {

	/**
	 * The Serial ID.
	 */
	private static final long serialVersionUID = -6450895964082971119L;

	/**
	 * The default file configuration.
	 */
	@Getter
	@Setter
	private File defaults;

	/**
	 * Create a new AbstractConfigurationFile.
	 * 
	 * @param file The file that will be used to serialize and deserialize the
	 *             configuration.
	 */
	public AbstractConfigurationFile(File file) {

		super(file.toString());
		this.defaults = null;

	}

	/**
	 * Create a new AbstractConfigurationFile.
	 * 
	 * @param path A path to the file that will be used to serialize and deserialize
	 *             the configuration.
	 */
	public AbstractConfigurationFile(String path) {

		super(path);
		this.defaults = null;

	}

	/**
	 * Create a new AbstractConfigurationFile.
	 * 
	 * @param path A path to the file that will be used to serialize and deserialize
	 *             the configuration.
	 */
	public AbstractConfigurationFile(Path path) {

		super(path.toFile().toString());
		this.defaults = null;

	}

	/**
	 * Create the file.
	 * 
	 * <p>
	 * The default configuration will be copied if it exists.
	 * </p>
	 * 
	 * <p>
	 * <strong>Note:</strong> This will overwrite the file if it already exists, be
	 * sure the check if it {@link #exists()} before calling the method.
	 * </p>
	 * 
	 * @throws IOException If the file could not be created, or the default
	 *                     configuration could not be found.
	 */
	public synchronized void create() throws IOException {

		this.copyDefaults();

	}

	/**
	 * Create the file if it does not exist.
	 * 
	 * <p>
	 * The default configuration will be copied if it exists.
	 * </p>
	 * 
	 * @throws IOException If the file could not be created, or the default
	 *                     configuration could not be found.
	 */
	public synchronized void createIfNotExists() throws IOException {

		if (!this.exists())
			this.create();

	}

	/**
	 * Load the configuration file.
	 * 
	 * @throws IOException If the file could not be loaded.
	 */
	public abstract void load() throws IOException;

	/**
	 * Save the configuration file.
	 * 
	 * @throws IOException If the file could not be saved.
	 */
	public abstract void save();

	/**
	 * Copy the default configuration.
	 * 
	 * <p>
	 * <strong>Note:</strong> This will overwrite the file if it already exists.
	 * </p>
	 * 
	 * @throws IOException If the file could not be created, or the default
	 *                     configuration could not be found.
	 */
	public synchronized void copyDefaults() throws IOException {

		if (this.defaults == null)
			this.createNewFile();
		else
			Files.copy(defaults.toPath(), this.toPath(), StandardCopyOption.REPLACE_EXISTING);

	}

}
