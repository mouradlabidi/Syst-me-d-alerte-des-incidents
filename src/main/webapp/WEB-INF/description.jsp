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
	<title>Consulter un incident</title>
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

                 
                <div class="cardHeader">
                    <a class="btn" href="incidents">Retour</a>
                </div>
            </div>
            <!-- ================ Order Details List ================= -->
            <div class="details1">
                <div class="recentOrders1" style="">
                    <div class="cardHeader">
                        <h2>Consulter</h2>
                        <a class="btn" href="updateincident?scroll=${report.id}">Modifier</a>
                    </div>
					<br>
					<div class="register-form2">
						<div class="incident-container" style="text-align: center; display: flex;flex-direction: column; align-items: center; ">
							<p style="font-size: 24px;  font-weight: bold;  color: #333; margin-bottom: 1%;">${report.name}</p>
					        <img style="width: 100%; height: auto;" src="${report.image}">
					        
							 <div class="description-container" style="text-align:left; border: 1px solid #ddd; padding: 1%; margin-top: 1%; width: 100%;">
							        <label style="font-weight: bold; margin-bottom: 4%">Description:</label>
							        <p class="description" style="text-align:left; margin-top: 1%; margin-left:5%">${report.description}</p>
							    </div>
					     </div>    
			        </div>
			        
                    
                </div>
               </div>
               
               
               
    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    

</body>
</html>