<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>


<%
Collection<?> products = (Collection<?>) request.getAttribute("products");
// Collection<?> products1 = (Collection<?>)request.getAttribute("login1");
%>



<!DOCTYPE html>
<html lang="en">
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
	function validateForm() {
		let a = document.forms["myForm"].value;
		alert("Logout eseguito con successo");

	}
</script>

<script>
	$(document).ready(function() {
		$("button").click(function() {
			$.ajax({
				url : "informazioni.html",
				success : function(result) {
					$("#div1").html(result);
				}
			});
		});
	});
</script>



<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- font awesome cdn link  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<!-- custom css file link  -->
<link rel="stylesheet" type="text/css" href="style.css">

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
					</form>

				</li>

			</ul>
		</div>
	</nav>

	<%
	String x = (String) request.getParameter("ciao");
	if (products != null && products.size() > 0) {

		Iterator<?> it = products.iterator();
		while (it.hasNext()) {
			ProductVeicolo bean = (ProductVeicolo) it.next();
			if (x.equals(bean.getModello())) {
		System.out.println(bean.getBase64Image());
	%>



	<div class="box-container">

		<div class="box">

			<div class="image-container">

				<div class="big-image">
					<img src="<%=bean.getPhoto()%>" alt="">
				</div>

				<div class="small-image">
					<img class="prova"
						src="<%=bean.getPhoto()%>" alt="">
				</div>

			</div>

			<div class="content">

				<h3 class="title"><%=bean.getMarchio()%>
					<%=bean.getModello()%></h3>
				<div class="price">
					&euro;<%=bean.getPrezzo()%></div>
				<p></p>

				<form action="">

					<div class="dropDown">
						<span> Colore : </span> <select name="" id="" disabled>
							<option value="small"><%=bean.getColore()%></option>
						</select> <span> KW : </span> <select name="" id="" disabled>
							<option value="small"><%=bean.getKw()%></option>
						</select>

						<%
						if (bean.getSconto() != null) {
						%>

						<span> Numero passegeri : </span> <select name="" id="" disabled>
							<option value="small"><%=bean.getNumero_passegeri()%></option>
						</select> <span> Sconto : </span> <select name="" id="" disabled>
							<option value="small"><%=bean.getSconto()%></option>
						</select>
						<%
						}
						%>

						<%
						if (bean.getSconto() == null) {
						%>

						<span> Accessori : </span> <select name="" id="" disabled>
							<option value="small"><%=bean.getAccessori()%></option>
						</select> <span> Custom : </span> <select name="" id="" disabled>
							<option value="small"><%=bean.getCustom()%></option>
						</select>
						<%
						}
						%>

					</div>

				</form>

				<br /> <br>
				<button class="btn">Informazioni Acquisto</button>
				<div id="div1">
					<h2></h2>
				</div>


			</div>

		</div>

	</div>

	<%
	}
	}
	}
	%>

</body>
</html>