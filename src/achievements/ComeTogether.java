package achievements;

import java.util.HashSet;

import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import database.Database;

public class ComeTogether extends Achievement {

	public ComeTogether(Database db) {
		super(db);
	}

	@Override
	protected int getAchievementId() {
		return 16;
	}
	
	public void onMessage(MessageEvent event) {
		int pingCount = 0;
		HashSet<String> usersInChannel = new HashSet<String>(); 
		for(User user : event.getChannel().getUsers()) {
			usersInChannel.add(user.getNick());
		}
		for(String token : event.getMessage().split("\\s")) {
			if(usersInChannel.contains(token)) pingCount++;
		}
		if(pingCount >= 5) {
			if(!db.hasAchievement(event.getUser().getNick(), getAchievementId())) {
				db.giveAchievement(event.getUser().getNick(), getAchievementId());
				event.getBot().sendMessage(event.getChannel().getName(), getAwardString(event.getUser().getNick()));
			}
		}
	}

}
