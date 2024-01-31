package dz.appdist.servlets.admin;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class Updateuser
 */
//@WebServlet("/Updateuser")
public class updateuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null;  
	String id = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateuser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		id = request.getParameter("scroll");
		

		System.out.println("*************"+ id);
		
		UserDAO us = new UserDAO();
		User user = us.selectUser(request, id);
		
		System.out.println("*************"+ user.getUseremail());
		
		if (user!=null) {	
			System.out.println("user exist");
			request.setAttribute("users", user);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/updatecustomers.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("userId", id);
		int id1  = Integer.parseInt(id);
		User user = new User();
	    user.setNom(request.getParameter("nom"));
	    user.setPrenom(request.getParameter("prenom"));
	    user.setUsername(request.getParameter("name"));
	    user.setUseremail(request.getParameter("email"));
	    user.setUserbirth(request.getParameter("Birthday"));
	    user.setUsermobile(request.getParameter("contact"));
	    user.setRole_admin("ForceD''ordre");
	    user.setGrade("Civil");
	    user.setId(id1);
	    
	    if (user.getUsername() == null || user.getUsername().equals("")) {
			 request.setAttribute("status", "invalideUsername");
	       	dispatcher = request.getRequestDispatcher("/WEB-INF/updatecustomers.jsp");
	       	dispatcher.forward(request, response);
	       	return;
		 }
		 
		if (user.getUserbirth() == null || user.getUserbirth().equals("")) {
			 request.setAttribute("status", "invalideBirthday");
	      	dispatcher = request.getRequestDispatcher("/WEB-INF/updatecustomers.jsp");
	      	dispatcher.forward(request, response);
	      	return;
		 }
		 if (user.getUseremail() == null || user.getUseremail().equals("")) {
			 request.setAttribute("status", "invalideEmail");
       	dispatcher = request.getRequestDispatcher("/WEB-INF/updatecustomers.jsp");
       	dispatcher.forward(request, response);
       	return;
		 }
		 
				 if (user.getUsermobile() == null || user.getUsermobile().equals("")) {
					 request.setAttribute("status", "invalideMobile");
		        	dispatcher = request.getRequestDispatcher("/WEB-INF/updatecustomers.jsp");
		        	dispatcher.forward(request, response);
		        	return;
				 }else if(user.getUsermobile().length()>10 || user.getUsermobile().length()<10) {
					 request.setAttribute("status", "invalideMobileLength");
			         	dispatcher = request.getRequestDispatcher("/WEB-INF/updatecustomers.jsp");
			         	dispatcher.forward(request, response);
			         	return;
				 }
				 if (user.getNom() == null || user.getNom().equals("")) {
					request.setAttribute("status", "Invalide name");
			       	dispatcher = request.getRequestDispatcher("/WEB-INF/updatecustomers.jsp");
			       	dispatcher.forward(request, response);
			       	return;
				 }
				 if (user.getPrenom() == null || user.getPrenom().equals("")) {
					request.setAttribute("status", "Invalide surname");
			       	dispatcher = request.getRequestDispatcher("/WEB-INF/updatecustomers.jsp");
			       	dispatcher.forward(request, response);
			       	return;
				 }
	    
	    
		    Date userbirthDate = null;
			try {
				// Utilisez un SimpleDateFormat pour parser la chaîne en java.util.Date
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date parsedDate = dateFormat.parse(user.getUserbirth());
			
		        // Convertissez la java.util.Date en java.sql.Date
		        userbirthDate = new Date(parsedDate.getTime());
			
		     // Affichez la valeur de userbirthDate dans la console
		        System.out.println("userbirthDate: " + userbirthDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			UserDAO us = new UserDAO();
			int rs = us.UpdatetUser(user,userbirthDate);
	    
	        
	        String resultMessage = (rs > 0) ? "Update successful" : "Update failed";
	        request.setAttribute("updateResult", resultMessage);
	        
	       // After processing update in your servlet
	        response.sendRedirect("customers");
	    
	}
	
	
}
