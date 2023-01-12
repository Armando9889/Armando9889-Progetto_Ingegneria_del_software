package it.unisa.testing.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import it.unisa.model.bean.ProductAcquisto;
import it.unisa.model.bean.ProductCarrello;
import it.unisa.model.bean.ProductCliente;
import it.unisa.query.QueryAcquisto;
import it.unisa.query.QueryCarrello;
import it.unisa.query.QueryCliente;
import it.unisa.utils.ConnectionDAO;

class AcquistoTestDAO {

	private HttpServletRequest mockedRequest;
	private RequestDispatcher mockedDispatcher;
	Context initContext;
	private QueryAcquisto queryAcquisto;
	private QueryCarrello queryCarrello;
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

	/******************** DO SAVE *************/

	// ACQUISTO EFFETTUATO
	@Test
	public void TC6_1() throws SQLException {

		queryAcquisto = new QueryAcquisto(ds);
		ProductAcquisto acquisto = new ProductAcquisto();

		acquisto.setTargaFK("ds123fs");
		acquisto.setCodice_fiscaleFK("BCCLDL92D30H501M");
		acquisto.setData_di_acquisto("01/03/2022");

		boolean result = queryAcquisto.doSave(acquisto);
		assertTrue(result);
	}

	// ACQUISTO NON EFFETTUATO TARGA NON ESISTENTE
	@Test
	public void TC6_2() throws SQLException {

		queryAcquisto = new QueryAcquisto(ds);
		ProductAcquisto acquisto = new ProductAcquisto();

		acquisto.setTargaFK("ds123fdsfs");
		acquisto.setCodice_fiscaleFK("GGDR1865GFEEFE");
		acquisto.setData_di_acquisto("01/03/2022");

		// boolean result = queryAcquisto.doSave(acquisto);
		// assertFalse(result);
		assertThrows(RuntimeException.class, () -> queryAcquisto.doSave(acquisto));
	}

	// ACQUISTO NON EFFETTUATO CODICE FISCALE NON ESISTENTE
	@Test
	public void TC6_3() throws SQLException {

		queryAcquisto = new QueryAcquisto(ds);
		ProductAcquisto acquisto = new ProductAcquisto();

		acquisto.setTargaFK("ds123fs");
		acquisto.setCodice_fiscaleFK("GGDR1865GF7676EEFE");
		acquisto.setData_di_acquisto("01/03/2022");

		// boolean result = queryAcquisto.doSave(acquisto);
		// assertFalse(result);
		assertThrows(RuntimeException.class, () -> queryAcquisto.doSave(acquisto));
	}

	// ACQUISTO NON INSERIMENTO DEI PARAMETRI
	@Test
	public void TC6_4() throws SQLException {

		queryAcquisto = new QueryAcquisto(ds);
		ProductAcquisto acquisto = new ProductAcquisto();

		acquisto.setTargaFK("");
		acquisto.setCodice_fiscaleFK("");
		acquisto.setData_di_acquisto("");

		// boolean result = queryAcquisto.doSave(acquisto);
		// assertFalse(result);
		assertThrows(RuntimeException.class, () -> queryAcquisto.doSave(acquisto));
	}

	/*************** DO RETRIEVEBYKEY *****************/

	// CODICE FISCALE ESISTENTE
	@Test
	public void TC6_5() throws SQLException {

		queryAcquisto = new QueryAcquisto(ds);
		ProductAcquisto acquisto = new ProductAcquisto();

		String codice_fiscale = "BCCLDL92D30H501M";
		acquisto = queryAcquisto.doRetrieveByKey(codice_fiscale);

		assertNotNull(acquisto);
	}

	// CODICE FISCALE NON ESISTENTE
	@Test
	public void TC6_6() throws SQLException {

		queryAcquisto = new QueryAcquisto(ds);
		ProductAcquisto acquisto = new ProductAcquisto();

		String codice_fiscale = "GGDR1865GFEEFEtrr";
		acquisto = queryAcquisto.doRetrieveByKey(codice_fiscale);

		assertNull(acquisto);
	}

	// CODICE FISCALE NON INSERITO
	@Test
	public void TC6_7() throws SQLException {

		queryAcquisto = new QueryAcquisto(ds);
		ProductAcquisto acquisto = new ProductAcquisto();

		String codice_fiscale = "";
		acquisto = queryAcquisto.doRetrieveByKey(codice_fiscale);

		assertNull(acquisto);
	}
	
/******************** DO SAVE *************/
	
	// CARRELLO INSERITO CORRETTAMENTE
		@Test
		public void TC7_1() throws SQLException {

			queryCarrello = new QueryCarrello(ds);
			ProductCarrello carrello = new ProductCarrello();
			carrello.setTarga("ds123fs");
			carrello.setModello("x5");
			carrello.setPrezzo(38500);
			carrello.setSessionid("8DF5C2A732F8B7F8044A196786F8B4AC");
			carrello.setutenteid("GGDR1865GFEEFE");

			boolean result = queryCarrello.doSave(carrello);
			assertTrue(result);
		}

}
