package it.unisa.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Logger;

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

import it.unisa.model.bean.ProductCliente;
import it.unisa.model.bean.ProductVeicolo;
import it.unisa.query.QueryCliente;
import it.unisa.query.QueryVeicolo;
import it.unisa.utils.Connection;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

@WebServlet("/InserimentoAuto")
public class InserimentoAuto extends HttpServlet {
	Logger logger = Logger.getLogger("com.api.jar");
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
		Part filePart = request.getPart("photo");

		System.out.println("/*********/");
		System.out.println(filePart.getSubmittedFileName());
		System.out.println(filePart.getHeader("content-disposition"));
		System.out.println(filePart.getName());
		System.out.println(filePart.getInputStream());
		System.out.println("/*********/");

		String sconto = request.getParameter("sconto");
		String numero_di_passegeri = request.getParameter("numero_di_passegeri");

		ProductVeicolo cl = new ProductVeicolo();

		cl.setCodice_telaio(codice_telaio);
		cl.setTarga(targa);
		cl.setColore(colore);
		cl.setMarchio(marchio);
		cl.setModello(modello);
		cl.setSconto(sconto);

		if (codice_telaio.isEmpty() || targa.isEmpty() || colore.isEmpty() || marchio.isEmpty() || modello.isEmpty()
				|| kw.isEmpty() || prezzo.isEmpty() || sconto.isEmpty() || numero_di_passegeri.isEmpty()
				|| filePart == null) {
			throw new NullPointerException("Dati mancanti");
		} else {

			try {
				String photoAppoggio = "";
				String path = "D:\\Desktop\\Michele(NON ELIMINARE)\\IS\\EclipseWorkspace\\VeicHomeIS\\WebContent\\uploadImage";
				File file = new File(path);
				file.mkdir();
				String fileName = getFileName(filePart);
				System.out.println(fileName);
				OutputStream out = null;
				InputStream filecontent = null;

				out = new FileOutputStream(new File(path + File.separator + fileName));
				filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
					photoAppoggio = path + "/" + fileName;
				}
				System.out.println(photoAppoggio);
				String realPath = photoAppoggio.replace(
						"D:\\Desktop\\Michele(NON ELIMINARE)\\IS\\EclipseWorkspace\\VeicHomeIS\\WebContent\\", "");
				System.out.println(realPath);
				cl.setPhoto(realPath);

				int x = Integer.parseInt(kw);
				int y = Integer.parseInt(prezzo);
				int n = Integer.parseInt(numero_di_passegeri);
				cl.setNumero_passegeri(n);
				cl.setKw(x);
				cl.setPrezzo(y);
				model.doInsertVeicoloAuto(cl);
				
				

			} catch (SQLException e) {
				// e.printStackTrace();
				logger.info("CODICE TELAIO O TARGA GIA' ESISTENTE");
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
