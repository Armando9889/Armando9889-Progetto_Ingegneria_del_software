package it.unisa.control;

import java.io.IOException;

import java.sql.SQLException;

import javax.naming.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.bean.*;

import it.unisa.query.QueryCliente;

import it.unisa.query.login;
import it.unisa.utils.Connection;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected String n;
	private boolean test_connessione = false;
	DataSource ds;
	Context initContext;
	String prova = "";
	Connection con = new Connection();
	String login;
	int saldoSessione;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}

		login model = new login(ds);
		QueryCliente model2 = new QueryCliente(ds);

		String action = "delete";

		String cod;
		HttpSession sessione = request.getSession();
		ProductCliente c = new ProductCliente();

		if (action.equals("delete")) {
			String nome = request.getParameter("id");
			String password = request.getParameter("sesso");

			if (nome.isEmpty() && password.isEmpty()) {
				throw new IllegalArgumentException("Dati mancanti");
			}
			if (nome.isEmpty() && !password.isEmpty()) {
				throw new IllegalArgumentException("username non inserito");
			}
			if (!nome.isEmpty() && password.isEmpty()) {
				throw new IllegalArgumentException("password non inserita");
			} else {

				try {
					login = model.login(nome, password);

					if (!login.equals("DVGVLP37C71A067D") && !login.equals("error")) {

						c = model2.doRetrieveByKey(nome, password);
						cod = c.getCodice_fiscale();
						saldoSessione = c.getSaldo();
						prova = c.getNome();
						sessione.setAttribute("cf", cod);
						sessione.setAttribute("saldo", saldoSessione);
						System.out.println(cod);

					}
					if (login.equals("DVGVLP37C71A067D") && !login.equals("error")) {
						c = model2.doRetrieveByKey(nome, password);
						cod = c.getCodice_fiscale();
						sessione.setAttribute("cf", cod);
					}
					sessione.setAttribute("login", prova);

					String x = sessione.getId();
					System.out.println(x);

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			if (login.equals("error")) {
				response.sendRedirect("errore.jsp");
			}
			if (!login.equals("DVGVLP37C71A067D") && !login.equals("error")) {
				response.sendRedirect("homeUfficiale.jsp");
			}
			if (login.equals("DVGVLP37C71A067D") && !login.equals("error")) {
				response.sendRedirect("adminHome.jsp");

			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void prova() {
		test_connessione = true;
	}

}