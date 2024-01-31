<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compte oubliées !</title>
    <link rel="stylesheet" href="css/Style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
<div class="container">
    <div class="login-image"></div>
    
    <div class="login-form">
        <div class="ico"><img src="images/ico.png" alt=""></div>
        <h2>Compte oubliées !</h2>
        <p>Trouvez votre compte</p>
        <hr>
        <form method="post" action="Searchcpt">
        	<br>
            <input type="text" name="username" id="username" placeholder="Nom d'utilisateur ou Email" required="required" />
            
            <button type="submit" name="signin" id="signin">Rechercher</button>
        </form>
        
        <hr>
        <a href="Login_Page">Connexion avec votre  <b>mot de passe !</b></a>
    </div>
    
</div>


	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

	<script type="text/javascript">
	 var status=document.getElementById("status").value;
	 if (status === "failed") {
	        swal("Erreur", "Nom d'utilisateur ou l'adresse email incorrect", "error");
	    } else if (status === "invalideusernameOruseremail") {
	        swal("Erreur", "Veuillez saisir le nom d'utilisateur ou l'adresse email", "error");
	    }
	</script>
	
</body>
</html>
	