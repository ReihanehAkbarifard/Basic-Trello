public class Activity {
    private int activityId;
    private String sender;
    private String activityText;

    public Activity(int activityId, String sender, String activityText) {
        this.activityId = activityId;
        this.sender = sender;
        this.activityText = activityText;
    }
    public Activity(String sender, String activityText) {
        this.sender = sender;
        this.activityText = activityText;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }
}
