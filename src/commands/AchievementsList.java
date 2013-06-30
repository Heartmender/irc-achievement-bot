package commands;

import java.util.LinkedList;
import java.util.List;

import org.pircbotx.hooks.events.PrivateMessageEvent;
import database.Database;

public class AchievementsList extends Command {

	
	public AchievementsList(Database db) {
		super(db);
	}

	public void onPrivateMessage(PrivateMessageEvent event) {
		String message = event.getMessage();
		if(message.startsWith("!list")) {
			String nick = event.getUser().getNick();
			List<Integer> achievementIds = db.getUserAchievements(nick);
			List<String> achievementList = getAchievementListString(achievementIds);
			for(String response : achievementList) {
				event.getBot().sendMessage(event.getUser(), response);
			}	
		}
	}
	
	public List<String> getAchievementListString(List<Integer> achievementIds) {
		List<String> awardList = new LinkedList<String>();
		awardList.add("You have the following achievements:");
		for(Integer id : achievementIds) {
			String achievementName = db.getAchievementName(id);
			int achievementPoints = db.getAchievementPoints(id);
			String achievementDescription = db.getAchievementDescription(id);
			awardList.add(achievementName + " [" + achievementPoints + "] - " + achievementDescription);
		}
		return awardList;
	}
	
}
