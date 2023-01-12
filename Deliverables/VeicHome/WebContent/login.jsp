<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- For google icons  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
	<link href="login.css" rel="stylesheet">
    <title>Login form</title>

    <script type="text/javascript">
        function validateForm(){
            let a=document.forms["myForm"]["id"].value;
            let b=document.forms["myForm"]["sesso"].value;
            if(a=="" && b==""){
                alert("Inserire dati mancanti");
                return false;
            }
            if(a != "" && b ==""){
                alert("Inserire password");	
                return false;
            }
            if(a == "" && b != ""){
                alert("Inserire username");
                return false;
            }
            
        }
        
        </script>
    

</head>

<body>

		<a href ="homeUfficiale.jsp">
	<img src = "images/logo_modificato.png" alt = "Logo VeicHome" width="100" height="100">
	</a>	
	

    <%
	ProductCliente cl = new ProductCliente();
	%>


    <div class="background"></div>
    <div class="container">
        <h2>Login Form</h2>


        <form action="<%=response.encodeURL("ProductControl")%>" method="POST" name="myForm" onsubmit="return validateForm()">
           
            <div class="form-item">
                <span class="material-icons-outlined">
                    account_circle
                </span>

                <input type="text" name="id" id="text" placeholder="username" value="<%=cl.getNome()%>">

            </div>

            <div class="form-item">
                <span class="material-icons-outlined">
                    lock
                </span>

                <input type="password" name="sesso" id="pass" placeholder="password" value="<%=cl.getPassword()%>"> 

            </div>

        
            <button type="submit"> LOGIN </button>
            <button type="reset"> RESET </button>
            <div class="options">
			
            </div>
            <p>New User? <a href="register.jsp">Create an account</a></p>
        
        </form>
        
    </div>

</body>
</html>