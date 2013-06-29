package commands;

import java.util.ArrayList;

import org.pircbotx.hooks.events.PrivateMessageEvent;

import database.Database;

public class Say extends Command {
	
	private static ArrayList<AuthorizedUser> authorizedUsers = new ArrayList<AuthorizedUser>();
	
	public Say(Database db) {
		super(db);
		loadAuthorizedUsers();
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
	
	public void onPrivateMessage(PrivateMessageEvent event) {
		String message = event.getMessage();
		if(message.startsWith("!say")) {
			String nick = event.getUser().getNick();
			String host = event.getUser().getHostmask();
			AuthorizedUser user = new AuthorizedUser(nick, host);
			if(authorizedUsers.contains(user)) {
				event.getBot().sendMessage("#tulpa", message.replace("!say", ""));
			}
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
