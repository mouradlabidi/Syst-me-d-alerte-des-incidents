package dz.appdist.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class ImageLocalisation
 */
//@WebServlet("/ImageLocalisation")
public class ImageLocalisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageLocalisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ImageLocalisation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String coordonnees = request.getParameter("local"); 
		System.out.println("Valeur de 'local' : " + coordonnees);
		if (coordonnees == null) {
			System.out.println("erreur coordonnees est null");
			return;
			}
		
        String[] parties = coordonnees.split(",");
        
        User user = new User();
        user.setLatitude(Float.parseFloat(parties[0]));
        user.setLongitude(Float.parseFloat(parties[1]));
        //user.setImage(request.getPart("photo"));
        user.setUsername(request.getParameter("username"));
        
        if (coordonnees == null || coordonnees.equals("")) {
            request.setAttribute("status", "invalideLongitudeAndLatitude");
            dispatcher = request.getRequestDispatcher("/WEB-INF/ImageLocalisation.jsp");
	       	dispatcher.forward(request, response);
            return;
        } 
		/*if (user.getImage() == null || user.getImage().getSize() == 0) {
            request.setAttribute("status", "invalidephoto");
            dispatcher = request.getRequestDispatcher("/WEB-INF/ImageLocalisation.jsp");
	       	dispatcher.forward(request, response);
            return;
        }*/
		
		UserDAO us = new UserDAO();
		int rs = us.UpdatetUserLI(user);
		String resultMessage = (rs > 0) ? "Update successful" : "Update failed";
		request.setAttribute("updateResult", resultMessage);
        
		System.out.println("Valeur de 'loca--------------l' : " + coordonnees);
	    // After processing update in your servlet
	    //response.sendRedirect("Accueil");
		if (resultMessage.equals("Update successful")) {
				HttpSession session = request.getSession();
				if (session != null ) {
			        session.invalidate();
			    }
				request.setAttribute("users", "");
				response.sendRedirect("Login_Page");
			}
		}

}
