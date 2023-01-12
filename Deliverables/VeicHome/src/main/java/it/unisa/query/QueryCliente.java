
package it.unisa.query;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import it.unisa.model.bean.ProductCliente;
import it.unisa.model.bean.ProductVeicolo;
import it.unisa.utils.Utility;

public class QueryCliente {

	private DataSource ds = null;

	public Collection<ProductCliente> doRetriveAll1(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM cliente";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		Collection<ProductCliente> products1 = new LinkedList<ProductCliente>();

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			Utility.print("doRetriveAll: " + preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductCliente cliente = new ProductCliente();
				cliente.setCodice_fiscale(rs.getString("codice_fiscale"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCognome(rs.getString("cognome"));
				cliente.setSesso(rs.getString("sesso"));
				cliente.setIndirizzo(rs.getString("indirizzo"));
				cliente.setData_di_nascita(rs.getString("data_di_nascita"));

				cliente.setNumero_di_carta(rs.getString("numero_di_carta"));
				cliente.setData_scadenza(rs.getString("data_scadenza"));
				cliente.setCvv(rs.getString("cvv"));
				cliente.setUsername(rs.getString("username"));
				cliente.setSaldo(rs.getInt("saldo"));
				products1.add(cliente);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products1;
	}

	public boolean doUpdate(ProductCliente cl) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = null;
		connection = ds.getConnection();
		connection.setAutoCommit(false);

		String codice_fiscale = cl.getCodice_fiscale();

		Pattern p = Pattern.compile("[^A-Za-z0-9-/ ]");
		Pattern p1 = Pattern.compile("[^0-9]");

		Matcher m1 = p.matcher(cl.getIndirizzo());
		Matcher m2 = p1.matcher(cl.getNumero_di_carta());
		Matcher m3 = p1.matcher(cl.getCvv());

		boolean b1 = m1.find();
		boolean b2 = m2.find();
		boolean b3 = m3.find();

		if ((cl.getUsername() != "" && cl.getIndirizzo() != "") && b1 != true) {
			if (cl.getNumero_di_carta() == "") {
				updateSQL = "UPDATE cliente SET " + " indirizzo = ?, username = ? WHERE codice_fiscale = '"
						+ codice_fiscale + "';";
				System.out.println("ciao");
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, cl.getIndirizzo());
				preparedStatement.setString(2, cl.getUsername());
			}

		}

		if (cl.getNumero_di_carta() != "" && b2 != true && b3 != true && cl.getNumero_di_carta().length() == 16
				&& cl.getCvv().length() == 3) {
			if (cl.getUsername() == "" && cl.getIndirizzo() == "") {
				updateSQL = "UPDATE cliente SET "
						+ " numero_di_carta = ?, cvv = ?, data_scadenza = ? WHERE codice_fiscale = '" + codice_fiscale
						+ "';";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, cl.getNumero_di_carta());
				preparedStatement.setString(2, cl.getCvv());
				preparedStatement.setString(3, cl.getData_scadenza());
			}

		}

		if (cl.getNumero_di_carta() != "" && cl.getUsername() != "" && b2 != true && b3 != true
				&& cl.getNumero_di_carta().length() == 16 && cl.getCvv().length() == 3) {
			if (cl.getIndirizzo() == "") {
				updateSQL = "UPDATE cliente SET "
						+ " numero_di_carta = ?, cvv = ?, data_scadenza = ?, username = ? WHERE codice_fiscale = '"
						+ codice_fiscale + "';";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, cl.getNumero_di_carta());
				preparedStatement.setString(2, cl.getCvv());
				preparedStatement.setString(3, cl.getData_scadenza());
				preparedStatement.setString(4, cl.getUsername());
			}

		}

