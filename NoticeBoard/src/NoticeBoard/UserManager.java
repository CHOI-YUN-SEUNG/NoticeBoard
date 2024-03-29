package NoticeBoard;

import java.util.HashMap;

public class UserManager {
	private HashMap<String, RegisteredUser> users;
	private boolean loggedIn;

	public UserManager() {
		users = new HashMap<>();
		loggedIn = false;
	}

	private static UserManager instance = new UserManager();
 
	public static UserManager getInstance() {
		return instance;
	}

	public void addUser(String username, String password) {
		RegisteredUser user = new RegisteredUser(username, password);
		users.put(username, user);
	}

	public RegisteredUser getUser(String username) {
		return users.get(username);
	}

	public HashMap<String, RegisteredUser> getUsers() {
		return users;
	}

	public void removeUser(String username) {
		users.remove(username);
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public int getUserSize() {
		return users.size();
	}

	public User findUserByIndex(int index) {
		return users.get(index).clone();
	}
}