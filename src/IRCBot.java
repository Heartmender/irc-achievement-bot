import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

import database.Database;

import achievements.*;


public class IRCBot {

	public static void main(String[] args) {
		PircBotX bot = new PircBotX();
		Database db = null;
		try {
			db = Database.getInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		setUpBot(bot);
		addModules(bot, db);
		try {
			bot.connect("irc.us.ponychat.net");
			bot.joinChannel("#tulpa");
		} catch (NickAlreadyInUseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IrcException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Configures various settings such as the bot's name, version info, and more.
	 * @param bot the bot to configure settings for
	 */
	private static void setUpBot(PircBotX bot) {
		bot.setName("AchievementBot");
		bot.setLogin("AchievementBot");
		bot.setVersion("AchievementBot v. 1.00");
		bot.setFinger("Hey! Save that for the second date <3");
		bot.setAutoReconnect(true);
		bot.setAutoReconnectChannels(true);
		bot.setVerbose(true);
		bot.setCapEnabled(true);
	}
	
	/**
	 * Adds various event handlers to the bot, which drive the logic behind the desired behaviors.
	 * @param bot the bot to add handlers to
	 */
	private static void addModules(PircBotX bot, Database db) {
		/* achievements */
		bot.getListenerManager().addListener(new CruiseControl(db));
		bot.getListenerManager().addListener(new Lollerskating(db));
		bot.getListenerManager().addListener(new Roflcopter(db));
		bot.getListenerManager().addListener(new OMFGWTFNukes(db));
		bot.getListenerManager().addListener(new TheAdvertiser(db));
		bot.getListenerManager().addListener(new TheSpammer(db));
		bot.getListenerManager().addListener(new DoYouEvenContent(db));
		bot.getListenerManager().addListener(new PopularityContest(db));
		bot.getListenerManager().addListener(new AttentionWhore(db));
		bot.getListenerManager().addListener(new ForeverAlone(db));
		
		/* commands */
		
	}
	
}
