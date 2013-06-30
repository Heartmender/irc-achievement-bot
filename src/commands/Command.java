package commands;

import java.util.ArrayList;

import database.Database;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.User;

public class Command extends ListenerAdapter {

	private static ArrayList<AuthorizedUser> authorizedUsers;
	protected Database db;
	
	public Command(Database db) {
		this.db = db;
		authorizedUsers = new ArrayList<AuthorizedUser>();
		loadAuthorizedUsers();
	}
	
	/**
	 * Returns true if the specified user is an authorized user.
	 * @param user the user to authenticate
	 * @return true if the specified user is an authorized user, false otherwise
	 */
	protected boolean isAuthorizedUser(User user) {
		return authorizedUsers.contains(new AuthorizedUser(user.getNick(), user.getHostmask())) && user.isVerified();
	}
	
	private class AuthorizedUser {
		public String nick;
		public String host;
		public AuthorizedUser(String nick, String host) {
			this.nick = nick;
			this.host = host;
		}
		public boolean equals(Object obj) {
			if(obj == null) return false;
			if(obj == this) return true;
			if(obj.getClass() != getClass()) return false;
			AuthorizedUser user = (AuthorizedUser) obj;
			return nick.equals(user.nick) && host.equals(user.host);
		}
	}
	
	/*
	 * I didn't use a database method because I suspect I will only ever be the only authorized user.
	 * Obivously this could be changed.
	 */
	private void loadAuthorizedUsers() {
		authorizedUsers.add(new AuthorizedUser("jaderain", "i.am.secretly.a.robot"));
	}
	
}
