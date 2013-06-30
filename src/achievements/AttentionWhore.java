package achievements;

import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class AttentionWhore extends Achievement {

	public AttentionWhore(Database db) {
		super(db);
		setTimeStamp();
	}

	@Override
	protected int getAchievementId() {
		return 9;
	}
	
	public void onMessage(MessageEvent event) {
		db.increaseCount(event.getUser().getNick(), getAchievementId());
		if (hasOneWeekPassed()) { // this will work well if people talk on IRC often enough (e.g. at least once a day)
			String nick = db.getLargestCount(getAchievementId());
			db.clearCounts(getAchievementId());
			if(!db.hasAchievement(nick, getAchievementId())) {
				db.giveAchievement(nick, getAchievementId());
				event.getBot().sendMessage(event.getChannel().getName(), getAwardString(nick));
			}
			setTimeStamp();
		}
	}
	
	private boolean hasOneWeekPassed() {
		long time = System.currentTimeMillis() - getTimeStamp();
		return (time >= 604800000);
	}
	
	private long getTimeStamp() {
		return db.getTimeStamp(getAchievementId(), "AchievementBot");
	}
	
	private void setTimeStamp() {
		db.setTimeStamp(getAchievementId(), "AchievementBot", System.currentTimeMillis());
	}

}
