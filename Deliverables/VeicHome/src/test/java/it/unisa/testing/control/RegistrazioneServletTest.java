package it.unisa.testing.control;

import it.unisa.control.Register;
import it.unisa.model.bean.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

class RegistrazioneServletTest {

	private ServletContext context;
	private RequestDispatcher dispatcher;

	@Spy
	private Register servlet;
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
	public void TC2_1() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("");
		when(request.getParameter("surname")).thenReturn("");
		when(request.getParameter("sesso")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("data")).thenReturn("");
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME
	@Test
	public void TC2_2() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("");
		when(request.getParameter("sesso")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("data")).thenReturn("");
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME
	@Test
	public void TC2_3() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("data")).thenReturn("");
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO
	@Test
	public void TC2_4() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("");
		when(request.getParameter("data")).thenReturn("");
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO
	@Test
	public void TC2_5() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("");
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO E DATA DI NASCITA
	@Test
	public void TC2_6() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO E DATA DI NASCITA E PASSWORD
	@Test
	public void TC2_7() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO E DATA DI NASCITA E PASSWORD E NUMERO DI CARTA
	@Test
	public void TC2_8() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO E DATA DI NASCITA E PASSWORD E NUMERO DI CARTA E SCADENZA MESE
	@Test
	public void TC2_9() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO E DATA DI NASCITA E PASSWORD E NUMERO DI CARTA E SCADENZA MESE
	// SCADENZA ANNO
	@Test
	public void TC2_10() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO E DATA DI NASCITA E PASSWORD E NUMERO DI CARTA E SCADENZA MESE
	// SCADENZA ANNO E CVV
	@Test
	public void TC2_11() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO NOME E COGNOME E SESSO E INDIRIZZO E DATA DI NASCITA E PASSWORD E NUMERO DI CARTA E SCADENZA MESE
	// SCADENZA ANNO E CVV E USERNAME
	@Test
	public void TC2_12() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// LUNGHEZZA NUMERO DI CARTA NON CORRETTO
	@Test
	public void TC2_13() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("515792293924021933");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// LUNGHEZZA CVV NON CORRETTO
	@Test
	public void TC2_14() throws ServletException, IOException {

		servlet.prova();
		// when(request.getParameter("id")).thenReturn("MLNTTV36S17C675B");
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("11345");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// NOME NON CORRETTO
	@Test
	public void TC2_15() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio465464");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// COGNOME NON CORRETTO
	@Test
	public void TC2_16() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio465464");
		when(request.getParameter("surname")).thenReturn("*&%45");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// COMUNE DI NASCITA NON CORRETTO
	@Test
	public void TC2_17() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Nap***gs4787");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// INDIRIZZO NON CORRETTO
	@Test
	public void TC2_18() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Pa287 15***R54*");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// CARTA NON CORRETTO
	@Test
	public void TC2_19() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("51579v9392*0219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// CVV NON CORRETTO
	@Test
	public void TC2_20() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("1B3");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// CREDENZIALI CORRETTE
	@Test
	public void TC2_21() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		servlet.doGet(request, response);
		verify(response).sendRedirect("login.jsp");
	}
	
	// USERNAME ESISTENTE
	@Test
	public void TC2_22() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("1579229392405487");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio36");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}
	
	// CARTA ESISTENTE
	@Test
	public void TC2_23() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("name")).thenReturn("Ottavio");
		when(request.getParameter("surname")).thenReturn("Milano");
		when(request.getParameter("sesso")).thenReturn("M");
		when(request.getParameter("indirizzo")).thenReturn("Via Palermo 27");
		when(request.getParameter("data")).thenReturn("1936-11-17");
		when(request.getParameter("password")).thenReturn("prova1");
		when(request.getParameter("carta")).thenReturn("5157922939240219");
		when(request.getParameter("scadenza")).thenReturn("6");
		when(request.getParameter("scadenza_anno")).thenReturn("2027");
		when(request.getParameter("cvv")).thenReturn("113");
		when(request.getParameter("username")).thenReturn("ottavio38");
		when(request.getParameter("comune")).thenReturn("Napoli");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

}
