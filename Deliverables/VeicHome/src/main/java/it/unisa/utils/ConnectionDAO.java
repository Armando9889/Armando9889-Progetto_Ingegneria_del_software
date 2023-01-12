package it.unisa.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import it.unisa.query.QueryAcquisto;

public class ConnectionDAO {
	
	Context initContext;
	DataSource ds;
	
	public DataSource connessioneDAO() throws NamingException {
		
		SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		this.initContext = new InitialContext();

		this.initContext.bind("java:comp/env/jdbc/datasource",
				new DriverManagerDataSource("jdbc:mysql://localhost:3306/filialedb", "root", "123Ciao33"));
		ds = (DataSource) this.initContext.lookup("java:comp/env/jdbc/datasource");
		
		return ds;
	}
}

