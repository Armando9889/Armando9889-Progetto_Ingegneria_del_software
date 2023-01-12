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

import it.unisa.control.EliminaVeicolo;
import it.unisa.control.InserimentoAuto;

class EliminaVeicoloServletTest {

	private ServletContext context;
	private RequestDispatcher dispatcher;
	
	@Spy
	private EliminaVeicolo servlet;
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

	//Credenziali non inserite
	@Test
	public void TC5_1() throws ServletException, IOException {
		
		servlet.prova();
		when(request.getParameter("telaio")).thenReturn("");
		when(request.getParameter("targa")).thenReturn("");

		//servlet.doGet(request, response);
		//verify(response).sendRedirect("adminHome.jsp");
		assertThrows(IllegalArgumentException.class, ()-> servlet.doGet(request,response));
	}
	
	//Inserimento solo targa
	@Test
	public void TC5_2() throws ServletException, IOException {
		
		servlet.prova();
		when(request.getParameter("telaio")).thenReturn("");
		when(request.getParameter("targa")).thenReturn("pl458io");

		//servlet.doGet(request, response);
		//verify(response).sendRedirect("adminHome.jsp");
		assertThrows(IllegalArgumentException.class, ()-> servlet.doGet(request,response));
	}
	
	//Inserimento solo codice telaio
	@Test
	public void TC5_3() throws ServletException, IOException {
		
		servlet.prova();
		when(request.getParameter("telaio")).thenReturn("mvcnhr76h6cio4534");
		when(request.getParameter("targa")).thenReturn("");

		//servlet.doGet(request, response);
		//verify(response).sendRedirect("adminHome.jsp");
		assertThrows(IllegalArgumentException.class, ()-> servlet.doGet(request,response));
	}
	
	//Inserimento targa non esistente
	@Test
	public void TC5_4() throws ServletException, IOException {
		
		servlet.prova();
		when(request.getParameter("telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("pl975ij");

		assertThrows(RuntimeException.class, ()-> servlet.doGet(request,response));
	}
	
	//Inserimento codice telaio non esistente
	@Test
	public void TC5_5() throws ServletException, IOException {
		
		servlet.prova();
		when(request.getParameter("telaio")).thenReturn("mvcnhr98h6ioo4534");
		when(request.getParameter("targa")).thenReturn("po897nm");
		
		assertThrows(RuntimeException.class, ()-> servlet.doGet(request,response));
	}
	
	//Inserimento di tutti i parametri giusti
	@Test
	public void TC5_6() throws ServletException, IOException {
		
		servlet.prova();
		when(request.getParameter("telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");

		servlet.doGet(request, response);
		verify(response).sendRedirect("adminHome.jsp");
		//assertThrows(IllegalArgumentException.class, ()-> servlet.doGet(request,response));
	}

}
