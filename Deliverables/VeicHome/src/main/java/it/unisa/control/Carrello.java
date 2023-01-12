package it.unisa.control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import it.unisa.model.bean.ProductCarrello;
import it.unisa.model.bean.ProductCliente;
import it.unisa.query.QueryCarrello;
import it.unisa.query.QueryCliente;
import it.unisa.utils.Connection;

@MultipartConfig
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean test_connessione = false;
	DataSource ds;
	Connection con = new Connection();

	public Carrello() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}

		//DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryCarrello model = new QueryCarrello(ds);

		String targa = request.getParameter("targa");
		String modello = request.getParameter("modello");
		String prezzo = request.getParameter("prezzo");
		String idsession = request.getParameter("session");

		if (targa.isEmpty() || modello.isEmpty() || prezzo.isEmpty() || idsession.isEmpty()) {
			System.out.println("Sono qui non faccio niente");
			throw new RuntimeException("Dati mancanti");
		} else {

			int pre = Integer.parseInt(prezzo);
			System.out.println("Faccio qualocsa");
			ProductCarrello cl = new ProductCarrello();
			cl.setTarga(targa);

			cl.setModello(modello);
			cl.setPrezzo(pre);
			cl.setSessionid(idsession);
			HttpSession sessione = request.getSession();
			String codice_fiscaleFK = (String) sessione.getAttribute("cf");
		
				cl.setutenteid(codice_fiscaleFK);
				

			try {
				model.doSave(cl);

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
