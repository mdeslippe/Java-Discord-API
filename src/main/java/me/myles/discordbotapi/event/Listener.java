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
package me.myles.discordbotapi.event;

/**
 * Implement this class in classes you wish to handle events.
 * 
 * <p>
 * <strong>Note:</strong> In order for the events to be passed to the event
 * handlers, the class implementing this interface must be registered in the
 * main class, as well as each event listener method must have the
 * {@link me.myles.discordbotapi.event.EventHandler EventHandler} annotation.
 * </p>
 * 
 * @author Myles Deslippe
 */
public interface Listener {

}
