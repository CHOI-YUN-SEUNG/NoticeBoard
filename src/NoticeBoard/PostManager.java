package NoticeBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PostManager {
	private HashMap<String, UserPost> userPosts;
	private HashMap<String, AdminNotice> adminNotices;
	private String fileName = "post_data.txt";

	public PostManager() {
		userPosts = new HashMap<>();
		adminNotices = new HashMap<>();
		loadData();
	}

	private void loadData() {
		if (new File(fileName).exists()) {
			try {
				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					String[] postData = line.split(",");
					String title = postData[0];
					String content = postData[1].replace(".", "\n");
					String author = postData[2];
					if (author.equals("admin"))
						adminNotices.put(title, new AdminNotice(title, content, author));
					else {
						UserPost post = new UserPost(title, content, author);
						int upvotes = Integer.parseInt(postData[3]);
						post.setUpvotes(upvotes);
						ArrayList<String> upvotedUsers = new ArrayList<>();
						String[] users = postData[4].split(";"); // 여러 사용자를 구분하는 문자
						for (String user : users) {
							upvotedUsers.add(user);
						}
						post.setUpvotedUsers(upvotedUsers);
						userPosts.put(title, post);

					}
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				System.err.println("파일을 찾을 수 없습니다");
			} catch (IOException e) {
				System.err.println("파일 읽기 중 오류가 발생했습니다");
			}
		}
	}

	public void saveData() {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);

			for (UserPost post : userPosts.values()) {
				bw.write(post.getTitle() + "," + post.getContentForSave() + "," + post.getAuthor() + ","
						+ post.getUpvotes() + "," + String.join(";", post.getUpvotedUsers()));
				bw.newLine();
			}

			for (AdminNotice notice : adminNotices.values()) {
				bw.write(notice.getTitle() + "," + notice.getContentForSave() + "," + notice.getAuthor());
				bw.newLine();
			}

			bw.close();
			fw.close();
		} catch (IOException e) {
			System.err.println("파일 저장 중 오류가 발생했습니다");
		}
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