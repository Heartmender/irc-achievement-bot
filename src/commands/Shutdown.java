package commands;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import bot.AchievementBot;
import database.Database;

public class Shutdown extends Command {

	public Shutdown(Database db) {
		super(db);
	}
	
	public void onPrivateMessage(PrivateMessageEvent event) {
		String message = event.getMessage();
		if(message.startsWith("!shutdown")) {
			if(this.isAuthorizedUser(event.getUser())) {
				AchievementBot.getInstance().shutDownEverything();
			}
		}
	}
	
}
