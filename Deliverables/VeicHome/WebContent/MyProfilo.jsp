<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>

<%
Collection<?> products = (Collection<?>) request.getAttribute("cliente");
Collection<?> products3 = (Collection<?>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html>
<head>

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
			<form action="prova" method="post">
				<a class="navbar-brand"><img src="images/logo.png" alt=""
					width="30" height="24" class="d-inline-block align-text-top"><input
					type="submit" value="VeicHome"
					style="border: none; outline: none; background-color: transparent;">

				</a>
			</form>
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<form action="Logout" name="myForm"
						onsubmit="return validateForm()">
						<a class="nav-link active" aria-current="page"
							href="homeUfficiale.jsp"><input
							style="outline: trasparent; border: none; background-color: rgba(248, 249, 251, 255); color: #0d6efd"
							id="ok" type="submit" name="ok" value="Logout"></a>
					</form> <!--  <a class="nav-link active" aria-current="page" href="Logout">Logout</a>-->
				</li>
			</ul>
		</div>
	</nav>

	<div class="wrapper">
		<div class="sidebar">
			<h2>
				<form action="StampaInfo" method="post">
					<input
						style="color: #fff; background-color: transparent; outline: none; border: none; font-size: 19px;"
						id="elimina" type="submit" value="INFO UTENTE"
						>
				</form>
			</h2>
			<br>
			<h2>
				<form action="Lista" method="post">
					<input
						style="color: #fff; background-color: transparent; outline: none; border: none; font-size: 19px;"
						id="elimina" type="submit" value="I MIEI ORDINI"
						>
				</form>
			</h2>
						<br>
			<h2>
				<a href="editProfilo.jsp" style="color: #fff; background-color: transparent; outline: none; border: none; font-size: 19px; text-decoration: none;" >MODIFICA PROFILO</a>
	
			</h2>

		</div>


		<div class="main_content">
			<div class="header"
				style="background-color: rgba(255, 255, 255, 0.05);">
				<h2
					style="outline: none; border: none; background-color: rgba(255, 255, 255, 0.05); color: black;">MIO
					PROFILO</h2>
			</div>

			<div class="info">

				<div>
					<br />

					<%
					String cf = (String) session.getAttribute("cf");
					%>
					<%
					ProductAcquisto ca = new ProductAcquisto();

					if (products != null && products.size() > 0) {

						Iterator<?> it = products.iterator();
						while (it.hasNext()) {
							ProductCliente bean = (ProductCliente) it.next();
							if (bean.getCodice_fiscale().equals(cf)) {
					%>


					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col">NOME</th>
								<th scope="col">COGNOME</th>
								<th scope="col">DATA DI NASCITA</th>
								<th scope="col">INDIRIZZO</th>
								<th scope="col">CODICE FISCALE</th>
								<th scope="col">SESSO</th>
								<th scope="col">NUMERO DI CARTA</th>
								<th scope="col">DATA DI SCADENZA</th>
								<th scope="col">CVV</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row"><%=bean.getNome()%></th>
								<td><%=bean.getCognome()%></td>
								<td><%=bean.getData_di_nascita()%></td>
								<td><%=bean.getIndirizzo()%></td>
								<td><%=bean.getCodice_fiscale()%></td>
								<td><%=bean.getSesso()%></td>
								<td><%=bean.getNumero_di_carta()%></td>
								<td><%=bean.getData_scadenza()%></td>
								<td><%=bean.getCvv()%></td>
							</tr>
						</tbody>
					</table>

					<%
					}
					}
					}
					%>


					<%
					Collection<?> products4 = (Collection<?>) request.getAttribute("products");
					%>

					<%
					String cf1 = (String) session.getAttribute("cf");
					%>

					<%
					if (products3 != null && products3.size() > 0 ) {

						Iterator<?> it = products3.iterator();
						while (it.hasNext()) {
							ProductAcquisto bean = (ProductAcquisto) it.next();
							if (bean.getCodice_fiscaleFK().equals(cf1)) {
					%>

					<%
					if (products4 != null && products4.size() > 0) {
						Iterator<?> it2 = products4.iterator();
						while (it2.hasNext()) {
							ProductVeicolo bean2 = (ProductVeicolo) it2.next();
							if (bean2.getTarga().equals(bean.getTargaFK())) {
					%>

					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col">MODELLO</th>
								<th scope="col">PREZZO</th>
								<th scope="col">DATA DI ACQUISTO</th>
								<%if(bean2.getSconto() != null) { %>
								<th scope="col">NUMERO PASSEGERI</th>
								<% } %>
								<%if(bean2.getSconto() == null) { %>
								<th scope="col">ACCESSORI</th>
								<th scope="col">CUSTOM</th>
								<% } %>
								<th scope="col">IMMAGINE</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td><%=bean2.getModello()%></td>
								<td><%=bean2.getPrezzo()%></td>
								<td><%=bean.getData_di_acquisto()%></td>
								<%if(bean2.getSconto() != null) { %>
								<td><%=bean2.getNumero_passegeri()%></td>
								<% } %>
								<%if(bean2.getSconto() == null) { %>
								<td><%=bean2.getAccessori()%></td>
								<td><%=bean2.getCustom()%></td>
								<% } %>
								<td><img
									src="<%=bean2.getPhoto()%>"
									style="width: 80px; right: 80px;"></td>
								<%
								}
								}
								}
								%>
							</tr>

						</tbody>
					</table>

					<%
					}
					}
					}
					%>
					
					
				</div>
			</div>
		</div>
	</div>


</body>
</html>