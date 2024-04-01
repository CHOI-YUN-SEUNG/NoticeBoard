package NoticeBoard;

public class AdminNotice extends Post {

	public AdminNotice(String title, String content, String author) {
		super(title, content, author);
	}
	
	public String toString() {
		return " 제목: " + super.getTitle() + " 내용: " + super.getContent() ;
	}
}