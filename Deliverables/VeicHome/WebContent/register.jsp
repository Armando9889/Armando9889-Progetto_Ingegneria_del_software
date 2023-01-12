<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>

<!DOCTYPE html>
<html lang="en">

<script type="text/javascript">
	function validateForm() {
		let a = document.forms["myForm"]["name"].value;
		let b = document.forms["myForm"]["surname"].value;
		let c = document.forms["myForm"]["sesso"].value;
		let d = document.forms["myForm"]["indirizzo"].value;
		let e = document.forms["myForm"]["data"].value;
		let f = document.forms["myForm"]["password"].value;
		let g = document.forms["myForm"]["comune"].value;
		let h = document.forms["myForm"]["carta"].value;
		let i = document.forms["myForm"]["scadenza"].value;
		let l = document.forms["myForm"]["cvv"].value;
		let user = document.forms["myForm"]["username"].value;
			
		/*if(a==""||b==""||c==""||d==""||e==""||f==""||h==""||i==""||l==""||user==""||g==""){
		 alert("Inserire dati mancanti");
		 return false;
		 }
		 if(!c.equals("F") || !c.equals("M")){
		 alert("Inserire il sesso corretto");
		 return false;
		 }*/
		 
		 //var espressione = /^[a-z ]+$/i;
		 var espressione = new RegExp('^[a-z ]+$','i');
		 var espressioneCarta = new RegExp('^[0-9]{16}$');
		 var espressioneCvv = new RegExp('^[0-9]{3}$');
		 var espressioneIndirizzo = new RegExp('^[a-z0-9-/ ]+$',"i");
		 
		 if(!espressione.test(a) || !espressione.test(b) || !espressione.test(g) || !espressioneCarta.test(h) ||
				 !espressioneCvv.test(l) || !espressioneIndirizzo.test(d) || c=="" || d=="" ||
				 e=="" || f =="" || i == "" || user == ""){
			 alert("Hai inserito dei caratteri non validi o campi mancanti");
			 return false;
		 }
	}
	</script>

<head>
<link rel="stylesheet" type="text/css" href="ProductView.css">
<meta charset="ISO-8859-1">
<meta name="author" content="Michele Del Mastro">
<title>Store</title>


</head>
<body>
	<a href ="homeUfficiale.jsp">
	<img src = "images/VEICHOME (2)1 (1).png" alt = "Logo VeicHome">
	</a>
	
<%

		ProductCliente cl=new ProductCliente();
%>


<form action="<%=response.encodeURL("Register")%>" method="POST" name="myForm" onsubmit="return validateForm()">
			<fieldset>
			<fieldset>
				<legend><b>Registrazione</b></legend>
				<label for="username">username:</label>
				<input type="text" name="username"  value="<%=cl.getUsername() %>">
				
				<br><label for="name">nome:</label>
				<input type="text" name="name" value="<%=cl.getNome() %>">
				
				<br><label for="surname">cognome:</label>
				<input type="text" name="surname" value="<%=cl.getCognome() %>">
								

				
				<br><label for="sesso">sesso:</label>
				<select name="sesso">
					<option value="F">F</option>
					<option value="M">M</option>
				
				</select>
				
				<br><label for="indirsizzo">indirizzo:</label>
				<input type="text" name="indirizzo" value="<%=cl.getIndirizzo() %>">
				
				<br><label for="comune">comune-di-nascita:</label>
				<input type="text" name="comune" value="<%=cl.getComune_di_nascita()%>">
				
				<br><label for="data">data-di-nascita:</label>
				<input type="date" name="data" value="<%=cl.getData_di_nascita() %>">
				
				<br><label for="password">password:</label>
				<input type="text" name="password" value="<%=cl.getPassword() %>">
				</fieldset>
				<fieldset>
				<legend><b>Dati carta</b></legend>
				
				<label for="carta">numero di carta:</label>
				<input type="text" name="carta" value="<%=cl.getNumero_di_carta() %>">
				

				
				
				<br><label for="scadenza">Scadenza carta:</label>
				<select name="scadenza">
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
				</select>
					<select name="scadenza_anno">
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
				
				<br><label for="cvv">CVV:</label>
				<input type="text" name="cvv" value="<%=cl.getCvv() %>">
			<br>
				
			</fieldset>
			<input id = "ok" type="submit" name="ok" value="Ok">
				<input id = "reset" type="reset" value="Reset">
			</fieldset>
		</form>	
		

</body>
</html>