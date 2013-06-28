package commands;

import database.Database;
import org.pircbotx.hooks.ListenerAdapter;

public class Command extends ListenerAdapter {

	protected Database db;
	
	public Command(Database db) {
		this.db = db;
	}
	
}
