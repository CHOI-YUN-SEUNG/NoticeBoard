package NoticeBoard;

abstract class Post {
	private String title;
	private String content;
	private String author;

	public Post(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	
	public String getContentForSave() {
	    return content.replaceAll("\n", ".");
	}
	
	public String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return "제목: " + title + "\n내용: " + content + "\n작성자: " + author;
	}
}