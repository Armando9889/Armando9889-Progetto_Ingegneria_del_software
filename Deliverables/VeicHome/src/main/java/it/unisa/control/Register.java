package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.model.bean.*;
import it.unisa.query.*;
import it.unisa.utils.CFGui;
import it.unisa.utils.Connection;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean test_connessione = false;
	DataSource ds;
	Context initContext;
	Connection con = new Connection();
	boolean reg = false;
	boolean carta1 = false;

	public Register() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}
		QueryCliente model = new QueryCliente(ds);

		String action = "delete";

		if (action.equals("delete")) {
			String name = request.getParameter("name");
			String cognome = request.getParameter("surname");
			String sesso = request.getParameter("sesso");
			String indirizzo = request.getParameter("indirizzo");
			String data = request.getParameter("data");
			String password = request.getParameter("password");
			String carta = request.getParameter("carta");
			String scadenza = request.getParameter("scadenza");
			String scadenza_anno = request.getParameter("scadenza_anno");
			String cvv = request.getParameter("cvv");
			String username = request.getParameter("username");
			String comune_di_nascita = request.getParameter("comune");

			if (name.isEmpty() || cognome.isEmpty() || sesso.isEmpty() || indirizzo.isEmpty() || data.isEmpty()
					|| password.isEmpty() || carta.isEmpty() || scadenza.isEmpty() || cvv.isEmpty()
					|| scadenza_anno.isEmpty() || username.isEmpty() || comune_di_nascita.isEmpty()) {
				throw new IllegalArgumentException("Dati mancanti");
			}
			Pattern p = Pattern.compile("[^A-Za-z ]");
			Pattern p1 = Pattern.compile("[^A-Za-z0-9-/ ]");
			Pattern p3 = Pattern.compile("[^0-9]");

			Matcher m = p.matcher(name);
			Matcher m1 = p.matcher(cognome);
			Matcher m2 = p.matcher(comune_di_nascita);
			Matcher m3 = p1.matcher(indirizzo);
			Matcher m4 = p3.matcher(carta);
			Matcher m5 = p3.matcher(cvv);

			boolean b = m.find();
			boolean b1 = m1.find();
			boolean b2 = m2.find();
			boolean b3 = m3.find();
			boolean b4 = m4.find();
			boolean b5 = m5.find();

			if (b == true || b1 == true || b2 == true || b3 == true || b4 == true || b5 == true) {
				throw new IllegalArgumentException("Dati non corretti");
			}

			if (carta.length() != 16 || cvv.length() != 3) {
				throw new IllegalArgumentException("Dati non corretti");
			} else {
				try {
					reg = model.controllo(username);
					carta1 = model.controlloCarta(carta);
					System.out.print(reg);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				if (reg == false) {
					if(carta1==false) {

					String[] dateParts = data.split("-");
					String year = dateParts[0];
					String month = dateParts[1];
					String day = dateParts[2];
					System.out.println(year + " " + month + " " + day);

					ProductCliente cl = new ProductCliente();
					cl.setNome(name);
					cl.setCognome(cognome);
					cl.setSesso(sesso);
					cl.setIndirizzo(indirizzo);
					cl.setData_di_nascita(data);
					cl.setPassword(password);
					cl.setComune_di_nascita(comune_di_nascita);

					cl.setNumero_di_carta(carta);
					cl.setData_scadenza(scadenza + "/" + scadenza_anno);
					cl.setCvv(cvv);
					cl.setUsername(username);

					CFGui prova = new CFGui();
					String monthApp = prova.ritornaMese(month);
					String app = prova.calcolaCodice(cl.getNome(), cl.getCognome(), cl.getComune_di_nascita(), day,
							year, monthApp, cl.getSesso());
					cl.setCodice_fiscale(app);
					try {
						model.doSave(cl);

					} catch (SQLException e) {
						e.printStackTrace();
					}
					response.sendRedirect("login.jsp");
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
				} else {
					PrintWriter out = response.getWriter();
					out.print("<body style=\"background-color:#c4ffd8;\">");
					out.println("<script type=\"text/javascript\">");
					out.println("alert('username gia esistente');");
					out.println("</script>");
					out.print("Per continuare la registrazione clicca il tasto Back ");
					out.print("<button onclick=\"history.back()\"><<< Back</button>");

				}
			}
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
