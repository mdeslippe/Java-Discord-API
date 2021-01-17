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
import java.nio.file.Path;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import lombok.Getter;

/**
 * A YAML configuration file utility.
 * 
 * @author Myles Deslippe
 */
public class ConfigurationFile extends AbstractConfigurationFile {

	/**
	 * The Serial ID.
	 */
	private static final long serialVersionUID = 7950438595670529199L;

	/**
	 * The YAML configuration.
	 */
	@Getter
	private YamlFile configuration;

	/**
	 * Create a new ConfigurationFile.
	 * 
	 * @param file The file that will be used to serialize and deserialize the
	 *             configuration.
	 */
	public ConfigurationFile(File file) {

		super(file);
		this.configuration = new YamlFile(this);

	}

	/**
	 * Create a new ConfigurationFile.
	 * 
	 * @param path A path to the file that will be used to serialize and deserialize
	 *             the configuration.
	 */
	public ConfigurationFile(String path) {

		super(path);
		this.configuration = new YamlFile(this);

	}

	/**
	 * Create a new ConfigurationFile.
	 * 
	 * @param path A path to the file that will be used to serialize and deserialize
	 *             the configuration.
	 */
	public ConfigurationFile(Path path) {

		super(path);
		this.configuration = new YamlFile(this);

	}

	@Override
	public synchronized void load() throws IOException {

		try {

			this.configuration.load();

		} catch (InvalidConfigurationException e) {

			throw new IOException(e);

		}

	}

	@Override
	public synchronized void save() {

		this.save();

	}

}
