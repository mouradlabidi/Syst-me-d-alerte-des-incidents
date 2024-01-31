package dz.appdist.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.ReportForm;
import dz.appdist.beans.User;
import dz.appdist.dao.ReportDAO;
import dz.appdist.dao.UserDAO;
import dz.appdist.beans.Notification;

/**
 * Servlet implementation class SignalementServlet
 */
@MultipartConfig
//@WebServlet("/signaler")
public class SignalementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignalementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String id = request.getParameter("id");
			
			System.out.println("Entering doPost" + id);
			
			ReportForm reportForm = new ReportForm();
			String nameIn = request.getParameter("namein");
			
			reportForm.setName(nameIn);
			reportForm.setDescription(request.getParameter("desc"));
			
	        String coordonnees = request.getParameter("coord"); 
	        String[] parties = coordonnees.split(",");
	        
			reportForm.setLatitude(Float.parseFloat(parties[0]));
			reportForm.setLongitude(Float.parseFloat(parties[1]));
			reportForm.setPhoto(request.getPart("photo"));
			
	        
			if (reportForm.getDescription() == null || reportForm.getDescription().equals("")) {
	            request.setAttribute("status", "invalideDescription");
	            dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		       	dispatcher.forward(request, response);
	            return;
	        } 
			if (reportForm.getLatitude() == null || reportForm.getLatitude().equals("")) {
	            request.setAttribute("status", "invalideLatitude");
	            dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		       	dispatcher.forward(request, response);
	            return;
	        } 
			if (reportForm.getLongitude() == null || reportForm.getLongitude().equals("")) {
	            request.setAttribute("status", "invalideLongitude");
	            dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		       	dispatcher.forward(request, response);
	            return;
	        } 
			if (reportForm.getPhoto() == null || reportForm.getPhoto().getSize() == 0) {
	            request.setAttribute("status", "invalidephoto");
	            dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		       	dispatcher.forward(request, response);
	            return;
	        }
			if (reportForm.getName() == null || reportForm.getName().equals("")) {
	            request.setAttribute("status", "invalideName");
	            dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		       	dispatcher.forward(request, response);
	            return;
	        }
	
			
			// Obtenir le timestamp actuel
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
			reportForm.setTime(currentTimestamp);
			reportForm.setStatus("NON_TRAITE");
			
	        System.out.println("Description : " + reportForm.getDescription());
	        System.out.println("coordonnees : " + coordonnees);
	        System.out.println("Latitude : " + reportForm.getLatitude());
	        System.out.println("Longitude : " + reportForm.getLongitude());
	        System.out.println("photo : " + reportForm.getPhoto());
	        
	        ReportDAO rpt = new ReportDAO();
            int rowsAffected = rpt.signaler(reportForm, id);
    		
               if (rowsAffected > 0) {
                   request.setAttribute("status", "success");
                   response.sendRedirect(request.getContextPath() + "#signaler");
               } else {
                   request.setAttribute("status", "failed");
                   response.sendRedirect(request.getContextPath() + "#signaler");
               }  
	        
                
	             // Extraire tous les utilisateurs de la table users
	                UserDAO userDAO = new UserDAO();
	                List<User> allUsers = userDAO.getAllUsers();
	                
	             // Coordonnées de l'incident
	                double incidentLat = reportForm.getLatitude();
	                double incidentLon = reportForm.getLongitude();
	                
	             // Rayon acceptable autour de l'incident (en kilomètres)
	                double acceptableRadius = 5.0;

	                // Liste pour stocker les utilisateurs proches
	                List<User> usersProches = new java.util.ArrayList<>();
	                
	              //récupérer id de cette incident
	                ReportForm reptWithId= rpt.selectIncidentbyLatitude(request, reportForm.getLatitude());
	                
	                System.out.println("calclulé");
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
		                        
		                        
		                        
		                        Notification nf = new Notification();
		                        nf.setUserId(user.getId());
		                        nf.setReport_form_id(reptWithId.getId());
			                    nf.setDistance(distance);
			                    
			                    // envoyer la notification par mail
		                        sendNotificationByEmail(user.getUseremail(), reportForm.getName()+";"+reportForm.getDescription(), nf.getDistance());
			                    System.out.println("cpt: "+cpt);
			                    
			                    // ajouter les informations sur la notifaction dans la BD
			    	            int result =userDAO.addNotif(nf);
			    	            if (result > 0) {
			    	                   request.setAttribute("status", "success");
			    	               } else {
			    	                   request.setAttribute("status", "failed");
			    	               }  
			    	            
		                	}
		                    if (user.getLatitude()==0) { System.out.println("non proche");}
	                }
		                
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
	    final String useremail = "labidimourad09@gmail.com";
	    final String password = "huzu qbuo avwv zxig";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(useremail, password);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(useremail));
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
	            message.setSubject("Notification de l'incident : "+ incidentName +" qui est proche de vous avec : " + formattedDistance + " km ");
	            message.setText("Incident: " + incidentName + "\n\nDescription: " + incidentDescription
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
