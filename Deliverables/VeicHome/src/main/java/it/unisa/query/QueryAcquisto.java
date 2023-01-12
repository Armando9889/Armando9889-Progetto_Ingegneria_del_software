package it.unisa.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.bean.ProductAcquisto;
import it.unisa.model.bean.ProductCarrello;
import it.unisa.utils.Utility;

public class QueryAcquisto {

	private DataSource ds = null;

	public QueryAcquisto(DataSource ds) {
		this.ds = ds;
	}

	public boolean doSave(ProductAcquisto product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO acquisto" + " (targaFK,codice_fiscaleFK,data_di_acquisto) VALUES (?,?,?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, product.getTargaFK());
			preparedStatement.setString(2, product.getCodice_fiscaleFK());
			preparedStatement.setString(3, product.getData_di_acquisto());

			if (product.getTargaFK().equals("") || product.getCodice_fiscaleFK().equals("")
					|| product.getData_di_acquisto().equals("")) {
				throw new RuntimeException("INSERT error.");
			}

			try {
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException("INSERT error.");
			}

			Utility.print("doSave: " + preparedStatement.toString());
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

	public ProductAcquisto doRetrieveByKey(String codice_fiscaleFK) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductAcquisto bean = new ProductAcquisto();
		ResultSet rs;

		String selectSQL = "SELECT * FROM acquisto WHERE codice_fiscaleFK = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice_fiscaleFK);

			if (codice_fiscaleFK.equals("")) {
				return null;
			}

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				System.out.println("sono dentro");
				bean.setTargaFK(rs.getString("targaFK"));
				bean.setCodice_fiscaleFK(rs.getString("codice_fiscaleFK"));
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

	public Collection<ProductAcquisto> doRetriveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM acquisto";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		Collection<ProductAcquisto> products7 = new LinkedList<ProductAcquisto>();

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			Utility.print("doRetriveAll: " + preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductAcquisto cr = new ProductAcquisto();
				cr.setTargaFK(rs.getString("targaFK"));
				cr.setCodice_fiscaleFK(rs.getString("codice_fiscaleFK"));
				cr.setData_di_acquisto(rs.getString("data_di_acquisto"));
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

	public boolean doDelete() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL1 = "DELETE FROM acquisto";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(deleteSQL1);

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
