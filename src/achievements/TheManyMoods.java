package achievements;

import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class TheManyMoods extends Achievement {

	public TheManyMoods(Database db) {
		super(db);
	}

	@Override
	protected int getAchievementId() {
		return 15;
	}
	
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		if(stringContainsEmoticon(message)) {
			String nick = event.getUser().getNick();
			db.increaseCount(nick, getAchievementId());
			if(db.getCount(nick, getAchievementId()) >= 10) {
				if(!db.hasAchievement(nick, getAchievementId())) {
					db.giveAchievement(nick, getAchievementId());
					event.respond(getAwardString(nick));
				}
			}
		}
	}
	
	/**
	 * This method greedily attempts to recognize an emoticon based on the following assumptions:
	 *   -- Users will place an emoticon at the end of a phrase
	 *   -- Emoticons are typically 2-4 characters in length or if greater, at least 50% symbols.
	 *   -- Most emoticons have non-alphanumeric characters with few exceptions
	 */
	private boolean stringContainsEmoticon(String str) {
		String[] split = str.split("\\s"); // presume emoticons won't have spaces
		if(split.length > 0) {
			String emoticon = split[split.length - 1];
			/* exceptions to the third rule */
			if(emoticon.toLowerCase().equals("xd") || emoticon.toLowerCase().equals("dx")) {
				return true;
			}
			int symbolCount = 0;
			for(int i = 0; i < emoticon.length(); i++) {
				char c = emoticon.charAt(i);
				if (c > 32 && c < 48) symbolCount++;
				else if (c > 58 && c < 65) symbolCount++;
				else if (c > 90 && c < 97) symbolCount++;
				else if (c > 122) symbolCount++;
			}
			if(symbolCount > 0 && emoticon.length() >= 2 && emoticon.length() <= 4) {
				return true;
			}
			if(emoticon.length() > 4 && (double)symbolCount / (double)emoticon.length() >= 0.5) {
				return true;
			}
		}
		return false;
	}

}
