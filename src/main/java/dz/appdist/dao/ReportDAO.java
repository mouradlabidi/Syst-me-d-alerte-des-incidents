package dz.appdist.dao;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dz.appdist.beans.Notification;
import dz.appdist.beans.ReportForm;

public class ReportDAO {

	public ReportDAO() {
		// TODO Auto-generated constructor stub
	}

	 public ResultSet Reportlist(HttpServletRequest request, HttpServletResponse response){
		 ResultSet rs = null;
	    	try {
	        	Class.forName("org.postgresql.Driver");
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
	                    "admin");
	
	            PreparedStatement pst = con.prepareStatement("SELECT * FROM report_forms");
	            rs = pst.executeQuery();
	            con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	    	return rs;
	}
	 
	 public ReportForm selectIncident(HttpServletRequest request, String Id){
		 ResultSet rs = null;
		 int id = Integer.parseInt(Id);
		 ReportForm report=new ReportForm();
		 report.setId(null);		 
	    	try {
	        	Class.forName("org.postgresql.Driver");
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
	                    "admin");
	
	            PreparedStatement pst = con.prepareStatement("SELECT * FROM report_forms WHERE id=?");
	            pst.setInt(1, id);
	            rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	report.setDescription(rs.getString("description"));
	            	report.setLatitude(rs.getFloat("latitude"));
	            	report.setLongitude(rs.getFloat("longitude"));
	            	report.setStatus(rs.getString("status"));
	            	report.setImage(rs.getString("photo"));
	                report.setTime(rs.getTimestamp("report_time"));
	                report.setName(rs.getString("name"));
	                report.setId(rs.getInt("id"));
	                
	            	System.out.println("Description: " + rs.getString("description"));
	                System.out.println("Latitude: " + rs.getFloat("latitude") );
	                System.out.println("Longitude: " + rs.getFloat("longitude"));
	                System.out.println("Status: " + rs.getString("status"));
	                System.out.println("Photo: " + rs.getString("photo"));
	            	}
	            
