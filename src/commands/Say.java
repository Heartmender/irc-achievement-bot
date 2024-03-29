package commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bot.IRCBot;

import database.Database;

public class Say extends Command {
	
	
	public Say(Database db) {
		super(db);
	}
	
	public void onPrivateMessage(PrivateMessageEvent event) {
		String message = event.getMessage();
		if(message.startsWith("!say")) {
			if(this.isAuthorizedUser(event.getUser())) {
				for(String channel : IRCBot.IRC_CHANNELS) {
					event.getBot().sendMessage(channel, message.replaceFirst("!say", ""));
				}
			}
		}
	}
	


}
