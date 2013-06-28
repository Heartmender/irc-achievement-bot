package achievements;

import database.Database;

public class BOOP extends BOP {

	protected static final int COUNT = 1000;
	
	public BOOP(Database db) {
		super(db);
	}
	
	protected int getAchievementId() {
		return 12;
	}

}
