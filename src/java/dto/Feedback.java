package dto;

public class Feedback {
    private String feedbackId;
    private String title;
    private String content;
    private String vote;
    private User user;
    private Field field;
    private String status;

    public Feedback() {
        this.feedbackId = "";
        this.title = "";
        this.content = "";
        this.vote = "";
        this.user = null;
        this.field = null;
        this.status = "";
    }

    public Feedback(String feedbackId, String title, String content, String vote, User user, Field field, String status) {
        this.feedbackId = feedbackId;
        this.title = title;
        this.content = content;
        this.vote = vote;
        this.user = user;
        this.field = field;
        this.status = status;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
