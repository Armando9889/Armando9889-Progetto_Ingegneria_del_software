package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.query.QueryAcquisto;
import it.unisa.query.QueryCarrello;
import it.unisa.query.QueryCliente;

import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Utility;

@WebServlet("/StampaOrdini")
public class StampaOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StampaOrdini() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryAcquisto model = new QueryAcquisto(ds);
		QueryCliente model1 = new QueryCliente(ds);
		QueryVeicolo model2 = new QueryVeicolo(ds);

		try {
			request.setAttribute("lista1", model.doRetriveAll(""));
			request.setAttribute("cliente1", model1.doRetriveAll1(""));
			request.setAttribute("products", model2.doRetriveAll5(""));
		} catch (SQLException e) {
			Utility.print(e);

			request.setAttribute("error", e.getMessage());
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ordini.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
