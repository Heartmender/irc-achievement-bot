package achievements;

import database.Database;

public class DoYouEvenContent extends TheAdvertiser {

	protected static final int COUNT = 10000;
	
	public DoYouEvenContent(Database db) {
		super(db);
	}
	
	protected int getAchievementId() {
		return 7;
	}

}
