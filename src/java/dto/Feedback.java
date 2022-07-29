package dto;

public class Feedback {
    private String feedbackId;
    private String content;
    private User user;
    private Field field;
    private String status;

    public Feedback() {
        this.feedbackId = "";
        this.content = "";
        this.user = null;
        this.field = null;
        this.status = "";
    }

    public Feedback(String feedbackId, String content, User user, Field field, String status) {
        this.feedbackId = feedbackId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
