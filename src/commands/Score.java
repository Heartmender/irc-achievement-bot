package commands;

import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class Score extends Command {

	public Score(Database db) {
		super(db);
	}
	
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		if(message.startsWith("!score")) {
			if(message.equals("!score")) { // the user is looking up his own score
				String nick = event.getUser().getNick();
				event.respond(getScoreString(nick, db.getUserPoints(nick)));
			} else { // the user is looking up a nick's score
				String[] split = message.split(" ");
				if(split.length < 2) {
					/* fallback behavior - the user is looking up his own score */
					String nick = event.getUser().getNick();
					event.respond(getScoreString(nick, db.getUserPoints(nick)));
				} else {
					event.respond(getScoreString(split[1], db.getUserPoints(split[1])));
				}
			}
		}
	}
	
	public String getScoreString(String nick, int points) {
		return nick + " has " + points + " achievement points!";
	}

}
