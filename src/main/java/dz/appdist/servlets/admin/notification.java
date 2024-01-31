package dz.appdist.servlets.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.Notification;
import dz.appdist.beans.ReportForm;
import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;
import dz.appdist.dao.ReportDAO;

/**
 * Servlet implementation class notification
 */
//@WebServlet("/notification")
public class notification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public notification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO user = new UserDAO();
		ReportDAO report = new ReportDAO();
		
		List<Notification> notifications = user.getAllNotifs();
		//int numberOfElements = liste.size();
		//request.setAttribute("nbr2", numberOfElements);
		//request.setAttribute("notifliste", liste);
		
		// Assuming you have a method to retrieve a user by ID
		for (Notification notification : notifications) {
		    User user1 = user.selectUser(request,String.valueOf(notification.getUserId()));
		    notification.setUser(user1);

		    // Assuming you have a method to retrieve a report form by ID
		    ReportForm reportForm = report.selectIncident(request, String.valueOf(notification.getReport_form_id()));
		    notification.setReportForm(reportForm);
		}

		request.setAttribute("notifliste", notifications);
		
		
		
		// Sort the list by NotifId
		report.sortByNotifId(notifications);
        // Add the 7 last notifications to a separate list
        List<Notification> lastSevenNotifications = report.getLastSevenNotifications(notifications);

        // Print the 7 last notifications
        for (Notification notification : lastSevenNotifications) {
            System.out.println("Last 7: " + notification.getNotifId());
        }
        
        // Assuming you have a method to retrieve a user by ID
     		for (Notification notification : lastSevenNotifications) {
     		    User user1 = user.selectUser(request,String.valueOf(notification.getUserId()));
     		    notification.setUser(user1);

     		    // Assuming you have a method to retrieve a report form by ID
     		    ReportForm reportForm = report.selectIncident(request, String.valueOf(notification.getReport_form_id()));
     		    notification.setReportForm(reportForm);
     		}
     		request.setAttribute("notifliste7", lastSevenNotifications);
     		
		this.getServletContext().getRequestDispatcher("/WEB-INF/notification.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
}
