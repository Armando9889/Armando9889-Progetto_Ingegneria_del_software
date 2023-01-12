<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,it.unisa.model.bean.*,it.unisa.control.*"%>

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

<title>Ordini</title>
</head>
<body>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>


	<nav class="navbar navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="adminHome.jsp"><img
				src="images/logo.png" alt="" width="30" height="24"
				class="d-inline-block align-text-top"> VeicHome </a>
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


	<%
	Collection<?> products3 = (Collection<?>) request.getAttribute("lista1");
	%>

	<%
	Collection<?> products = (Collection<?>) request.getAttribute("cliente1");
	Collection<?> products4 = (Collection<?>) request.getAttribute("products");
	%>

	<%
	ProductAcquisto ca = new ProductAcquisto();

	if (products3 != null && products3.size() > 0) {

		Iterator<?> it = products3.iterator();
		while (it.hasNext()) {
			ProductAcquisto bean = (ProductAcquisto) it.next();
	%>
	<%
	if (products != null && products.size() > 0) {

		Iterator<?> it1 = products.iterator();
		while (it1.hasNext()) {
			ProductCliente bean1 = (ProductCliente) it1.next();
			if (bean1.getCodice_fiscale().equals(bean.getCodice_fiscaleFK())) {
	%>

	<%
	if (products4 != null && products4.size() > 0) {

		Iterator<?> it2 = products4.iterator();
		while (it2.hasNext()) {
			ProductVeicolo bean2 = (ProductVeicolo) it2.next();
			if (bean.getTargaFK().equals(bean2.getTarga())) {
	%>
	
	<table class="table table-sm">
		<thead>
			<tr>
				<th scope="col">MODELLO</th>
				<th scope="col">PREZZO</th>
				<th scope="col">Codice fiscale</th>
				<th scope="col">NOME</th>
				<th scope="col">COGNOME</th>
				<th scope="col">INDIRIZZO</th>
				<th scope="col">IMMAGINE</th>

			</tr>
		</thead>

		<tbody>
			<tr>
				<td><%=bean2.getModello()%></td>
				<td><%=bean2.getPrezzo()%></td>
				<td><%=bean.getCodice_fiscaleFK()%></td>
				<th scope="row"><%=bean1.getNome()%></th>
				<td><%=bean1.getCognome()%></td>
				<td><%=bean1.getIndirizzo()%></td>

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
	}
	}
	%>
	
	<form action="EliminaAcquisti" method="post">
		<input id="elimina" type="submit" value="Svuota db acquisti">
	</form>



</body>
</html>