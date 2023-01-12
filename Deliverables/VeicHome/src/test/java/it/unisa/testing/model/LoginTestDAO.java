package it.unisa.testing.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import it.unisa.model.bean.ProductCliente;
import it.unisa.query.*;
import it.unisa.utils.ConnectionDAO;
import it.unisa.utils.Utility;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

class LoginTestDAO {

	private HttpServletRequest mockedRequest;
	private RequestDispatcher mockedDispatcher;
	Context initContext;
	private login login;
	DataSource ds;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setUp() throws SQLException, NamingException {

		mockedRequest = Mockito.mock(HttpServletRequest.class);
		mockedDispatcher = Mockito.mock(RequestDispatcher.class);

		ConnectionDAO conn = new ConnectionDAO();
		ds = conn.connessioneDAO();

	}

	/********** LOGIN **********/

	// LOGIN DEL CLIENTE
	@Test
	public void TC1_1() throws Exception {

		login = new login(ds);
		String username = "landolfo92";
		String password = "prova2";
		String oracle = "BCCLDL92D30H501M";

		String result = login.login(username, password);
		assertEquals(oracle, result);

	}

	// LOGIN DELL'ADMIN
	@Test
	public void TC1_2() throws Exception {

		login = new login(ds);
		String username = "Tullio";
		String password = "admin";
		String oracle = "DVGVLP37C71A067D";

		String result = login.login(username, password);
		assertEquals(oracle, result);

	}

	// LOGIN INSERIMENTO DI NESSUN PARAMETRO
	@Test
	public void TC1_3() throws Exception {

		login = new login(ds);
		String username = "";
		String password = "";
		String oracle = "error";

		String result = login.login(username, password);
		assertEquals(oracle, result);

	}
	
	// LOGIN INSERIMENTO DI SOLO USERNAME
	@Test
	public void TC1_4() throws Exception {

		login = new login(ds);
		String username = "landolfo92";
		String password = "";
		String oracle = "error";

		String result = login.login(username, password);
		assertEquals(oracle, result);

	}
	
	// LOGIN INSERIMENTO DI SOLO PASSWORD
	@Test
	public void TC1_5() throws Exception {

		login = new login(ds);
		String username = "";
		String password = "prova2";
		String oracle = "error";

		String result = login.login(username, password);
		assertEquals(oracle, result);

	}
	
	// LOGIN INSERIMENTO PARAMETRI SBAGLIATI
	@Test
	public void TC1_6() throws Exception {

		login = new login(ds);
		String username = "Lcdo454";
		String password = "prv454";
		String oracle = "error";

		String result = login.login(username, password);
		assertEquals(oracle, result);

	}

}
