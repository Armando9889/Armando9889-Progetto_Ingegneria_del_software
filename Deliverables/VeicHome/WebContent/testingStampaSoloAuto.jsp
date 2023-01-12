<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>

<%
Collection<?> products = (Collection<?>) request.getAttribute("prova");
//Collection<?> products1 = (Collection<?>)request.getAttribute("login1");
%>


<!doctype html>
<html lang="en">
<head>

<script type="text/javascript">
	function validateForm() {
		let a = document.forms["myForm"]["ciao"].value;
		if (a == "") {
			alert("Inserire dati mancanti");
			return false;
		}

	}
</script>


<div class="image"></div>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="StampaAutoMoto.css" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/f">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

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
				<li class="nav-item"><a id="prova" class="nav-link active"
					aria-current="page" href="login.jsp">Login</a></li>
				<%
				String y;

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
					<%if(saldoSessione == 0){ %>
					<a href="addCredito" style="text-decoration: none;"><img src="images/portafoglioSenzaSoldi.png" style="height: 25px; width: 25px;" > &nbsp<%=saldoSessione%>&#8364</a>
					<%}else{ %>
					<a href="addCredito" style="text-decoration: none;"><img src="images/portafoglioSoldi.png" style="height: 25px; width: 25px;" > &nbsp<%=saldoSessione%>&#8364</a>
					<%}%> 
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
					<form action="Logout" name="myForm">
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>


	<%
	if (products != null && products.size() > 0) {

		Iterator<?> it = products.iterator();
		while (it.hasNext()) {
			ProductVeicolo bean = (ProductVeicolo) it.next();
			if (bean.getNumero_passegeri() != 0 && bean.getSconto() != null) {
	%>

	<div class="container">
		<div class="row">
			<!-- Card -->
			<div class="col-sm">
				<div class="card mt-3"
					style="background-color: rgba(214, 214, 214, 0.822); height: 350px; width: 1000px; margin: auto; padding: 10px">
					<div class="product-1 align-items-center p-2 text-center">
						<img src="<%=bean.getPhoto()%>" alt=""
							class="rounded" width="400" height="200">

						<form action="Auto" method="post" name="myForm"
							onsubmit="return validateForm()">
							<input type="hidden" name="ciao" value="<%=bean.getModello()%>">
							<h3 class="text-uppercase"><%=bean.getModello()%></h3>
							<div class="div p-3 ferrari text-center text-white mt-3 cursor">
								<input
									style="outline: none; border: none; background-color: rgb(0, 50, 66); color: white; font-size: 20px;"
									type="submit" value="INFORMAZIONI" class="text-uppercase">
							</div>

						</form>

					</div>


				</div>

			</div>

			<%
			}
			}
			}
			%>
		</div>
	</div>



</body>
</html>











