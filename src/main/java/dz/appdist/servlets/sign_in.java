package dz.appdist.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class sign_in
 */
//@WebServlet("")
public class sign_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String result;
	RequestDispatcher dispatcher =null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sign_in() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setUserpass(request.getParameter("password"));
		
		HttpSession session=request.getSession();
		 
		 if (user.getUsername() == null || user.getUsername().equals("")) {
			request.setAttribute("status", "invalideUsername");
        	dispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
        	dispatcher.forward(request, response);
        	return;
		 }
		 if (user.getUserpass() == null || user.getUserpass().equals("")) {
			request.setAttribute("status", "invalidePass");
        	dispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
        	dispatcher.forward(request, response);
        	return;
		 }
		 
				 UserDAO us = new UserDAO();
	            // invoque la fonction login qui se trouve dans UserDAO
	            ResultSet Result = us.login(user);
	           
	            //System.out.println(Result + " ligne(s) affectée(s)");
	            session.setAttribute("user", user);
	            
	            try {
					if(Result.next()) {
						user.setId(Result.getInt("id"));
					    System.out.println("L'utilisateur a été connecté avec succès. ID de l'utilisateur : " + user.getId());
					    user.setNom(Result.getString("lastname"));
					    user.setPrenom(Result.getString("firstname"));
					    user.setLatitude(Result.getFloat("latitude"));
					    user.setUsername(Result.getString("username"));
					    
					    if(user.getLatitude()==0) {
					    	session.setAttribute("inscription", "incomplète");
					    	System.out.println("User connected: " + user.getUsername() +" ---------------------------------------------------");
					    	request.setAttribute("username", user.getUsername());
					    	//response.sendRedirect("ImageLocalisation");
					    	dispatcher = request.getRequestDispatcher("/WEB-INF/ImageLocalisation.jsp");
							dispatcher.forward(request, response);
							return;
					    }
						session.setAttribute("name", Result.getString("username"));
						System.out.println("User connected: " + Result.getString("role_admin").equals("Admin"));
						if(Result.getString("role_admin").equals("Admin")) {
							session.setAttribute("name", "admin");
							response.sendRedirect("admin");	
						}else if(Result.getString("role_admin").equals("ForceD''ordre")){
							session.setAttribute("name", "Force d'ordre");
							response.sendRedirect("admin");	
						}else {
							session.setAttribute("name", "user");
							response.sendRedirect("Accueil");
						}
						session.setAttribute("useremail", Result.getString("useremail"));
						session.setAttribute("userid", Result.getInt("id"));
					}else {
						request.setAttribute("status", "failed");
						System.out.println("Login failed");
						dispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
						dispatcher.forward(request, response);
					}
				} catch (SQLException | IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
	
		 
	}

}
