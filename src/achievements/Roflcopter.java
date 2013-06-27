package achievements;

import database.Database;

public class Roflcopter extends Lollerskating {

	public Roflcopter(Database db) {
		super(db);
		COUNT = 1000;
	}
	
	protected int getAchievementId() {
		return 3;
	}
	
}
