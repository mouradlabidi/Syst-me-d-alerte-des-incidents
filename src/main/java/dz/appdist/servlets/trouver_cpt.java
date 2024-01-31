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
 * Servlet implementation class trouvercpt
 */
//@WebServlet("/trouver_cpt")
public class trouver_cpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public trouver_cpt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/Trouvercompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
	    user.setUseremail(request.getParameter("username"));
	    
	    if (user.getUsername() == null || user.getUsername().equals("") ) {
		 	request.setAttribute("status", "invalideusernameOruseremail");
	     	dispatcher = request.getRequestDispatcher("/WEB-INF/Trouvercompte.jsp");
	     	dispatcher.forward(request, response);
		return;
		 }
	    UserDAO us = new UserDAO();
	    
        // invoque la méthode du UserDAO
		User utilisateur = us.selectUserByusernameOremail(request, user);
		
		if (utilisateur.getUsername()!=null) {	
			request.setAttribute("users", utilisateur);
			request.setAttribute("status", "success");
			System.out.println("user exist: "+utilisateur.getUseremail());
			dispatcher = request.getRequestDispatcher("/WEB-INF/resetpass.jsp");
	     	dispatcher.forward(request, response);
		}else{	
			System.out.println("user doesn't exist");
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("/WEB-INF/Trouvercompte.jsp");
	     	dispatcher.forward(request, response);
	     	return;
		}
		
	}

}
