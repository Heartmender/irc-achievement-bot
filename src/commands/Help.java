package commands;

import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class Help extends Command {

	public Help(Database db) {
		super(db);
	}
	
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		if(message.startsWith("!help")) {
			event.getBot().sendNotice(event.getUser(), "Information on my features can be found here: https://raw.github.com/jaderain/irc-achievement-bot/master/README.md");
		}
	}

}
