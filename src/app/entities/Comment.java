package app.entities;


public class Comment {
    private String commentText;
    private User commentOwner;

    public Comment(String commentText, User owner){
        this.commentText = commentText;
        this.commentOwner = owner;
    }

    public User getCommentOwner() {
        return commentOwner;
    }

    public String getCommentText() {
        return commentText;
    }
}
