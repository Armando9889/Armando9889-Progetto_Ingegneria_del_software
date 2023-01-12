package it.unisa.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import it.unisa.model.bean.ProductVeicolo;
import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Connection;

@MultipartConfig
@WebServlet("/InserimentoMoto")
public class InserimentoMoto extends HttpServlet {

	private boolean test_connessione = false;
	DataSource ds;
	Context initContext;
	Connection con = new Connection();

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (con.connessione(test_connessione) == null) {
			ds = (DataSource) getServletContext().getAttribute("DataSource");
		} else {
			ds = con.connessione(test_connessione);
		}
		QueryVeicolo model = new QueryVeicolo(ds);

		String codice_telaio = request.getParameter("codice_telaio");
		String targa = request.getParameter("targa");
		String colore = request.getParameter("colore");
		String marchio = request.getParameter("marchio");
		String modello = request.getParameter("modello");
		String kw = request.getParameter("kw");
		String prezzo = request.getParameter("prezzo");
		//Part filePart = request.getPart("path");
		//InputStream inputStream = filePart.getInputStream();
		
		Part filePart = request.getPart("photo");
		String photoAppoggio="";
		String path="D:\\Desktop\\Michele(NON ELIMINARE)\\IS\\EclipseWorkspace\\VeicHomeIS\\WebContent\\uploadImage";
		File file = new File(path);
		file.mkdir();
		String fileName = getFileName(filePart);

		OutputStream out = null;
		InputStream filecontent = null;

		PrintWriter writer = response.getWriter();

		out = new FileOutputStream(new File(path + File.separator + fileName));
		filecontent = filePart.getInputStream();

		int read = 0;
		final byte[] bytes = new byte[1024];

		while ((read = filecontent.read(bytes)) != -1) {
			out.write(bytes, 0, read);
			photoAppoggio = path + "/" + fileName;
		}
		System.out.println(photoAppoggio);
		
		String accessori = request.getParameter("accessori");
		String custom = request.getParameter("custom");

		ProductVeicolo cl = new ProductVeicolo();

		cl.setCodice_telaio(codice_telaio);
		cl.setTarga(targa);
		cl.setColore(colore);
		cl.setMarchio(marchio);
		cl.setModello(modello);
		
		String realPath = photoAppoggio.replace("D:\\Desktop\\Michele(NON ELIMINARE)\\IS\\EclipseWorkspace\\VeicHomeIS\\WebContent\\", "");
		System.out.println(realPath);
		cl.setPhoto(realPath);
		
		//cl.setPath(inputStream);
		cl.setAccessori(accessori);
		cl.setCustom(custom);

		if (codice_telaio.isEmpty() || targa.isEmpty() || colore.isEmpty() || marchio.isEmpty() || modello.isEmpty()
				|| prezzo.isEmpty() || kw.isEmpty() || accessori.isEmpty() || custom.isEmpty() /*|| lePart == null*/) {
			throw new IllegalArgumentException("Dati mancanti");
		} else {
			try {
				int y = Integer.parseInt(prezzo);
				int x = Integer.parseInt(kw);
				cl.setKw(x);
				cl.setPrezzo(y);
				model.doInsertVeicoloMoto(cl);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("adminHome.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void prova() {
		test_connessione = true;
	}
	
	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
