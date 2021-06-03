# Discord Bot API
This is a simple utility that allows you to easily create and manage discord bots!

## Installation
1) Add the following repository to your pom.xml file
```xml
<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>
```
2) Add the following dependency to your pom.xml file
```xml
<dependency>
	<groupId>com.github.mdeslippe</groupId>
	<artifactId>java-discord-api</artifactId>
	<version>{VERSION}</version>
	<scope>provided</scope>
</dependency>
```
3) You are good to go!

## How to Create a Bot
Creating a bot is very simple, and can be done very easily as follows:
```java
Bot bot = new Bot("token", "command_prefix");
bot.start();
```

## How to Create a Command
Creating a command can be done very easily as follows:
```java
public class MyCommand extends Command {

	public MyCommand() {
		super("command_name", Permission.command_permission, aliases);
	}

	@Override
	public void onCommand(Guild guild, MessageChannel channel, User executor, String label, String[] args) {
		// This will be ran when the command is executed and the executor has permission
		// to use the command.
	}

	@Override
	public void onPermissionDenied(Guild guild, MessageChannel channel, User executor, String label, String[] args) {
		// This will be ran when the command is executed and the executor does not have
		// permission to use the command.
	}

}
```
**IMPORTANT:** You must register the command with the bot in order for the commands to be dispatched! You can do so like this:
```java
Bot bot = new Bot("token", "command_prefix");
bot.getCommandManager().registerCommand(new MyCommand());
```

## How to Create a Sub-Command
Registering sub-commands is also very easy to do, this can be done anywhere that you have an instance of the command, but I suggest you do it in the constructor for the command like so:
```java
public class MyCommand extends Command {

	public MyCommand() {
		super("command_name", Permission.command_permission, aliases);
		this.addSubCommand(new MySubCommand());
	}

}
```
**IMPORTANT:** You do not need to register sub-commands with the bot, only the root command!

## How to Create an Event Listener
Creating an event listener is very similar to creating a command, just simply do the following:\
*(Note: You can find a list of the supported events [here](https://github.com/DV8FromTheWorld/JDA/wiki/8\)-List-of-Events))*
```java
public class MyListener implements Listener {

	// This method name can be anything.
	@EventHandler
	public void myListenerMethod(EventType event) {
		// This will be ran when the event occurs.
	}

}
```
You can have as many event listener methods as you want in a single class!

**IMPORTANT:** You must register the event listener with the bot in order for the events to be dispatched! You can do so like this:
```java
Bot bot = new Bot("token", "command_prefix");
bot.getEventManager().registerEvent(new MyListener());
```

## Common Pitfalls
### Commands Not Working?
You must ensure that the command is registered with the bot in order for it to work, you can see how to do so above in the "How to Create a Command" section.

### Event Listeners Not Working?
You must ensure that the event is registered with the bot in order for it to work, you can see how to do so above in the "How to Create an Event Listener" section.

## Contributions
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
