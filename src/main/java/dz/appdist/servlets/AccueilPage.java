package dz.appdist.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.ReportForm;
import dz.appdist.dao.ReportDAO;

@WebServlet("")
public class AccueilPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    public AccueilPage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// à réflichir
    	list(request,response);
    	//HttpSession session=request.getSession();
    	//session.setAttribute("name", null);
        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        

    }
    
    protected void list(HttpServletRequest request, HttpServletResponse response){
    	
	    		ReportDAO rpt = new ReportDAO();
	            ResultSet rs = rpt.Reportlist(request, response);
	            
	            // Créer une liste de rapports
	            List<ReportForm> reportsList = new ArrayList<>();
	            
	
	            try {
					while (rs.next()) {
						ReportForm report = new ReportForm();
						report.setDescription(rs.getString("description"));
					    report.setLatitude(Float.parseFloat(rs.getString("latitude")));
					    report.setLongitude(Float.parseFloat(rs.getString("longitude")));
					    report.setStatus(rs.getString("status"));
					    report.setImage(rs.getString("photo"));
					    Timestamp reportTime = rs.getTimestamp("report_time");
					    report.setTime(reportTime);
					    report.setName(rs.getString("name"));
					    report.setId(rs.getInt("id"));
					    
					    // Ajouter le rapport à la liste
					    reportsList.add(report);
					
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
	            
	
	         
	}
    
}
