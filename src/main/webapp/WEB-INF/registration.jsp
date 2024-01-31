<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>S'inscrire</title>
    <link rel="stylesheet" href="css/Style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
<div class="container">
    <div class="login-image"></div>   
    <div class="register-form">
        <div class="ico"><img src="/images/ico.png" alt=""></div>
        <h2>Rejoignez-nous !</h2>
        <form method="post" action="Register_Page">
            <input type="text" name="nom" id="nom" required="required" placeholder="Votre nom de famille" />
            <input type="text" name="prenom" id="prenom" required="required" placeholder="Votre pr�nom" />
            <input type="text" name="name" id="name" required="required" placeholder="Votre nom d'utilisateur" />
            <input type="email" name="email" id="email" required="required" placeholder="Votre adresse e-mail" />
            <input type="password" name="pass" id="pass" required="required" placeholder="Mot de passe" />
            <input type="password" name="re_pass" required="required" id="re_pass" placeholder="R�p�ter votre mot de passe"  />
            <input type="text" name="Birthday" id="Birthday" placeholder="AAAA-MM-JJ" pattern="\d{4}-\d{2}-\d{2}" required="required"  />
            <input type="text" name="contact" id="contact" placeholder="Num�ro de contact" required="required"  />
            
            <!--  <button type="submit" name="signup" id="signup">Register</button>-->
            <div class="buttonR">
				<input type="submit" name="signup" id="signup" value="S'inscrire" />
			</div>
        </form>
        <a href="Login_Page">Vous avez d�j� un compte ? <b>Connectez-vous</b></a>
    </div>
</div>
</body>
</html>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
 var status=document.getElementById("status").value;
 if (status === "success") {
     swal("F�licitations", "Compte cr�� avec succ�s", "success");
 } else if (status === "invalideEmail") {
     swal("D�sol�", "Veuillez entrer votre adresse e-mail", "error");
 } else if (status === "invalidePass") {
     swal("D�sol�", "Veuillez entrer un mot de passe", "error");
 } else if (status === "invalideconfirmepassword") {
     swal("D�sol�", "Les mots de passe ne correspondent pas", "error");
 } else if (status === "invalideUsername") {
     swal("D�sol�", "Veuillez entrer un nom d'utilisateur", "error");
 } else if (status === "invalideMobile") {
     swal("D�sol�", "Veuillez entrer un num�ro de t�l�phone", "error");
 } else if (status === "invalideMobileLength") {
     swal("D�sol�", "Le num�ro de t�l�phone doit comporter 10 chiffres", "error");
 } else if (status === "Invalide name") {
	    swal("D�sol�", "Veuillez entrer un nom valide", "error");
 } else if (status === "Invalide surname") {
     swal("D�sol�", "Veuillez entrer un pr�nom valide", "error");
 }else if (status === "invalidepassLength") {
     swal("D�sol�", "Le mot de passe doit contenir au moins 8 caract�res.", "error");
 }else if (status === "invalidPasswordFormat") {
     swal("D�sol�", "Le mot de passe doit contenir au moins 6 caract�res ainsi qu une combinaison de chiffres, de lettres et de caract�res sp�ciaux ( !$@%).", "error");
 }
</script>
