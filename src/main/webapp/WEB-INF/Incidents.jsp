<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="dz.appdist.beans.User" %>
<%@ page import="java.util.List" %>

				<% 
				if (session.getAttribute("name")==null){
				response.sendRedirect("Login_Page");
				}else if (session.getAttribute("name")=="user"){	
					response.sendRedirect("Accueil");	
				} %>
				<% 
				if (session.getAttribute("inscription")=="incomplète"){	
					response.sendRedirect("ImageLocalisation");	
				} %>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang=fr">
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Incidents</title>
	<link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
	<style>
        /* Hide the default DataTables search input */
        div.dataTables_wrapper div.dataTables_filter {
            display: none;
        }
        
        .modal {
		  display: none;
		  position: fixed;
		  z-index: 1;
		  left: 0;
		  top: 0;
		  width: 100%;
		  height: 100%;
		  overflow: auto;
		  background-color: rgba(0, 0, 0, 0.5);
		}
		
		.modal-content {
		  background-color: #fefefe;
		  margin: 15% auto;
		  padding: 20px;
		  border: 1px solid #888;
		  width: 80%;
		}
		
		.close {
		  color: #aaa;
		  float: right;
		  font-size: 28px;
		  font-weight: bold;
		}
		
		.close:hover,
		.close:focus {
		  color: black;
		  text-decoration: none;
		  cursor: pointer;
		}
    </style>
 	<!-- ======= Styles ====== -->
 	<link rel="stylesheet" href="assets/css/crud.css">
    <link rel="stylesheet" href="assets/css/style.css">
    
        <!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
    <!-- Styles DataTables -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

<!-- Scripts DataTables -->
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
</head>
<body>

<div class="container">
         <%@include file="header.jsp"  %>  
         <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="search">
	                 <form id="searchForm">
                    <label>
                        <input type="text" id="searchInput" placeholder="Search here">
                        <ion-icon name="search-outline" ></ion-icon>
                    </label>
    				</form>
                </div>

                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div>
            </div>
            <!-- ================ Order Details List ================= -->
            <div class="details1">
                <div class="recentOrders1" style="">
                    <div class="cardHeader">
                        <h2>Incidents</h2>
                        <a href="#cart" class="btn">Map</a>
                    </div>
                    
                    <div>
                    <table id="example" class="no-datatables-style">
                        <thead>
                            <tr>
                                <td>Incident</td>
                                <td>Latitude</td>
                                <td>Longitude</td>
                                <td>Status</td>
                                <td>Action</td>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="reportsList" items="${reportsList}">
                            <tr>
                            
                                <td>${reportsList.name}</td>
                                <td>${reportsList.latitude}</td>
                                <td>${reportsList.longitude}</td>
                                <td><span class="status delivered">${reportsList.status}</span></td>
                                <td>
                                	
                                	
								    <a href="updateincident?scroll=${reportsList.id}" >
								        <img class="user" src="assets/imgs/pen.png" alt="Modifier">
								    </a>
								    
								    <a href="description?scroll=${reportsList.id}" >
                                		<img class="user" src="assets/imgs/Open.png" alt="Consulter">
                                	</a>
                                	
									<a href="" onclick="deleteIncidnets('${reportsList.id}')">
									        <img class="user" src="assets/imgs/delete.png" alt="Supprimer">
									    </a>								
							   </td>
                            </tr>
						</c:forEach>
                           

                        </tbody>
                    </table>
                    </div>
				

                    <div class="mapa" style="" id="cart">
      
    				</div>
    				
                </div>
               </div>
               

               
               
    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Coordonnées initiales pour le centre de la carte
        var initialCoordinates = [36.478015804869244, 2.825940055851588];

        // Créer une carte Leaflet avec le centre et le niveau de zoom initiaux
        var map = L.map('cart').setView(initialCoordinates, 7);

        // Ajouter une couche de tuiles OpenStreetMap à la carte
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);

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
         	// Utiliser la géocodification inverse pour obtenir le nom de l'emplacement
            var lata = parseFloat('<c:out value="${report.latitude}"/>');
            var lona = parseFloat('<c:out value="${report.longitude}"/>');

            console.log('Latitude----------:', lata);
            console.log('Longitude-----------:', lona);
            console.log('Type de lat:', typeof lata);
            console.log('Type de lon:', typeof lona);

          	marker.bindPopup('Statut: ${report.status}<br>Heure du Rapport: ${report.time}');

         	
            
            
            // Ajoutez un gestionnaire d'événement pour le double clic sur l'icône
            marker.on('dblclick', function (e) {
		    var reportId = '${report.id}';
		
		    fetch('description?scroll=' + encodeURIComponent(reportId), {
		        method: 'GET'
		    })
		    .then(response => response.text())
		    .then(data => {
		        // Mettez à jour le contenu de votre popup avec le contenu de la page JSP
		        marker.setPopupContent(data);
		    })
		    .catch(error => {
		        console.error('Erreur lors de la requête fetch:', error);
		    });
		});
            
            
            
        </c:forEach>
    });
</script>

<script>
    var table; // Déclarer la variable à l'extérieur de la fonction ready

    $(document).ready(function() {
        // Initialiser le DataTable
        table = $('#example').DataTable({
            searching: true, 
            lengthChange: false,
            bJQueryUI: false,
            ordering: false
        });
        
        
     // Add an event listener to the search input
        $('#searchInput').on('input', function() {
            var searchValue = $(this).val(); // Get the current value of the search input
            table.search(searchValue).draw(); // Apply the new search value to the DataTable
        });
    });
    
    
	</script>
	
	<!-- Supprimer un utilisateur -->
	<script>
	    function deleteIncidnets(id) {
	        if (confirm('Êtes-vous sûr de vouloir supprimer cet incident ?')) {
	            // Utilisez AJAX pour envoyer la demande de suppression
	            var xhr = new XMLHttpRequest();
	            xhr.open('POST', 'deleteincident?action=delete&id=' + encodeURIComponent(id), true);
	            xhr.onreadystatechange = function () {
	                if (xhr.readyState == 4 && xhr.status == 200) {
	                    // Mettez à jour ou réagissez à l'interface utilisateur si nécessaire
	                    // (par exemple, supprimer la ligne de l'utilisateur de la table)
	                    // Note : Vous devrez peut-être ajouter des gestionnaires d'erreurs et de succès appropriés.
	                }
	            };
	            xhr.send();
	        }
	    }
	</script>
	
	

	


	
</body>
</html>