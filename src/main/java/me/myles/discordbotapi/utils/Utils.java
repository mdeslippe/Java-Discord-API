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
package me.myles.discordbotapi.utils;

/**
 * A general utilities class.
 * 
 * @author Myles Deslippe
 */
public final class Utils {

	/**
	 * Check if a string array contains a string.
	 * 
	 * <p>
	 * <strong>Note:</strong> this method is not case sensitive.
	 * </p>
	 * 
	 * @param array The array to check.
	 * @param value The value to check for.
	 * 
	 * @return The truth status associated with the array containing the value.
	 */
	public static boolean strArrContainsStr(String[] array, String value) {

		// If the array is null, return.
		if (array == null)
			return false;

		// Search for the value.
		for (String index : array) {

			// If the value is found, return true.
			if (index.equalsIgnoreCase(value))
				return true;

		}

		// Since the loop fell through, that implies the value was not found, thus
		// return false.
		return false;

	}

}
