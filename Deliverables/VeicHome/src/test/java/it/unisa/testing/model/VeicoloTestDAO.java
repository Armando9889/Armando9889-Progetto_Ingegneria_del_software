package it.unisa.testing.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import it.unisa.model.bean.ProductVeicolo;
import it.unisa.query.QueryVeicolo;
import it.unisa.utils.ConnectionDAO;

class VeicoloTestDAO {

	private HttpServletRequest mockedRequest;
	private RequestDispatcher mockedDispatcher;
	Context initContext;
	private QueryVeicolo queryVeicolo;
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

	/******************* doRetrieveByKey1 ****************************/

	// RICERCA DI UN VEICOLO ESISTENTE
	@Test
	public void TC5_6() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo = queryVeicolo.doRetrieveByKey1("AUm3RBf4WWNYz1249", "bd698hj");

		assertNotNull(veicolo);
	}

	// RICERCA DI UN VEICOLO SENZA PARAMETRI
	@Test
	public void TC5_7() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo = queryVeicolo.doRetrieveByKey1("", "");

		assertNull(veicolo);
	}

	// RICERCA DI UN VEICOLO SENZA CODICE TELAIO
	@Test
	public void TC5_8() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo = queryVeicolo.doRetrieveByKey1("", "fg380dh");

		assertNull(veicolo);
	}

	// RICERCA DI UN VEICOLO SENZA TARGA
	@Test
	public void TC5_9() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo = queryVeicolo.doRetrieveByKey1("rom3x87rt6jhm5014", "");

		assertNull(veicolo);
	}

	// RICERCA DI UN VEICOLO NON ESISTENTE
	@Test
	public void TC5_10() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo = queryVeicolo.doRetrieveByKey1("rom3xFG87rt6jhm5014", "zx214xc");

		assertNull(veicolo);
	}

	/******************* doInsertVeicoloAuto ****************************/

	// NESSUNA DELLE CREDENZIALI
	@Test
	public void TC5_11() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("");
		veicolo.setTarga("");
		veicolo.setColore("");
		veicolo.setMarchio("");
		veicolo.setModello("");
		veicolo.setKw(0);
		veicolo.setPrezzo(0);
		veicolo.setPartita_iva("");
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO
	@Test
	public void TC5_12() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("");
		veicolo.setColore("");
		veicolo.setMarchio("");
		veicolo.setModello("");
		veicolo.setKw(0);
		veicolo.setPrezzo(0);
		veicolo.setPartita_iva("");
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA
	@Test
	public void TC5_13() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("");
		veicolo.setMarchio("");
		veicolo.setModello("");
		veicolo.setKw(0);
		veicolo.setPrezzo(0);
		veicolo.setPartita_iva("");
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE
	@Test
	public void TC5_14() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("");
		veicolo.setModello("");
		veicolo.setKw(0);
		veicolo.setPrezzo(0);
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE E MARCHIO
	@Test
	public void TC5_15() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("");
		veicolo.setKw(0);
		veicolo.setPrezzo(0);
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE E MARCHIO E MODELLO
	@Test
	public void TC5_16() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("Raptor-F150");
		veicolo.setKw(0);
		veicolo.setPrezzo(0);
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE E MARCHIO E MODELLO E KW
	@Test
	public void TC5_17() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("Raptor-F150");
		veicolo.setKw(200);
		veicolo.setPrezzo(0);
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE E MARCHIO E MODELLO E KW E
	// PREZZO

	@Test
	public void TC5_18() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("Raptor-F150");
		veicolo.setKw(200);
		veicolo.setPrezzo(55000);
		veicolo.setPhoto("");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE E MARCHIO E MODELLO E KW E
	// PREZZO E PHOTO
	@Test
	public void TC5_19() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("Raptor-F150");
		veicolo.setKw(200);
		veicolo.setPrezzo(55000);
		veicolo.setPhoto("uploadImage/raptor.png");
		veicolo.setNumero_passegeri(0);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE E MARCHIO E MODELLO E KW E
	// PREZZO E PHOTO E #PASSEGERI
	@Test
	public void TC5_20() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("Raptor-F150");
		veicolo.setKw(200);
		veicolo.setPrezzo(55000);
		veicolo.setPhoto("uploadImage/raptor.png");
		veicolo.setNumero_passegeri(5);
		veicolo.setSconto("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertFalse(resultVeicolo);
	}

	// CREDENZIALI CORRETTE
	@Test
	public void TC5_21() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("Raptor-F150");
		veicolo.setKw(54);
		veicolo.setPrezzo(55000);
		veicolo.setPhoto("uploadImage/raptor.png");
		veicolo.setNumero_passegeri(5);
		veicolo.setSconto("3%");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		assertTrue(resultVeicolo);
	}

	// CREDENZIALI CORRETTE MA VEICOLO ESISTENTE
	@Test
	public void TC5_22() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("mvcnhr76h6cio4534");
		veicolo.setTarga("pl458io");
		veicolo.setColore("Blu");
		veicolo.setMarchio("Ford");
		veicolo.setModello("Raptor-F150");
		veicolo.setKw(54);
		veicolo.setPrezzo(55000);
		veicolo.setPhoto("uploadImage/raptor.png");
		veicolo.setNumero_passegeri(5);
		veicolo.setSconto("3%");

		// boolean resultVeicolo = queryVeicolo.doInsertVeicoloAuto(veicolo);
		// assertTrue(resultVeicolo);
		assertThrows(SQLIntegrityConstraintViolationException.class, () -> queryVeicolo.doInsertVeicoloAuto(veicolo));
	}

	/******************* doInsertVeicoloMoto ****************************/
	// INSERIMENTO SOLO CODICE TELAIO TARGA E COLORE E MARCHIO E MODELLO E KW E
	// PREZZO E PHOTO E ACCESSORI
	@Test
	public void TC5_23() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("Waz85ndAyBsXE6947");
		veicolo.setTarga("qw728jk");
		veicolo.setColore("Bianco");
		veicolo.setMarchio("Aprilia");
		veicolo.setModello("Rsv-1000");
		veicolo.setKw(105);
		veicolo.setPrezzo(15000);
		veicolo.setPhoto("uploadImage/aprilia.png");
		veicolo.setAccessori("Tuta integrale");
		veicolo.setCustom("");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloMoto(veicolo);
		assertFalse(resultVeicolo);
	}

	// CREDENZIALI CORRETTE
	@Test
	public void TC5_24() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("Waz85ndAyBsXE6947");
		veicolo.setTarga("qw728jk");
		veicolo.setColore("Bianco");
		veicolo.setMarchio("Aprilia");
		veicolo.setModello("Rsv-1000");
		veicolo.setKw(105);
		veicolo.setPrezzo(15000);
		veicolo.setPhoto("uploadImage/aprilia.png");
		veicolo.setAccessori("Tuta integrale");
		veicolo.setCustom("Adesivi");

		boolean resultVeicolo = queryVeicolo.doInsertVeicoloMoto(veicolo);
		assertTrue(resultVeicolo);
	}

	// CREDENZIALI CORRETTE MA VEICOLO ESISTENTE
	@Test
	public void TC5_25() throws SQLException, IOException {

		queryVeicolo = new QueryVeicolo(ds);
		ProductVeicolo veicolo = new ProductVeicolo();

		veicolo.setCodice_telaio("Waz85ndAyBsXE6947");
		veicolo.setTarga("qw728jk");
		veicolo.setColore("Bianco");
		veicolo.setMarchio("Aprilia");
		veicolo.setModello("Rsv-1000");
		veicolo.setKw(105);
		veicolo.setPrezzo(15000);
		veicolo.setPhoto("uploadImage/aprilia.png");
		veicolo.setAccessori("Tuta integrale");
		veicolo.setCustom("Adesivi");

		// boolean resultVeicolo = queryVeicolo.doInsertVeicoloMoto(veicolo);
		// assertFalse(resultVeicolo);
		assertThrows(SQLIntegrityConstraintViolationException.class, () -> queryVeicolo.doInsertVeicoloMoto(veicolo));
	}
	
	/******************* DO DELETE ****************************/

	// INSERIMENTO CORRETTO DATI
	@Test
	public void TC5_1() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo.setTarga("pl458io");
		veicolo.setCodice_telaio("mvcnhr76h6cio4534");

		boolean result = queryVeicolo.doDelete(veicolo);
		assertTrue(result);
	}

	// CAMPI MANCANTI
	@Test
	public void TC5_2() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo.setTarga("");
		veicolo.setCodice_telaio("");

		boolean result = queryVeicolo.doDelete(veicolo);
		assertFalse(result);
	}

	// INSERIMENTO SOLO TARGA
	@Test
	public void TC5_3() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo.setTarga("zx214xc");
		veicolo.setCodice_telaio("");

		boolean result = queryVeicolo.doDelete(veicolo);
		assertFalse(result);
	}

	// INSERITO SOLO CODICE TELAIO
	@Test
	public void TC5_4() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo.setTarga("");
		veicolo.setCodice_telaio("oqtqXXEXmmspv2147");

		boolean result = queryVeicolo.doDelete(veicolo);
		assertFalse(result);
	}

	// INSERIMENTO ERRATO CREDENZIALI
	@Test
	public void TC5_5() throws SQLException {
		queryVeicolo = new QueryVeicolo(ds);

		ProductVeicolo veicolo = new ProductVeicolo();
		veicolo.setTarga("ab65kgh");
		veicolo.setCodice_telaio("oqtqXXEX65mmspv2147");

		// boolean result = queryVeicolo.doDelete(veicolo);
		// assertFalse(result);
		assertThrows(RuntimeException.class, () -> queryVeicolo.doDelete(veicolo));
	}
}
