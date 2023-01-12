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
import it.unisa.query.QueryCliente;
//import it.unisa.query.QueryLogin;
import it.unisa.utils.Utility;

@WebServlet("/StampaInfo")
public class StampaInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StampaInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryCliente model = new QueryCliente(ds);

		try {
			request.setAttribute("cliente", model.doRetriveAll1(""));
		} catch (SQLException e) {
			Utility.print(e);

			request.setAttribute("error", e.getMessage());
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/MyProfilo.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
