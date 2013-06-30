package achievements;

import java.net.MalformedURLException;
import java.net.URL;

import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class TheAdvertiser extends Achievement {
	
	protected static final int COUNT = 100;

	public TheAdvertiser(Database db) {
		super(db);
	}

	@Override
	protected int getAchievementId() {
		return 5;
	}
	
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		if(containsURL(message)) {
			String nick = event.getUser().getNick();
			db.increaseCount(nick, getAchievementId());
			if(db.getCount(nick, getAchievementId()) >= COUNT) {
				if(!db.hasAchievement(nick, getAchievementId())) {
					db.giveAchievement(nick, getAchievementId());
					event.getBot().sendMessage(event.getChannel().getName(), getAwardString(nick));
				}
			}
		}
	}
	
	protected boolean containsURL(String message) {
		String[] parts = message.split("\\s");
		for(String str : parts) try {
			new URL(str);
			return true;
		} catch (MalformedURLException e) {
			// the str was not a URL
		}
		return false;
	}

}