	            con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	    	return report;
	}
	 
	 public ReportForm selectIncidentbyLatitude(HttpServletRequest request, Float lat){
		 ResultSet rs = null;
		 
		 ReportForm report=new ReportForm();
		 report.setLatitude(lat);;		 
	    	try {
	        	Class.forName("org.postgresql.Driver");
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
	                    "admin");
	
	            PreparedStatement pst = con.prepareStatement("SELECT * FROM report_forms WHERE latitude=?");
	            pst.setFloat(1, report.getLatitude());;
	            rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	report.setId(rs.getInt("id"));
	            	report.setDescription(rs.getString("description"));
	            	report.setLatitude(rs.getFloat("latitude"));
	            	report.setLongitude(rs.getFloat("longitude"));
	            	report.setStatus(rs.getString("status"));
	            	report.setImage(rs.getString("photo"));
	                report.setTime(rs.getTimestamp("report_time"));
	                report.setName(rs.getString("name"));
	                report.setId(rs.getInt("id"));
	                
	            	System.out.println("Description: " + rs.getString("description"));
	                System.out.println("Latitude: " + rs.getFloat("latitude") );
	                System.out.println("Longitude: " + rs.getFloat("longitude"));
	                System.out.println("Status: " + rs.getString("status"));
	                System.out.println("Photo: " + rs.getString("photo"));
	            	}
	            
	            con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	    	return report;
	}
	 public int updateIncident(ReportForm report){
		 int rs = 0;
		 		 
		 java.sql.Connection con = null;
		  try {
		        Class.forName("org.postgresql.Driver");
		        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
	
		        PreparedStatement pst = con.prepareStatement("update report_forms set status=? Where id = ?");
		        pst.setString(1, report.getStatus());
		        pst.setInt(2, report.getId());
		       
	
		        System.out.println("SQL Query: " + pst.toString());
		        rs = pst.executeUpdate();
		        System.out.println("Incident to update: " + report.getName());
		        
          }catch(Exception e) {
          	e.printStackTrace();
          }
	return rs;
	}
	 
	// supprimer un utilisateur
	    public int deleteIncident(String Id) {
			java.sql.Connection con=null;
			int rs = 0;
			int id = Integer.parseInt(Id);
			int resultNotif =deleteNotif(Id);
			
				try {
					Class.forName("org.postgresql.Driver");
		            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",  "admin");

		            PreparedStatement pst = con.prepareStatement("delete from report_forms Where id = ?");
		            pst.setInt(1, id);
		            rs =  pst.executeUpdate();
		            
				}	catch(Exception e) {
					
					}
            
			return rs;
			
	    }
	    
	 // supprimer une Notification
	    public int deleteNotif(String Id) {
			java.sql.Connection con=null;
			int rs = 0;
			int id = Integer.parseInt(Id);
			System.out.println("//////////////////////////////////////////////////////////////////////// " + id);
			try {
				Class.forName("org.postgresql.Driver");
	            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",  "admin");

	            PreparedStatement pst = con.prepareStatement("DELETE FROM notification WHERE report_form_id = ?");
	            pst.setInt(1, id);
	            System.out.println("//////////////////////////////////////////////////////////////////////// " + pst.toString());
	            rs = pst.executeUpdate();
		        System.out.println("Notif to update:///////////////////////////////////////////////////////////////// " );
			}	catch(Exception e) {
				
				}
			return rs;
			
	    }
	    
	    
	 
	public int signaler(ReportForm reportForm, String Id) {
		int rowsAffected = 0;
		int id = Integer.parseInt(Id);
		
		Connection con = null;      
        try {
             Class.forName("org.postgresql.Driver");
             con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                     "admin");

             // Utilisez PreparedStatement avec les marqueurs de position pour éviter les attaques par injection SQL
             PreparedStatement pst = con.prepareStatement("INSERT INTO report_forms (description, latitude, longitude, photo, status, report_time, name, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

             // Définir les valeurs pour les marqueurs de position
             pst.setString(1, reportForm.getDescription());
             pst.setFloat(2, reportForm.getLatitude());
             pst.setFloat(3, reportForm.getLongitude());
             pst.setString(5, reportForm.getStatus());
             pst.setTimestamp(6, reportForm.getTime());
             pst.setString(7, reportForm.getName());
             pst.setInt(8, id);
             
             String fileName = getFileName(reportForm.getPhoto());
             
             // Définissez le chemin du dossier où vous souhaitez enregistrer la photo
        	 String uploadDir = "C://Users/dreams/eclipse-workspace/mourad/Projet-Appdist/src/main/webapp/photo";

             File uploadDirFile = new File(uploadDir);
             if (!uploadDirFile.exists()) {
                 uploadDirFile.mkdir();
             }
             
             // Obtenez le chemin complet du fichier de destination
             String filePath = uploadDir + File.separator + fileName;
             
             // Convertir le chemin de fichier en objet Path
             Path path = Paths.get(filePath);

             // Obtenez le chemin de base (répertoire racine)
             Path baseDir = Paths.get("C://Users/dreams/eclipse-workspace/mourad/Projet-Appdist/src/main/webapp");

             // Obtenez le chemin relatif par rapport au répertoire de base
             Path cheminRelatif = baseDir.relativize(path);
             
             // Convertissez le chemin relatif en une chaîne en utilisant le séparateur "/"
             String resultat = cheminRelatif.toString().replace("\\", "/");



             // Afficher le résultat
             System.out.println(resultat);
             
             System.out.println("--------------------------" + id);
             
             // Gérez la partie photo
             try (InputStream photoInputStream = reportForm.getPhoto().getInputStream()) {
                 
            	 Files.copy(photoInputStream, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                 // Stockez l'URL relative du fichier dans la base de données
                 pst.setString(4, resultat);
             }
             
            rowsAffected = pst.executeUpdate();
	        }catch(Exception e){
				e.printStackTrace();
				
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
     return rowsAffected;
	}
	
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : partHeader.split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	public static void sortByNotifId(List<Notification> notifications) {
        // Define a custom comparator based on NotifId
        Comparator<Notification> notifIdComparator = Comparator.comparingInt(Notification::getNotifId).reversed();

        // Sort the list using the custom comparator
        Collections.sort(notifications, notifIdComparator);
    }
	
	public static List<Notification> getLastSevenNotifications(List<Notification> notifications) {
        // Ensure the original list has at least 7 elements
        if (notifications.size() >= 7) {
            // Get the sublist of the last 7 notifications
            return notifications.subList(notifications.size() - 7, notifications.size());
        } else {
            // If there are less than 7 elements, return the whole list
            return notifications;
        }
    }
}
