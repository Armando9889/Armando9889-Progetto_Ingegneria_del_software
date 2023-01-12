package it.unisa.testing.control;

import it.unisa.control.ProductControl;
import it.unisa.model.bean.*;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
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

class LoginServletTest {

	private ServletContext context;
	private RequestDispatcher dispatcher;

	@Spy
	private ProductControl servlet;
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

		when(context.getRequestDispatcher("/homeUfficiale.jsp")).thenReturn(dispatcher);
		when(sg.getServletContext()).thenReturn(context);
		servlet.init();
	}

	// LOGIN ESATTO DAL CLIENTE
	@Test
	public void TC1_1() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("id")).thenReturn("giuseppe70");
		when(request.getParameter("sesso")).thenReturn("qwerty1");

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("GGDR1865GFEEFE");
		servlet.doGet(request, response);
		verify(response).sendRedirect("homeUfficiale.jsp");
	}

	// LOGIN ESATTO DELL'ADMIN
	@Test
	public void TC1_2() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("id")).thenReturn("Tullio");
		when(request.getParameter("sesso")).thenReturn("admin");

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn("DVGVLP37C71A067D");
		servlet.doGet(request, response);
		verify(response).sendRedirect("adminHome.jsp");
	}

	// LOGIN NON INSERIMENTO PASSWORD
	@Test
	public void TC1_3() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("id")).thenReturn("giuseppe70");
		when(request.getParameter("sesso")).thenReturn("");

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn(null);
		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// LOGIN NON INSERIMENTO USERNAME
	@Test
	public void TC1_4() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("sesso")).thenReturn("qwerty1");

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn(null);
		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

	// LOGIN EERORE INSERIMENTO
	@Test
	public void TC1_5() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("id")).thenReturn("giuseppe70");
		when(request.getParameter("sesso")).thenReturn("qwetyu2");

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn(null);
		servlet.doGet(request, response);
		verify(response).sendRedirect("errore.jsp");
	}


	// Login inserimento senza parametri
	@Test
	public void TC1_6() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("id")).thenReturn("");
		when(request.getParameter("sesso")).thenReturn("");

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("cf")).thenReturn(null);
		assertThrows(IllegalArgumentException.class, () -> servlet.doGet(request, response));
	}

}
