package NoticeBoard;

import java.util.HashMap;

public class PostManager {
	private HashMap<String, UserPost> posts;
	private int count;

	public PostManager() {
		posts = new HashMap<>();
		count = 0;
	}

	public void addBoard(String title, String content, RegisteredUser author) {
		UserPost post = new UserPost(title, content, author);
		posts.put(title, post);
		count++;
	}

	public UserPost getPost(String title) {
		return posts.get(title);
	}

	public HashMap<String, UserPost> getPosts() {
		return posts;
	}

	public void removePost(String title) {
		posts.remove(title);
	}

	public int getCount() {
		return count;
	}
}
