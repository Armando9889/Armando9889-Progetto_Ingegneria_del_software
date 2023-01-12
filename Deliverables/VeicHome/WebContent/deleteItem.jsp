<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>

<%
Collection<?> products = (Collection<?>) request.getAttribute("products");
%>



<!doctype html>
<html lang="en">
<head>

<script type="text/javascript">
	function validateForm() {
		let a = document.forms["myForm"].value;
		alert("Logout eseguito con successo");

	}
</script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="adminHomeUfficiale.css" type="text/css">
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>


<title>VeicHome-Vendita di veicoli nuovi e usati</title>
</head>
<body>




	<nav class="navbar navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="adminHome.jsp"><img
				src="images/logo.png" alt="" width="30" height="24"
				class="d-inline-block align-text-top"><input type="submit"
				value="VeicHome"
				style="border: none; outline: none; background-color: transparent;">

			</a>
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<form action="Logout" name="myForm"
						onsubmit="return validateForm()">
						<input
							style="outline: trasparent; border: none; background-color: rgba(248, 249, 251, 255); color: #0d6efd"
							id="ok" type="submit" name="ok" value="Logout"></a>
					</form> <!--  <a class="nav-link active" aria-current="page" href="Logout">Logout</a>-->
				</li>
			</ul>
		</div>
	</nav>


	<div class="wrapper">
		<div class="sidebar">

			<h2>ADMIN</h2>

			<ul>
				<li><a href="InserimentoAuto.jsp"><i class="fas fa-car">
							Inserisci Auto</i></a></li>
				<li><a href="inserimentoMoto.jsp"><i
						class="fas fa-motorcycle"> Inserisci Moto</i></a></li>
				<li><a href="autoStampaSoloAutoAdmin"><i
						class="fas fa-print"> Stampa Auto</i></a></li>
				<li><a href="motoStampaSoloMotoAdmin"><i
						class="fas fa-print"> Stampa Moto</i></a></li>
				<li><a href="StampaOrdini"><i class="fas fa-print">
							Stampa ordini</i></a></li>


			</ul>
		</div>

		<div class="main_content">
				<div class="header"
					style="background-color: rgba(255, 255, 255, 0.05);">
					<h2 style="outline: none; border: none; background-color: rgba(255, 255, 255, 0.05);">ELIMINAZIONE VEICOLO</h2>
				</div>
			<div class="info">

				<div>
					<br />
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">CODICE TELAIO</th>
								<th scope="col">MARCHIO</th>
								<th scope="col">MODELLO</th>
								<th scope="col">IMMAGINE</th>
								<th scope="col">REMOVE</th>
							</tr>
						</thead>

						<%
							if (products != null && products.size() > 0) {
						
								Iterator<?> it = products.iterator();
								while (it.hasNext()) {
									ProductVeicolo bean = (ProductVeicolo) it.next();
						%>


						<tbody>
								<form action="EliminaVeicolo" method="post">
							<tr>
								<th scope="row"><%=bean.getTarga()%></th>
								<td><%=bean.getCodice_telaio()%></td>
								<td><%=bean.getMarchio()%></td>
								<td><%=bean.getModello()%></td>
								<td><img src="<%=bean.getPhoto()%>" style="width: 80px; right: 80px;"></td>
								<input type="hidden" name="targa" value="<%=bean.getTarga()%>">
								<input type="hidden" name="telaio" value="<%=bean.getCodice_telaio()%>">
								<td><input type="image" src="images/cestino.png" alt="Submit" width="35" height="35"></td>
								
							</tr>
							</form>
							
							<%
							}
							}
							%>

						</tbody>
					</table>

				</div>

			</div>
		</div>
	</div>

</body>
</html>