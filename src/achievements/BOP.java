package achievements;

import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class BOP extends Achievement {

	protected static final int COUNT = 100;
	
	public BOP(Database db) {
		super(db);
	}

	@Override
	protected int getAchievementId() {
		return 11;
	}
	
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		if(message.toLowerCase().contains("bop") || message.toLowerCase().contains("boop")) {
			String nick = event.getUser().getNick();
			db.increaseCount(nick, getAchievementId());
			if(db.getCount(nick, getAchievementId()) >= COUNT) {
				if(!db.hasAchievement(nick, getAchievementId())) {
					db.giveAchievement(nick, getAchievementId());
					event.getBot().sendNotice(event.getUser(), getAwardString(nick));
				}
			}
		}
	}
	
	public void onAction(ActionEvent event) {
		String message = event.getAction();
		if(message.toLowerCase().contains("bop") || message.toLowerCase().contains("boop")) {
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

}
