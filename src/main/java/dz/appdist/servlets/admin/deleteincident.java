package dz.appdist.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.dao.ReportDAO;

/**
 * Servlet implementation class deleteincident
 */
//@WebServlet("/deleteincident")
public class deleteincident extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteincident() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

	    if ("delete".equals(action)) {
	        String incidentId = request.getParameter("id");

	        //invoque deleteUser de UserDAO
			ReportDAO us = new ReportDAO();
	        int rs =  us.deleteIncident(incidentId);

            if(rs>0) {
            	System.out.println("true");
            }
            else {
            	System.out.println("false");
            }

	        // Réponse avec un statut 200 OK (à adapter selon vos besoins)
	        response.setStatus(HttpServletResponse.SC_OK);
	        
	        response.sendRedirect("incidents");
            
	    } else {
	        // Autres actions si nécessaire
	        doGet(request, response);
	    }
	}

}
