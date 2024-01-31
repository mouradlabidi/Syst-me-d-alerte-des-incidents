<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compte oubliées !</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #E9EBEE; 
        }

        .container {
            width: 100%;
            max-width: 400px;
            margin: 0 auto;
            margin-top:5%;
            padding: 20px;
            box-sizing: border-box;
            background-color: #ffffff; /* Couleur de fond */
    		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Ombre */
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

       input[type="radio"],
		label {
		    display: inline-block;
		    margin-right: 10px; /* Ajustez la marge entre les boutons radio et les étiquettes selon vos besoins */
		}

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }

        button:hover {
            background-color: #45a049;
        }
        .container .ico{
		  display: inline-block;
		    width: 16%; 
		    height: 17%; 
		    border-radius: 50%; 
		    overflow: hidden;
		    margin: 0 auto; 
		    margin-left: 40%;
		}
		.container .ico img {
		    width: 100%; 
		    height: 100%; 
		    object-fit: cover; 
		}
    </style>
    
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
    <div class="container">
    
    	<div class="ico"><img src="assets/imgs/customer01.jpg" alt=""></div>
    	
        <h1>Réinitialiser votre mot de passe</h1>
        <hr>
        <form method="post" action="resetpass">
        	<c:if test="${empty name}">
            <input type="email" id="email" name="userEmail" value="${users.useremail}" required readonly>
			</c:if>
			<c:if test="${not empty name}">
            <input type="email" id="email" name="userEmail" value="${useremail}" required readonly>
			</c:if>
			
            <p>Comment voulez-vous recevoir votre code de réinitialisation du mot de passe ?</p>
            <input type="radio" id="emailRadio" name="method" value="email" checked>
			<label for="emailRadio">Envoyer le code par e-mail</label><br>
            
            <c:if test="${empty name}">
            <input type="radio" id="phone" name="method" value="phone">
            <label for="phone">Connexion avec votre mot de passe</label><br>
			</c:if>
            <button type="submit">Réinitialiser le mot de passe</button>
        </form>
        <hr>
        <c:if test="${empty name}">
        <div style="text-align:center;">
        <a href="Searchcpt" style="color:#000000; text-decoration:none;">Ce n'est pas <b>vous ?</b></a>
        </div>
        </c:if>	
        <c:if test="${not empty name}">
        <div style="text-align:center;">
        <a href="updatepass?us=${userid}" style="color:#000000; text-decoration:none;">Je me souviens de <b>mon mot de passe ?</b></a>
        </div>
        </c:if>	
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
	