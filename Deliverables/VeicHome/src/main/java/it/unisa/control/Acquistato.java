package it.unisa.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/Acquistato")
public class Acquistato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Acquistato() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

		String mode = request.getParameter("modello");
		System.out.println(mode);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/homeUfficiale.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
