package NoticeBoard;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> userList;

	private UserManager() {
		userList = new ArrayList<>();
	}

	private static UserManager instance = new UserManager();
 
	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(String id, String passWord) {
		if (checkId(id)) {
			User user = new User(id, passWord);
			userList.add(user);
			return user.clone();
		}
		return new User();
	}

	private boolean checkId(String id) {
		for (User user : userList) {
			if (user.getId().equals(id))
				return false;
		}
		return true;
	}

	public User findUserByUserID(String id) {
		for (User user : userList) {
			if (user.getId().equals(id))
				return user.clone();
		}
		return new User();
	}

	public User findUserByUserPW(String passWord) {
		for (User user : userList) {
			if (user.getPassWord().equals(passWord))
				return user.clone();
		}
		return new User();
	}


	public boolean deleteUser(User user) {
		String userId = user.getId();
		User targetUser = getUserById(userId);
		return userList.remove(targetUser);
	}

	private User getUserById(String Id) {
		for (User user : userList) {
			if (user.getId() == Id)
				return user;
		}
		return new User();
	}

	public int getUserSize() {
		return userList.size();
	}

	public ArrayList<User> getUserList() {
		ArrayList<User> temp = new ArrayList<>();
		for (User user : userList)
			temp.add(user.clone());
		return temp;
	}

	public User findUserByIndex(int index) {
		return userList.get(index).clone();
	}
}