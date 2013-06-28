package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static Database instance = null;
	private static final String CONNECT_STRING = "jdbc:mysql://localhost/achievement_bot?"
			+ "user=achievement&password=bot";
	
	private Database() throws InstantiationException, IllegalAccessException, ClassNotFoundException { 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	}
	
	/**
	 * Gets the singleton instance of this database.
	 * @return the singleton instance of this database
	 * @throws Exceptions when things go poorly.
	 */
	public static Database getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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
	 * @throws SQLException when things go poorly.
	 */
	public boolean hasAchievement(String nick, int achievementId) throws SQLException {
		boolean hasAchievement = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(CONNECT_STRING);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id FROM users WHERE nick='" + nick + "';");
			while(rs.next()) {
				if(achievementId == rs.getInt("id")) {
					hasAchievement = true;
					break;
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { } // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { } // ignore
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { } // ignore
				conn = null;
			}
		}
		return hasAchievement;
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
