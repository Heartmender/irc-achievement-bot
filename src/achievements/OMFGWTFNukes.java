package achievements;

import database.Database;

public class OMFGWTFNukes extends Lollerskating {

	public OMFGWTFNukes(Database db) {
		super(db);
		COUNT = 10000;
	}

	protected int getAchievementId() {
		return 4;
	}
	
}
