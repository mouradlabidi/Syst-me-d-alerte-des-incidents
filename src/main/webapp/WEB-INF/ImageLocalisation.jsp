<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complétez l'inscription !</title>
    
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
	<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>
    
    <link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.css" />
	<script src="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js"></script>
    
    
     <Style>
     	#map{
     	 	height: 100%; 
		    width: 100%;
		    border-radius: 5px;
     	}
     	
     	.login-image1{
		    width: 50%;
		    background-color: #f4f4f4;
		    background-size: cover;
		    background-position: center;   
		}
     	.login-form1 {
		    padding: 20px;
		    box-sizing: border-box;
		    width: 50%;
		    text-align: center;
		}
		.login-form1 form div{
		    
		    text-align: left;
		}
     	.login-form1 h3 {
		    margin-bottom: 12%;
		    margin-top: 0%;
		    color: #1B1D21;
		    font-family: 'Times New Roman', Times, serif;
		    text-align: center;
		    letter-spacing: 1px;
		}
		.login-form1 p {
		    margin-bottom: 5%;
		    color: #1B1D21;
		    opacity: 0.7;
		    text-align: center;
		}
		.login-form1 a {
		    margin-bottom: 20px;
		    margin-top: 30%;
		    color: #1B1D21;
		    text-decoration: none;
		    text-align: center;
		    opacity: 0.8;
		}
		.login-form1 button {
		    background-color: #1B1D21;
		    color: #fff;
		    padding: 10px 20px;
		    width: 50%;
		    border: none;
		    margin-top: 4%;
		    margin-bottom: 1%;
		    border-radius: 50px;
		    cursor: pointer;
		}
		
		.login-form1 input {
		    width: 70%;
		    padding: 10px;
		    margin-left: 10%;
		    margin-bottom: 10%;
		    box-sizing: border-box;
		    border-top: #1B1D21;
		    border-left: #1B1D21;
		    border-right: #1B1D21;
		    outline: none;
		}
		.login-form1 label {
		    width: 70%;
		    padding: 10px;
		    margin-left: 1%%;
		    margin-bottom: 1%;
		    box-sizing: border-box;
		    border-top: #1B1D21;
		    border-left: #1B1D21;
		    border-right: #1B1D21;
		    outline: none;
		}
		.login-form1 button:hover {
		    background-color: #030303;
		}
		.login-form1 .ico{
	  		display: inline-block;
		    width: 16%; /* Ajustez la taille du cercle selon vos besoins */
		    height: 17%; /* Ajustez la taille du cercle selon vos besoins */
		    border-radius: 50%; /* Rend le conteneur en forme de cercle */
		    overflow: hidden; /* Assure que l'image à l'intérieur du cercle est coupée correctement */
		    margin: 0 auto; 
		}
		.login-form1 .ico img {
		    width: 100%; /* Assure que l'image occupe toute la largeur du conteneur */
		    height: 100%; /* Assure que l'image occupe toute la hauteur du conteneur */
		    object-fit: cover; /* Ajuste la taille de l'image pour couvrir le conteneur sans déformer l'image */
		}
		.login-form1 hr{
			margin-top:1%; 
			margin-left:10%; 
			margin-right:10%
		}
		
		@media (max-width: 768px) {
		    .login-image1 {
		        display: none;
		    }
		    .login-form1 {
		        width: 100%;
		    }
		}
		span{
		color:red;
		}
     </Style>
     
    <link rel="stylesheet" href="css/Style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
 <input type="hidden" id="coordonnees" name="coord1" value="" />
<div class="container">
    <div class="login-image1"><div id="map"></div></div>
    
    <div class="login-form1">
        <div class="ico"><img src="images/ico.png" alt=""></div>
        <h3>Complétez les informations de votre compte !</h3>
        <form method="post" action="ImageLocalisation" >
        	<div>
        	<!-- enctype="multipart/form-data" <label for="location"><b>Image:</b><span> *</span></label><br>
            <input type="file" id="photo" required="required" name="photo"> -->
           <br>
           
        	<label for="location"><b>Indiquez Votre localisation:</b><span> *</span></label>
        	<br><br>
        	<hr style="margin-bottom: 30%;">
        	 <input type="hidden" id="username" name="username" value="${username}" />
        	 <input type="hidden" id="userlocalisation" name="local" value="" />
        	 <input type="hidden" id="user" name="localisation" value="" />
        	 
           </div>
            <button type="submit" name="signin" id="signin">Continuer</button>
        </form>
        
        <hr>
        <a href="logout">Se <b>déconnecter </b></a>
    </div>
    
