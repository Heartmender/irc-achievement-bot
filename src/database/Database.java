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
			System.err.println(e.getLocalizedMessage());
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
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(CONNECT_STRING);
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO users VALUES (" + achievementId + ", '" + nick + "';");
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
		} finally {
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
		
	}

	/**
	 * Gets the name of the specified achievement id. 
	 * @param achievementId the specified achievement id
	 * @return the name of the achievement
	 */
	public String getAchievementName(int achievementId) {
		String achievementName = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(CONNECT_STRING);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name FROM achievements WHERE id=" + achievementId + ";");
			if(rs.next()) { // should only be one result
				achievementName = rs.getString("name");
			}
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
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
		return achievementName;
	}

	/**
	 * Gets the points of the specified achievement id.
	 * @param achievementId the specified achievement id
	 * @return the points associated with the achievement
	 */
	public int getAchievementPoints(int achievementId) {
		int achievementPoints = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(CONNECT_STRING);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT points FROM achievements WHERE id=" + achievementId + ";");
			if(rs.next()) { // should only be one result
				achievementPoints = rs.getInt("points");
			}
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
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
		return achievementPoints;
	}

	/**
	 * Gets the achievement description of the specified achievement id.
	 * @param achievementId the specified achievement id
	 * @return the description associated with the achievement
	 */
	public String getAchievementDescription(int achievementId) {
		String achievementDescription = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(CONNECT_STRING);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name FROM achievements WHERE id=" + achievementId + ";");
			if(rs.next()) { // should only be one result
				achievementDescription = rs.getString("description");
			}
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
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
		return achievementDescription;
	}

	/**
	 * Increases the count in the tracker table for the associated nick and achievement id.
	 * @param nick the associated nick 
	 * @param achievementId the associated achievement id
	 */
	public void increaseCount(String nick, int achievementId) {
		int numberOfPoints = getCount(nick, achievementId);
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(CONNECT_STRING);
			stmt = conn.createStatement();
			if (numberOfPoints == 0) { // case where we have to insert a new value
				stmt.executeUpdate("INSERT INTO tracker VALUES (" + achievementId + ", '" + nick + "', 1)");
			} else { // case where we have ot update a value
				stmt.executeUpdate("UPDATE tracker SET count=" + (numberOfPoints + 1) + " WHERE id=" + achievementId + " AND nick='" + nick + "'");
			}
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
		} finally {
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
	}

	/**
	 * Gets the count in the tracker table for the associated nick and achievement id.
	 * @param nick the associated nick
	 * @param achievementId the associated achievement id
	 * @return the count stored in the tracker
	 */
	public int getCount(String nick, int achievementId) {
		int numberOfPoints = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(CONNECT_STRING);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count FROM tracker WHERE id=" + achievementId + " AND nick='" + nick + "'");
			if(rs.next()) { // should only be one result 
				numberOfPoints = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
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
		return numberOfPoints;
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
