package dz.appdist.dao;

import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import dz.appdist.beans.Notification;
import dz.appdist.beans.User;

public class UserDAO {

	public UserDAO(){
		super();
	}
	
	//selectionner tous les utilisateurs
	public List<User> getAllUsers() {
	    List<User> userList = new ArrayList<>();
	    Connection con = null;
	    int cpt=0;
	    try {
	        Class.forName("org.postgresql.Driver");
	        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");

	        // Utilisez Statement car il n'y a pas de paramètres de requête
	        PreparedStatement pst = con.prepareStatement("SELECT id, lastname, firstname, username, useremail, birthday, mobile, userpassword, latitude, longitude FROM users");
	        ResultSet rs = pst.executeQuery();
	        System.out.println("***************************************************************************************************");
	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setNom(rs.getString("lastname"));
	            user.setPrenom(rs.getString("firstname"));
	            user.setUsername(rs.getString("username"));
	            user.setUseremail(rs.getString("useremail"));
	            user.setUserbirth(rs.getString("birthday"));
	            user.setUsermobile(rs.getString("mobile"));
	            user.setUserpass(rs.getString("userpassword"));
	            user.setLatitude(rs.getFloat("latitude"));
	            user.setLongitude(rs.getFloat("longitude"));

	            userList.add(user);
	            cpt++;
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Lastname: " + rs.getString("lastname"));
	            System.out.println("Firstname: " + rs.getString("firstname"));
	            System.out.println("Username: " + rs.getString("username"));
	            System.out.println("Useremail: " + rs.getString("useremail"));
	            System.out.println("Mobile: " + rs.getString("mobile"));
	            System.out.println("Password: " + rs.getString("userpassword"));
	            System.out.println("Latitude: " + rs.getFloat("latitude"));
	            System.out.println("Longitude: " + rs.getFloat("longitude"));
	        }
	        
	        System.out.println("cpt:"+cpt+"***************************************************************************************************");

	    } catch (Exception e) {
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

	    return userList;
	}

	//selectionner un utilisateur
	public User selectUser(HttpServletRequest request, String Id) {

		int id = Integer.parseInt(Id);
		User user = new User();
		user.setId(id);
		
		 java.sql.Connection con=null;
		 try {
			 	Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
	            
				// Utilisez PreparedStatement avec les marqueurs de position pour éviter les attaques par injection SQL
				PreparedStatement pst = con.prepareStatement("SELECT lastname, firstname, username, useremail, birthday, mobile, userpassword FROM users WHERE id=?");

				pst.setInt(1, user.getId());
				
	            ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	user.setNom(rs.getString("lastname"));
	            	user.setPrenom(rs.getString("firstname"));
	            	user.setUsername(rs.getString("username"));
	            	user.setUseremail(rs.getString("useremail"));
	            	user.setUserbirth(rs.getString("birthday"));
	                user.setUsermobile(rs.getString("mobile"));
	                user.setUserpass(rs.getString("userpassword"));
	                
	            	System.out.println("Lastname: " + rs.getString("lastname") );
	                System.out.println("Firstname: " + rs.getString("firstname") );
	                System.out.println("Username: " + rs.getString("username") );
	                System.out.println("Useremail: " + rs.getString("useremail"));
	                System.out.println("Mobile: " + rs.getString("mobile"));
	                System.out.println("Password: " + rs.getString("userpassword"));
	            	}
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
		return user;
	}
	
