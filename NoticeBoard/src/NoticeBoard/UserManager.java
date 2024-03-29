package NoticeBoard;

import java.util.HashMap;

public class UserManager {
	private HashMap<String, RegisteredUser> users;
	private admin admin;

	public UserManager() {
		users = new HashMap<>();
		admin = new admin();
		addUser(admin.getId(), admin.getPassWord()); // 관리자 계정 추가
	}

	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}

	public void addUser(String id, String password) {
		RegisteredUser user = new RegisteredUser(id, password);
		users.put(id, user);
	}

	public RegisteredUser getUser(String id) {
		return users.get(id);
	}

	public HashMap<String, RegisteredUser> getUsers() {
		return users;
	}

	public boolean checkUser(String id) {
		if (getUser(id) != null)
			return true;
		return false;
	}

	public void removeUser(String id) {
		users.remove(id);
	}

	public int getUserSize() {
		return users.size();
	}

	public User findUserByIndex(int index) {
		return users.get(index).clone();
	}
}