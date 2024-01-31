<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="css/Style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
<div class="container">
    <div class="login-image"></div>
    
    <div class="login-form">
        <div class="ico"><img src="images/ico.png" alt=""></div>
        <h2>Bienvenue !</h2>
        <p>Veuillez entrer vos informations</p>
        <form method="post" action="Login_Page">
            <input type="text" name="username" id="username" placeholder="Nom d'utilisateur" required="required" />
            <input type="password" name="password" id="password" placeholder="Mot de passe" required="required" />
            <!--<input type="checkbox" value="Remeber for 30 Days">-->
            <button type="submit" name="signin" id="signin">Se connecter</button>
        </form>
        <a href="Searchcpt"><div style="margin-left:3%; color:#000000;">Mot de passe oublié?</div></a>
        <hr>
        <a href="Register_Page">Vous n'avez pas de compte ? <b>Inscrivez-vous</b></a>
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
	        swal("Erreur", "Nom d'utilisateur ou mot de passe incorrect", "error");
	    } else if (status === "invalideUsername") {
	        swal("Erreur", "Veuillez saisir le nom d'utilisateur", "error");
	    } else if (status === "invalidePass") {
	        swal("Erreur", "Veuillez saisir le mot de passe", "error");
	    }
	</script>
	
</body>
</html>
	