</div>

	<script>	   
	//Initialise la carte
	const map = L.map('map').setView([36.478015804869244, 2.825940055851588], 13);
	
	L.tileLayer('https://api.maptiler.com/maps/openstreetmap/{z}/{x}/{y}.jpg?key=HjaQ8k0MsUG2AwUGWOYV', {
		maxZoom: 19,
		attribution: '<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">&copy; OpenStreetMap contributors</a>',
	}).addTo(map);
	
	let marker, circle, zoomed;
	let selectedCoordinates = null; // Variable globale pour stocker les coordonnées
	
	// Obtient automatiquement la position actuelle de l'utilisateur
	navigator.geolocation.getCurrentPosition(success, error);
	
	// Ajoute un gestionnaire d'événements pour permettre à l'utilisateur de cliquer sur la carte
	map.on('click', function (e) {
	    // Récupère les coordonnées du clic
	    const lat = e.latlng.lat;
	    const lng = e.latlng.lng;
	
	    // Met à jour la position du marqueur et du cercle
	    updateMarker(lat, lng);
	
	    // Stocke les coordonnées sélectionnées dans la variable globale
	    selectedCoordinates = { lat, lng };
	    
	    //document.getElementById("coordonnees").value = lat + ',' + lng;
	 	
	 	// Mettre à jour le contenu du span avec les coordonnées
	    document.getElementById("userlocalisation").value = lat + "," + lng;
	});
	
	function success(pos) {
	    const lat = pos.coords.latitude;
	    const lng = pos.coords.longitude;
	    const accuracy = pos.coords.accuracy;
	
	    // Ajoute un marqueur et un cercle pour la précision
	    addMarker(lat, lng, accuracy);
	
	    // Ajuste le zoom pour s'adapter aux limites du cercle de précision
	    if (!zoomed) {
	        map.fitBounds(circle.getBounds());
	        zoomed = true;
	    }
	
	    // Stocke les coordonnées de la position actuelle dans la variable globale
	    selectedCoordinates = { lat, lng };
	    
	}
	
	function error(err) {
	    if (err.code === 1) {
	        alert("Veuillez autoriser l'accès à la géolocalisation");
	    } else {
	        alert("Impossible d'obtenir la position actuelle");
	    }
	}
	
	function addMarker(lat, lng, accuracy) {
	    // Supprime le marqueur et le cercle existants s'ils existent
	    if (marker) {
	        map.removeLayer(marker);
	        map.removeLayer(circle);
	    }
	
	    // Ajoute un marqueur et un cercle pour la précision
	    marker = L.marker([lat, lng]).addTo(map);
	    circle = L.circle([lat, lng], { radius: accuracy }).addTo(map);
	}
	
	function updateMarker(lat, lng) {
	    // Met à jour la position du marqueur et du cercle
	    if (marker) {
	        map.removeLayer(marker);
	        map.removeLayer(circle);
	    }
	
	    // Ajoute un marqueur avec une précision de 0 car la position est choisie par l'utilisateur
	    addMarker(lat, lng, 0);

	}
	
	
	// Ajoute le contrôle de géocodage à la carte
	L.Control.geocoder().addTo(map);
	
	let userInputValue;

	// Ajoute un gestionnaire d'événements pour mettre à jour la carte lorsque la localisation est sélectionnée
	document.getElementById('userpla').addEventListener('change', function () {
		userInputValue  = this.value;

	    // Utilise l'API de géocodage pour obtenir la localisation à partir de la saisie de l'utilisateur
	    L.Control.Geocoder.nominatim().geocode(userInputValue, function (results) {
	        if (results && results.length > 0) {
	            const latlng = [results[0].center.lat, results[0].center.lng];

	            // Met à jour la carte avec la nouvelle localisation
	            updateMarker(latlng[0], latlng[1]);

	            // Stocke les nouvelles coordonnées dans la variable globale
	            selectedCoordinates = { lat: latlng[0], lng: latlng[1] };
	            
	        }
	    });
	 // Affiche la valeur dans la console
	    console.log('Saisie de l\'utilisateur :', userInputValue);
	    
	});
	document.getElementById("user").value = userInputValue;
	</script>

	
	
</body>
</html>
	