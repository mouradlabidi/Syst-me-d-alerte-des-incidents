package dz.appdist.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.dao.UserDAO;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Random;


/*import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.type.PhoneNumber;*/

/**
 * Servlet implementation class resetpass
 */
//@WebServlet("/resetpass")
public class resetpass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*public static final String ACCOUNT_SID = "AC2be872267210d4298b030d58d3db0d73";
	public static final String AUTH_TOKEN = "70424998643c16345daaf1d63d9f3f1d";   */
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetpass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/resetpass.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer la valeur du bouton radio
	    String selectedMethod = request.getParameter("method");
		
		// Générer un code aléatoire
        String resetCode = generateRandomCode();

        String email = request.getParameter("userEmail");
        System.out.println("mon email : "+email + " "+resetCode);
        
        // Enregistrer le code dans la base de données (vous devrez implémenter cette partie)
        saveCodeToDatabase(request, email, resetCode);

        // Vérifier la méthode sélectionnée
        if ("email".equals(selectedMethod)) {
            // Envoyer le code par e-mail
            sendResetCodeByEmail(email, resetCode);
        } else if ("phone".equals(selectedMethod)) {
            // Envoyer le code par sms
            //sendResetCodeBySMS("+213675285067", resetCode);
        	// Rediriger vers la page d'authentification du code
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }
        
        // Envoyer le code par sms 
        //sendResetCodeBySMS("+213675285067", resetCode);
        
        request.setAttribute("email", email);

        // Rediriger vers la page de vérification du code
        request.getRequestDispatcher("/WEB-INF/resetcode.jsp").forward(request, response);
   
	}

	 private String generateRandomCode() {
	        // Générer un code aléatoire (par exemple, un code à 6 chiffres)
	        Random random = new Random();
	        int code = 100000 + random.nextInt(900000);
	        return String.valueOf(code);
	    }
	 
	 private void saveCodeToDatabase(HttpServletRequest request, String userEmail, String resetCode) {
		 	UserDAO us = new UserDAO();
		    
	        // invoque la méthode du UserDAO
			int rs = us.Addresetcode(userEmail, Integer.parseInt(resetCode));
			
			String resultMessage = (rs > 0) ? "Set successful" : "Set failed";
	        request.setAttribute("SetResetCodeeResult", resultMessage);
	    }
	 
	 private void sendResetCodeByEmail(String userEmail, String resetCode) {
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
	            message.setSubject("Password Reset Code");
	            message.setText("Your password reset code is: " + resetCode);

	            Transport.send(message);

	            System.out.println("Email sent successfully!");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 // send code to phone with sms (twillio) 
	 /*private void sendResetCodeBySMS(String userPhoneNumber, String resetCode) {
		    // Initialisation de Twilio avec votre SID et votre jeton d'authentification
		 	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    // Numéro Twilio à partir duquel vous envoyez le SMS
		    PhoneNumber from = new PhoneNumber("+18582408365");

		    // Numéro de téléphone de l'utilisateur
		    PhoneNumber to = new PhoneNumber(userPhoneNumber);

		    // Corps du message SMS
		    String messageBody = "Votre code de réinitialisation du mot de passe est : " + resetCode;

		    // Envoi du message SMS
		    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(to, from, messageBody).create();

		    System.out.println("SMS sent successfully! SID: " + message.getSid());
		}*/
	 
}
