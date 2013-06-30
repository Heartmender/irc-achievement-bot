package achievements;

import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class ForeverAlone extends Achievement {

	private String nick = "";
	private int count = 1;
	
	public ForeverAlone(Database db) {
		super(db);
	}

	@Override
	protected int getAchievementId() {
		return 10;
	}
	
	public void onMessage(MessageEvent event) {
		if(nick.equals(event.getUser().getNick())) {
			count++;
		} else {
			nick = event.getUser().getNick();
			count = 1;
		}
		if (count >= 10) {
			if(!db.hasAchievement(nick, getAchievementId())) {
				db.giveAchievement(nick, getAchievementId());
				event.getBot().sendNotice(event.getUser(), getAwardString(nick));
			}
		}
	}

}
