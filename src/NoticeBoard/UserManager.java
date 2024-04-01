package NoticeBoard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class UserManager {
	private HashMap<String, RegisteredUser> users;
	private Admin admin;
	private String fileName = "user_data.txt";

	public UserManager() {
		users = new HashMap<>();
		admin = new Admin();
		addUser(admin.getId(), admin.getPassword());
		loadData();
	}

	public void saveData() {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			for (RegisteredUser user : users.values()) {
				bw.write(user.getId() + "," + user.getPassword());
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println("파일 저장 중 오류가 발생했습니다");
		}
	}

	private void loadData() {
		if (new File(fileName).exists()) {
			try {
				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					String[] userInfo = line.split(",");
					String username = userInfo[0];
					String password = userInfo[1];
					users.put(username, new RegisteredUser(username, password));
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				System.err.println("파일을 찾을 수 없습니다");
			} catch (IOException e) {
				System.err.println("파일 읽기 중 오류가 발생했습니다");
			}
		}
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

	public boolean checkUser(String id) {
		return users.containsKey(id);
	}

	public Admin getAdmin() {
		return admin;
	}

	public HashMap<String, RegisteredUser> getUsers() {
		return users;
	}

	public void removeUser(String id) {
		users.remove(id);
	}

	public int getUserSize() {
		return users.size();
	}

	public User findUserByIndex(int index) {
		return users.get(index);
	}
}