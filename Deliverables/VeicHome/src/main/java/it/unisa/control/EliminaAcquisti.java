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

@WebServlet("/EliminaAcquisti")
public class EliminaAcquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EliminaAcquisti() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryAcquisto model = new QueryAcquisto(ds);

		String action = "delete";

		if (action.equals("delete")) {
			try {
				model.doDelete();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/adminHome.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
