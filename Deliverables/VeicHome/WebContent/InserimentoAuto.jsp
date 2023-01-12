<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>
<!doctype html>
<html lang="en">
<head>

<script type="text/javascript">

function validateForm(){
	let a=document.forms["myForm"]["targa"].value;
	let b=document.forms["myForm"]["codice_telaio"].value;
	let c=document.forms["myForm"]["colore"].value;
	let d=document.forms["myForm"]["marchio"].value;
	let e=document.forms["myForm"]["modello"].value;
	let f=document.forms["myForm"]["kw"].value;
	let g=document.forms["myForm"]["prezzo"].value;
	//let i=document.forms["myForm"]["path"].value;
	let l=document.forms["myForm"]["sconto"].value;
	let m=document.forms["myForm"]["numero_di_passegeri"].value;
	
	
	if(a==""||b==""||c==""||d==""||e==""||f==""||g==""||l==""||m==""){
		alert("Inserisci dati mancanti");
		return false;
	}
	
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
					<form action="Logout">
						<input
							style="outline: trasparent; border: none; background-color: rgba(248, 249, 251, 255); color: #0d6efd"
							id="ok" type="submit" name="ok" value="Logout"></a>
					</form>
				</li>
			</ul>
		</div>
	</nav>


	<div class="wrapper">
		<div class="sidebar">
			<h2>ADMIN</h2>
			<ul>
				<li><a href="ModificaMia"><i class="fas fa-trash-alt">
							Elimina</i></a></li>
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
		
		<%
		String pt = (String) session.getAttribute("pt");
		ProductVeicolo cl=new ProductVeicolo();
		%>
		

		<div class="main_content">
				<div class="header"
					style="background-color: rgba(255, 255, 255, 0.05);">
					<h2 style="outline: none; border: none; background-color: rgba(255, 255, 255, 0.05);">INSERIMENTO AUTO</h2>
				</div>
			<div class="info">

				<div>
					<br />
					
			<form action="<%=response.encodeURL("InserimentoAuto")%>" method="POST" name="myForm" enctype="multipart/form-data" onsubmit="return validateForm()"
			style="	padding:10px; border:2px double;">
			<fieldset>
				
				<label style="display: inline-block; vertical-align: baseline; width: 125px;" for="codice_telaio">Codice Telaio:</label>
				<input  type="text" name="codice_telaio" value="<%=cl.getCodice_telaio()%>">
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="targa">ID:</label>
				<input type="text" name="targa" value="<%=cl.getTarga()%>">
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="colore">Colore:</label>
				<input type="text" name="colore" value="<%=cl.getColore() %>">
								
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="marchio">Marchio:</label>
				<input  name="marchio" type="text" maxlength="20"  value="<%=cl.getMarchio()%>">
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="modello">Modello:</label>
				<input type="text" name="modello" value="<%=cl.getModello() %>">
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="kw">Kw:</label>
				<input type="text" name="kw" >
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="prezzo">Prezzo:</label>
				<input type="text" name="prezzo">
				
				<!-- <br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="path">Foto:</label>
				<input type="file" name="path" value=""> -->
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="sconto">Sconto:</label>
				<input type="text" name="sconto" value="<%=cl.getSconto() %>">
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="numero_di_passegeri">Numero di passegeri:</label>
				<input type="text" name="numero_di_passegeri">
				
				<br><label style="display: inline-block; vertical-align: baseline; width: 125px;" for="photo">Photo:</label>
				<input type="file" name="photo">
				
			<br />
			<br>
				<input style="display: inline-block; vertical-align: baseline; width: 125px;" id = "ok" type="submit" name="ok" value="Ok">
				<input style="display: inline-block; vertical-align: baseline; width: 125px;" id = "reset" type="reset" value="Reset">
			</fieldset>
		</form>	

				</div>

			</div>
		</div>
</body>
</html>