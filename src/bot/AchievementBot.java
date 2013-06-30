package bot;

import org.pircbotx.PircBotX;

public class AchievementBot extends PircBotX {

	private boolean RUNNING = true;
	private static AchievementBot instance = null;
	
	private AchievementBot() {
		super();
	}
	
	public static AchievementBot getInstance() {
		if(instance == null) {
			instance = new AchievementBot();
		}
		return instance;
	}
	
	public boolean isRunning() {
		return RUNNING;
	}
	
	public void shutDownEverything() {
		RUNNING = false;
	}
	
}
