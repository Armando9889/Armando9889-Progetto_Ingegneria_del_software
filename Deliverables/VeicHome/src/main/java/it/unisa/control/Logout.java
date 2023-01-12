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
import it.unisa.query.QueryCarrello;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryCarrello model1 = new QueryCarrello(ds);

		String action = "delete";

		if (action.equals("delete")) {
			try {
				HttpSession sessione = request.getSession();
				String codice_fiscaleFK = (String) sessione.getAttribute("cf");
				sessione.getAttribute("login");
				String id = (String) sessione.getId();

				model1.doDelete(id,codice_fiscaleFK);
				sessione.invalidate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/homeUfficiale.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
