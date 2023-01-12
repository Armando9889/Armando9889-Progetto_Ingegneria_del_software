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

	function acquisto() {
		let saldo = parseInt(document.forms["myFormAcquisto"]["prezzoSessione"].value);
		let prezzo = parseInt(document.forms["myFormAcquisto"]["prezzo"].value);
		if(prezzo > saldo){
			alert("Saldo non sufficiente per effetuare l'acquisto");
			return false;
		}else {
			alert("Grazie per l'acquisto");
		}
			
	}

</script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="VisualizzaCarrello.css" type="text/css">

<title>VeicHome-Vendita di veicoli nuovi e usati</title>
</head>
<body>

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
					int saldoSessione = (int) session.getAttribute("saldo");
				System.out.println("Ciao");
				%>
				<div class="nav-link active" aria-current="page">
					<%
					if (saldoSessione == 0) {
					%>
					<a href="addCredito" style="text-decoration: none;"><img
						src="images/portafoglioSenzaSoldi.png"
						style="height: 25px; width: 25px;"> &nbsp<%=saldoSessione%>&#8364</a>
					<%
					} else {
					%>
					<a href="addCredito" style="text-decoration: none;"><img
						src="images/portafoglioSoldi.png"
						style="height: 25px; width: 25px;"> &nbsp<%=saldoSessione%>&#8364</a>
					<%
					}
					%>
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
			</ul>
		</div>
	</nav>



	<table>
		<tr>
			<th>Prodotto</th>
			<th id="ok" style="padding-left: 850px;">Quantit&#224;</th>
			<th>Totale</th>
		</tr>
	</table>

	<%
	Collection<?> products = (Collection<?>) request.getAttribute("carrello");
	Collection<?> products3 = (Collection<?>) request.getAttribute("products");
	%>


	<%
	String idsession = session.getId();

	ProductCarrello bean = new ProductCarrello();
	if (products != null && products.size() > 0) {
		Iterator<?> it = products.iterator();
		
		while (it.hasNext()) {
			bean = (ProductCarrello) it.next();

			if (bean.getSessionid().equals(idsession)||(bean.getutenteid()!=null&&cf!=null&&bean.getutenteid().equals(cf))) {

		if (products3 != null && products3.size() > 0) {

			Iterator<?> it2 = products3.iterator();
			while (it2.hasNext()) {
				ProductVeicolo bean2 = (ProductVeicolo) it2.next();
				if (bean2.getModello().equals(bean.getModello())) {
	%>

	<div class="small-container cart-page">
		<table>
			<tr>
				<td>
					<div class="cart-info">
						<img src="<%=bean2.getPhoto()%>">
						<%
						}
						}
						}
						%>
						<div>
							<p><%=bean.getModello()%></p>
							<small>Prezzo:&euro;<%=bean.getPrezzo()%></small> <br> <small>Pagamento
								alla consegna</small> <br>
						</div>
					</div>

				</td>
				<td><input type="number" value="1" disabled></td>
				<td>&euro;<%=bean.getPrezzo()%></td>
			</tr>
		</table>
	</div>


	<%
	String cf1 = (String) session.getAttribute("cf");
	if (cf1 == null) {
	%>
	<%
	} else { 
		int saldoSessione = (int) session.getAttribute("saldo");
		System.out.println("Saldo:" + saldoSessione + " " + "Prezzo:" + bean.getPrezzo());
	%>

	<form action="Acquisto" method="POST" name="myFormAcquisto"
		onsubmit=" return acquisto()">

		<input type="text" name="targa" id="tar" value=<%=bean.getTarga()%>>

		<!--  <input type="text" name="codice_fiscale" id="cod" value=""> -->
		
		<input type="hidden" name="prezzoSessione" value="<%=saldoSessione%>">
		<input type="hidden" name="prezzo" value="<%=bean.getPrezzo()%>">
		<input type="hidden" name="idCarrello" value="<%=bean.getIdCarrello()%>">

		<br> <input id="ok" type="submit" name="ok" value="Acquista">

	</form>


	<%
	}
	}
	}
	}
	%>

	<form action="EliminaCarrello">
		<input id="ok" type="submit" name="ok" value="Svuota carrello">
	</form>

</body>
</html>