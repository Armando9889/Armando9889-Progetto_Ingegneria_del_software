package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.naming.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.bean.ProductAcquisto;
import it.unisa.model.bean.ProductCarrello;
import it.unisa.model.bean.ProductCliente;
import it.unisa.query.QueryAcquisto;
import it.unisa.query.QueryCarrello;
import it.unisa.query.QueryCliente;
import it.unisa.utils.Connection;

@WebServlet("/Acquisto")
public class Acquisto extends HttpServlet {

	private boolean test_connessione = false;
	DataSource ds;
	Context initContext;
	Connection con = new Connection();
	private static final long serialVersionUID = 1L;

	public Acquisto() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}
		
		HttpSession sessione = request.getSession();
		QueryAcquisto model = new QueryAcquisto(ds);
		QueryCarrello model1 = new QueryCarrello(ds);
		QueryCliente model2 = new QueryCliente(ds);

		String targaFK = request.getParameter("targa");
		String codice_fiscaleFK = (String) sessione.getAttribute("cf");
		String saldoAppoggio = request.getParameter("prezzoSessione");
		String prezzoAppoggio = request.getParameter("prezzo");
		
		int saldo = Integer.parseInt(saldoAppoggio);
		int prezzo = Integer.parseInt(prezzoAppoggio);
		
		LocalDate todaysDate = LocalDate.now();
		String data_di_acquisto = todaysDate.getDayOfMonth() + "/" + todaysDate.getMonthValue() + "/" + todaysDate.getYear();
		System.out.println(data_di_acquisto);

		ProductAcquisto cl = new ProductAcquisto();
		ProductCarrello cl1 = new ProductCarrello();
		ProductCliente cl2 = new ProductCliente();
		
		cl.setTargaFK(targaFK);
		cl.setCodice_fiscaleFK(codice_fiscaleFK);
		cl.setData_di_acquisto(data_di_acquisto);
		cl2.setSaldo(saldo-prezzo);
		cl2.setCodice_fiscale(codice_fiscaleFK);
		
		try {
			model2.doUpdateSaldo(cl2);
			sessione.setAttribute("saldo", saldo-prezzo);
			model.doSave(cl);
			cl1 = model1.doRetrieveByKey1(targaFK);
			model1.doDelete(cl1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("Acquistato?modello=" + targaFK);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void prova() {
		test_connessione = true;
	}
}