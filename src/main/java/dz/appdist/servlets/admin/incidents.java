package dz.appdist.servlets.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.ReportForm;
import dz.appdist.dao.ReportDAO;

/**
 * Servlet implementation class incidents
 */
//@WebServlet("/incidents")
public class incidents extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public incidents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		incidlist(request,response);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Incidents.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 protected int incidlist(HttpServletRequest request, HttpServletResponse response){
	    	int cpt =0;
	    	
	    		//utiliser DAO
	            ReportDAO rpt = new ReportDAO();
	            ResultSet rs = rpt.Reportlist(request, response);
	            
	            // Créer une liste de rapports
	            List<ReportForm> reportsList = new ArrayList<>();
	            
	
	            try {
					while (rs.next()) {
						ReportForm report = new ReportForm();
						report.setId(Integer.parseInt(rs.getString("id")));
						report.setDescription(rs.getString("description"));
					    report.setLatitude(Float.parseFloat(rs.getString("latitude")));
					    report.setLongitude(Float.parseFloat(rs.getString("longitude")));
					    report.setStatus(rs.getString("status"));
					    report.setName(rs.getString("name"));
					    report.setImage(rs.getString("photo"));
					    Timestamp reportTime = rs.getTimestamp("report_time");
					    report.setTime(reportTime);
					    
					    ++cpt;
					    // Ajouter le rapport à la liste
					    reportsList.add(report);
					    
					    System.out.println("Description: " + rs.getString("name") );
					    System.out.println("Latitude: " + rs.getDouble("latitude") );
					    System.out.println("Longitude: " + rs.getDouble("longitude") );
					    System.out.println("Photo: " + rs.getString("photo"));
					    System.out.println("Statut: " + rs.getString("status"));
					    
					    System.out.println("Heure du Rapport: " + reportTime);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	            String aa = reportsList.get(0).getImage();
	            if (aa != null) {
	                System.out.println(aa);
	            }
	            // Définir la liste de rapports comme attribut de la requête
	            request.setAttribute("reportsList", reportsList);
	            
	    	return cpt;
	}

}
