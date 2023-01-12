package it.unisa.query;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.bean.ProductCarrello;
import it.unisa.model.bean.ProductVeicolo;
import it.unisa.utils.Utility;

public class QueryCarrello {

	private DataSource ds = null;

	public QueryCarrello(DataSource ds) {
		this.ds = ds;
	}

	public boolean doSave(ProductCarrello product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO carrello" + " (targa,modello,prezzo,sessionid,utenteid) VALUES (?,?,?,?,?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, product.getTarga());
			preparedStatement.setString(2, product.getModello());
			preparedStatement.setInt(3, product.getPrezzo());
			preparedStatement.setString(4, product.getSessionid());
			preparedStatement.setString(5, product.getutenteid());

			if (product.getTarga().equals("") || product.getModello().equals("") || product.getPrezzo() == 0
					|| product.getSessionid().equals("")) {
				return false;
			}

			Utility.print("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
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
		return true;
	}

	public Collection<ProductCarrello> doRetriveAll(String order) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM carrello";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		Collection<ProductCarrello> products7 = new LinkedList<ProductCarrello>();

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			Utility.print("doRetriveAll: " + preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductCarrello cr = new ProductCarrello();
				cr.setTarga(rs.getString("targa"));
				cr.setModello(rs.getString("modello"));
				cr.setPrezzo(rs.getInt("prezzo"));
				cr.setSessionid(rs.getString("sessionid"));
				cr.setutenteid(rs.getString("utenteid"));
				// cr.setPath(rs.getString("path"));

				// Blob blob = rs.getBlob("path");
				// InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				// byte [] buffer = new byte [4096];
				// int bytesRead = -1;

				// while((bytesRead = inputStream.read(buffer)) != -1) {
				// outputStream.write(buffer,0,bytesRead);
				// }

				// byte [] imageBytes = outputStream.toByteArray();
				// String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				// inputStream.close();
				outputStream.close();
				// cr.setBase64Image(base64Image);

				products7.add(cr);
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
		return products7;
	}

	public void doDelete() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL1 = "DELETE FROM carrello";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(deleteSQL1);

			Utility.print("doDelete: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

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

	public ProductCarrello doRetrieveByKey(String sessionid) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductCarrello bean = new ProductCarrello();

		String selectSQL1 = "SELECT * FROM carrello WHERE sessionid = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL1);
			preparedStatement.setString(1, sessionid);

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setModello(rs.getString("sessionid"));
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

	public void doDelete(String product,String cf) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL1 = "DELETE FROM carrello WHERE sessionid = ? or utenteid = ?";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(deleteSQL1);
			preparedStatement.setString(1, product);
			preparedStatement.setString(2, cf);

			Utility.print("doDelete: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

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

	public ProductCarrello doRetrieveByKey1(String targa) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductCarrello bean = new ProductCarrello();

		String selectSQL1 = "SELECT * FROM carrello WHERE targa = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL1);
			preparedStatement.setString(1, targa);

			if (targa.equals("")) {
				return null;
			}

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setTarga(rs.getString("targa"));
				bean.setModello(rs.getString("modello"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setSessionid(rs.getString("sessionid"));
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

		return bean;
	}

	public boolean doDelete(ProductCarrello product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL1 = "DELETE FROM carrello WHERE targa = ? and modello = ? and prezzo = ? and sessionid = ?";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(deleteSQL1);
			preparedStatement.setString(1, product.getTarga());
			preparedStatement.setString(2, product.getModello());
			preparedStatement.setInt(3, product.getPrezzo());
			preparedStatement.setString(4, product.getSessionid());

			if (product.getTarga().equals("")) {
				return false;
			}

			Utility.print("doDelete: " + preparedStatement.toString());
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

}
