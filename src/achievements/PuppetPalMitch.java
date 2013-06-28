package achievements;

import database.Database;

public class PuppetPalMitch extends BOP {

	protected static final int COUNT = 10000;
	
	public PuppetPalMitch(Database db) {
		super(db);
	}
	
	protected int getAchievementId() {
		return 13;
	}

}
