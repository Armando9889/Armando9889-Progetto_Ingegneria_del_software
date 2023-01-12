<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.bean.*"%>

<%
Collection<?> products = (Collection<?>) request.getAttribute("products");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- custom css file link  -->
<link rel="stylesheet" href="creditCard.css">

</head>
<body>

	<%
	String cf = (String) session.getAttribute("cf");
	%>

	<div class="container">
		<%
		if (products != null && products.size() > 0) {
			Iterator<?> it = products.iterator();
			while (it.hasNext()) {
				ProductCliente bean = (ProductCliente) it.next();
				if (bean.getCodice_fiscale().equals(cf)) {
			String data = bean.getData_scadenza();
			String[] dateParts = data.split("/");
			String month = dateParts[0];
			String year = dateParts[1];
			System.out.println(month + " " + year);
		%>


		<div class="card-container">

			<div class="front">
				<div class="image">
					<img src="images/chip.png" alt=""> <img src="images/visa.png"
						alt="">
				</div>
				<div class="card-number-box"><%=bean.getNumero_di_carta()%></div>
				<div class="flexbox">
					<div class="box">
						<span>card holder</span>
						<div class="card-holder-name"><%=bean.getNome() + " " + bean.getCognome()%></div>
					</div>
					<div class="box">
						<span>expires</span>
						<div class="expiration">
							<span class="exp-month"><%=month%></span> <span class="exp-year"><%=year%></span>
						</div>
					</div>
				</div>
			</div>

			<div class="back">
				<div class="stripe"></div>
				<div class="box">
					<span><%=bean.getCvv()%></span>
					<div class="cvv-box"></div>
					<img src="images/visa.png" alt="">
				</div>
			</div>

		</div>

		<form action="Ricarica">

			<div class="flexbox">
				<div class="inputBox">
					<span>Selezionare l'importo da ricaricare</span> <select name="ricarica" id=""
						class="month-input">
						<option value="importo" selected disabled>importo</option>
						<option value="10000">10000</option>
						<option value="20000">20000</option>
						<option value="30000">30000</option>
						<option value="40000">40000</option>
						<option value="50000">50000</option>
						<option value="60000">60000</option>
						<option value="65000">65000</option>
						<option value="67000">67000</option>
						<option value="70000">70000</option>
						<option value="73000">73000</option>
						<option value="75000">75000</option>
						<option value="76000">76000</option>
					</select>
				</div>
						<input type="hidden" value="<%=cf%>" name="codice_fiscale">
						<input type="hidden" value="<%=bean.getSaldo()%>" name="saldo_precedente">
			</div>
			<input type="submit" value="submit" class="submit-btn">
		</form>

	</div>

	<%
	}
	}
	}
	%>

	<script>

document.querySelector('.card-number-input').oninput = () =>{
    document.querySelector('.card-number-box').innerText = document.querySelector('.card-number-input').value;
}

document.querySelector('.card-holder-input').oninput = () =>{
    document.querySelector('.card-holder-name').innerText = document.querySelector('.card-holder-input').value;
}

document.querySelector('.month-input').oninput = () =>{
    document.querySelector('.exp-month').innerText = document.querySelector('.month-input').value;
}

document.querySelector('.year-input').oninput = () =>{
    document.querySelector('.exp-year').innerText = document.querySelector('.year-input').value;
}

document.querySelector('.cvv-input').onmouseenter = () =>{
    document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(-180deg)';
    document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(0deg)';
}

document.querySelector('.cvv-input').onmouseleave = () =>{
    document.querySelector('.front').style.transform = 'perspective(1000px) rotateY(0deg)';
    document.querySelector('.back').style.transform = 'perspective(1000px) rotateY(180deg)';
}

document.querySelector('.cvv-input').oninput = () =>{
    document.querySelector('.cvv-box').innerText = document.querySelector('.cvv-input').value;
}

</script>

</body>
</html>