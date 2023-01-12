package it.unisa.testing.control;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import it.unisa.control.InserimentoAuto;
import it.unisa.model.bean.ProductCliente;
import it.unisa.model.bean.ProductVeicolo;
import it.unisa.query.*;
import it.unisa.utils.Utility;

import static org.mockito.Mockito.mock;
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
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
class InserimentoAutoServletTest {

	private ServletContext context;
	private RequestDispatcher dispatcher;

	@Spy
	private InserimentoAuto servlet;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;

	private HttpSession session;
	private ServletConfig sg;
	final MultipartFile mockFile = mock(MultipartFile.class);

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

	// NON INSERIMENTO DELLE CREDENZIALI
	@Test
	public void TC3_1() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("");
		when(request.getParameter("targa")).thenReturn("");
		when(request.getParameter("colore")).thenReturn("");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO
	@Test
	public void TC3_2() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("");
		when(request.getParameter("colore")).thenReturn("");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA
	@Test
	public void TC3_3() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA E COLORE
	@Test
	public void TC3_4() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(NullPointerException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA E COLORE E MARCHIO
	@Test
	public void TC3_5() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA E COLORE E MARCHIO E MODELLO
	@Test
	public void TC3_6() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("Polo-Gti");
		when(request.getParameter("kw")).thenReturn("");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA E COLORE E MARCHIO E MODELLO E KW
	@Test
	public void TC3_7() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("Polo-Gti");
		when(request.getParameter("kw")).thenReturn("152");
		when(request.getParameter("prezzo")).thenReturn("");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA E COLORE E MARCHIO E MODELLO E KW E
	// PREZZO
	@Test
	public void TC3_8() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("Polo-Gti");
		when(request.getParameter("kw")).thenReturn("152");
		when(request.getParameter("prezzo")).thenReturn("29000");
		when(request.getParameter("photo")).thenReturn("");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA E COLORE E MARCHIO E MODELLO E KW E
	// PREZZO E PHOTO
	@Test
	public void TC3_9() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("Polo-Gti");
		when(request.getParameter("kw")).thenReturn("152");
		when(request.getParameter("prezzo")).thenReturn("29000");
		when(request.getParameter("photo")).thenReturn("uploadImage/polo.png");
		when(request.getParameter("sconto")).thenReturn("");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO SOLO CODICE TELAIO E TARGA E COLORE E MARCHIO E MODELLO E KW E
	// PREZZO E PHOTO E SCONTO
	@Test
	public void TC3_10() throws ServletException, IOException {

		servlet.prova();
		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("Polo-Gti");
		when(request.getParameter("kw")).thenReturn("152");
		when(request.getParameter("prezzo")).thenReturn("29000");
		when(request.getParameter("photo")).thenReturn("uploadImage/polo.png");
		when(request.getParameter("sconto")).thenReturn("5%");
		when(request.getParameter("numero_di_passegeri")).thenReturn("");

		assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
	}

	// INSERIMENTO VEICOLO CORRETTO
	@Test
	public void TC3_11() throws ServletException, IOException {
		servlet.prova();
		when(request.getContentType()).thenReturn("multipart/form-data");

		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("Polo-Gti");
		when(request.getParameter("kw")).thenReturn("152");
		when(request.getParameter("prezzo")).thenReturn("29000");
		
		Part file = mock(Part.class);
		BufferedImage image = ImageIO.read(new File("D:/Desktop/polo.png"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		
		when(file.getHeader("content-disposition")).thenReturn("form-data; name=\"photo\"; filename=\"polo.png\"");
		when(file.getInputStream()).thenReturn(is);
		when(file.getSize()).thenReturn(3L);
		when(file.getName()).thenReturn("photo");
		when(request.getPart("photo")).thenReturn(file);

		when(request.getParameter("sconto")).thenReturn("5%");
		when(request.getParameter("numero_di_passegeri")).thenReturn("5");

		servlet.doGet(request, response);
		verify(response).sendRedirect("adminHome.jsp");
	}
	
	// INSERIMENTO VEICOLO GIA' PRESENTE 
	@Test
	public void TC3_12() throws ServletException, IOException {
		servlet.prova();
		when(request.getContentType()).thenReturn("multipart/form-data");

		when(request.getParameter("codice_telaio")).thenReturn("vBKBNwaaj3r2s2886");
		when(request.getParameter("targa")).thenReturn("po897nm");
		when(request.getParameter("colore")).thenReturn("Bianco");
		when(request.getParameter("marchio")).thenReturn("Volkwswagen");
		when(request.getParameter("modello")).thenReturn("Polo-Gti");
		when(request.getParameter("kw")).thenReturn("152");
		when(request.getParameter("prezzo")).thenReturn("29000");
		
		Part file = mock(Part.class);
		BufferedImage image = ImageIO.read(new File("D:/Desktop/polo.png"));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		
		when(file.getHeader("content-disposition")).thenReturn("form-data; name=\"photo\"; filename=\"polo.png\"");
		when(file.getInputStream()).thenReturn(is);
		when(file.getSize()).thenReturn(3L);
		when(file.getName()).thenReturn("photo");
		when(request.getPart("photo")).thenReturn(file);

		when(request.getParameter("sconto")).thenReturn("5%");
		when(request.getParameter("numero_di_passegeri")).thenReturn("5");

		servlet.doGet(request, response);
		verify(response).sendRedirect("adminHome.jsp");
	}


}
