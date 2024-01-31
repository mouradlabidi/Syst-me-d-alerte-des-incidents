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
 * Servlet implementation class resetcode
 */

public class resetcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetcode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/resetcode.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUseremail(request.getParameter("usermail"));
		
		// Vérifier si le paramètre "resetcode" est présent dans la requête
	    String resetCodeParameter = request.getParameter("resetcode");
	    if (resetCodeParameter != null && !resetCodeParameter.isEmpty()) {
	        try {
	            // Convertir la chaîne "resetcode" en entier
	            int code = Integer.parseInt(resetCodeParameter);
	            System.out.println("wait----------- " + code);
	            user.setResetcode(code);
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid resetcode format");
	           
	            // Gérer l'erreur de format du resetcode, par exemple, rediriger vers une page d'erreur
	            // ...
	            return;
	        }
	    } else {
	        System.out.println("Resetcode parameter is missing");
	        request.setAttribute("status", "invalideresetpassword");
	        request.setAttribute("email", user.getUseremail());
	        // Gérer le cas où le paramètre resetcode est manquant, par exemple, rediriger vers une page d'erreur
	        // ...
	        dispatcher = request.getRequestDispatcher("/WEB-INF/resetcode.jsp");
	     	dispatcher.forward(request, response);
	        return;
	    }
	    
		
		UserDAO us = new UserDAO();
		System.out.println("wait");
        // invoque la méthode du UserDAO
		int rs = us.Getresetcode(user.getUseremail());
		
		if(rs==user.getResetcode()) {
			System.out.println("success");
			request.setAttribute("status", "succes");
			request.setAttribute("email", user.getUseremail());
	     	dispatcher = request.getRequestDispatcher("/WEB-INF/newpassword.jsp");
	     	dispatcher.forward(request, response);
		}else {
			System.out.println("failed");
			request.setAttribute("status", "failed");
			request.setAttribute("email", user.getUseremail());
			dispatcher = request.getRequestDispatcher("/WEB-INF/resetcode.jsp");
	     	dispatcher.forward(request, response);
		}
	}

}
