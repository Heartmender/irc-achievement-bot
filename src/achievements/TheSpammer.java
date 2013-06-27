package achievements;

import database.Database;

public class TheSpammer extends TheAdvertiser {

	public TheSpammer(Database db) {
		super(db);
		COUNT = 1000;
	}
	
	protected int getAchievementId() {
		return 6;
	}

}
