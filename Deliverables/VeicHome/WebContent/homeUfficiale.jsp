<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,it.unisa.model.bean.*,it.unisa.control.*"%>

<!doctype html>
<html lang="en">
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
	function validateForm() {
		let a = document.forms["myForm"].value;
		alert("Logout eseguito con successo");

	}
</script>



<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="modificaHome.css" type="text/css">

<title>VeicHome-Vendita di veicoli nuovi e usati</title>
</head>
<body>

	<%
	//Collection<?> products1 = (Collection<?>)request.getAttribute("login1");
	%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>


	<nav class="navbar navbar-light bg-light">
		<div class="container-fluid">
			<form action="prova" method="post">
				<a class="navbar-brand"><img src="images/logo.png" alt=""
					width="30" height="24" class="d-inline-block align-text-top"><input
					type="submit" value="VeicHome"
					style="border: none; outline: none; background-color: transparent;">

				</a>
			</form>
			<ul class="nav justify-content-end">
				<li class="nav-item"><a id="prova" class="nav-link active"
					aria-current="page" href="login.jsp">Login</a></li>
				<%
				String cf = (String) session.getAttribute("cf");
				if (cf == null) {
					System.out.println(cf);
				%>
				<%
				} else {
				System.out.println("prova" + cf);

				HttpSession session1 = request.getSession(false);
				String x = (String) session.getAttribute("login");
				int saldoSessione = (int) session.getAttribute("saldo");
				%>
				
				<div class="nav-link active" aria-current="page">
				
					<%if(saldoSessione == 0){ %>
					<a href="addCredito" style="text-decoration: none;"><img src="images/portafoglioSenzaSoldi.png" style="height: 25px; width: 25px;" > &nbsp<%=saldoSessione%>&#8364</a>
					<%}else{ %>
					<a href="addCredito" style="text-decoration: none;"><img src="images/portafoglioSoldi.png" style="height: 25px; width: 25px;" > &nbsp<%=saldoSessione%>&#8364</a>				
					<%}%> 
					
					&nbsp &nbsp Ciao <%=x%>
				</div>
				<style type="text/css">
				
#prova {
	display: none;
	outline: none;
}
</style>
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="MyProfilo.jsp">Mio Profilo</a></li>
				<li class="nav-item">
					<form action="Logout" name="myForm"
						onsubmit="return validateForm()">
						<a class="nav-link active" aria-current="page"
							href="homeUfficiale.jsp"><input
							style="outline: trasparent; border: none; background-color: rgba(248, 249, 251, 255); color: #0d6efd"
							id="ok" type="submit" name="ok" value="Logout"></a>
					</form>
				</li>
				<%
				}
				%>



				<!--  <a class="nav-link active" aria-current="page" href="Logout">Logout</a>-->

				<li class="nav-item"><a id="prova" class="nav-link"
					href="register.jsp">Registrati</a></li>
				<li class="nav-item">
					<form action="StampaCarrello" method="post">
						<a class="nav-link active" aria-current="page"><input
							style="outline: trasparent; border: none; background-color: rgba(248, 249, 251, 255); color: #0d6efd"
							type="submit" value="Carrello"></a>
					</form>
				</li>
			</ul>
		</div>
	</nav>


	<div id="carouselExampleCaptions" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="images/bg1.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h3>Date a un bambino un foglio di carta</h3>

					<!--  <a href="adminHome.jsp">
               <a href="moto.jsp"> -->


					<div class="containerButton">
						<form action="autoStampaSoloAuto" method="post">
							<button class="prova">Auto</button>
						</form>
						<form action="motoStampaSoloMoto" method="post">
							<button class="prova1">Moto</button>
						</form>
					</div>

				</div>
			</div>
			<div class="carousel-item">
				<img src="images/bg2.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h3>dei colori e chiedetegli di disegnare un'automobile</h3>


					<div class="containerButton">
						<form action="autoStampaSoloAuto" method="post">
							<button class="prova">Auto</button>
						</form>
						<form action="motoStampaSoloMoto" method="post">
							<button class="prova1">Moto</button>
						</form>
					</div>



				</div>
			</div>
			<div class="carousel-item">
				<img src="images/bg.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h3>sicuramente la far&#224; rossa</h3>


					<div class="containerButton">
						<form action="autoStampaSoloAuto" method="post">
							<button class="prova">Auto</button>
						</form>
						<form action="motoStampaSoloMoto" method="post">
							<button class="prova1">Moto</button>
						</form>
					</div>



				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>

	</div>


	<button type="button" id="idb" onclick="loadDoc()">SERVIZIO
		CLIENTI</button>
	<br>
	<br>
	<table id="demo"></table>

	<script>
		function loadDoc() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					myFunction(this);
				}
			};
			xhttp.open("GET", "contattaci.xml", true);
			xhttp.send();
		}
		function myFunction(xml) {
			var i;
			var xmlDoc = xml.responseXML;
			var table = "<tr><th>CONTATTI</th> <th>ORARIO</th></tr>";
			var x = xmlDoc.getElementsByTagName("info");
			for (i = 0; i < x.length; i++) {
				table += "<tr><td>"
						+ x[i].getElementsByTagName("contatti")[0].childNodes[0].nodeValue
						+ "</td><td>"
						+ x[i].getElementsByTagName("orario")[0].childNodes[0].nodeValue
						+ "</td></tr>";
			}
			document.getElementById("demo").innerHTML = table;
			$(document).ready(function() {
				$("#idb").remove();
			});
		}
	</script>


</body>
</html>