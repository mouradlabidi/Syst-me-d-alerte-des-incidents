<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ page import="dz.appdist.beans.ReportForm" %>
<%@ page import="java.util.List" %>

				<% 
				if (session.getAttribute("name")=="Force d'ordre" || session.getAttribute("name")=="admin"){	
					response.sendRedirect("admin");	
				} %>
				<% 
				if ("incomplète".equals(session.getAttribute("inscription"))){	
					request.setAttribute("username", session.getAttribute("username1"));
					response.sendRedirect("ImageLocalisation");	
				} %>
				

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertes Criminelles</title>
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <link rel="stylesheet" href="assets/css/crud.css" />
    <link rel="stylesheet" href="css/index.css" />
    <link rel="stylesheet" href="css/carte.css" />
    
    
    
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css" integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin="" />

    <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js" integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM=" crossorigin=""></script>
        
        <!-- Import this CDN to use icons -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"
    />
    
    
    <link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.css" />
	<script src="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js"></script>
</head>

<body>

    <header>
        <nav class="navbar">
            <div class="nav__logo">Alertes<span>Criminelles</span></div>
            <ul class="nav__links">
                <li class="link"><a href="">Accueil</a></li>
                <li class="link"><a href="#a_propos">À propos</a></li>
                <li class="link"><a href="#nos_services">Nos Services</a></li>
                <c:if test="${not empty name}">
                <li class="link"><a href="#signaler">Signalement</a></li>
                </c:if>
                <li class="link"><a href="#nos_indidents">Carte des incidents</a></li>
                <li class="link"><a href="#discussion">Discussion</a></li>
                 
            </ul>
            
            
            <c:choose>
			    <c:when test="${empty name}">
			        
			        <a class="btn" href="Login_Page">Connexion</a>
			    </c:when>
			    <c:otherwise>
			        <a class="btn" href="logout">Se déconnecter</a>
			    </c:otherwise>
			</c:choose>
            
           
			
        </nav>
        <div class="section__container header__container" id="accueil">
            <div class="header__content">
                <h1>Renforcez la sécurité de votre communauté</h1>
                <p>
                    Bienvenue sur notre plateforme où vous pouvez contribuer activement à la sécurité de votre communauté.
                    Signalez des incidents, restez informé avec des mises à jour en temps réel et participez à assurer la sécurité
                    et le bien-être de tous autour de vous.
                </p>
                <a class="btn" href="#nos_services">Voir les services</a>
            </div>
        </div>
    </header>
    <main>
        <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
        
        <section id="nos_services">
            <div class="service__header">
                <div class="service__header__content">
                    <h1 class="section__header">Nos Services pour une Sécurité Communautaire Renforcée</h1>
                    <p>
					    En allant au-delà de la simple sécurité, notre dévouement consiste à assurer la protection
					    de votre communauté en fournissant des services exceptionnels parfaitement adaptés à ses besoins particuliers.
					</p>

                </div>
                <!--  <button class="service__btn">Demander un service</button>-->
            </div>
            <div class="section__container service__container">
                <div class="service__card">
                    <span><div class="image-container"><img src="images/exclamation-triangle.png" alt=""></div></span>
                    <h3>Signalement d'Incident</h3>
                    <p>Signalez un incident en fournissant des détails, des photos et la localisation.</p>
                    <a href="#signaler">En savoir plus</a>
                </div>
            
                <div class="service__card">
                    <span><div class="image-container"><img src="images/Maps-Location.png" alt=""></div></span>
                    <h3>Carte des Incidents</h3>
                    <p>Consultez la carte interactive des incidents signalés dans votre région.</p>
                    <a href="#nos_indidents">En savoir plus</a>
                </div>
            
                <div class="service__card">
                    <span><div class="image-container"><img src="images/Comment.png" alt=""></div></span>
                    <h3>Discussion Communautaire</h3>
                    <p>Participez à des discussions sur la sécurité avec d'autres membres de la communauté.</p>
                    <a href="#discussion">En savoir plus</a>
                </div>
            </div>
        </section>

        <section class="about__container" id="a_propos">
            <div class="sectionPro__container">
                <img src="images/props.jpg" alt="">
                <div class="propos__content">
                    <h2>À propos d'Alertes Criminelles</h2>
                    <p>
                        Bienvenue sur Alertes Criminelles, votre partenaire dédié à la sécurité communautaire.
                        Nous sommes engagés à fournir des services exceptionnels pour assurer le bien-être de votre communauté.
                    </p>
                    <p>
                        Notre mission est de faciliter le signalement d'incidents, de fournir des informations en temps réel
                        sur la sécurité et de favoriser la collaboration au sein de la communauté pour un environnement plus sûr.
                    </p>
                </div>
            </div>
        </section>
               
       <!--  Signaler un incident-------------------------------------------------------------------------------------------->                 
        <c:if test="${not empty name}">
        <section id="signalement">
                <div class="section__container" >
                    <video id="signaler" src="images/silent-sizzle-v07D.mp4" autoplay loop muted class="Phone__PhoneVideo-sc-1ajsie4-2 etjCCe"></video>
                    <div class="signalement__content">
                        <h2 style="margin-buttom:2%" >Signalement d'Incident</h2>
                        
                        <!-- <p>Signalez un incident en fournissant des détails, des photos et la localisation.</p> -->
                        <form method="post" action="signaler?id=${user.id}" enctype="multipart/form-data">
                            
                          
                            <label for="namein">Type d'incident:</label>
                            <input type="text" id="namein" name="namein" required="required"></input>
            
                            
                            <label for="description">Description:</label>
                            <textarea id="description" name="desc" rows="2" required="required"></textarea>
            
                            <label for="location">Localisation:</label>
                            <div id="map"></div>
			
							<input type="hidden" id="coordonnees" name="coord" value="" />	
								
                            <label for="photo">Photo:</label>
                            <input type="file" id="photo" required="required" name="photo">

                            <div class="submitsignalement">
                            <button type="submit">Soumettre</button>
                            </div>
                        </form>
                    </div>
                </div>
        </section>
       </c:if>	
        
       	
		<c:if test="${not empty name}">
		<section id="nos_indidents">      
				<div class="service__header__content">
                    <h1 class="section__header">Carte des Incidents</h1>
                    <p>
					    Explorez la carte interactive pour découvrir les incidents signalés dans votre région.
						Accédez aux détails des incidents grâce à notre carte interactive.
					</p>

                </div>
            <div class="section__container service__container Card">
                <div  style="padding: 2rem; text-align: center; border-radius: 10px; box-shadow: 5px 5px 20px rgba(0, 0, 0, 0.1); cursor: pointer; transition: transform 0.3s;">                  
                    <div  class="imageCard1" style="">
                    	<img />	
                    </div>
                    <h3 class="h3Card1"></h3>
                    <p class="pCard1"></p>
                    <a href="#">En savoir plus</a>
                </div>
                
                <div  style="padding: 2rem; text-align: center; border-radius: 10px; box-shadow: 5px 5px 20px rgba(0, 0, 0, 0.1); cursor: pointer; transition: transform 0.3s;">                  
                    <div  class="imageCard2" style="">
                    	<img />	
                    </div>
                    <h3 class="h3Card2"></h3>
                    <p class="pCard2"></p>
                    <a href="#">En savoir plus</a>
                </div>
            
                <div  style="padding: 2rem; text-align: center; border-radius: 10px; box-shadow: 5px 5px 20px rgba(0, 0, 0, 0.1); cursor: pointer; transition: transform 0.3s;">
                   <div class="imageCard3">
                    	<img />
                    </div>  
                    <h3 class="h3Card3"></h3>
                    <p class="pCard3"></p>
                    <a href="#">En savoir plus</a>                 
                </div>
            </div>
        </section>
		</c:if>	
	<c:if test="${not empty name}">
	<section class="mapa" id="carte">
      
    	</section>
	</c:if>		
		
        <c:if test="${not empty name}">
		<section >
		    <!--<h2>Discussion Communautaire</h2>
		    <p>Participez à des discussions sur la sécurité avec d'autres membres de la communauté.</p>
		     Ajoutez ici des éléments liés aux discussions communautaires -->
		    
		    <div class="containerD" id="discussion">
		      <!-- msg-header section starts -->
		      <div class="msg-header">
		        <div class="container1">
		          <img src="image/mourad.jpg" class="msgimg" />
		          <div class="active">
		            <p>User name</p>
		          </div>
		        </div>
		      </div>
		      <!-- msg-header section ends -->
			<!-- Chat inbox  -->
		      <div class="chat-page">
		        <div class="msg-inbox">
		          <div class="chats">
		            <!-- Message container -->
		            <div class="msg-page">
		              <!-- Incoming messages -->
		
		              <div class="received-chats">
		                <div class="received-chats-img">
		                  <img src="image/user.jpg" />
		                </div>
		                <div class="received-msg">
		                  <div class="received-msg-inbox">
		                    <p>
		                      Hi !! This is message from Riya . Lorem ipsum, dolor sit
		                      amet consectetur adipisicing elit. Non quas nemo eum,
		                      earum sunt, nobis similique quisquam eveniet pariatur
		                      commodi modi voluptatibus iusto omnis harum illum iste
		                      distinctio expedita illo!
		                    </p>
		                    <span class="time">18:06 PM | July 24</span>
		                  </div>
		                </div>
		              </div>
		              <!-- Outgoing messages -->
		              <div class="outgoing-chats">
		                <div class="outgoing-chats-img">
		                  <img src="image/mourad.jpg" />
		                </div>
		                <div class="outgoing-msg">
		                  <div class="outgoing-chats-msg">
		                    <p class="multi-msg">
		                      Hi riya , Lorem ipsum dolor sit amet consectetur
		                      adipisicing elit. Illo nobis deleniti earum magni
		                      recusandae assumenda.
		                    </p>
		                    <p class="multi-msg">
		                      Lorem ipsum dolor sit amet consectetur.
		                    </p>
		
		                    <span class="time">18:30 PM | July 24</span>
		                  </div>
		                </div>
		              </div>
		              <div class="received-chats">
		                <div class="received-chats-img">
		                  <img src="image/user.jpg" />
		                </div>
		                <div class="received-msg">
		                  <div class="received-msg-inbox">
		                    <p class="single-msg">
		                      Hi !! This is message from John Lewis. Lorem ipsum, dolor
		                      sit amet consectetur adipisicing elit. iste distinctio
		                      expedita illo!
		                    </p>
		                    <span class="time">18:31 PM | July 24</span>
		                  </div>
		                </div>
		              </div>
		              <div class="outgoing-chats">
		                <div class="outgoing-chats-img">
		                  <img src="image/mourad.jpg" />
		                </div>
		                <div class="outgoing-msg">
		                  <div class="outgoing-chats-msg">
		                    <p>
		                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
		                      Velit, sequi.
		                    </p>
		
		                    <span class="time">18:34 PM | July 24</span>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </div>
					<!-- msg-bottom section -->
		
		          <div class="msg-bottom">
		            <div class="input-group">
		              <input
		                type="text"
		                class="form-control"
		                id="messageInput"
		                placeholder="Write message..."
		              />
		
		              <span class="input-group-text send-icon" id="sendButton">
		                <i class="bi bi-send"></i>
		              </span>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>	    
		</section>
		</c:if>	
	

		
		</main>
		
		<footer class="footer">
    	<div class="section__container footer__container" id="contact_us">
        <div class="footer__col">
            <h3>Alertes<span>Criminelles</span></h3>
            <p>
                Nous sommes honorés de faire partie de vos efforts pour assurer la sécurité de votre communauté,
                et nous nous engageons à fournir des services de qualité tout au long de ce parcours crucial.
            </p>
            <p>
                Faites-nous confiance pour la sécurité, et travaillons ensemble pour atteindre les meilleurs résultats
                possibles pour vous et vos proches.
            </p>
        </div>
        <div class="footer__col">
            <h4>À propos</h4>
            <p><a href="Accueil" style="text-decoration:none; color:#000">Accueil</a></p>
            <p><a href="#a_propos" style="text-decoration:none; color:#000">À propos de nous</a></p>
            <c:if test="${empty name}">
            	<p><a href="Register_Page" style="text-decoration:none; color:#000">Rejoignez-nous</a></p>
            </c:if>	
            <p>Blog</p>
            <p>Conditions générales</p>
        </div>
        <div class="footer__col">
            <h4>Nos Services</h4>
            <p><a href="#signaler" style="text-decoration:none; color:#000">Signalement d'Incident</a></p>
            <p><a href="#nos_indidents" style="text-decoration:none; color:#000">Carte des Incidents</a></p>
            <p><a href="#discussion" style="text-decoration:none; color:#000">Discussion Communautaire</a></p>
            <!-- Ajoutez d'autres services au besoin -->
        </div>
        <div class="footer__col">
            <h4>Contact</h4>
            <p><i class="ri-map-pin-2-fill"></i> Votre Adresse ici</p>
            <p><i class="ri-mail-fill"></i> contact@alertescriminelles.com</p>
            <p><i class="ri-phone-fill"></i> Votre Numéro de Téléphone</p>
        </div>
	    </div>
	    <div class="footer__bar">
	        <div class="footer__bar__content">
	            <p>&copy; 2024 Alertes Criminelles. Tous droits réservés.</p>
	            <div class="footer__socials">
	                <span><i class="ri-instagram-line"></i></span>
	                <span><i class="ri-facebook-fill"></i></span>
	                <span><i class="ri-heart-fill"></i></span>
	                <span><i class="ri-twitter-fill"></i></span>
	            </div>
	        </div>
	    </div>
		</footer>

	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
    
    <script src="script.js"></script>


