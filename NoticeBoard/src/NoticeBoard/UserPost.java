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
	
	@Override
	public String toString() {
		return String.format("제목: %s\n" + "내용: %s"+ "\n작성자: %s\n" , getTitle(), getContent(), this.author.getId());
	}
}
