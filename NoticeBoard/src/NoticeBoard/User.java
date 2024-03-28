package NoticeBoard;

public class User {
	private String id;
	private String passWord;
	private boolean authorized;

	public User() {

	}

	public User(String id, String passWord) {
		this.id = id;
		this.passWord = passWord;
	}

	public User clone() {
		User user = new User(this.id, this.passWord);
		user.setAuthorized(this.authorized);
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

	public void setPw(String passWord) {
		this.passWord = passWord;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	@Override
	public String toString() {
		String info = String.format("");
		return info;
	}
}