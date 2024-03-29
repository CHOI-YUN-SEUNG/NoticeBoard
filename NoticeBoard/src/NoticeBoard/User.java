package NoticeBoard;

public class User {
	private String id;
	private String passWord;

	public User() {

	}

	public User(String id, String passWord) {
		this.id = id;
		this.passWord = passWord;
	}

	public User clone() {
		User user = new User(this.id, this.passWord);
		return user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setgetPassWord(String passWord) {
		this.passWord = passWord;
	}
}