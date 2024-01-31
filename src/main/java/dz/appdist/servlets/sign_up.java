package dz.appdist.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class sign_up
 */
//@WebServlet("/sign_up")
public class sign_up extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sign_up() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setNom(request.getParameter("nom"));
		user.setPrenom(request.getParameter("prenom"));
		user.setUsername(request.getParameter("name"));
		user.setUseremail(request.getParameter("email"));
		user.setUserbirth(request.getParameter("Birthday"));
		user.setUserpass(request.getParameter("pass"));
		user.setRe_userpass(request.getParameter("re_pass"));
		user.setUsermobile(request.getParameter("contact"));
		user.setRole_admin("Utilisateur");
		user.setGrade("Civil");
		
		
		
			
		if (user.getUsername() == null || user.getUsername().equals("")) {
			 request.setAttribute("status", "invalideUsername");
	       	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
	       	dispatcher.forward(request, response);
	        return;
		 }
		 
		if (user.getUserbirth() == null || user.getUserbirth().equals("")) {
			 request.setAttribute("status", "invalideBirthday");
	      	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
	      	dispatcher.forward(request, response);
	      	 return;
		 }
		 if (user.getUseremail() == null || user.getUseremail().equals("")) {
			 request.setAttribute("status", "invalideEmail");
        	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
        	dispatcher.forward(request, response);
        	 return;
		 }
		 
		if (user.getUserpass() == null || user.getUserpass().equals("")) {
				 request.setAttribute("status", "invalidePass");
	       	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
	       	dispatcher.forward(request, response);
	        return;
		}else if(!user.getUserpass().equals(user.getRe_userpass())) {
				request.setAttribute("status", "invalideconfirmepassword");
		        dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
		        dispatcher.forward(request, response);
		        return;
		}else if(user.getUserpass().length()<8 || user.getUserpass().length()<8) {
			 request.setAttribute("status", "invalidepassLength");
	         	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
	         	dispatcher.forward(request, response);
	         	return;
		 }else if (!isValidPassword(user.getUserpass())) {
		        request.setAttribute("status", "invalidPasswordFormat");
		        request.setAttribute("email", user.getUseremail());
		        dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
		        dispatcher.forward(request, response);
		        return;
		    }
			 
		if (user.getUsermobile() == null || user.getUsermobile().equals("")) {
					 request.setAttribute("status", "invalideMobile");
		        	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
		        	dispatcher.forward(request, response);
		        	 return;
		}else if(user.getUsermobile().length()>10 || user.getUsermobile().length()<10) {
					 request.setAttribute("status", "invalideMobileLength");
			         	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
			         	dispatcher.forward(request, response);
			         	 return;
		}
		if (user.getNom() == null || user.getNom().equals("")) {
					request.setAttribute("status", "Invalide name");
			       	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
			       	dispatcher.forward(request, response);
			        return;
		}
		if (user.getPrenom() == null || user.getPrenom().equals("")) {
					request.setAttribute("status", "Invalide surname");
			       	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
			       	dispatcher.forward(request, response);
			        return;
		}
		 

			            UserDAO us = new UserDAO();
			            // invoque la méthode du UserDAO
			            int rowsAffected = us.addUser(user);
			            
			            
			            if(rowsAffected>0) {
			            	request.setAttribute("status", "succes");
			            	request.setAttribute("username", user.getUsername());
			            	dispatcher = request.getRequestDispatcher("/WEB-INF/ImageLocalisation.jsp");
			            }else {
			            	request.setAttribute("status", "failed");
			            	dispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
			            }
			            dispatcher.forward(request, response);

	}
	
	private boolean isValidPassword(String password) {
	    // Valider que le mot de passe a au moins 6 caractères et contient une combinaison de chiffres, de lettres et de caractères spéciaux
	    String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!$@%]).{6,}$";
	    return password.matches(regex);
	}

}
