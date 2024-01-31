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
	<title>Modifier un utilisateur</title>
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
                        <a href="customers" class="btn">Retour</a>
                    </div>
					<br>
					<div class="register-form2">
			        <form method="post" action="updateuser?scroll=${user.id}">
			        	<div class="input-div">
			            <input type="text" name="nom" id="nom" required="required" placeholder="Your Last Name" value="${users.nom}" />
			            </div>
			            <div class="input-div">
					    <input type="text" name="prenom" id="prenom" required="required" placeholder="Your First Name" value="${users.prenom}" />
					    </div>
					    <div class="input-div">
					    <input type="text" name="name" id="name" required="required" placeholder="Your UserName" value="${users.username}" />
					    </div>
					    <div class="input-div">
					    <input type="email" name="email" id="email" required="required" placeholder="Your Email" value="${users.useremail}" />
					    </div>
					    <div class="input-div">
					    <input type="text" name="Birthday" id="Birthday" placeholder="YYYY-MM-DD" pattern="\d{4}-\d{2}-\d{2}" required="required" value="${users.userbirth}" />
					    </div>
					    <div class="input-div">
					    <input type="text" name="contact" id="contact" placeholder="Contact number" required="required" value="${users.usermobile}" />
    					</div> 
			            <!--  <button type="submit" name="signup" id="signup">Register</button>-->
			            <div class="buttonRR">
							<input type="submit" name="signup" id="signup" value="Modifier" />
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
   String idFromServlet = (String)request.getAttribute("userId");
   // Stocker l'ID en tant que variable JavaScript
   %>
<script type="text/javascript">
	var idFromServlet = '<%= idFromServlet %>';
 var status=document.getElementById("status").value;
 if (status === "success") {
     swal("Félicitations", "Compte créé avec succès", "success");
 } else if (status === "invalideEmail") {
     swal("Désolé", "Veuillez entrer votre adresse e-mail", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         // Rediriger vers la page "updateuser?scroll=id"
         window.location.href = "updateuser?scroll=" + idFromServlet;
     });
 } else if (status === "invalideUsername") {
     swal("Désolé", "Veuillez entrer un nom d'utilisateur", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         // Rediriger vers la page "updateuser?scroll=id"
         window.location.href = "updateuser?scroll=" + idFromServlet;
     });
 } else if (status === "invalideMobile") {
     swal("Désolé", "Veuillez entrer un numéro de téléphone", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         // Rediriger vers la page "updateuser?scroll=id"
         window.location.href = "updateuser?scroll=" + idFromServlet;
     });
 } else if (status === "invalideMobileLength") {
     swal("Désolé", "Le numéro de téléphone doit comporter 10 chiffres", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         // Rediriger vers la page "updateuser?scroll=id"
         window.location.href = "updateuser?scroll=" + idFromServlet;
     });
 } else if (status === "Invalide name") {
	    swal("Désolé", "Veuillez entrer un nom valide", "error").then(function() {
	         // Événement à exécuter après le clic sur le bouton "OK"
	         console.log("Bouton OK cliqué !");
	         
	         // Rediriger vers la page "updateuser?scroll=id"
	         window.location.href = "updateuser?scroll=" + idFromServlet;
	     });
 } else if (status === "Invalide surname") {
     swal("Désolé", "Veuillez entrer un prénom valide", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");
         
         // Rediriger vers la page "updateuser?scroll=id"
         window.location.href = "updateuser?scroll=" + idFromServlet;
     });
 }
</script>
</body>
</html>