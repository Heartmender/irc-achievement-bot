package achievements;

import org.pircbotx.hooks.ListenerAdapter;

import database.Database;

public abstract class Achievement extends ListenerAdapter {

	protected Database db;
	
	public Achievement(Database db) {
		this.db = db;
	}
	
	/**
	 * Gets the achievement id used in the database.
	 * @return the achievement id
	 */
	protected abstract int getAchievementId();
	
	/**
	 * Gets the award string for a successful achievement.
	 * @param nick the nick that earned the award
	 * @return the success string for the achievement
	 */
	protected String getAwardString(String nick) {
		String achievementName = db.getAchievementName(getAchievementId());
		int achievementPoints = db.getAchievementPoints(getAchievementId());
		String achievementDescription = db.getAchievementDescription(getAchievementId());
		
		return "Congratulations, " + nick + "! You have earned the award " + achievementName +
				" [" + achievementPoints + "] - " + achievementDescription;
	}
	
}
