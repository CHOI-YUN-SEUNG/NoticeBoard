package NoticeBoard;

import java.util.ArrayList;

class UserPost extends Post {
    private int upvotes;
    private ArrayList<String> upvotedUsers;

    public UserPost(String title, String content, String author) {
        super(title, content, author);
        this.upvotes = 0;
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
        return upvotedUsers.contains(userId);
    }

    @Override
    public String toString() {
        return super.toString() + "\n추천수: " + upvotes;
    }
}