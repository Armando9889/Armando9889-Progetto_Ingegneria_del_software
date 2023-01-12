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
import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Utility;


@WebServlet("/ModificaMia")
public class ModificaMia extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModificaMia() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		QueryVeicolo model2 = new QueryVeicolo(ds);
	
		try {
			//request.setAttribute("lista", model.doRetriveAll(""));
			request.setAttribute("products", model2.doRetriveAll5(""));
		} catch (SQLException e) {
			Utility.print(e);
			
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/deleteItem.jsp");
		dispatcher.forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
