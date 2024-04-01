package NoticeBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostManager {
	private HashMap<String, UserPost> userPosts;
	private HashMap<String, AdminNotice> adminNotices;

	public PostManager() {
		userPosts = new HashMap<>();
		adminNotices = new HashMap<>();
	}

	private static PostManager instance = new PostManager();

	public static PostManager getInstance() {
		return instance;
	}

	public void addUserPost(String title, String content, String author) {
		UserPost post = new UserPost(title, content, author);
		userPosts.put(title, post);
	}

	public void addAdminPost(String title, String content, String adminId) {
		AdminNotice post = new AdminNotice(title, content, adminId);
		adminNotices.put(title, post);
	}

	public Post getUserPost(String title) {
		return userPosts.get(title);
	}

	public Post getAdminPost(String title) {
		return adminNotices.get(title);
	}

	public void addAdminNotice(String title, String content, String author) {
		AdminNotice post = new AdminNotice(title, content, author);
		adminNotices.put(title, post);
	}

	public Post getPost(String title) {
		return userPosts.get(title);
	}

	public List<Post> getPostsByAuthor(String author) {
		List<Post> authorPosts = new ArrayList<>();
		for (Post post : userPosts.values()) {
			if (post.getAuthor().equals(author)) {
				authorPosts.add(post);
			}
		}
		return authorPosts;
	}
	
	public List<AdminNotice> getAllAdminNotices() {
		return new ArrayList<>(adminNotices.values());
	}
	
	
	public List<UserPost> getAllPosts() {
		return new ArrayList<>(userPosts.values());
	}

	public void removePost(String title) {
		userPosts.remove(title);
	}

	public Post findPost(String author) {
		List keyset = new ArrayList(userPosts.keySet());
		for (Object postTitle : keyset) {
			Post post = userPosts.get(postTitle);
			if (post != null && author.equals(post.getAuthor())) {
				return post;
			}
		}
		return null;
	}

	public void removeAllPost(String id) {
		while (findPost(id) != null) {
			Post post = findPost(id);
			removePost(post.getTitle());
		}
	}

	public int getSize() {
		return userPosts.size();
	}
	
	
}