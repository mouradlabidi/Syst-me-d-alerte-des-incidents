package dz.appdist.beans;
import java.io.Serializable;

import javax.servlet.http.Part;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nom;
    private String prenom;
    private String username;
    private String useremail;
    private String userbirth;
    private String oldpass; 
    private String userpass;
    private String re_userpass;
    private String usermobile;
	private String role_admin;
	private String grade;
	private int id;
	private int userid;
	private int resetcode;
	private Part image;
	private String photo;
	private float latitude;
	private float longitude;

	
    // Constructeurs (par défaut et paramétré)

    public User() {
    }

    public User(String nom, String prenom, String username, String useremail, String userbirth, String userpass, String re_userpass, String usermobile, String role_admin, String grade) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.useremail = useremail;
        this.userbirth = userbirth;
        this.userpass = userpass;
        this.re_userpass = re_userpass;
        this.usermobile = usermobile;
        this.role_admin = role_admin;
        this.setGrade(grade);
    }
    public User(String username, String userpass) {
        this.username = username;
        this.userpass = userpass;
    }

    // Getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(String userbirth) {
        this.userbirth = userbirth;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getRe_userpass() {
        return re_userpass;
    }

    public void setRe_userpass(String re_userpass) {
        this.re_userpass = re_userpass;
    }

    public String getUsermobile() {
        return usermobile;
    }

    public void setUsermobile(String usermobile) {
        this.usermobile = usermobile;
    }

	public String getRole_admin() {
		return role_admin;
	}

	public void setRole_admin(String role_admin) {
		this.role_admin = role_admin;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public int getResetcode() {
		return resetcode;
	}

	public void setResetcode(int resetcode) {
		this.resetcode = resetcode;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

    // Autres méthodes si nécessaire (hashCode, equals, toString, etc.)
}
