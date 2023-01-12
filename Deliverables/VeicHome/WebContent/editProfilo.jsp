<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>


<!DOCTYPE html>
<html lang="en">

<head>

<script type="text/javascript">
	function validateForm() {

		let a = document.forms["myForm"]["id"].value;
		let b = document.forms["myForm"]["indirizzo"].value;
		let c = document.forms["myForm"]["numero_di_carta"].value;
		let d = document.forms["myForm"]["cvv"].value;
		let e = document.forms["myForm"]["scadenza"].value;
		let f = document.forms["myForm"]["scadenza_anno"].value;
		
		var lunghezzaStringa = c.length;
		var lunghezzaCVV = d.length;
		
		var espressioneIndirizzo = new RegExp('^[a-z0-9-/ ]+$',"i");
		var espressioneCarta = new RegExp('^[0-9]{16}$');
		 var espressioneCvv = new RegExp('^[0-9]{3}$');
		 
		 if(!espressioneIndirizzo.test(b) && b != ""){
			 alert("Hai inserito dei caratteri non validi.");
			 return false;
		 }
		
		 if(c != "" && d !=""){
			  if(!espressioneCarta.test(c) || !espressioneCvv.test(d) ){
				  alert("Dati errati")
				  return false;
			  }
		 }
		
		
		if(lunghezzaStringa < 16 && lunghezzaCVV < 3 && a == "" && b == "" && c!= "" && d != ""){
			alert("Dati errati");
			return false;
		}
		if(lunghezzaStringa < 16 && lunghezzaCVV < 3 && a != "" && b == "" && c!= "" && d != ""){
			alert("Dati errati");
			return false;
		}
		if(lunghezzaStringa < 16 && lunghezzaCVV < 3 && a == "" && b != "" && c!= "" && d != ""){
			alert("Dati errati");
			return false;
		}
		if(lunghezzaStringa < 16 && lunghezzaCVV < 3 && a != "" && b != "" && c!= "" && d != ""){
			alert("Dati errati");
			return false;
		}
			
		if (c != "" && d == "" && a == "" && b == "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}

		else if (c == "" && d != "" && a == "" && b == "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}

		else if (c != "" && d == "" && a != "" && b == "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}

		else if (c != "" && d == "" && a == "" && b != "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}

		else if (c == "" && d != "" && a != "" && b == "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}

		else if (c == "" && d != "" && a == "" && b != "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}

		else if (c != "" && d == "" && a != "" && b != "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}

		else if (c == "" && d != "" && a != "" && b != "") {
			alert("Inserire carta,cvv e scadenza");
			return false;
		}	
		
		else if (c != "" && d != "" && a == "" && b == "") {
			return true;
		}


		else if (a == "" && b == "") {
			alert("Inserire dati mancanti");
			return false;
		}
	}
</script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- For google icons  -->
<link
	href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined"
	rel="stylesheet">
<link href="editProfilo.css" rel="stylesheet">
<title>Login form</title>

</head>

<body>

	<%
	ProductCliente cl = new ProductCliente();
	String cf = (String) session.getAttribute("cf");
	%>


	<div class="background"></div>
	<div class="container">
		<h2>MODIFICA PROFILO</h2>


		<form action="<%=response.encodeURL("EditProfilo")%>" method="POST"
			name="myForm" onsubmit="return validateForm()">

			<div class="form-item">
				<span class="material-icons-outlined"> account_circle </span> <input
					type="text" name="id" placeholder="username"
					value="<%=cl.getUsername()%>">

			</div>

			<div class="form-item">
				<span class="material-icons-outlined"> account_circle </span> <input
					type="text" name="indirizzo" placeholder="indirizzo"
					value="<%=cl.getIndirizzo()%>">

			</div>

			<div class="form-item">
				<span class="material-icons-outlined"> account_circle </span> <input
					type="text" name="numero_di_carta" placeholder="numero di carta"
					value="<%=cl.getNumero_di_carta()%>">

			</div>

			<div class="form-item">
				<span class="material-icons-outlined"> account_circle </span> <input
					type="text" name="cvv" placeholder="CVV" value="<%=cl.getCvv()%>">

			</div>

			<div class="">
				<span class="material-icons-outlined"> account_circle </span> <select
					name="scadenza"
					style="opacity: 0.5; width: 110px; height: 35px; font-size: 20px; text-align: center;">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select> <select name="scadenza_anno"
					style="opacity: 0.5; width: 110px; height: 35px; font-size: 20px; text-align: center;">
					<option value="2023">2023</option>
					<option value="2024">2024</option>
					<option value="2025">2025</option>
					<option value="2026">2026</option>
					<option value="2027">2027</option>
					<option value="2028">2028</option>
					<option value="2029">2029</option>
					<option value="2030">2029</option>
					<option value="2031">2030</option>
					<option value="2032">2031</option>
					<option value="2033">2032</option>
				</select>

			</div>

			<br />
			<button type="submit">MODIFICA</button>
			<button type="reset">RESET</button>
			<div class="options"></div>



		</form>

	</div>

</body>
</html>