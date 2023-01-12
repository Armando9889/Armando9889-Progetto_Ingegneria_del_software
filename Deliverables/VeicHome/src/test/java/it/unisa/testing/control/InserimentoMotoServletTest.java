package it.unisa.testing.control;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import it.unisa.control.InserimentoAuto;
import it.unisa.control.InserimentoMoto;

class InserimentoMotoServletTest {

	private ServletContext context;
	private RequestDispatcher dispatcher;

	@Spy
	private InserimentoMoto servlet;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;

	private HttpSession session;
	private ServletConfig sg;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		session = mock(HttpSession.class);
		context = mock(ServletContext.class);
		dispatcher = mock(RequestDispatcher.class);
		sg = mock(ServletConfig.class);

		when(sg.getServletContext()).thenReturn(context);
		servlet.init();
	}

	// TC4_1 NON INSERIMENTO CREDENZIALI
	@Test
	public void TC4_1() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("");
		when(request.getParameter("targa")).thenReturn("");
		when(request.getParameter("colore")).thenReturn("");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");

		Part file = mock(Part.class);
		when(file.getInputStream()).thenReturn(null);
		when(request.getPart("photo")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_2 inserisce solo il codice_telaio
	@Test
	public void TC4_2() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("");
		when(request.getParameter("colore")).thenReturn("");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");

		Part file = mock(Part.class);

		when(file.getInputStream()).thenReturn(null);
		when(request.getPart("path")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_3 inserisce solo il codice_telaio e targa
	@Test
	public void TC4_3() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");

		Part file = mock(Part.class);

		when(file.getInputStream()).thenReturn(null);
		when(request.getPart("path")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_4 inserisce solo il codice_telaio , targa , colore
	@Test
	public void TC4_4() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");

		Part file = mock(Part.class);

		when(file.getInputStream()).thenReturn(null);
		when(request.getPart("path")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_5 inserisce solo il codice_telaio , targa , colore , marchio
	@Test
	public void TC4_5() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("Ducati");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");

		Part file = mock(Part.class);

		when(file.getInputStream()).thenReturn(null);
		when(request.getPart("path")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_6 inserisce solo il codice_telaio , targa , colore , marchio , modello
	@Test
	public void TC4_6() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("Ducati");
		when(request.getParameter("modello")).thenReturn("Panigale-V4");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");

		Part file = mock(Part.class);

		when(file.getInputStream()).thenReturn(null);
		when(request.getPart("path")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_7 inserisce solo il codice_telaio , targa , colore , marchio , modello ,
	// kw
	@Test
	public void TC4_7() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("Ducati");
		when(request.getParameter("modello")).thenReturn("Panigale-V4");
		when(request.getParameter("kw")).thenReturn("157");
		when(request.getParameter("prezzo")).thenReturn("");

		Part file = mock(Part.class);

		when(file.getInputStream()).thenReturn(null);
		when(request.getPart("path")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_8 inserisce solo il codice_telaio , targa , colore , marchio , modello ,
	// kw , prezzo
	@Test
	public void TC4_8() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("Ducati");
		when(request.getParameter("modello")).thenReturn("Panigale-V4");
		when(request.getParameter("kw")).thenReturn("157");
		when(request.getParameter("prezzo")).thenReturn("29990");

		Part file = mock(Part.class);
		BufferedImage image = ImageIO.read(new File("D:/Desktop/ducati.png"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());

		when(file.getHeader("content-disposition")).thenReturn("");
		when(file.getInputStream()).thenReturn(is);
		when(file.getSize()).thenReturn(3L);
		when(file.getName()).thenReturn("photo");
		when(request.getPart("photo")).thenReturn(file);
		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// TC4_9 inserisce solo il codice_telaio , targa , colore , marchio , modello ,
	// kw , prezzo , path
	@Test
	public void TC4_9() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("Ducati");
		when(request.getParameter("modello")).thenReturn("Panigale-V4");
		when(request.getParameter("kw")).thenReturn("157");
		when(request.getParameter("prezzo")).thenReturn("29990");

		Part file = mock(Part.class);
		BufferedImage image = ImageIO.read(new File("D:/Desktop/ducati.png"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());

		when(file.getHeader("content-disposition")).thenReturn("form-data; name=\"photo\"; filename=\"ducati.png\"");
		when(file.getInputStream()).thenReturn(is);
		when(file.getSize()).thenReturn(3L);
		when(file.getName()).thenReturn("photo");
		when(request.getPart("photo")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_10 inserisce solo il codice_telaio , targa , colore , marchio , modello ,
	// kw , prezzo , path , accessori
	@Test
	public void TC4_10() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("Ducati");
		when(request.getParameter("modello")).thenReturn("Panigale-V4");
		when(request.getParameter("kw")).thenReturn("157");
		when(request.getParameter("prezzo")).thenReturn("29990");

		Part file = mock(Part.class);
		BufferedImage image = ImageIO.read(new File("D:/Desktop/ducati.png"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());

		when(file.getHeader("content-disposition")).thenReturn("form-data; name=\"photo\"; filename=\"ducati.png\"");
		when(file.getInputStream()).thenReturn(is);
		when(file.getSize()).thenReturn(3L);
		when(file.getName()).thenReturn("photo");
		when(request.getPart("photo")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("Borsa da serbatoio");
		when(request.getParameter("custom")).thenReturn("");

		// servlet.doGet(request, response);
		// verify(response).sendRedirect("adminHome.jsp");
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// TC4_11 Credenziali corrette
	@Test
	public void TC4_11() throws ServletException, IOException {
		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("rom3x87rt6jhm5014");
		when(request.getParameter("targa")).thenReturn("fg380dh");
		when(request.getParameter("colore")).thenReturn("Rosso");
		when(request.getParameter("marchio")).thenReturn("Ducati");
		when(request.getParameter("modello")).thenReturn("Panigale-V4");
		when(request.getParameter("kw")).thenReturn("157");
		when(request.getParameter("prezzo")).thenReturn("29990");

		Part file = mock(Part.class);
		BufferedImage image = ImageIO.read(new File("D:/Desktop/ducati.png"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());

		when(file.getHeader("content-disposition")).thenReturn("form-data; name=\"photo\"; filename=\"ducati.png\"");
		when(file.getInputStream()).thenReturn(is);
		when(file.getSize()).thenReturn(3L);
		when(file.getName()).thenReturn("photo");
		when(request.getPart("photo")).thenReturn(file);

		when(request.getParameter("accessori")).thenReturn("Borsa da serbatoio");
		when(request.getParameter("custom")).thenReturn("Si");

		servlet.doGet(request, response);
		verify(response).sendRedirect("adminHome.jsp");
	}

}
