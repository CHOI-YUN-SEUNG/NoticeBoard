package NoticeBoard;

import java.util.ArrayList;

class UserPost extends Post {
	private int upvotes;
	private ArrayList<String> upvotedUsers = new ArrayList<String>();

	public UserPost(String title, String content, String author) {
		super(title, content, author);
		this.upvotes = 0;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	
	public ArrayList<String> getUpvotedUsers() {
		return upvotedUsers;
	}
	
	public void setUpvotedUsers(ArrayList<String> upvotedUsers) {
		this.upvotedUsers = upvotedUsers;
	}
	
	public int getUpvotes() {
		return upvotes;
	}

	public void upvote(String userId) {
		if (!upvotedUsers.contains(userId)) {
			upvotes++;
			upvotedUsers.add(userId);
		}
	}

	public boolean hasUpvoted(String userId) {
		if (upvotedUsers != null)
			return upvotedUsers.contains(userId);
		else
			return false;
	}

	public String toString() {
		return super.toString() + "\n추천수: " + upvotes;
	}
}