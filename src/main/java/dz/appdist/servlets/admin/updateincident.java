package dz.appdist.servlets.admin;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.ReportForm;
import dz.appdist.beans.User;
import dz.appdist.dao.ReportDAO;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class updateincident
 */
//@WebServlet("/updateincident")
public class updateincident extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null; 
	String id ="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateincident() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		id = request.getParameter("scroll");

		System.out.println("*************"+ id);
		
		ReportDAO rp = new ReportDAO();
		ReportForm report = rp.selectIncident(request, id);
		
		System.out.println("*************"+ report.getDescription());
		
		if (report!=null) {	
			System.out.println("incident exist");
			request.setAttribute("report", report);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateIncidents.jsp").forward(request, response);
		//response.sendRedirect("incidents");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("incidentId", id);
		int id1 = Integer.parseInt(id);
		ReportForm report = new ReportForm();
		report.setStatus(request.getParameter("status1"));
		report.setId(id1);
		
		System.out.println("Status "+ report.getStatus());
		
		if (report.getStatus() == null || report.getStatus().equals("")) {
			 request.setAttribute("status", "invalideStatus");
	       	dispatcher = request.getRequestDispatcher("/WEB-INF/updateIncidents.jsp");
	       	dispatcher.forward(request, response);
	       	return;
		 }
		
		ReportDAO rp = new ReportDAO();
		int  rs = rp.updateIncident(report);
		
		String resultMessage = (rs > 0) ? "Update successful" : "Update failed";
        request.setAttribute("updateincidentResult", resultMessage);
        
        
        // ----------------- envoyer une notification informative --------------------
	     // Extraire tous les utilisateurs de la table users
	        UserDAO userDAO = new UserDAO();
	        List<User> allUsers = userDAO.getAllUsers();
	        
	        ReportDAO reportdao = new ReportDAO();
	        ReportForm rpt = reportdao.selectIncident(request,id);
	     // Coordonnées de l'incident
	        double incidentLat = rpt.getLatitude();
	        double incidentLon = rpt.getLongitude();
	        
	     // Rayon acceptable autour de l'incident (en kilomètres)
	        double acceptableRadius = 5.0;
	
	        // Liste pour stocker les utilisateurs proches
	        List<User> usersProches = new java.util.ArrayList<>();
	        
	        System.out.println("calclulé");
	        if (report.getStatus().equals("NON_TRAITE")) {
	        	response.sendRedirect("incidents");
	        	return;
	        	}
	        int cpt =0;
            // Filtrer les utilisateurs proches
               for (User user : allUsers) {
               	System.out.println("calclulé1 + "+user.getLatitude());
               	
	                    double distance = haversineDistance(user.getLatitude(), user.getLongitude(), incidentLat, incidentLon);
	                    System.out.println("distance calclulé");
	                    if (distance <= acceptableRadius) {
	                        // L'utilisateur est proche de l'incident, l'ajouter à la liste
	                        usersProches.add(user);
	                        cpt++;
	                        System.out.println("email: " + user.getUseremail());   
	                        
	                        sendNotificationByEmail(user.getUseremail(), rpt.getName()+";"+ rpt.getDescription(), distance);
		                    System.out.println("cpt: "+cpt);
	                	}
	                    if (user.getLatitude()==0) { System.out.println("non proche");}
               }
       // After processing update in your servlet
        response.sendRedirect("incidents");
    
	}
	
	public static final double RADIUS_OF_EARTH = 6371.0;
	public static double haversineDistance(double userLat, double userLon, double incidentLat, double incidentLon) {
	    // Vérifier si les coordonnées sont valides
	    if (userLat == 0 || userLon == 0 || incidentLat == 0 || incidentLon == 0) {
	        // Gérer le cas où les coordonnées sont nulles
	        System.out.println("Coordonnées invalides");
	        return Double.MAX_VALUE; // Valeur maximale pour indiquer une distance infinie
	    }
	    
	    // Convertir les latitudes et longitudes en radians
	    userLat = Math.toRadians(userLat);
	    userLon = Math.toRadians(userLon);
	    incidentLat = Math.toRadians(incidentLat);
	    incidentLon = Math.toRadians(incidentLon);

	    // Calculer les différences de latitudes et longitudes
	    double dlat = incidentLat - userLat;
	    double dlon = incidentLon - userLon;

	    // Formule de la distance haversine
	    double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(userLat) * Math.cos(incidentLat) * Math.pow(Math.sin(dlon / 2), 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    // Distance en kilomètres
	    double distance = RADIUS_OF_EARTH * c;

	    return distance;
	}
	public void sendNotificationByEmail(String userEmail, String incidentInfo, double distance) {
	    final String username = "labidimourad09@gmail.com";
	    final String password = "huzu qbuo avwv zxig";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
	        
	        // Utiliser split pour obtenir le nom de l'incident, la description et la distance séparément
	        String[] incidentParts = incidentInfo.split(";", 2);

	        // Vérifier si incidentInfo est correctement formaté
	        if (incidentParts.length == 2) {
	            String incidentName = incidentParts[0].trim();
	            String incidentDescription = incidentParts[1].trim();

	            // Formater la distance avec deux chiffres après la virgule
	            java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
	            String formattedDistance = df.format(distance);
	            
	            // Utiliser incidentName, incidentDescription et distance dans le corps du message
	            message.setSubject("L'incident qui est proche de vous avec " + formattedDistance + " km" + " a été traité");
	            message.setText("Incident: " + incidentName + "\n\nDescription: " + incidentDescription + "\n\nEtat: " + "Traité"
	                    + "\n\nDistance : " + formattedDistance + " km");
	        } else {
	            System.out.println("Format de l'incident incorrect");
	            return;
	        }

	        Transport.send(message);

	        System.out.println("Email sent successfully!");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}

}
