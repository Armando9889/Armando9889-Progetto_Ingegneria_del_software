package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.bean.ProductCliente;
import it.unisa.query.QueryCliente;
import it.unisa.utils.Connection;

@WebServlet("/EditProfilo")
public class EditProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean test_connessione = false;
	DataSource ds;
	Context initContext;
	Connection con = new Connection();
	boolean carta1 = false;

	public EditProfilo() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessione = request.getSession();
		boolean reg = false;

		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}

		QueryCliente model = new QueryCliente(ds);

		String codice_fiscale = (String) sessione.getAttribute("cf");
		String username = request.getParameter("id");
		String indirizzo = request.getParameter("indirizzo");
		String numero_di_carta = request.getParameter("numero_di_carta");
		String cvv = request.getParameter("cvv");
		String scadenza_mese = request.getParameter("scadenza");
		String scadenza_anno = request.getParameter("scadenza_anno");

		ProductCliente cl = new ProductCliente();
		
		if(!numero_di_carta.isEmpty() && cvv.isEmpty() && username.isEmpty() && indirizzo.isEmpty()) {
			throw new RuntimeException("Dati mancanti"); 
		}
		
		else if(numero_di_carta.isEmpty() && !cvv.isEmpty() && username.isEmpty() && indirizzo.isEmpty()) {
			throw new RuntimeException("Dati mancanti");
		}
		
		else if(!numero_di_carta.isEmpty() && cvv.isEmpty() && !username.isEmpty() && indirizzo.isEmpty()) {
			throw new RuntimeException("Dati mancanti");
		}
		
		else if(!numero_di_carta.isEmpty() && cvv.isEmpty() && username.isEmpty() && !indirizzo.isEmpty() ) {
			throw new RuntimeException("Dati mancanti");
		}
		
		else if(numero_di_carta.isEmpty() && !cvv.isEmpty() && !username.isEmpty() && indirizzo.isEmpty()) {
			throw new RuntimeException("Dati mancanti");
		}
		
		else if(numero_di_carta.isEmpty() && !cvv.isEmpty() && username.isEmpty() && !indirizzo.isEmpty()) {
			throw new RuntimeException("Dati mancanti");
		}
		
		else if(!numero_di_carta.isEmpty() && cvv.isEmpty() && !username.isEmpty() && !indirizzo.isEmpty()) {
			throw new RuntimeException("Dati mancanti");
		}
		
		else if(numero_di_carta.isEmpty() && !cvv.isEmpty() && !username.isEmpty() && !indirizzo.isEmpty()) {
			throw new RuntimeException("Dati mancanti");
		}
		
		Pattern p = Pattern.compile("[^A-Za-z ]");
		Pattern p1 = Pattern.compile("[^A-Za-z0-9-/ ]");
		Pattern p3 = Pattern.compile("[^0-9]");

		Matcher m3 = p1.matcher(indirizzo);
		Matcher m4 = p3.matcher(numero_di_carta);
		Matcher m5 = p3.matcher(cvv);

		boolean b3 = m3.find();
		boolean b4 = m4.find();
		boolean b5 = m5.find();

		if (b3 == true || b4 == true || b5 == true) {
			throw new RuntimeException("Dati non corretti");
		}

		if (numero_di_carta.length() != 16 && cvv.length() != 3 && username.isEmpty() && indirizzo.isEmpty()) {
			throw new RuntimeException("Dati non corretti");
		}
		
		if (numero_di_carta.length() != 16 && cvv.length() != 3 && !username.isEmpty() && indirizzo.isEmpty() && !numero_di_carta.isEmpty() && !cvv.isEmpty()) {
			throw new RuntimeException("Dati non corretti");
		}
		
		if (numero_di_carta.length() != 16 && cvv.length() != 3 && username.isEmpty() && !indirizzo.isEmpty() && !numero_di_carta.isEmpty() && !cvv.isEmpty()) {
			throw new RuntimeException("Dati non corretti");
		}
		
		if (numero_di_carta.length() != 16 && cvv.length() != 3 && !username.isEmpty() && !indirizzo.isEmpty() && !numero_di_carta.isEmpty() && !cvv.isEmpty()) {
			throw new RuntimeException("Dati non corretti");
		}
		
		cl.setCodice_fiscale(codice_fiscale);
		cl.setUsername(username);
		cl.setIndirizzo(indirizzo);
		cl.setNumero_di_carta(numero_di_carta);
		cl.setCvv(cvv);
		cl.setData_scadenza(scadenza_mese + "/" + scadenza_anno);

		try {
			reg = model.controllo(username);
			carta1 = model.controlloCarta(numero_di_carta);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (reg == false) {
			if(carta1==false) {
			try {
				model.doUpdate(cl);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			response.sendRedirect("MyProfilo.jsp");
		}
			else {
				PrintWriter out = response.getWriter();
				out.print("<body style=\"background-color:#c4ffd8;\">");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('carta gia in uso');");
				out.println("</script>");
				out.print("Per continuare la registrazione clicca il tasto Back ");
				out.print("<button onclick=\"history.back()\"><<< Back</button>");

			}
		}
		else {
			PrintWriter out = response.getWriter();
			out.print("<body style=\"background-color:#c4ffd8;\">");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('username gia esistente');");
			out.println("</script>");
			out.print("Per continuare la modifica dei dati clicca il tasto Back ");
			out.print("<button onclick=\"history.back()\"><<< Back</button>");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void prova() {
		test_connessione = true;
	}

}
