package dz.appdist.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class notification
 */
//@WebServlet("/updatepass")
public class updatepass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null; 
	String use;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatepass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		use = request.getParameter("us");
		System.out.println("user id : "+use);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/updatepass.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userId", use);
		int id  = Integer.parseInt(use);
		User user = new User();
		user.setId(id);
		user.setOldpass(request.getParameter("oldpass"));
	    user.setUserpass(request.getParameter("newpass"));
	    user.setRe_userpass(request.getParameter("repass"));
	   
	   
	    UserDAO us = new UserDAO();
	    
        // invoque la méthode du UserDAO
		User utilisateur = us.selectUser(request, use);
		
		
		if (utilisateur!=null) {	
			System.out.println("user exist");
			request.setAttribute("users", utilisateur);
		}
		
		if (user.getOldpass() == null || user.getOldpass().equals("") || !user.getOldpass().equals(utilisateur.getUserpass())) {
			 	request.setAttribute("status", "invalideOldPass");
		     	dispatcher = request.getRequestDispatcher("/WEB-INF/updatepass.jsp");
		     	dispatcher.forward(request, response);
    	return;
		 }
		
		 
	    if (user.getUserpass() == null || user.getUserpass().equals("")) {
			 request.setAttribute("status", "invalidePass");
      	dispatcher = request.getRequestDispatcher("/WEB-INF/updatepass.jsp");
      	dispatcher.forward(request, response);
      	return;
		 }else if(!user.getUserpass().equals(user.getRe_userpass())) {
			request.setAttribute("status", "invalideconfirmepassword");
	        dispatcher = request.getRequestDispatcher("/WEB-INF/updatepass.jsp");
	        dispatcher.forward(request, response);
	        return;
		 }else if(user.getUserpass().length()<8 || user.getUserpass().length()<8) {
			 request.setAttribute("status", "invalidepassLength");
	         	dispatcher = request.getRequestDispatcher("/WEB-INF/updatepass.jsp");
	         	dispatcher.forward(request, response);
	         	return;
		 }else if (!isValidPassword(user.getUserpass())) {
		        request.setAttribute("status", "invalidPasswordFormat");
		        request.setAttribute("email", user.getUseremail());
		        dispatcher = request.getRequestDispatcher("/WEB-INF/updatepass.jsp");
		        dispatcher.forward(request, response);
		        return;
		    }
	    
	    // invoque la méthode du UserDAO pour changer le mot de passe
        int rowsAffected = us.userpass(user);
            
        if(rowsAffected>0) {
        	request.setAttribute("status", "succes");
        	dispatcher = request.getRequestDispatcher("/WEB-INF/updatepass.jsp");
	       	dispatcher.forward(request, response);
        }else {
        	request.setAttribute("status", "failed");
        	dispatcher = request.getRequestDispatcher("/WEB-INF/updatepass.jsp");
	       	dispatcher.forward(request, response);
        }
	    
	}

	private boolean isValidPassword(String password) {
	    // Valider que le mot de passe a au moins 6 caractères et contient une combinaison de chiffres, de lettres et de caractères spéciaux
	    String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!$@%]).{6,}$";
	    return password.matches(regex);
	}
}
