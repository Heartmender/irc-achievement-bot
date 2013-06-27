package achievements;

import database.Database;

public class DoYouEvenContent extends TheAdvertiser {

	public DoYouEvenContent(Database db) {
		super(db);
		COUNT = 10000;
	}
	
	protected int getAchievementId() {
		return 7;
	}

}
