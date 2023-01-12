<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>
    
<%
	Collection<?> products = (Collection<?>)request.getAttribute("stampa");
	
	
%>  
    
    
<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="stampa.css">
	<meta charset="ISO-8859-1">
	<meta name="author" content="Michele Del Mastro">
	<title>Store</title>
	
	
</head>
<body>

		
	<%
		if(products != null && products.size() > 0) {
			
			Iterator<?> it = products.iterator();
			while(it.hasNext()) {
				ProductVeicolo bean = (ProductVeicolo)it.next();
			
				
	%>
		
		<div id = "img">
		<img src="<%= bean.getPath()%>" width="710" height="500">	</div>
		
		<p id = "informazioni">INFORMAZIONI</p>
		<table id ="t">
		<th>CODICE TELAIO: <%= bean.getCodice_telaio()%><tr></tr> </th>
		<th>TARGA: <%= bean.getTarga()%><tr></tr> </th>
		<th>COLORE: <%= bean.getColore()%><tr></tr> </th>
		<th>MARCHIO: <%= bean.getMarchio()%><tr></tr> </th>
		<th>MODELLO: <%= bean.getModello()%><tr></tr> </th>
		<th>KW: <%= bean.getKw()%><tr></tr> </th>

	</table>
					<table id ="t"><th>PREZZO: <%= bean.getPrezzo()%>&euro;<tr></tr></th> </table>
					PARTITA IVA:<%= bean.getPartita_iva()%>
	<% } } %>


</body>
</html>