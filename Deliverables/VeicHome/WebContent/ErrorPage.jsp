<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<meta charset="ISO-8859-1">
<title>ErrorPage</title>
<link rel="stylesheet" type="text/css" href="erroreCss.css">
</head>
   <body>
      <div id="error-page">
         <div class="content">
            <h2 class="header" data-text="400">
               400
            </h2>
            <h4 data-text="Opps! Page not found">
               Opps! Qualcosa è andato storto
            </h4>
            <p>
               Il veicolo che stai cercando di eliminare non esiste.
            </p>
            <div class="btns">
               <a href="adminHome.jsp">Home</a>
               <a href="eliminaveicolo.jsp">Elimina veicolo</a>
            </div>
         </div>
      </div>
   </body>
</html>