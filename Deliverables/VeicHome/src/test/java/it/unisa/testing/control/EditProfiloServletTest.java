package it.unisa.testing.control;

import it.unisa.control.Carrello;
import it.unisa.control.EditProfilo;

import static org.junit.jupiter.api.Assertions.*;
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

class EditProfiloServletTest {

	private ServletContext context;
	private RequestDispatcher dispatcher;

	@Spy
	private EditProfilo servlet;
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
	public void TC8_1() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("cf")).thenReturn("");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// INSERIMENTO SOLO CF
	@Test
	public void TC8_2() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// INSERIMENTO CF E MODIFICA USERNAME
	@Test
	public void TC8_3() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("landolfo93");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		servlet.doGet(request, response);
		verify(response).sendRedirect("MyProfilo.jsp");
	}
	
	// INSERIMENTO CF E MODIFICA INDIRIZZO
	@Test
	public void TC8_4() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("Via Piave 21");
		when(request.getParameter("numero_di_carta")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		servlet.doGet(request, response);
		verify(response).sendRedirect("MyProfilo.jsp");
	}
	
	// INSERIMENTO CF E MODIFICA INDIRIZZO E MODIFICA USERNAME
	@Test
	public void TC8_5() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("landolfo92");
		when(request.getParameter("indirizzo")).thenReturn("Via Roma 5");
		when(request.getParameter("numero_di_carta")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		servlet.doGet(request, response);
		verify(response).sendRedirect("MyProfilo.jsp");
	}
	
	// INSERIMENTO CF E MODIFICA INDIRIZZO SBAGLIATO
	@Test
	public void TC8_6() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("landolfo92");
		when(request.getParameter("indirizzo")).thenReturn("Via-? Roma 5");
		when(request.getParameter("numero_di_carta")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	//INSERIMENTO SOLO NUMERO DI CARTA 
	@Test
	public void TC8_7() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("4485136236688436");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	//INSERIMENTO SOLO NUMERO DI CARTA E CVV
	@Test
	public void TC8_8() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("4485198853818907");
		when(request.getParameter("cvv")).thenReturn("589");
		when(request.getParameter("scadenza")).thenReturn("1");
		when(request.getParameter("scadenza_anno")).thenReturn("2023");
		
		servlet.doGet(request, response);
		verify(response).sendRedirect("MyProfilo.jsp");
	}
	
	//INSERIMENTO SOLO NUMERO DI CARTA GIA' IN USO
	@Test
	public void TC8_9() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("4485198853818907");
		when(request.getParameter("cvv")).thenReturn("589");
		when(request.getParameter("scadenza")).thenReturn("1");
		when(request.getParameter("scadenza_anno")).thenReturn("2023");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	//INSERIMENTO SOLO NUMERO DI CARTA CAMPI ERRATI
	@Test
	public void TC8_10() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("44851988-*-18902");
		when(request.getParameter("cvv")).thenReturn("589");
		when(request.getParameter("scadenza")).thenReturn("1");
		when(request.getParameter("scadenza_anno")).thenReturn("2023");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	//INSERIMENTO SOLO NUMERO DI CARTA CAMPI ERRATI > 16 CIFRE
	@Test
	public void TC8_11() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("4485198854654456418902");
		when(request.getParameter("cvv")).thenReturn("589");
		when(request.getParameter("scadenza")).thenReturn("1");
		when(request.getParameter("scadenza_anno")).thenReturn("2023");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	//INSERIMENTO SOLO NUMERO DI CARTA E CVV ERRATO
	@Test
	public void TC8_12() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("4485198853818902");
		when(request.getParameter("cvv")).thenReturn("5/-");
		when(request.getParameter("scadenza")).thenReturn("1");
		when(request.getParameter("scadenza_anno")).thenReturn("2023");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));	
	}
	
	//INSERIMENTO USERNAME GIA' ESISTENTE
	@Test
	public void TC8_13() throws ServletException, IOException {

		servlet.prova();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("BCCLDL92D30H501M");
		when(request.getParameter("id")).thenReturn("landolfo92");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("numero_di_carta")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		
		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));	
	}
}
