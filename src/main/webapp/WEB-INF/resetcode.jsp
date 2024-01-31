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
            margin-top:10%;
            padding: 20px;
            box-sizing: border-box;
            background-color: #ffffff; /* Couleur de fond */
    		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Ombre */
        }

        h1 {
        	margin-top: 25px;
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
    
    	
        <h1>Entrez le code de sécurité</h1>
        <hr>
        <form method="post" action="resetcode">
        	
			
            <p>Merci de vérifier dans vos e-mails que vous avez reçu un message avec votre code. Celui-ci est composé de 6 chiffres.</p>
            
            <input type="text" id="code" name="resetcode" required >
			<input type="hidden" id="mail" name="usermail" value="${email}" />	
			

            <button type="submit">Continuer</button>
        </form>
        <hr>
        <div style="text-align:center;">
        <a href="resetpass" style="color:#000000; text-decoration:none;">Code non <b>reçu ?</b></a>
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
	        swal("Erreur", "Le code de sécurité est incorrect", "error");
	    } else if (status === "invalideresetpassword") {
	        swal("Erreur", "Veuillez saisir le code de sécurité", "error");
	    } else if (status === "invalidePass") {
	        swal("Erreur", "Veuillez saisir le mot de passe", "error");
	    }
	</script>
	
</body>
</html>
	