		if (cl.getNumero_di_carta() != "" && cl.getIndirizzo() != "" && b1 != true && b2 != true && b3 != true
				&& cl.getNumero_di_carta().length() == 16 && cl.getCvv().length() == 3) {
			if (cl.getUsername() == "") {
				updateSQL = "UPDATE cliente SET "
						+ " numero_di_carta = ?, cvv = ?, data_scadenza = ?, indirizzo = ? WHERE codice_fiscale = '"
						+ codice_fiscale + "';";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, cl.getNumero_di_carta());
				preparedStatement.setString(2, cl.getCvv());
				preparedStatement.setString(3, cl.getData_scadenza());
				preparedStatement.setString(4, cl.getIndirizzo());
			}

		}

		if (cl.getNumero_di_carta() != "" && cl.getIndirizzo() != "" && cl.getUsername() != "" && b1 != true
				&& b2 != true && b3 != true && cl.getNumero_di_carta().length() == 16 && cl.getCvv().length() == 3) {
			updateSQL = "UPDATE cliente SET "
					+ " numero_di_carta = ?, cvv = ?, data_scadenza = ?, indirizzo = ?, username = ? WHERE codice_fiscale = '"
					+ codice_fiscale + "';";
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, cl.getNumero_di_carta());
			preparedStatement.setString(2, cl.getCvv());
			preparedStatement.setString(3, cl.getData_scadenza());
			preparedStatement.setString(4, cl.getIndirizzo());
			preparedStatement.setString(5, cl.getUsername());

		}

		if (cl.getUsername() != "") {
			if (cl.getIndirizzo() == "" && cl.getNumero_di_carta() == "") {
				updateSQL = "UPDATE cliente SET " + " username = ? WHERE codice_fiscale = '" + codice_fiscale + "';";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, cl.getUsername());
			}

		}

		if (cl.getIndirizzo() != "" && b1 != true) {
			if (cl.getUsername() == "" && cl.getNumero_di_carta() == "") {
				updateSQL = "UPDATE cliente SET " + " indirizzo = ? WHERE codice_fiscale = '" + codice_fiscale + "';";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, cl.getIndirizzo());
			}

		}

		try {
			
			try {
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException("UPDATE error.");
			}
			
			Utility.print("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
			return true;

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
	}

	public QueryCliente(DataSource ds) {
		this.ds = ds;
	}

	public boolean doSave(ProductCliente product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO cliente"
				+ " (codice_fiscale,nome,cognome,sesso,indirizzo,data_di_nascita,password,numero_di_carta,data_scadenza,cvv,username,comune_di_nascita) VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, product.getCodice_fiscale());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getCognome());
			preparedStatement.setString(4, product.getSesso());
			preparedStatement.setString(5, product.getIndirizzo());
			preparedStatement.setString(6, product.getData_di_nascita());
			preparedStatement.setString(7, product.getPassword());
			preparedStatement.setString(8, product.getNumero_di_carta());
			preparedStatement.setString(9, product.getData_scadenza());
			preparedStatement.setString(10, product.getCvv());
			preparedStatement.setString(11, product.getUsername());
			preparedStatement.setString(12, product.getComune_di_nascita());

			if (product.getCodice_fiscale().equals("") || product.getNome().equals("")
					|| product.getCognome().equals("") || product.getSesso().equals("")
					|| product.getIndirizzo().equals("") || product.getData_di_nascita().equals("")
					|| product.getPassword().equals("") || product.getNumero_di_carta().equals("")
					|| product.getData_scadenza().equals("") || product.getCvv().equals("")
					|| product.getUsername().equals("") || product.getComune_di_nascita().equals("")) {

				return false;
			}

			Pattern p = Pattern.compile("[^A-Za-z ]");
			Pattern p1 = Pattern.compile("[^A-Za-z0-9-/ ]");
			Pattern p3 = Pattern.compile("[^0-9]");

			Matcher m = p.matcher(product.getNome());
			Matcher m1 = p.matcher(product.getCognome());
			Matcher m2 = p.matcher(product.getComune_di_nascita());
			Matcher m3 = p1.matcher(product.getIndirizzo());
			Matcher m4 = p3.matcher(product.getNumero_di_carta());
			Matcher m5 = p3.matcher(product.getCvv());

			boolean b = m.find();
			boolean b1 = m1.find();
			boolean b2 = m2.find();
			boolean b3 = m3.find();
			boolean b4 = m4.find();
			boolean b5 = m5.find();

			if (b == true || b1 == true || b2 == true || b3 == true || b4 == true || b5 == true) {
				return false;
			}

			if (product.getNumero_di_carta().length() != 16 || product.getCvv().length() != 3) {
				return false;
			}

			try {
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException("INSERT error.");
			}

			Utility.print("doSave: " + preparedStatement.toString());
			// System.out.println(preparedStatement.executeUpdate());

			connection.commit();
			return true;

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					connection.close();

				}
			}
		}
	}

	public ProductCliente doRetrieveByKey(String nome, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductCliente bean = new ProductCliente();

		String selectSQL1 = "SELECT * FROM cliente WHERE username = ? and password = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL1);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, password);

			if (nome.equals("") || password.equals("")) {
				return null;
			}

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("username"));
				bean.setCodice_fiscale(rs.getString("codice_fiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setSaldo(rs.getInt("saldo"));
				return bean;
			}

			Utility.print(bean.toString());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}

		return null;
	}

	public boolean controllo(String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductCliente bean = new ProductCliente();

		String selectSQL1 = "SELECT * FROM cliente WHERE username = ?";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL1);
			preparedStatement.setString(1, username);

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setNome(rs.getString("nome"));

				return true;
			}

			Utility.print(bean.toString());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}

		return false;
	}

	public boolean doUpdateSaldo(ProductCliente cl) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = null;
		connection = ds.getConnection();
		connection.setAutoCommit(false);

		String codice_fiscale = cl.getCodice_fiscale();
		if (codice_fiscale.equals("")) {
			throw new RuntimeException("INSERT error.");
		}
		
		if(cl.getSaldo() <= 0) {
			throw new RuntimeException("INSERT error.");
		}

		updateSQL = "UPDATE cliente SET " + " saldo = ? WHERE codice_fiscale = '" + codice_fiscale + "';";
		preparedStatement = connection.prepareStatement(updateSQL);
		preparedStatement.setInt(1, cl.getSaldo());

		try {

			Utility.print("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
			return true;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
	}
	
	public boolean controlloCarta(String numero_di_carta) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductCliente bean = new ProductCliente();

		String selectSQL1 = "SELECT * FROM cliente WHERE numero_di_carta = ?";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL1);
			preparedStatement.setString(1, numero_di_carta);

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setNumero_di_carta("numero_di_carta");

				return true;
			}

			Utility.print(bean.toString());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}

		return false;
	}
	
	
	
	

}