package NoticeBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostManager {
	private HashMap<String, Post> posts;

	private PostManager() {
		this.posts = new HashMap<>();
	}

	private static PostManager instance = new PostManager();

	public static PostManager getInstance() {
		return instance;
	}

	public void addUserPost(String title, String content, String author) {
		UserPost post = new UserPost(title, content, author);
		posts.put(title, post);
	}

	public void addAdminNotice(String title, String content, String author) {
		AdminNotice post = new AdminNotice(title, content, author);
		posts.put(title, post);
	}

	public Post getPost(String title) {
		return posts.get(title);
	}

	public List<Post> getPostsByAuthor(String author) {
		List<Post> authorPosts = new ArrayList<>();
		for (Post post : posts.values()) {
			if (post.getAuthor().equals(author)) {
				authorPosts.add(post);
			}
		}
		return authorPosts;
	}

	public List<Post> getAllPosts() {
		return new ArrayList<>(posts.values());
	}

	public void removePost(String title) {
		posts.remove(title);
	}

	public int getSize() {
		return posts.size();
	}

}