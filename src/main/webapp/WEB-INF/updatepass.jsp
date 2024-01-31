<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<html lang="fr">
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Changer de mot de passe</title>
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
                        <h2>Changer de mot de passe</h2><br>
                        <a href="admin" class="btn">Retour</a>
                    </div>
                    <div style="max-width: 90%; overflow-y: auto;">  
                    <p> Votre mot de passe doit contenir au moins 6 caractères ainsi qu’une combinaison de chiffres, 
                    de lettres et de caractères spéciaux ( !$@%).</p>
                    </div>

					<div class="register-form2" >
				        <form method="post" action="updatepass">
				        	<div class="input-div">
					            <label for="name" >Mot de passe actuel</label>
					            <input type="password" name="oldpass" id="name" required="required"   />
					        </div>
					
				            <div class="input-div">
					            <label for="latitude" >Nouveau mot de passe</label>
					            <input type="password" name="newpass" id="latitude" required="required" />
					        </div>
					
					        <div class="input-div">
					            <label for="longitude" >Retapez le nouveau mot de passe</label>
					            <input type="password" name="repass" id="longitude" required="required"  />
					        </div>
					
				        <br>
				        	<a href="resetpass"><div style="margin-left:33%; color:blue;">Mot de passe oublié?</div></a>
				        	
				            <div class="buttonRR" style="margin-top:3%">
								<input type="submit" name="update" id="update" value="Changer le mot de passe" style="" />
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
 if (status === "succes") {
     swal("Félicitations", "mot de passe changé avec succès", "success").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");    
         // Rediriger vers la page "updatepass?us=id"
         window.location.href = "admin";
    }); 
 } else if (status === "invalideOldPass") {
     swal("Désolé", "Le mot de passe entré est incorrect. Veuillez réessayer.", "error").then(function() {
         // Événement à exécuter après le clic sur le bouton "OK"
         console.log("Bouton OK cliqué !");    
         // Rediriger vers la page "updatepass?us=id"
         window.location.href = "updatepass?us=" + idFromServlet;
    }); 
 } else if (status === "invalidePass") {
    swal("Désolé", "Veuillez entrer un mot de passe", "error").then(function() {
        // Événement à exécuter après le clic sur le bouton "OK"
        console.log("Bouton OK cliqué !");    
        // Rediriger vers la page "updatepass?us=id"
        window.location.href = "updatepass?us=" + idFromServlet;
   }); 
} else if (status === "invalideconfirmepassword") {
    swal("Désolé", "Les mots de passe ne correspondent pas", "error").then(function() {
        // Événement à exécuter après le clic sur le bouton "OK"
        console.log("Bouton OK cliqué !");    
        // Rediriger vers la page "updatepass?us=id"
        window.location.href = "updatepass?us=" + idFromServlet;
   }); 
} else if (status === "invalidepassLength") {
    swal("Désolé", "Le mot de passe doit contenir au moins 6 caractères ainsi qu’une combinaison de chiffres, de lettres et de caractères spéciaux ( !$@%).", "error").then(function() {
        // Événement à exécuter après le clic sur le bouton "OK"
        console.log("Bouton OK cliqué !");    
        // Rediriger vers la page "updatepass?us=id"
        window.location.href = "updatepass?us=" + idFromServlet;
   }); 
}else if (status === "invalidPasswordFormat") {
    swal("Désolé", "Le mot de passe doit contenir au moins 6 caractères ainsi qu une combinaison de chiffres, de lettres et de caractères spéciaux ( !$@%).", "error").then(function() {
        // Événement à exécuter après le clic sur le bouton "OK"
        console.log("Bouton OK cliqué !");    
        // Rediriger vers la page "updatepass?us=id"
        window.location.href = "updatepass?us=" + idFromServlet;
   }); 
}
</script>
</body>
</html>