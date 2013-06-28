package achievements;

import database.Database;

public class TheSpammer extends TheAdvertiser {

	protected static final int COUNT = 1000;
	
	public TheSpammer(Database db) {
		super(db);
	}
	
	protected int getAchievementId() {
		return 6;
	}

}
