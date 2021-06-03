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
import java.util.HashMap;

import lombok.Getter;

/**
 * A {@link java.io.File File} management utility.
 * 
 * @author Myles Deslippe
 */
public class FileManager {

	/**
	 * The registered files.
	 */
	@Getter
	private HashMap<String, File> files;

	/**
	 * Create a new FileManager.
	 */
	public FileManager() {

		this.files = new HashMap<String, File>();

	}

	/**
	 * Get a file.
	 * 
	 * @param key The file's key.
	 * 
	 * @return The file.
	 */
	public File getFile(String key) {

		return this.files.get(key);

	}

	/**
	 * Add a file.
	 * 
	 * @param key  The key to bind to the file.
	 * @param file The file.
	 */
	public void addFile(String key, File file) {

		this.files.put(key, file);

	}

	/**
	 * Remove a file.
	 * 
	 * @param key The key that is bound to the file.
	 */
	public void removeFile(String key) {

		if (this.contains(key))
			this.files.remove(key);

	}

	/**
	 * Check if a key is registered with the FileManager.
	 * 
	 * @param key The key to check for.
	 * 
	 * @return The truth value associated with the key being registered with the
	 *         FileManager.
	 */
	public boolean contains(String key) {

		return this.files.containsKey(key);

	}

	/**
	 * Check if a file is registered with the FileManager.
	 * 
	 * @param file The file to check for.
	 * 
	 * @return The truth value associated with the file being registered with the
	 *         FileManager.
	 */
	public boolean contains(File file) {

		return this.files.containsValue(file);

	}
	
}
