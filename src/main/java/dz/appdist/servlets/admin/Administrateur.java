package dz.appdist.servlets.admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.Notification;
import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class Administrateur
 
@WebServlet("/Administrateur")*/
public class Administrateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administrateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// nbr nombre d'utilisateur
		int nbr=Userlist(request,response);
		request.setAttribute("Nombre", nbr);
		
		// nbr1 nombre d'incident
		incidents inc = new incidents();
		int nbr1= inc.incidlist(request, response);
		request.setAttribute("nbr", nbr1);
		
		UserDAO user = new UserDAO();
		List<Notification> liste = user.getAllNotifs();
		int numberOfElements = liste.size();
		request.setAttribute("nbr2", numberOfElements);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/AdminDash.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected int Userlist(HttpServletRequest request, HttpServletResponse response){
			 int cpt = 0;
	    	
			 UserDAO us = new UserDAO();
			 // invoque la méthode userlist dans UserDAO
			 ResultSet rs = us.Userlist();
            
            // Créer une liste de rapports
            List<User> user1 = new ArrayList<>();
            
           
            try {
				while (rs.next()) {
					User user = new User();
					user.setNom(rs.getString("lastname"));
					user.setPrenom(rs.getString("firstname"));
					user.setUsername(rs.getString("username"));
					user.setUseremail(rs.getString("useremail"));
					user.setUserbirth(rs.getString("birthday"));
				    user.setUsermobile(rs.getString("mobile"));
				    user.setGrade(rs.getString("grade"));
				    user.setId(rs.getInt("id"));
				    
				    // Ajouter le rapport à la liste
				    user1.add(user);
				    ++cpt;
				    System.out.println("************* nbr"+ cpt);
				    System.out.println("Lastname: " + rs.getString("lastname") );
				    System.out.println("Firstname: " + rs.getString("firstname") );
				    System.out.println("Username: " + rs.getString("username") );
				    System.out.println("Useremail: " + rs.getString("useremail"));
				    System.out.println("Mobile: " + rs.getString("mobile"));
				    
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            String aa = user1.get(0).getNom();
            if (aa != null) {
                System.out.println(aa);
            }
            // Définir la liste de rapports comme attribut de la requête
            request.setAttribute("users", user1);
            

         
    	return cpt;
}
}
