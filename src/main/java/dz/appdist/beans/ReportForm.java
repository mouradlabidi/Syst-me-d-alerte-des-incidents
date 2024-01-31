package dz.appdist.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.servlet.http.Part;

public class ReportForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer Id;
    private String description;
    private Float latitude;
    private Float longitude;
    private Part photo;
    private String image;
    private String status;
    private Timestamp time;
    private String name;

    // Constructeurs (par défaut et paramétré)

    public ReportForm() {
    }

    public ReportForm(String description, Float latitude, Float longitude, Part photo, String status, Timestamp Time, String name) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
        this.status= status;
        this.time=Time;
        this.name = name;
    }
    
    public ReportForm(Float latitude, Float longitude,String status, Timestamp Time, String image) {
        
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.status = status;
        this.time = Time;
    }

    // Getters et setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Part getPhoto() {
        return photo;
    }

    public void setPhoto(Part photo) {
        this.photo = photo;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}