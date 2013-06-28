package achievements;

import database.Database;

public class Roflcopter extends Lollerskating {

	protected static final int COUNT = 1000;
	
	public Roflcopter(Database db) {
		super(db);
	}
	
	protected int getAchievementId() {
		return 3;
	}
	
}
