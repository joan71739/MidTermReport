package activityJavaBean;

import java.io.Serializable;

public class ActivityJavaBean implements Serializable{
	
	public ActivityJavaBean() {	}
	
	public int ID;
	public String activityTopic;
	public String startTime;
	public String endTime;
	public String activityContent;
	public int activityType;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getActivityTopic() {
		return activityTopic;
	}
	public void setActivityTopic(String activityTopic) {
		this.activityTopic = activityTopic;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	
	
}
