package dz.appdist.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.ReportForm;
import dz.appdist.dao.ReportDAO;

/**
 * Servlet implementation class descriptionIncident
 */
//@WebServlet("/descriptionIncident")
public class descriptionIncident extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String id = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public descriptionIncident() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = request.getParameter("scroll");

		System.out.println("*************"+ id);
		
		ReportDAO rp = new ReportDAO();
		ReportForm report = rp.selectIncident(request, id);
		
		System.out.println("*************"+ report.getDescription());
		
		if (report!=null) {	
			System.out.println("incident exist");
			request.setAttribute("report", report);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/description.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
