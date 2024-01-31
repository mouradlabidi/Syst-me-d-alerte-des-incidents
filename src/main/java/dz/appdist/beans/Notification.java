package dz.appdist.beans;

import java.sql.Timestamp;

public class Notification {

    private int notifId;
    private int userId;
    private int report_form_id;
    private Timestamp dateOfIncident;
    private double distance; 

    
 // References to related objects
    private User user;
    private ReportForm reportForm;
    
    // Constructors (you can have multiple constructors if needed)

    public Notification() {
    }

    public Notification(int notifId, String userMail, String incidentName, String incidentDescription, Timestamp dateOfIncident) {
        this.notifId = notifId;
        this.dateOfIncident = dateOfIncident;
    }

    // Getters and Setters

    public int getNotifId() {
        return notifId;
    }

    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }

    public Timestamp getDateOfIncident() {
        return dateOfIncident;
    }

    public void setDateOfIncident(Timestamp dateOfIncident) {
        this.dateOfIncident = dateOfIncident;
    }

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReport_form_id() {
		return report_form_id;
	}

	public void setReport_form_id(int report_form_id) {
		this.report_form_id = report_form_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ReportForm getReportForm() {
		return reportForm;
	}

	public void setReportForm(ReportForm reportForm) {
		this.reportForm = reportForm;
	}
}