	//modifier un utilisateur
	public int UpdatetUser(User user, Date userbirthDate) {
		int rs = 0;
				java.sql.Connection con = null;
			    try {
			        Class.forName("org.postgresql.Driver");
			        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
		
			        PreparedStatement pst = con.prepareStatement("update users set lastname=?, firstname=?, username=?, useremail=?, birthday=?, mobile=? Where id = ?");
			        pst.setString(1, user.getNom());
			        pst.setString(2, user.getPrenom());
			        pst.setString(3, user.getUsername());
			        pst.setString(4, user.getUseremail());
			        pst.setDate(5, userbirthDate);
			        pst.setString(6, user.getUsermobile());
			        pst.setInt(7, user.getId());
		
			        System.out.println("SQL Query: " + pst.toString());
			        rs = pst.executeUpdate();
			        System.out.println("Username to update: " + user.getUserbirth());
			        
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
		return rs;
	}
	
	//modifier un utilisateur pour ajjouter localisation et la photo de profil
		public int UpdatetUserLI(User user) {
			int rs = 0;
					java.sql.Connection con = null;
				    try {
				        Class.forName("org.postgresql.Driver");
				        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
			
				        PreparedStatement pst = con.prepareStatement("update users set latitude=?, longitude=? Where username = ?");
				        pst.setFloat(1, user.getLatitude());
				        pst.setFloat(2, user.getLongitude());
				        //pst.setString(3, user.getPhoto());
				        pst.setString(3, user.getUsername());
			
				        /*String fileName = getFileName(user.getImage());
			             
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
			             
			             // Gérez la partie photo
			             try (InputStream photoInputStream = user.getImage().getInputStream()) {
			                 
			            	 Files.copy(photoInputStream, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
			                 // Stockez l'URL relative du fichier dans la base de données
			                 pst.setString(3, resultat);
			             }
			             */
				        System.out.println("SQL Query: " + pst.toString());
				        rs = pst.executeUpdate();
				        System.out.println("Username to update: " + user.getUsername());
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
			return rs;
		}
		/*private String getFileName(final Part part) {
		    final String partHeader = part.getHeader("content-disposition");
		    for (String content : partHeader.split(";")) {
		        if (content.trim().startsWith("filename")) {
		            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
		        }
		    }
		    return null;
		}*/
		
	// créer un utilisateur
    public int addUser(User user) {
    	int rowsAffected;
    	Date userbirthDate = null;
		try {
			// Utilisez un SimpleDateFormat pour parser la chaîne en java.util.Date
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = dateFormat.parse(user.getUserbirth());
		
	        // Convertissez la java.util.Date en java.sql.Date
	        userbirthDate = new Date(parsedDate.getTime());
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
            
         try {
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                                "admin");
                String sql = "INSERT INTO users (lastname, firstname, username, useremail, userpassword, birthday, mobile, role_admin, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            	PreparedStatement pst = con.prepareStatement(sql); 
                pst.setString(1, user.getNom());
                pst.setString(2, user.getPrenom());
                pst.setString(3, user.getUsername());
                pst.setString(4, user.getUseremail());
                pst.setString(5, user.getUserpass());
                pst.setDate(6, userbirthDate);
                pst.setString(7, user.getUsermobile());
                pst.setString(8, user.getRole_admin());
                pst.setString(9, user.getGrade());

                rowsAffected = pst.executeUpdate();

	            System.out.println(rowsAffected + " ligne(s) affectée(s)");
                
                
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
         return rowsAffected;
    }

    // créer un utilisateur force d'ordre
    public int addForce(User user) {
    	int rowsAffected;
    	Date userbirthDate = null;
		try {
			// Utilisez un SimpleDateFormat pour parser la chaîne en java.util.Date
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = dateFormat.parse(user.getUserbirth());
		
	        // Convertissez la java.util.Date en java.sql.Date
	        userbirthDate = new Date(parsedDate.getTime());
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
            
         try {
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                                "admin");
                String sql = "INSERT INTO users (lastname, firstname, username, useremail, userpassword, birthday, mobile, role_admin, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            	PreparedStatement pst = con.prepareStatement(sql); 
                pst.setString(1, user.getNom());
                pst.setString(2, user.getPrenom());
                pst.setString(3, user.getUsername());
                pst.setString(4, user.getUseremail());
                pst.setString(5, user.getUserpass());
                pst.setDate(6, userbirthDate);
                pst.setString(7, user.getUsermobile());
                pst.setString(8, user.getRole_admin());
                pst.setString(9, user.getGrade());

                rowsAffected = pst.executeUpdate();

	            System.out.println(rowsAffected + " ligne(s) affectée(s)");
                
                
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
         return rowsAffected;
    }
    
    
    
    // supprimer un utilisateur
    public int deleteUser(String username) {
		java.sql.Connection con=null;
		int rs = 0;
		try {
			Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",  "admin");

            PreparedStatement pst = con.prepareStatement("delete from users Where username = ?");
            pst.setString(1, username);
            rs =  pst.executeUpdate();
            
		}	catch(Exception e) {
			
			}
		return rs;
		
    }
    
    ///////////////////////////////////////////////////////////////////////////////////
    // créer un notofication
    public int addNotif(Notification notif) {
    	int rowsAffected;
    	   
    	// Formater la distance avec deux chiffres après la virgule
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
        String formattedDistance = df.format(notif.getDistance());

        // Replace commas with dots
        formattedDistance = formattedDistance.replace(",", ".");

        notif.setDistance(Double.parseDouble(formattedDistance));
            // Get the current timestamp
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

         try {
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                                "admin");
                String sql = "INSERT INTO notification (incident_distance ,date_of_incident, user_id, report_form_id) VALUES (?, ?, ?, ?)";
            	PreparedStatement pst = con.prepareStatement(sql); 
                pst.setDouble(1, notif.getDistance());
                pst.setTimestamp(2, currentTimestamp);
                pst.setInt(3, notif.getUserId());
                pst.setInt(4, notif.getReport_form_id());
                

                rowsAffected = pst.executeUpdate();

	            System.out.println(rowsAffected + " ligne(s) affectée(s)");
                
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return rowsAffected;  
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
  //selectionner tous les utilisateurs
  	public List<Notification> getAllNotifs() {
  	    List<Notification> notifList = new ArrayList<>();
  	    Connection con = null;
  	    int cpt=0;
  	    try {
  	        Class.forName("org.postgresql.Driver");
  	        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");

  	        // Utilisez Statement car il n'y a pas de paramètres de requête
  	        PreparedStatement pst = con.prepareStatement("SELECT * FROM notification");
  	        ResultSet rs = pst.executeQuery();
  	        System.out.println("***************************************************************************************************");
  	        while (rs.next()) {
  	        	Notification nf = new Notification();
  	        	nf.setNotifId(rs.getInt("notif_id"));
  	        	nf.setUserId(rs.getInt("user_id"));
  	        	nf.setReport_form_id(rs.getInt("report_form_id"));
                nf.setDistance(rs.getDouble("incident_distance"));
                nf.setDateOfIncident(rs.getTimestamp("date_of_incident"));
  	           
                notifList.add(nf);
  	            cpt++;
  	            System.out.println("Distance of incident: " + rs.getDouble("incident_distance"));
  	            System.out.println("Time: " + rs.getTimestamp("date_of_incident"));
  	        }
  	        
  	        System.out.println("cpt:"+cpt+"***************************************************************************************************");

  	    } catch (Exception e) {
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

  	    return notifList;
  	}
    
    // sign-in
    public ResultSet login(User user) {
    	ResultSet Result = null;
    	 java.sql.Connection con=null;
		 try {
			 	Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
	            
				// Utilisez PreparedStatement avec les marqueurs de position pour éviter les attaques par injection SQL
				PreparedStatement pst = con.prepareStatement("SELECT * from users where username=? and userpassword=?");
	            
	            // Définir les valeurs pour les marqueurs de position
	            pst.setString(1, user.getUsername());
	            pst.setString(2, user.getUserpass());
	            

	            // Exécuter la requête
	            Result = pst.executeQuery();
	            
	            
	     		 }catch(Exception e) {
	     				e.printStackTrace();
	     		 }finally{
	     				try {
	     		                if (con != null) {
	     		                    con.close();
	     		                }
	     		            } catch (SQLException e) {
	     		                e.printStackTrace();
	     		            }
	     		 }
		 return Result;
    }
    
    public ResultSet Userlist() {
    	ResultSet rs = null;
    	try {
        	Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                    "admin");

            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE role_admin != 'Admin' ORDER BY id DESC LIMIT 8");
            rs = pst.executeQuery();
    	    con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
        return rs;    
    }
    
    //changer le mot de passe
    public int userpass(User user) {
    	int rs =0;
    	
    	try {
        	Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                    "admin");

            PreparedStatement pst = con.prepareStatement("UPDATE users SET userpassword =? WHERE id =?");
            pst.setString(1, user.getUserpass());
            pst.setInt(2, user.getId());
            
            System.out.println("SQL Query: " + pst.toString());
	        rs = pst.executeUpdate();
           
    	    con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
    	
    	return rs;
    }
    
  //selectionner un utilisateur by username ou useremail
  	public User selectUserByusernameOremail(HttpServletRequest request, User nameemail) {

  		
  		User user = new User();
  		
  		 java.sql.Connection con=null;
  		 try {
  			 	Class.forName("org.postgresql.Driver");
  				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
  	            
  				// Utilisez PreparedStatement avec les marqueurs de position pour éviter les attaques par injection SQL
  				PreparedStatement pst = con.prepareStatement("SELECT lastname, firstname, username, useremail, birthday, mobile, userpassword FROM users WHERE username=?");

  				pst.setString(1, nameemail.getUsername());
  				
  	            ResultSet rs = pst.executeQuery();
  	            
  	            while (rs.next()) {
  	            	user.setNom(rs.getString("lastname"));
  	            	user.setPrenom(rs.getString("firstname"));
  	            	user.setUsername(rs.getString("username"));
  	            	user.setUseremail(rs.getString("useremail"));
  	            	user.setUserbirth(rs.getString("birthday"));
  	                user.setUsermobile(rs.getString("mobile"));
  	                user.setUserpass(rs.getString("userpassword"));
  	                
  	            	System.out.println("Lastname: " + rs.getString("lastname") );
  	                System.out.println("Firstname: " + rs.getString("firstname") );
  	                System.out.println("Username: " + rs.getString("username") );
  	                System.out.println("Useremail: " + rs.getString("useremail"));
  	                System.out.println("Mobile: " + rs.getString("mobile"));
  	                System.out.println("Password: " + rs.getString("userpassword"));
  	            	}
  	            }catch(Exception e) {
  	            	e.printStackTrace();
  	            }
  		return user;
  	}
  //selectionner un utilisateur by useremail
  /*	public User selectUserByuseremail(HttpServletRequest request, User email) {

  		
  		User user = new User();
  		
  		 java.sql.Connection con=null;
  		 try {
  			 	Class.forName("org.postgresql.Driver");
  				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
  	            
  				// Utilisez PreparedStatement avec les marqueurs de position pour éviter les attaques par injection SQL
  				PreparedStatement pst = con.prepareStatement("SELECT lastname, firstname, username, useremail, birthday, mobile, userpassword FROM users WHERE useremail=?");

  				pst.setString(1, email.getUseremail());
  				
  	            ResultSet rs = pst.executeQuery();
  	            
  	            while (rs.next()) {
  	            	user.setId(rs.getInt("id"));
  	            	user.setNom(rs.getString("lastname"));
  	            	user.setPrenom(rs.getString("firstname"));
  	            	user.setUsername(rs.getString("username"));
  	            	user.setUseremail(rs.getString("useremail"));
  	            	user.setUserbirth(rs.getString("birthday"));
  	                user.setUsermobile(rs.getString("mobile"));
  	                user.setUserpass(rs.getString("userpassword"));
  	                
  	            	System.out.println("Lastname: " + rs.getString("lastname") );
  	                System.out.println("Firstname: " + rs.getString("firstname") );
  	                System.out.println("Username: " + rs.getString("username") );
  	                System.out.println("Useremail: " + rs.getString("useremail"));
  	                System.out.println("Mobile: " + rs.getString("mobile"));
  	                System.out.println("Password: " + rs.getString("userpassword"));
  	            	}
  	            }catch(Exception e) {
  	            	e.printStackTrace();
  	            }
  		return user;
  	}*/
    
  //modifier reset code champ d'un utilisateur 
  	public int Addresetcode(String userEmail, int resetCode) {
  		int rs =0;
    	User user = new User();
    	user.setResetcode(resetCode);
    	user.setUseremail(userEmail);
    	
    	try {
        	Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                    "admin");

            PreparedStatement pst = con.prepareStatement("UPDATE users SET resetcode =? WHERE useremail  =?");
            pst.setInt(1, user.getResetcode());
            pst.setString(2, user.getUseremail());
            
            System.out.println("SQL Query: " + pst.toString());
	        rs = pst.executeUpdate();
           
    	    con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
    	
    	return rs;
  	}
  	
  //récupérer le reset code d'un utilisateur
  	public int Getresetcode(String email) {

  		int code = 0;
  		User user = new User();
  		user.setUseremail(email);
  		
  		 java.sql.Connection con=null;
  		 try {
  			 	Class.forName("org.postgresql.Driver");
  				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres", "admin");
  	            
  				// Utilisez PreparedStatement avec les marqueurs de position pour éviter les attaques par injection SQL
  				PreparedStatement pst = con.prepareStatement("SELECT resetcode FROM users WHERE useremail=?");

  				pst.setString(1, user.getUseremail());
  				
  	            ResultSet rs = pst.executeQuery();
  	            
  	            while (rs.next()) {
  	            	user.setResetcode(rs.getInt("resetcode"));
  	            	code= user.getResetcode();
  	               
  	                System.out.println("code: " + rs.getInt("resetcode"));
  	            	}
  	            }catch(Exception e) {
  	            	e.printStackTrace();
  	            }
  		return code;
  	}
    
  //changer le mot de passe de l'itilisateur qui a cet email
    public int userpassBymail(User user) {
    	int rs =0;
    	
    	try {
        	Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appdist?useSSL=false", "postgres",
                    "admin");

            PreparedStatement pst = con.prepareStatement("UPDATE users SET userpassword =? WHERE useremail =?");
            pst.setString(1, user.getUserpass());
            pst.setString(2, user.getUseremail());
            
            System.out.println("SQL Query: " + pst.toString());
	        rs = pst.executeUpdate();
           
    	    con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
    	
    	return rs;
    }
}
