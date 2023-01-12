package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
import it.unisa.utils.Utility;

@WebServlet("/Ricarica")
public class Ricarica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean test_connessione = false;
	DataSource ds;
	Connection con = new Connection();
	

	public Ricarica() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}
		
		HttpSession sessione = request.getSession();
		QueryCliente model = new QueryCliente(ds);

		String codice_fiscale = request.getParameter("codice_fiscale");
		String saldoAppoggio = request.getParameter("ricarica");
		String saldoPrecedemteAppoggio = request.getParameter("saldo_precedente");
		
		int saldo = Integer.parseInt(saldoAppoggio);
		int saldoPrecedente = Integer.parseInt(saldoPrecedemteAppoggio);
		
		if(codice_fiscale.isEmpty() || saldo <= 0) {
			throw new RuntimeException("Ricarica non effettuata");
		}
		else {
		
			ProductCliente cl = new ProductCliente();
			cl.setCodice_fiscale(codice_fiscale);
			cl.setSaldo(saldo+saldoPrecedente);
	
			try {
				sessione.setAttribute("saldo", saldo+saldoPrecedente);
				model.doUpdateSaldo(cl);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		response.sendRedirect("homeUfficiale.jsp");
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
