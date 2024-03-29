package NoticeBoard;

public class UserPost extends Post{
	private RegisteredUser author;

	public UserPost(String title, String content, RegisteredUser author) {
		super(title, content);
		this.author = author;
	}

	public RegisteredUser getAuthor() {
		return author;
	}
}
