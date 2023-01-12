package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import it.unisa.model.bean.ProductVeicolo;
import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Connection;

@WebServlet("/EliminaVeicolo")
public class EliminaVeicolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean test_connessione = false;
	DataSource ds;
	Context initContext;
	Connection con = new Connection();
	boolean ridirezione = false;

	public EliminaVeicolo() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}

		QueryVeicolo model = new QueryVeicolo(ds);

		ProductVeicolo prova = null;

		String action = "delete";

		if (action.equals("delete")) {
			String targa = request.getParameter("targa");
			String codice = request.getParameter("telaio");

			try {
				System.out.println("ciao");
				prova = model.doRetrieveByKey1(codice, targa);
				System.out.println(prova);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (targa.isEmpty() || codice.isEmpty() || prova.getTarga().isEmpty()
					|| prova.getCodice_telaio().isEmpty()) {
				System.out.println("ciao");
				ridirezione = true;
			}
			if (targa.isEmpty() && codice.isEmpty()) {
				throw new IllegalArgumentException("Dati mancanti");
			}
			if (targa.isEmpty() && !codice.isEmpty()) {
				throw new IllegalArgumentException("targa non inserita");
			}
			if (!targa.isEmpty() && codice.isEmpty()) {
				throw new IllegalArgumentException("codice telaio non inserita");
			} else {

				try {

					ProductVeicolo x = model.doRetrieveByKey1(codice, targa);
					model.doDelete(x);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ridirezione == true) {
				ridirezione = false;
				response.sendRedirect("ErrorPage.jsp");
			} else
				response.sendRedirect("adminHome.jsp");
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
