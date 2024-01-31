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
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Notifications </title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="assets/css/style.css">
    
    <style>
        /* Hide the default DataTables search input */
        div.dataTables_wrapper div.dataTables_filter {
            display: none;
        }
        
        .modal {
		  display: none;
		  position: fixed;
		  z-index: 1;
		  left: 0;
		  top: 0;
		  width: 100%;
		  height: 100%;
		  overflow: auto;
		  background-color: rgba(0, 0, 0, 0.5);
		}
		
		.modal-content {
		  background-color: #fefefe;
		  margin: 15% auto;
		  padding: 20px;
		  border: 1px solid #888;
		  width: 80%;
		}
		
		.close {
		  color: #aaa;
		  float: right;
		  font-size: 28px;
		  font-weight: bold;
		}
		
		.close:hover,
		.close:focus {
		  color: black;
		  text-decoration: none;
		  cursor: pointer;
		}
    </style>
    
     	<!-- jQuery -->
		<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
		    
		    <!-- Styles DataTables -->
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
		
		<!-- Scripts DataTables -->
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
</head>

<body>
    <!-- =============== Navigation ================ -->
     <%@include file="header.jsp"  %>  
        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <!--  <div class="search">
                    <label>
                        <input type="text" placeholder="Search here">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>-->

                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div>
            </div>

            <!-- ======================= Cards ================== -->
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers">${Nombre}</div>
                        <div class="cardName"></div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="sunny-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">${nbr}</div>
                        <div class="cardName"></div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cloud-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers"></div>
                        <div class="cardName"></div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="rainy-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers"></div>
                        <div class="cardName"></div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="thunderstorm-outline"></ion-icon>
                    </div>
                </div>
            </div>

            <!-- ================ Order Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Notifications</h2>
                        <div class="search">
			                 <form id="searchForm">
		                    <label>
		                        <input type="text" id="searchIn" placeholder="Search here">
		                        <ion-icon name="search-outline" ></ion-icon>
		                    </label>
		    				</form>
		                </div>
                    </div>

                    <table id="example" class="no-datatables-style">
                        <thead>
                            <tr>
                                <td>Email d'utilisateur</td>
                                <td>Nom de l'incident</td>
                                <td>Distance</td>
                                <td>Date de l'incident</td>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="nf" items="${notifliste}">
                            <tr>
                                <td>${nf.user != null ? nf.user.useremail : 'N/A'}</td>
                                <td>${nf.reportForm != null ? nf.reportForm.name : ''}</td>
                                <td>${nf.distance != null ? nf.distance  : 'N/A'} Km</td>
                                <td><span class="time delivered">${nf.dateOfIncident}</span></td>
                            </tr>
						</c:forEach>
                           

                        </tbody>
                    </table>
                </div>

                <!-- ================= New Customers ================ -->
                <div class="recentCustomers">
                    <div class="cardHeader">
                        <h2>Récents Notifications</h2>
                    </div>

                    <table>
                    <c:forEach var="nf" items="${notifliste7}">
                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="assets/imgs/customer02.jpg" alt=""></div>
                            </td>
                            <td>
                                <h4>${nf.user != null ? nf.user.useremail : 'N/A'} <br> <span>${nf.reportForm != null ? nf.reportForm.name : ''}</span><br> <span>${nf.distance != null ? nf.distance  : 'N/A'} Km</span></h4>
                            </td>
                        </tr>
					</c:forEach>
                        
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
    <script>
    var table; // Déclarer la variable à l'extérieur de la fonction ready

    $(document).ready(function() {
        // Initialiser le DataTable
        table = $('#example').DataTable({
            searching: true, 
            lengthChange: false,
            bJQueryUI: false,
            ordering: false
        });
        
        
     // Add an event listener to the search input
        $('#searchIn').on('input', function() {
            var searchValue = $(this).val(); // Get the current value of the search input
            table.search(searchValue).draw(); // Apply the new search value to the DataTable
        });
    });
	</script>
</body>

</html>