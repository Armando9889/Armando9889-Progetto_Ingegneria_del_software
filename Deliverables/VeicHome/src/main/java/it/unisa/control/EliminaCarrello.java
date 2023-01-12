package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.bean.ProductCarrello;
import it.unisa.model.bean.ProductVeicolo;
import it.unisa.query.QueryCarrello;
import it.unisa.query.QueryCliente;
import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Connection;

@WebServlet("/EliminaCarrello")
public class EliminaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean test_connessione = false;
	DataSource ds;
	Connection con = new Connection();

	public EliminaCarrello() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}
		
		QueryCarrello model = new QueryCarrello(ds);
		String action = "delete";

		if (action.equals("delete")) {
			try {

				HttpSession sessione = request.getSession(false);
				sessione.getAttribute("login");
				String codice_fiscaleFK = (String) sessione.getAttribute("cf");
				String id = (String) sessione.getId();


				model.doDelete(id,codice_fiscaleFK);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/VisualizzaCarrello.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void prova() {
		test_connessione = true;
	}

}