<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Coordonnées initiales pour le centre de la carte
        var initialCoordinates = [36.478015804869244, 2.825940055851588];

        // Créer une carte Leaflet avec le centre et le niveau de zoom initiaux
        var map = L.map('carte').setView(initialCoordinates, 7);

        // Ajouter une couche de tuiles OpenStreetMap à la carte
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);

        var a=1;
        var image;
        var resultat;
        
        // Utiliser JSTL pour récupérer la liste de rapports depuis la requête
        <c:forEach var="report" items="${reportsList}">
            var position = [${report.latitude}, ${report.longitude}];
            var iconUrl;

            if ('${report.status}' === "NON_TRAITE") {
                iconUrl = 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png';
            } else {
                iconUrl = 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png';
            }
            var greenIcon = new L.Icon({
            	  iconUrl: iconUrl,
            	  shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
            	  iconSize: [15, 25],
            	  iconAnchor: [12, 41],
            	  popupAnchor: [1, -34],
            	  shadowSize: [41, 41]
            	});
            var marker = L.marker(position, {icon: greenIcon}).addTo(map);
            marker.bindPopup('Incident: ${report.name}<br>Description: ${report.description}<br>Statut: ${report.status}<br>Heure du Rapport: ${report.time}');
        	
            
            resultat = a % 3;
            
            if (resultat==1) {
            	image = document.querySelector('.imageCard1');
            	h = document.querySelector('.h3Card1');
            	p= document.querySelector('.pCard1');
            	console.log('La condition 1 est vraie.');
            } else if (resultat==2) {
            	image = document.querySelector('.imageCard2');
            	h = document.querySelector('.h3Card2');
            	p= document.querySelector('.pCard2');
            	console.log('La condition 2 est vraie.');
            } else{
            	image = document.querySelector('.imageCard3');
            	h = document.querySelector('.h3Card3');
            	p= document.querySelector('.pCard3');
            	console.log('La condition 3 est vraie.');
            }
		            
		      // Créer un élément d'image
		      var img = document.createElement('img');
		      img.src = '${report.image}';
		      img.style.width = '280px';
		      img.style.height = '245px'; 
		      img.style.overflow = 'auto';
		            
		      image.innerHTML = '';
		      image.appendChild(img);
		      
		      h.textContent = '${report.name}';
		      p.textContent = '${report.description}';
            
            a++;
            var clics = 0;
            var reportIds = [];
            var previousImage;
            marker.on('dblclick', function() {
            	

                var reportId = '${report.id}';
                
                if (!reportIds.includes(reportId)) {
                	reportIds.push(reportId);
                	console.log('ID des rapports:', reportIds);
                      
		             	// Incrémenter le compteur de clics
		                clics++;
		                // Sélectionner l'élément en fonction du compteur de clics
		                 var imageCard1, h, p;
		                if (clics % 3 === 1) {
		                    imageCard1 = document.querySelector('.imageCard1');
		                    h = document.querySelector('.h3Card1');
		                	p= document.querySelector('.pCard1');
		                } else if (clics % 3 === 2) {
		                    imageCard1 = document.querySelector('.imageCard2');
		                    h = document.querySelector('.h3Card2');
		                	p= document.querySelector('.pCard2');
		                } else {
		                    imageCard1 = document.querySelector('.imageCard3');
		                    h = document.querySelector('.h3Card3');
		                	p= document.querySelector('.pCard3');
		                }
		            	
		                // Créer un élément d'image
		                var img = document.createElement('img');
		                img.src = '${report.image}';
		                img.style.width = '280px';
		            	img.style.height = '245px'; 
		            	img.style.overflow = 'auto';
		            	
		            	if (previousImage) {
		            	    previousImage.style.border = 'none';
		            	}
		            	
		            	// Ajouter une bordure à l'image pour indiquer qu'elle a été ajoutée
		            	img.style.border = '2px solid blue';
		            	
		            	// Sauvegarder une référence à l'image actuelle pour la prochaine fois
		            	previousImage = img;

		             	// Ajouter l'image à l'élément ''
		                imageCard1.innerHTML = '';
		                imageCard1.appendChild(img);
		                h.textContent = '${report.name}';
		  		       	p.textContent = '${report.description}';
		  		       	
		  		   // Faire défiler la div dans la vue
		  		   		Card = document.querySelector('.Card');
		  		        Card.scrollIntoView({ behavior: 'smooth' });
		  		       	
                }
                else {
                    // Si l'ID existe déjà, ne rien faire (ou afficher un message, selon les besoins)
                    console.log('L\'ID du rapport existe déjà dans le tableau.');
                    
                 // Faire défiler la div dans la vue
	  		   		Card = document.querySelector('.Card');
	  		        Card.scrollIntoView({ behavior: 'smooth' });	
                } 
               
            });
            
            </c:forEach>
        
        
    });
