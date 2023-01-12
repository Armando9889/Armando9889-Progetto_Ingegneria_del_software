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

import it.unisa.control.Acquisto;
import it.unisa.control.ProductControl;

class AcquistoServletTest {


	private ServletContext context;
	private RequestDispatcher dispatcher;
	
	@Spy
	private Acquisto servlet;
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
	
	//Acquisto effettuato
	@Test
	public void TC6_1() throws ServletException, IOException {
		
		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("GGDR1865GFEEFE");
		when(request.getParameter("targa")).thenReturn("jd791ds");
		when(request.getParameter("prezzoSessione")).thenReturn("60000");
		when(request.getParameter("prezzo")).thenReturn("50000");

		servlet.doGet(request, response);
		verify(response).sendRedirect("Acquistato?modello=jd791ds");
		
		
	}

}