package bot;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.NickAlreadyInUseException;

import database.*;
import achievements.*;
import commands.*;


public class IRCBot {
	
	public static final String IRC_NETWORK = "irc.us.ponychat.net";
	public static final String[] IRC_CHANNELS = { "#tulpa" };
	public static boolean RUNNING = true;

	/**
	 * IRCBot takes the NickServ password as an argument via CLI.
	 */
	public static void main(String[] args) {
		
		if(args.length < 1 || args.length > 1) {
			System.err.println("This program requires that the NickServ pass be the only argument.");
			System.exit(1);
		}
		
		/* start the bot and connect to the network */
		PircBotX bot = new PircBotX();
		setUpBot(bot);
		try {
			bot.connect(IRC_NETWORK);
			for(String channel : IRC_CHANNELS) {
				bot.joinChannel(channel);
			}
		} catch (NickAlreadyInUseException e) {
			try {
				bot.sendMessage("NickServ", "GHOST AchievementBot " + args[0]);
				Thread.sleep(1000);
				bot.setName("AchievementBot");
			} catch (Exception ex) {
				System.err.println("Our nick is already in use, and we couldn't change to it! :(");
				System.exit(1);
			}
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}
		bot.sendMessage("NickServ", "IDENTIFY " + args[0]);
		bot.sendMessage("HostServ", "ON"); // attempt to turn on our VHOST
		
		/* start the database */
		Database db = null;
		try {
			db = Database.getInstance(bot);
		} catch (Exception e) {
			System.err.println("Unable to load JDBC driver, so unable to continue execution of program. Sorry :(");
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

		/* start record keeping for users */
		UserRecords userRecords = UserRecords.getInstance();
		userRecords.setBot(bot);
		try {
			userRecords.loadUsers();
		} catch (Exception e) {
			System.err.println("We couldn't load the user records! :(");
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}
		
		/* add the modules */
		addModules(bot, db);
		
		/* running loop */
		while(RUNNING) { // run until forcibly terminated
			if(!RUNNING) {
				bot.quitServer("Going down for maintenance!");
				bot.disconnect();
				System.exit(0);
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) { } // ignore
		}
	}
	
	/**
	 * Configures various settings such as the bot's name, version info, and more.
	 * @param bot the bot to configure settings for
	 */
	private static void setUpBot(PircBotX bot) {
		bot.setName("AchievementBot");
		bot.setLogin("Two");
		bot.setVersion("AchievementBot v. 1.04");
		bot.setFinger("Hey! Save that for the second date <3");
		bot.setAutoNickChange(true);
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
		bot.getListenerManager().addListener(new Actor(db));
		bot.getListenerManager().addListener(new TheManyMoods(db));
		bot.getListenerManager().addListener(new ComeTogether(db));
		
		/* commands */
		bot.getListenerManager().addListener(new AchievementsList(db));
		bot.getListenerManager().addListener(new Help(db));
		bot.getListenerManager().addListener(new Say(db));
		bot.getListenerManager().addListener(new Score(db));
	}
	
}
