package NoticeBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostManager {
	private HashMap<String, UserPost> posts;

	public PostManager() {
		posts = new HashMap<>();
	}

	public void addPost(String title, String content, RegisteredUser author) {
		UserPost post = new UserPost(title, content, author);
		posts.put(title, post);
	}

	public UserPost getPost(String title) {
		return posts.get(title);
	}

	public UserPost findPost(String author) {
		List keyset = new ArrayList(posts.keySet());
		for (Object postTitle : keyset) {
			UserPost post = posts.get(postTitle);
			if (post != null && author.equals(post.getAuthor().getId())) {
				return post;
			}
		}
		return null;
	}

	public HashMap<String, UserPost> getPosts() {
		return posts;
	}

	public void removePost(String title) {
		posts.remove(title);
	}

	public int getSize() {
		return posts.size();
	}
	
	public void removeAllPost(String id) {
		while (findPost(id) != null) {
			UserPost post = findPost(id);
			removePost(post.getTitle());
		}
	}
}
