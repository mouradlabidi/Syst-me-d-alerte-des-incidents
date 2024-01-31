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
		
		.container .input-div{
			width: 100%;
            max-width: 400px;
		}

        h1 {
        	margin-top: 25px;
            font-size: 24px;
            margin-bottom: 20px;
        }

		
		.input-div input{
			width: 92%;
			flex: 2; 
			padding: 0.375rem 0.75rem; 
			font-size: 1rem; 
			line-height: 1.5; 
			color: #495057; 
			background-color: #fff; 
			background-clip: padding-box; 
			border: 1px solid #ced4da; 
			border-radius: 0.25rem; 
			transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; 
			margin-bottom: 1rem; 
			display: inline-block;
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
       
      
    </style>
    
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
    <div class="container">
    
    	
        <h1>Choisissez un nouveau mot de passe</h1>
        <hr>
        <form method="post" action="newpassword">
        	
			
            <p>Créez un mot de passe d au moins 6 caractères. Un mot de passe fort est une combinaison de lettres, de chiffres et de signes de ponctuation.</p>
            
            <div class="input-div">
					<input type="password" id="password" name="password" placeholder="Nouveau mot de passe" required >
			</div>
					
			<div class="input-div">
					<input type="password" id="password" name="repassword" placeholder="Retapez le nouveau mot de passe" required >
			</div>
			<input type="hidden" id="mail" name="usermail" value="${email}" />	
			
			<c:if test="${not empty name}">
			<input type="hidden" id="id" name="userid" value="${userid}" />
			</c:if>	
			
            <button type="submit">Continuer</button>
        </form>
        <hr>
        <c:if test="${empty name}">
        <div style="text-align:center;">
        <a href="Login_Page" style="color:#000000; text-decoration:none;">Je me souviens de <b>mon mot de passe ?</b></a>
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
	        swal("Erreur", "failed", "error");
	    } else if (status === "invalideconfirmepassword") {
	        swal("Erreur", "Les mots de passe ne correspondent pas", "error");
	    } else if (status === "invalidePass") {
	        swal("Erreur", "Veuillez saisir le mot de passe", "error");
	    }else if (status === "invalideRePass") {
	        swal("Erreur", "Veuillez saisir le mot de passe de confirmation", "error");
	    }else if (status === "invalidepassLength") {
	        swal("Désolé", "Le mot de passe doit contenir au moins 8 caractères.", "error");
	    }else if (status === "invalidPasswordFormat") {
	        swal("Désolé", "Le mot de passe doit contenir au moins 6 caractères ainsi qu une combinaison de chiffres, de lettres et de caractères spéciaux ( !$@%).", "error");
	    }
	</script>
	
</body>
</html>
	