<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="ProductView.css">
<meta charset="ISO-8859-1">
<meta name="author" content="Michele Del Mastro">
<title>Store</title>

<script type="text/javascript">
function validateForm(){
	let a=document.forms["myForm"]["targa"].value;
	let b=document.forms["myForm"]["telaio"].value;

	if(a=="" && b==""){
		alert("Inserire dati mancanti");
		return false;
	}
	
	if(a == "" && b != ""){
		alert("Inserire targa");
		return false;
	}
	
	if(a != "" && b == ""){
		alert("Inserire codice telaio");
		return false;
	}
	
}

</script>
</head>
<body>
	<a href ="adminHome.jsp">
	<img src = "images/VEICHOME (2)1 (1).png" alt = "Logo VeicHome">
	</a>
	
<%

		ProductVeicolo cl=new ProductVeicolo();
%>


<form action="<%=response.encodeURL("EliminaVeicolo")%>" method="POST" name="myForm" onsubmit="return validateForm()">
			<fieldset>
				<legend><b>Eliminare Veicolo</b></legend>
				
				<label for="targa">targa:</label>
				<input type="text" name="targa" value="<%=cl.getTarga() %>">
				
				<br><label for="telaio">codice-telaio:</label>
				<input type="text" name="telaio" value="<%=cl.getCodice_telaio() %>">
				
	
			
			<br>
				<input id = "ok" type="submit" name="ok" value="Ok">
				<input id = "reset" type="reset" value="Reset">
			</fieldset>
		</form>	
		

</body>
</html>