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

class RegisterTestDAO {

	private HttpServletRequest mockedRequest;
	private RequestDispatcher mockedDispatcher;
	Context initContext;
	private QueryCliente queryCliente;
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

	/*************************** DO SAVE INSERIMENTO SENZA PARAMETRI *************/

	// NESSUNA DELLE CREDENZIALI
	@Test
	public void TC2_1() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("");
		cliente.setNome("");
		cliente.setCognome("");
		cliente.setSesso("");
		cliente.setIndirizzo("");
		cliente.setData_di_nascita("");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO DEL CODICE FISCALE
	@Test
	public void TC2_2() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("");
		cliente.setCognome("");
		cliente.setSesso("");
		cliente.setIndirizzo("");
		cliente.setData_di_nascita("");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE E NOME
	@Test
	public void TC2_3() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("");
		cliente.setSesso("");
		cliente.setIndirizzo("");
		cliente.setData_di_nascita("");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME
	@Test
	public void TC2_4() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("");
		cliente.setIndirizzo("");
		cliente.setData_di_nascita("");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO
	@Test
	public void TC2_5() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("");
		cliente.setData_di_nascita("");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO
	@Test
	public void TC2_6() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO
	@Test
	public void TC2_7() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO E DATA DI
	// NASCITA
	@Test
	public void TC2_8() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO E DATA DI
	// NASCITA E PASSWORD
	@Test
	public void TC2_9() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO E DATA DI
	// NASCITA E PASSWORD E NUMERO DI CARTA
	@Test
	public void TC2_10() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO E DATA DI
	// NASCITA E PASSWORD E NUMERO DI CARTA
	@Test
	public void TC2_11() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO E DATA DI
	// NASCITA E PASSWORD E NUMERO DI CARTA E
	// DATA DI SCADENZA
	@Test
	public void TC2_12() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO E DATA DI
	// NASCITA E PASSWORD E NUMERO DI CARTA E
	// DATA DI SCADENZA E CVV
	@Test
	public void TC2_13() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO SOLO CODICE FISCALE NOME E COGNOME E SESSO INDIRIZZO E DATA DI
	// NASCITA E PASSWORD E NUMERO DI CARTA E
	// DATA DI SCADENZA E CVV
	@Test
	public void TC2_14() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	/*************************** DO SAVE INSERIMENTO CONTROLLO NOME *************/

	// INSERIMENTO NOME ERRATO
	@Test
	public void TC2_15() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Liu9789-*");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO COGNOME ERRATO
	@Test
	public void TC2_16() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("?)/&cd");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO NUMERO CARTA ERRATO
	@Test
	public void TC2_17() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("47165?55A39cd493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO LUNGHEZZA CARTA ERRATO
	@Test
	public void TC2_18() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("47139cd493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO NUMERO CVV ERRATO
	@Test
	public void TC2_19() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("6-a");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO LUNGHEZZA CVV ERRATO
	@Test
	public void TC2_20() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("456464");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// INSERIMENTO COMUNE DI NASCITA ERRATO
	@Test
	public void TC2_21() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("456464");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Na646445");

		boolean result = queryCliente.doSave(cliente);
		assertFalse(result);
	}

	// DO SAVE INSERIMENTO CORRETTO
	@Test
	public void TC2_23() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		boolean result = queryCliente.doSave(cliente);
		assertTrue(result);
	}

	// DO SAVE INSERIMENTO CORRETTO CLIENTE GIA' ESISTENTE
	@Test
	public void TC2_24() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setNome("Luigi");
		cliente.setCognome("Siciliano");
		cliente.setSesso("M");
		cliente.setIndirizzo("Via San Cosmo fuori Porta Nolana 96");
		cliente.setData_di_nascita("1971-10-27");
		cliente.setPassword("prova3");
		cliente.setNumero_di_carta("4716525593946493");
		cliente.setData_scadenza("04/2024");
		cliente.setCvv("690");
		cliente.setUsername("luigi71");
		cliente.setComune_di_nascita("Napoli");

