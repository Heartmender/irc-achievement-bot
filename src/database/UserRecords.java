package database;

import java.util.HashMap;
import java.util.Map;

import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.NickChangeEvent;
import org.pircbotx.hooks.events.PartEvent;
import org.pircbotx.hooks.events.QuitEvent;
import org.pircbotx.hooks.events.WhoisEvent;

import bot.IRCBot;

/**
 * This class tries to associate a given user with an identity. 
 * @author jaderain
 */
public class UserRecords extends ListenerAdapter {

	private static UserRecords instance = null;
	private Map<String, String> userRecords = new HashMap<String, String>();
	private PircBotX bot;
	
	private UserRecords() {
		super();
	}
	
	/**
	 * Gets the singleton instance of this UserRecords class.
	 * @return the singleton instance of this UserRecords class
	 */
	public static UserRecords getInstance() {
		if(instance == null) {
			instance = new UserRecords();
		}
		return instance;
	}
	
	/**
	 * Sets the bot for this UserRecords class.
	 * @param bot the bot for this UserRecords class
	 */
	public void setBot(PircBotX bot) {
		this.bot = bot;
	}
	
	/**
	 * Starts tracking all of the users present in all of the channels.
	 * Warning: EXPENSIVE!
	 */
	public void loadUsers() {
		if(bot == null) {
			throw new NullPointerException("Bot can't be null!");
		}
		for(String channel : IRCBot.IRC_CHANNELS) {
			for(User user : bot.getChannel(channel).getUsers()) {
				getActualName(user.getNick());
			}
		}
	}
	
	/**
	 * Gets the registered nick of the user present in the channel, or the current nick if the user has no registered nick.
	 * @param currentNick the current nick of the user
	 * @return the registered nick of the user present in the channel, or the current nick if the user has no registered nick
	 */
	public String getActualName(String currentNick) {
		if(userRecords.containsKey(currentNick)) 
			return userRecords.get(currentNick);
		String registeredNick = currentNick;

		try {
			bot.sendRawLine("WHOIS " + currentNick + " " + currentNick);
			WaitForQueue waitForQueue = new WaitForQueue(bot);
			WhoisEvent event = waitForQueue.waitFor(WhoisEvent.class);
			waitForQueue.close();
			if(event.getRegisteredAs() != null && !event.getRegisteredAs().isEmpty()) {
				registeredNick = event.getRegisteredAs();
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
