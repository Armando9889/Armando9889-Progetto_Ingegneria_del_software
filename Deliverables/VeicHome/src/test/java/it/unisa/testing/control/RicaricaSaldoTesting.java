package it.unisa.testing.control;

import it.unisa.control.Carrello;
import it.unisa.control.Ricarica;

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

class RicaricaSaldoTesting {

	private ServletContext context;
	private RequestDispatcher dispatcher;

	@Spy
	private Ricarica servlet;
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
	
	// INSERIMENTO CORRETTO CREDENZIALI
	@Test
	public void TC9_1() throws ServletException, IOException {

		servlet.prova();
		
		when(request.getParameter("codice_fiscale")).thenReturn("GGDR1865GFEEFE");
		when(request.getParameter("ricarica")).thenReturn("1000");
		when(request.getParameter("saldo_precedente")).thenReturn("35000");
		when(request.getSession()).thenReturn(session);
		servlet.doGet(request, response);
		verify(response).sendRedirect("homeUfficiale.jsp");
	}
	
	//INSERIMENTO ERRATO CREDENZIALI SALDO
	@Test
	public void TC9_2() throws ServletException, IOException {

		servlet.prova();
		
		when(request.getParameter("codice_fiscale")).thenReturn("GGDR1865GFEEFE");
		when(request.getParameter("ricarica")).thenReturn("0");
		when(request.getParameter("saldo_precedente")).thenReturn("35000");
		when(request.getSession()).thenReturn(session);
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	//INSERIMENTO ERRATO CF VUOTO
	@Test
	public void TC9_3() throws ServletException, IOException {

		servlet.prova();
		
		when(request.getParameter("codice_fiscale")).thenReturn("");
		when(request.getParameter("ricarica")).thenReturn("1000");
		when(request.getParameter("saldo_precedente")).thenReturn("35000");
		when(request.getSession()).thenReturn(session);
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

}