		assertThrows(RuntimeException.class, () -> queryCliente.doSave(cliente));
	}

	/***************************
	 * CONTROLLO 
	 *************/

	// CONTROLLO USERNAME ESISTENTE
	@Test
	public void TC2_25() throws SQLException {

		queryCliente = new QueryCliente(ds);
		String username = "luigi71";

		boolean result = queryCliente.controllo(username);
		assertTrue(result);
	}

	// CONTROLLO USERNAME NON ESISTENTE
	@Test
	public void TC2_26() throws SQLException {

		queryCliente = new QueryCliente(ds);
		String username = "pasquale21";

		boolean result = queryCliente.controllo(username);
		assertFalse(result);
	}

	// CONTROLLO USERNAME NON INSERITO
	@Test
	public void TC2_27() throws SQLException {

		queryCliente = new QueryCliente(ds);
		String username = "";

		boolean result = queryCliente.controllo(username);
		assertFalse(result);
	}
	
	/***************************
	 * DORETRIEVEBYKEY INSERIMENTO CONTROLLO USERNAME E PASSWORD
	 *************/
	
	// CONTROLLO USERNAME E PASSWORD ESISTENTE
	@Test
	public void TC2_28() throws SQLException {

		queryCliente = new QueryCliente(ds);
		String username = "luigi71";
		String password = "prova3";

		ProductCliente result = queryCliente.doRetrieveByKey(username, password);
		assertNotNull(result);
	}
	
	// CONTROLLO USERNAME NON EISTENTE E PASSWORD ESISTENTE
	@Test
	public void TC2_29() throws SQLException {

		queryCliente = new QueryCliente(ds);
		String username = "";
		String password = "prova3";

		ProductCliente result = queryCliente.doRetrieveByKey(username, password);
		assertNull(result);
	}
	
	// CONTROLLO USERNAME EISTENTE E PASSWORD NON ESISTENTE
	@Test
	public void TC2_30() throws SQLException {

		queryCliente = new QueryCliente(ds);
		String username = "luigi71";
		String password = "";

		ProductCliente result = queryCliente.doRetrieveByKey(username, password);
		assertNull(result);
	}
	
	// CONTROLLO USERNAME NON EISTENTE E PASSWORD NON ESISTENTE
	@Test
	public void TC2_31() throws SQLException {

		queryCliente = new QueryCliente(ds);
		String username = "GFDG";
		String password = "GDG";

		ProductCliente result = queryCliente.doRetrieveByKey(username, password);
		assertNull(result);
	}
	
	/***************************
	 * DOUPDATESALDO 
	 *************/
	
	//CONTROLLO CODICE FISCALE NON ESISTENTE
	@Test
	public void TC2_32() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("");
		cliente.setSaldo(10000);

		assertThrows(RuntimeException.class, () -> queryCliente.doUpdateSaldo(cliente));
	}
	
	//CONTROLLO CODICE FISCALE ESISTENTE E SALDO MINORE
	@Test
	public void TC2_33() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setSaldo(-10);

		assertThrows(RuntimeException.class, () -> queryCliente.doUpdateSaldo(cliente));
	}
	
	//CONTROLLO CODICE FISCALE ESISTENTE E SALDO VALIDO
	@Test
	public void TC2_35() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("SCLLGU71R27F839B");
		cliente.setSaldo(20000);
		
		boolean result = queryCliente.doUpdateSaldo(cliente);
		assertTrue(result);
		
	}
		
	/***************************
	 * DOUPDATE MODIFICA PROFILO
	 *************/
	
	//MODIFICA SOLO USERNAME 
	@Test
	public void TC2_36() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setUsername("landolfo92");
		
		boolean result = queryCliente.doUpdate(cliente);
		assertTrue(result);
		//assertThrows(RuntimeException.class, () -> queryCliente.doUpdateSaldo(cliente));
	}
	
	//MODIFICA SOLO USERNAME E INDIRIZZO
	@Test
	public void TC2_37() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setUsername("landolfo92");
		cliente.setIndirizzo("Via roma 64");
		
		boolean result = queryCliente.doUpdate(cliente);
		assertTrue(result);
		//assertThrows(RuntimeException.class, () -> queryCliente.doUpdateSaldo(cliente));
	}
	
	//MODIFICA TUTTI I PARAMETRI
	@Test
	public void TC2_38() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setUsername("landolfo92");
		cliente.setIndirizzo("Via roma 64");
		cliente.setNumero_di_carta("4024007169490819");
		cliente.setData_scadenza("04/2025");
		cliente.setCvv("488");
		
		boolean result = queryCliente.doUpdate(cliente);
		assertTrue(result);
		//assertThrows(RuntimeException.class, () -> queryCliente.doUpdateSaldo(cliente));
	}
	
	//MODIFICA SOLO INDIRIZZO 
	@Test
	public void TC2_39() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setIndirizzo("Via roma 64");
		
		boolean result = queryCliente.doUpdate(cliente);
		assertTrue(result);
		//assertThrows(RuntimeException.class, () -> queryCliente.doUpdateSaldo(cliente));
	}
	
	//MODIFICA SOLO  DATI DI PAGAMENTO
	@Test
	public void TC2_40() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setNumero_di_carta("4024007169490819");
		cliente.setData_scadenza("04/2025");
		cliente.setCvv("488");
		
		boolean result = queryCliente.doUpdate(cliente);
		assertTrue(result);
		//assertThrows(RuntimeException.class, () -> queryCliente.doUpdateSaldo(cliente));
	}
	
	//MODIFICA SOLO USERNAME E INDIRIZZO SBAGLIATO
	@Test
	public void TC2_41() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setUsername("landolfo92");
		cliente.setIndirizzo("*-*-*-*-");
		
		//boolean result = queryCliente.doUpdate(cliente);
		//assertTrue(result);
		assertThrows(RuntimeException.class, () -> queryCliente.doUpdate(cliente));
	}
	
	//MODIFICA SOLO USERNAME E DATI DI PAGAMENTO SBAGLIATO
	@Test
	public void TC2_42() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setUsername("landolfo92");
		cliente.setNumero_di_carta("40240071ab0819");
		cliente.setData_scadenza("04/2025");
		cliente.setCvv("488");
		
		//boolean result = queryCliente.doUpdate(cliente);
		//assertTrue(result);
		assertThrows(RuntimeException.class, () -> queryCliente.doUpdate(cliente));
	}
	
	//MODIFICA SOLO USERNAME E DATI DI PAGAMENTO LUNGHEZZA SUPERATA
	@Test
	public void TC2_43() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setUsername("landolfo92");
		cliente.setNumero_di_carta("402400717980819");
		cliente.setData_scadenza("04/2025");
		cliente.setCvv("489898");
		
		//boolean result = queryCliente.doUpdate(cliente);
		//assertTrue(result);
		assertThrows(RuntimeException.class, () -> queryCliente.doUpdate(cliente));
	}
	
	//MODIFICA SOLO INDIRIZZO E DATI DI PAGAMENTO 
	@Test
	public void TC2_44() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setIndirizzo("Via roma 64");
		cliente.setNumero_di_carta("4024007169490819");
		cliente.setData_scadenza("04/2025");
		cliente.setCvv("489");
		
		boolean result = queryCliente.doUpdate(cliente);
		assertTrue(result);
		//assertThrows(RuntimeException.class, () -> queryCliente.doUpdate(cliente));
	}
	
	//MODIFICA SOLO INDIRIZZO SBAGLIATO E DATI DI PAGAMENTO 
	@Test
	public void TC2_45() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setIndirizzo("Via !!!76 roma 64");
		cliente.setNumero_di_carta("4024007169490819");
		cliente.setData_scadenza("04/2025");
		cliente.setCvv("489");
		
		//boolean result = queryCliente.doUpdate(cliente);
		//assertTrue(result);
		assertThrows(RuntimeException.class, () -> queryCliente.doUpdate(cliente));
	}
	
	//MODIFICA DI NESSUN PARAMETRO
	@Test
	public void TC2_46() throws SQLException {

		queryCliente = new QueryCliente(ds);
		ProductCliente cliente = new ProductCliente();
		cliente.setCodice_fiscale("BCCLDL92D30H501M");
		cliente.setUsername("");
		cliente.setIndirizzo("");
		cliente.setNumero_di_carta("");
		cliente.setData_scadenza("");
		cliente.setCvv("");
		
		//boolean result = queryCliente.doUpdate(cliente);
		//assertTrue(result);
		assertThrows(RuntimeException.class, () -> queryCliente.doUpdate(cliente));
	}
	

}
