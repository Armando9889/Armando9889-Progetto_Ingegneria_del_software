<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>
    
<%
	Collection<?> products = (Collection<?>)request.getAttribute("products");
	
	
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
<a href ="auto.jsp">
	<img src = "images/vhauto.png" alt = "Logo VeicHome" width="75" height="22">
	</a>
	&nbsp;&nbsp;
	<a href ="moto.jsp">
	<img src = "images/vhmoto.png" alt = "Logo veicmoto" width="75" height="22">
	</a>	
	
	<%
	String x = (String) request.getParameter("ciao");
		if(products != null && products.size() > 0) {
			
			Iterator<?> it = products.iterator();
			while(it.hasNext()) {
				ProductVeicolo bean = (ProductVeicolo)it.next();
				if(x.equals(bean.getModello())){
				
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
	
	<%

		ProductCarrello cl=new ProductCarrello();
%>


					
			
					
				<form action="<%=response.encodeURL("Carrello")%>" method="POST">
				
	
				<input type="text" name="targa" id="tar" value=<%= bean.getTarga()%>>
				
				
				<input type="text" name="codice" id="cod"  value="<%=bean.getCodice_telaio()%>" >
				
				
				
				<input type="text" name="modello" id="tar" value=<%= bean.getModello()%>>
				
				<input type="text" name="prezzo" id="tar" value=<%= bean.getPrezzo()%>>
				
				
				<input type="text" name="codice" id="cod"  value="<%=bean.getCodice_telaio()%>" >
			<br>
				<input id = "ok" type="submit" name="ok" value="Aggiungi al carrello">
				
		</form>	
		
		<form action="StampaCarrello">
		<input id = "ok" type="submit" name="ok" value="Visualizza carrello">
		</form>
		
		
		<form action="Lista" method="post">
		<input id = "ok" type="submit" name="ok" value="Prodotti Acquistati">
		</form>
					

	<% } } }%>
	
	
	
	
	
	
	




</body>
</html>