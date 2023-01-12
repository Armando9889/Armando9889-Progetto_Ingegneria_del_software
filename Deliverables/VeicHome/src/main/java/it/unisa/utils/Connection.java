package it.unisa.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public class Connection {
	
	DataSource ds = null;
	Context initContext;

	public DataSource connessione(boolean connection) {
		if(connection == true) {
			try {
				SimpleNamingContextBuilder.emptyActivatedContextBuilder();
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.initContext = new InitialContext();
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				this.initContext.bind("java:comp/env/jdbc/datasource",
						new DriverManagerDataSource("jdbc:mysql://localhost:3306/filialedb", "root", "123Ciao33"));
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ds = (DataSource) this.initContext.lookup("java:comp/env/jdbc/datasource");
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		return ds;
	}

}
