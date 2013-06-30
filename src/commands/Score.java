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
				String[] split = message.split("//s");
				if(split.length < 2) {
					/* fallback behavior - the user is looking up his own score */
					String nick = event.getUser().getNick();
					event.respond(getScoreString(nick, db.getUserPoints(nick)));
				} else if (split.length == 2) {
					event.respond(getScoreString(split[1], db.getUserPoints(split[1])));
				} else if (split.length == 3) {
					event.respond(getComparisonString(split[1], db.getUserPoints(split[1]), split[2], db.getUserPoints(split[2])));
				} else if (split.length > 3) {
					event.respond("I can only compare up to two users at once! D:");
				}
			}
		}
	}
	
	public String getScoreString(String nick, int points) {
		return nick + " has " + points + " achievement points!";
	}
	
	public String getComparisonString(String nick1, int points1, String nick2, int points2) {
		return nick1 + " has " + points1 + " achievement points and " + nick2 + " has " + points2 + " achievement points.";
	}

}
