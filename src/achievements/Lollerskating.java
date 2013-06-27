package achievements;

import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class Lollerskating extends Achievement {
	
	protected static int COUNT;

	public Lollerskating(Database db) {
		super(db);
		COUNT = 100;
	}

	protected int getAchievementId() {
		return 2;
	}
	
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		if(message.toLowerCase().contains("lol")) {
			String nick = event.getUser().getNick();
			db.increaseCount(nick, getAchievementId());
			if(db.getCount(nick, getAchievementId()) >= COUNT) {
				if(!db.hasAchievement(nick, getAchievementId())) {
					db.giveAchievement(nick, getAchievementId());
					event.respond(getAwardString(nick));
				}
			}
		}
	}

}
