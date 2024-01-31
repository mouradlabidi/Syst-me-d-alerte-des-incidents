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
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css" integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin="" />

    <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js" integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM=" crossorigin=""></script>
    
	<title>Utilisateurs</title>
 	<!-- ======= Styles ====== -->
    <link rel="stylesheet" href="assets/css/style.css">
    
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
                    <label>
                        <input type="text" id="searchIn" placeholder="Search here">
                        <ion-icon id="searchIcon" name="search-outline"></ion-icon>
                    </label>				
                </div>

                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div>
            </div>
            <!-- ================ Order Details List ================= -->
            <div class="details1">
                <div class="recentOrders1" style="">
                    <div class="cardHeader">
                        <h2>Utilisateurs</h2>
                        <a href="adduser" class="btn">Ajouter</a>
                    </div>

                    <table id="example" class="no-datatables-style">
                        <thead>
                            <tr>
                                <td>Nom et Prénom</td>
                                <td>Nom d'utilisateur</td>
                                <td>Email</td>
                                <td>Grade</td>
                                <td>Mobile</td>
                                <td>Action</td>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.nom} ${user.prenom}</td>
                                <td>${user.username}</td>
                                <td>${user.useremail}</td>
                                <td><span class="status delivered">${user.grade}</span></td>
                                <td>${user.usermobile} </td>
                                
                                
                                <td>
								    <a href="updateuser?scroll=${user.id}">
								        <img class="user" src="assets/imgs/pen.png" alt="">
								    </a>
									<a href="" onclick="deleteUser('${user.username}')">
									        <img class="user" src="assets/imgs/delete.png" alt="">
									    </a>								
							   </td>
                            </tr>
						</c:forEach>
                           

                        </tbody>
                    </table>
                    
                </div>
               </div>
               
               
               
    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
    
    <!-- Supprimer un utilisateur -->
	<script>
	    function deleteUser(username) {
	        if (confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')) {
	            // Utilisez AJAX pour envoyer la demande de suppression
	            var xhr = new XMLHttpRequest();
	            xhr.open('POST', 'deleteuser?action=delete&username=' + encodeURIComponent(username), true);
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
	<!-- Modifier un utilisateur -->
	<script>
    function updateUser(username) {
        // Récupérer les informations de l'utilisateur à mettre à jour (par exemple, envoi d'une autre requête AJAX)
        // Vous pouvez afficher un formulaire de mise à jour ou utiliser une boîte de dialogue pour saisir les nouvelles informations

        // En supposant que vous avez obtenu les nouvelles informations à mettre à jour (newData)...
        //var newData = prompt("Entrez les nouvelles informations pour l'utilisateur " + username + " :");

        // Utilisez AJAX pour envoyer la demande de mise à jour
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'Updateuser?action=update&username=' + encodeURIComponent(username) , true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    // Mettez à jour ou réagissez à l'interface utilisateur si nécessaire
                    // (par exemple, mettre à jour la ligne de l'utilisateur dans la table)
                    // Note : Vous devrez peut-être ajouter des gestionnaires d'erreurs et de succès appropriés.
                } else {
                    // Gérez les erreurs si la requête a échoué
                    alert("Erreur lors de la mise à jour de l'utilisateur.");
                }
            }
        };
        xhr.send();
    }
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
        $('#searchIn').on('input', function() {
            var searchValue = $(this).val(); // Get the current value of the search input
            table.search(searchValue).draw(); // Apply the new search value to the DataTable
        });
    });
	</script>
	
</body>
</html>