package achievements;

import database.Database;

public class OMFGWTFNukes extends Lollerskating {

	protected static final int COUNT = 10000;
	
	public OMFGWTFNukes(Database db) {
		super(db);
	}

	protected int getAchievementId() {
		return 4;
	}
	
}