</script>

<script>	   
	//Initialise la carte
	const map = L.map('map').setView([36.478015804869244, 2.825940055851588], 13);
	
	// Ajoute une couche de tuiles OpenStreetMap à la carte
	L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    maxZoom: 19,
	    attribution: '© OpenStreetMap'
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
	    
	    document.getElementById("coordonnees").value = lat + ',' + lng;
	 	
	 	// Mettre à jour le contenu du span avec les coordonnées
	    document.getElementById("location").innerText = lat + "," + lng;
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
	});
	
</script>







	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
 var status=document.getElementById("status").value;
 if (status === "success") {
     swal("Félicitations", "Incident signalé avec succès !", "success");
 } else if (status === "invalideName") {
     swal("Désolé", "Veuillez entrer un type valide.", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         window.location.href = "#signaler" 
     });
 } else if (status === "invalidephoto") {
     swal("Désolé", "Veuillez joindre une photo de l'incident.", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         window.location.href = "#signaler" 
     });
 } else if (status === "invalideLongitude") {
     swal("Désolé", "La longitude de l'incident n'est pas valide.", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         window.location.href = "#signaler" 
     });
 } else if (status === "invalideLatitude") {
     swal("Désolé", "La latitude de l'incident n'est pas valide.", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         window.location.href = "#signaler" 
     });
 }else if (status === "invalideDescription") {
     swal("Désolé", "Veuillez fournir une description de l'incident.", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         window.location.href = "#signaler" 
     });
 }
</script>

</body>

</html>


