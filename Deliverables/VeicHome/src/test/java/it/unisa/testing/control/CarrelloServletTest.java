package it.unisa.testing.control;

import it.unisa.control.Carrello;
import it.unisa.control.Register;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class CarrelloServletTest {

	private ServletContext context;
	private RequestDispatcher dispatcher;

	@Spy
	private Carrello servlet;
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

	// NON INSERIMENTO DI CREDENZIALI
	@Test
	public void TC7_1() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("targa")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("session")).thenReturn("");
		when(request.getSession()).thenReturn(session);		
		when(session.getAttribute("cf")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// INSERIMENTO SOLO TARGA
	@Test
	public void TC7_2() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("targa")).thenReturn("dg719gh");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("session")).thenReturn("");
		when(request.getSession()).thenReturn(session);		
		when(session.getAttribute("cf")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// INSERIMENTO SOLO TARGA, MODELLO
	@Test
	public void TC7_3() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("targa")).thenReturn("dg719gh");
		when(request.getParameter("modello")).thenReturn("serie1");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("session")).thenReturn("");
		when(request.getSession()).thenReturn(session);		
		when(session.getAttribute("cf")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// INSERIMENTO SOLO TARGA, MODELLO, PREZZO
	@Test
	public void TC7_4() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("targa")).thenReturn("dg719gh");
		when(request.getParameter("modello")).thenReturn("serie1");
		when(request.getParameter("prezzo")).thenReturn("22500");
		when(request.getParameter("session")).thenReturn("");
		when(request.getSession()).thenReturn(session);		
		when(session.getAttribute("cf")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// INSERIMENTO TUTTE LE CREDENZIALI
	@Test
	public void TC7_5() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("targa")).thenReturn("dg719gh");
		when(request.getParameter("modello")).thenReturn("serie1");
		when(request.getParameter("prezzo")).thenReturn("22500");
		when(request.getParameter("session")).thenReturn("8DF5C2A732F8B7F8044A196786F8B4AC");
		when(request.getSession()).thenReturn(session);		
		when(session.getAttribute("cf")).thenReturn("GGDR1865GFEEFE");
		
		servlet.doGet(request, response);
		verify(response).sendRedirect("homeUfficiale.jsp");
	}

}
