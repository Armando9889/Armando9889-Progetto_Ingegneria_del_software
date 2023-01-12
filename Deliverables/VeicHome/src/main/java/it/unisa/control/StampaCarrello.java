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

import it.unisa.query.QueryCarrello;

import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Utility;

@WebServlet("/StampaCarrello")
public class StampaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StampaCarrello() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryCarrello model = new QueryCarrello(ds);
		QueryVeicolo model2 = new QueryVeicolo(ds);

		try {
			request.setAttribute("carrello", model.doRetriveAll(""));
			request.setAttribute("products", model2.doRetriveAll5(""));
		} catch (SQLException e) {
			Utility.print(e);

			request.setAttribute("error", e.getMessage());
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/VisualizzaCarrello.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
