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
	<title>Modifier un incident</title>
 	<!-- ======= Styles ====== -->
 	<link rel="stylesheet" href="assets/css/crud.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
<div class="container">
        <%@include file="header.jsp"  %>  
         <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                
                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div>
            </div>
            <!-- ================ Order Details List ================= -->
            <div class="details1">
                <div class="recentOrders1" style="">
                    <div class="cardHeader">
                        <h2>Modifier</h2>
                        <a href="incidents" class="btn">Retour</a>
                    </div>

					<div class="register-form2" >
			        <form method="post" action="updateincident?scroll=${report.id}"">
			        	<div class="input-div">
				            <label for="name" >Type:</label>
				            <input type="text" name="name" id="name" required="required" value="${report.name}" readonly  />
				        </div>
				
			            <div class="input-div">
				            <label for="latitude" >Latitude:</label>
				            <input type="text" name="latitude" id="latitude" required="required" value="${report.latitude}" readonly  />
				        </div>
				
				        <div class="input-div">
				            <label for="longitude" >Longitude:</label>
				            <input type="text" name="longitude" id="longitude" required="required" value="${report.longitude}" readonly  />
				        </div>
				
				        <div class="input-div">
				            <label for="status" >Status:</label>
				            <input type="text" name="status" id="status" required="required" value="${report.status}" readonly  />
				        </div>
				        
						<div class="input-div1">
						    <label for="option1" >NON-TRAITE:</label>
						    <input type="radio" id="option1" name="status1" value="NON_TRAITE" ${report.status eq 'NON_TRAITE' ? 'checked' : ''} />
						
						    <label for="option2" >TRAITE:</label>
						    <input type="radio" id="option2" name="status1" value="TRAITE" ${report.status eq 'TRAITE' ? 'checked' : ''} />
						</div>


			        
			        <!--  <button type="submit" name="signup" id="signup">Register</button>-->
			        <br>
			            <div class="buttonRR">
							<input type="submit" name="update" id="update" value="Modifier" style="" />
						</div>
			        </form>
			        </div>
			        
                    
                </div>
               </div>
               
               
               
    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
   
   			<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<%
   // Récupérer l'ID de la requête
   String idFromServlet = (String)request.getAttribute("incidentId");
   // Stocker l'ID en tant que variable JavaScript
   %>
<script type="text/javascript">
	var idFromServlet = '<%= idFromServlet %>';
 var status=document.getElementById("status").value;
 if (status === "Update successful") {
     swal("Félicitations", "Compte créé avec succès", "success");
 } else if (status === "invalideStatus") {
     swal("Désolé", "Veuillez entrer votre adresse e-mail", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         // Rediriger vers la page "updateuser?scroll=id"
         window.location.href = "updateincident?scroll=" + idFromServlet;
     });
 }
  
</script> 
	
</body>
</html>