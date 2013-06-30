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
					event.getBot().sendNotice(event.getUser(), getAwardString(nick));
				}
			}
		}
	}
	
	/**
	 * Identifies if a string contains an emoticion.
	 * @param str the string to test for emoticons
	 * @return true if the string contains an emoticon, false otherwise
	 */
	public static boolean stringContainsEmoticon(String str) {
		str = " " + str + " "; // helps identify spacing around emoticons
		if(str.matches("(.*?)( )(:|;|x|X|8|=)(-?)(\\S)( )")) /* identifies standard 'western' emoticons */
			return true;
		if(str.matches("(.*?)( )(\\S)(-?)(:|;|x|X|8|=)( )")) /* identifies standard backwards 'western' emoticons */
			return true;
		if(str.matches("( )(\\(?)(>|<|;|T|\\^|-|#|@|\\$|Q|\\*)(_*)(>|<|;|T|\\^|-|#|@|\\$|Q|\\*)(\\)?)(;*)( )")) /* identfies standard 'eastern' emoticons */
			return true;
		return false;
	}

}
