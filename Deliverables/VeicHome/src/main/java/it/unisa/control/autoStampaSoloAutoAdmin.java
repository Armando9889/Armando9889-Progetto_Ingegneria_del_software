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

import it.unisa.model.bean.ProductCliente;
import it.unisa.model.bean.ProductVeicolo;
import it.unisa.query.QueryCliente;

import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Utility;

@WebServlet("/autoStampaSoloAutoAdmin")
public class autoStampaSoloAutoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public autoStampaSoloAutoAdmin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryVeicolo model = new QueryVeicolo(ds);

		try {
			request.setAttribute("prova", model.cercaAuto(""));
		} catch (SQLException e) {
			Utility.print(e);
			
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/autoStamposoloAutoadmin.jsp");
		dispatcher.forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}