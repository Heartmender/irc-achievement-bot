package database;

public class Database {
	
	private static Database instance = null;
	
	private Database() {
		
	}
	
	/**
	 * Gets the singleton instance of this database.
	 * @return the singleton instance of this database
	 */
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}

	/**
	 * Checks if the specified nick has already earned this achievement.
	 * @param nick the specified nick
	 * @param achievementId the specified achievement id
	 * @return true if the specified nick has already earned this achievement, false otherwise
	 */
	public boolean hasAchievement(String nick, int achievementId) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gives the specified nick the specified achievement.
	 * @param nick the specified nick
	 * @param achievementId the specified achievement id
	 */
	public void giveAchievement(String nick, int achievementId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets the name of the specified achievement id. 
	 * @param achievementId the specified achievement id
	 * @return the name of the achievement
	 */
	public String getAchievementName(int achievementId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the points of the specified achievement id.
	 * @param achievementId the specified achievement id
	 * @return the points associated with the achievement
	 */
	public int getAchievementPoints(int achievementId) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Gets the achievement description of the specified achievement id.
	 * @param achievementId the specified achievement id
	 * @return the description associated with the achievement
	 */
	public String getAchievementDescription(int achievementId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Increases the count in the tracker table for the associated nick and achievement id.
	 * @param nick the associated nick 
	 * @param achievementId the associated achievement id
	 */
	public void increaseCount(String nick, int achievementId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets the count in the tracker table for the associated nick and achievement id.
	 * @param nick the associated nick
	 * @param achievementId the associated achievement id
	 * @return the count stored in the tracker
	 */
	public int getCount(String nick, int achievementId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setTimeStamp(int achievementId, String string,
			long currentTimeMillis) {
		// TODO Auto-generated method stub
		
	}

	public long getTimeStamp(int achievementId, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getLargestCount(int achievementId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void clearCounts(int achievementId) {
		// TODO Auto-generated method stub
		
	}

}
