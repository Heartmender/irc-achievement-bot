package database;

import java.util.HashMap;
import java.util.Map;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.NickChangeEvent;
import org.pircbotx.hooks.events.PartEvent;
import org.pircbotx.hooks.events.QuitEvent;
import org.pircbotx.hooks.events.WhoisEvent;

import bot.AchievementBot;

/**
 * This class tries to associate a given user with an identity. 
 * @author jaderain
 */
public class UserRecords extends ListenerAdapter {

	private static UserRecords instance = null;
	private Map<String, String> userRecords = new HashMap<String, String>();
	
	private UserRecords() {
		super();
	}
	
	public static UserRecords getInstance() {
		if(instance == null) {
			instance = new UserRecords();
		}
		return instance;
	}
	
	public String getActualName(String currentNick) {
		if(userRecords.containsKey(currentNick)) 
			return userRecords.get(currentNick);
		String registeredNick = currentNick;
		AchievementBot.getInstance().sendRawLine("WHOIS " + currentNick + " " + currentNick);
		try {
			WhoisEvent whoisEvent = AchievementBot.getInstance().waitFor(WhoisEvent.class);
			if(whoisEvent.getRegisteredAs() != null) {
				registeredNick = whoisEvent.getRegisteredAs();
			}
		} catch (InterruptedException e) {
			System.err.println(e.getLocalizedMessage());
		}
		userRecords.put(currentNick, registeredNick);
		System.out.println(currentNick + " is actually " + registeredNick);
		return registeredNick;
	}
	
	public void onJoin(JoinEvent event) {
		userRecords.put(event.getUser().getNick(), getActualName(event.getUser().getNick()));
	}
	
	public void onKick(KickEvent event) {
		userRecords.remove(event.getRecipient().getNick());
		System.out.println(event.getRecipient().getNick() + " removed");
	}
	
	public void onNickChange(NickChangeEvent event) {
		String registeredNick = userRecords.get(event.getOldNick());
		userRecords.remove(event.getOldNick());
		userRecords.put(event.getNewNick(), registeredNick);
		System.out.println(event.getNewNick() + " is actually " + registeredNick);
	}
	
	public void onPart(PartEvent event) {
		userRecords.remove(event.getUser().getNick());
		System.out.println(event.getUser().getNick() + " removed");
	}
	
	public void onQuit(QuitEvent event) {
		userRecords.remove(event.getUser().getNick());
		System.out.println(event.getUser().getNick() + " removed");
	}
	
	
}
