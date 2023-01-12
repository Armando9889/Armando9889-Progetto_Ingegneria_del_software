package it.unisa.query;

import it.unisa.model.bean.ProductVeicolo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javax.swing.ImageIcon;

import org.apache.catalina.tribes.group.Response;

import it.unisa.utils.Utility;

public class QueryVeicolo {

	private DataSource ds = null;

	public QueryVeicolo(DataSource ds) {
		this.ds = ds;
	}

	public Collection<ProductVeicolo> doRetriveAll5(String order) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM veicolo";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		Collection<ProductVeicolo> products5 = new LinkedList<ProductVeicolo>();

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			Utility.print("doRetriveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductVeicolo vei = new ProductVeicolo();

				vei.setTarga(rs.getString("targa"));
				vei.setCodice_telaio(rs.getString("codice_telaio"));
				vei.setColore(rs.getString("colore"));
				vei.setMarchio(rs.getString("marchio"));
				vei.setModello(rs.getString("modello"));
				vei.setKw(rs.getInt("kw"));
				vei.setPrezzo(rs.getInt("prezzo"));
				vei.setNumero_passegeri(rs.getInt("numero_passegeri"));
				vei.setSconto(rs.getString("sconto"));
				vei.setAccessori(rs.getString("accessori"));
				vei.setCustom(rs.getString("custom"));
				vei.setPhoto(rs.getString("photo"));

				products5.add(vei);
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
		return products5;
	}

	public ProductVeicolo doRetrieveByKey1(String targa, String codice_telaio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductVeicolo bean = new ProductVeicolo();

		String selectSQL1 = "SELECT * FROM veicolo WHERE targa = ? and codice_telaio = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL1);
			preparedStatement.setString(1, codice_telaio);
			preparedStatement.setString(2, targa);

			if (targa.equals("") || codice_telaio.equals("")) {
				return null;
			}

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodice_telaio(rs.getString("codice_telaio"));
				bean.setTarga(rs.getString("targa"));
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

	public boolean doDelete(ProductVeicolo product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL1 = "DELETE FROM veicolo WHERE targa = ? and codice_telaio = ?";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(deleteSQL1);
			preparedStatement.setString(1, product.getTarga());
			preparedStatement.setString(2, product.getCodice_telaio());

			if (product.getTarga().equals("") || product.getCodice_telaio().equals("")) {
				return false;
			}

			if (preparedStatement.executeUpdate() != 1) {
				throw new RuntimeException("DELETE Error.!");
			}

			Utility.print("doDelete: " + preparedStatement.toString());

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

	public boolean doInsertVeicoloAuto(ProductVeicolo product) throws SQLException, FileNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO veicolo"
				+ " (codice_telaio,targa,colore,marchio,modello,kw,prezzo,photo,sconto,numero_passegeri,accessori,custom) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, product.getCodice_telaio());
			preparedStatement.setString(2, product.getTarga());
			preparedStatement.setString(3, product.getColore());
			preparedStatement.setString(4, product.getMarchio());
			preparedStatement.setString(5, product.getModello());
			preparedStatement.setInt(6, product.getKw());
			preparedStatement.setInt(7, product.getPrezzo());
			preparedStatement.setString(8, product.getPhoto());
			preparedStatement.setString(9, product.getSconto());
			preparedStatement.setInt(10, product.getNumero_passegeri());

			if (product.getCodice_telaio().equals("") || product.getTarga().equals("") || product.getColore().equals("")
					|| product.getMarchio().equals("") || product.getModello().equals("") || product.getKw() == 0
					|| product.getPrezzo() == 0 || product.getPhoto().equals("") || product.getNumero_passegeri() == 0
					|| product.getSconto().equals("")) {

				return false;
			}

			Utility.print("doSave: " + preparedStatement.toString());
			try {
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new SQLIntegrityConstraintViolationException("INSERT error.");
			}

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

	public boolean doInsertVeicoloMoto(ProductVeicolo product) throws SQLException, FileNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO veicolo"
				+ " (codice_telaio,targa,colore,marchio,modello,kw,prezzo,photo,sconto,numero_passegeri,accessori,custom) VALUES (?, ?, ?, ?, ?, ?, ?, ?, null, null, ?, ?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, product.getCodice_telaio());
			preparedStatement.setString(2, product.getTarga());
			preparedStatement.setString(3, product.getColore());
			preparedStatement.setString(4, product.getMarchio());
			preparedStatement.setString(5, product.getModello());
			preparedStatement.setInt(6, product.getKw());
			preparedStatement.setInt(7, product.getPrezzo());
			preparedStatement.setString(8, product.getPhoto());
			preparedStatement.setString(9, product.getAccessori());
			preparedStatement.setString(10, product.getCustom());

			if (product.getCodice_telaio().equals("") || product.getTarga().equals("") || product.getColore().equals("")
					|| product.getMarchio().equals("") || product.getModello().equals("") || product.getKw() == 0
					|| product.getPrezzo() == 0 || product.getPhoto().equals("") || product.getAccessori().equals("")
					|| product.getCustom().equals("")) {

				return false;
			}

			Utility.print("doSave: " + preparedStatement.toString());

			try {
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new SQLIntegrityConstraintViolationException("INSERT error.");
			}

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

	public Collection<ProductVeicolo> cercaAuto(String order) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM veicolo";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		Collection<ProductVeicolo> products5 = new LinkedList<ProductVeicolo>();

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			;

			while (rs.next()) {
				ProductVeicolo vei = new ProductVeicolo();

				vei.setTarga(rs.getString("targa"));
				vei.setColore(rs.getString("colore"));
				vei.setMarchio(rs.getString("marchio"));
				vei.setModello(rs.getString("modello"));
				vei.setKw(rs.getInt("kw"));
				vei.setPrezzo(rs.getInt("prezzo"));
				vei.setNumero_passegeri(rs.getInt("numero_passegeri"));
				vei.setSconto(rs.getString("sconto"));
				vei.setPhoto(rs.getString("photo"));

				products5.add(vei);
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
		return products5;
	}

	public Collection<ProductVeicolo> cercaMoto(String order) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM veicolo";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		Collection<ProductVeicolo> products5 = new LinkedList<ProductVeicolo>();

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			Utility.print("doRetriveAll: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductVeicolo vei = new ProductVeicolo();

				vei.setTarga(rs.getString("targa"));
				vei.setColore(rs.getString("colore"));
				vei.setMarchio(rs.getString("marchio"));
				vei.setModello(rs.getString("modello"));
				vei.setKw(rs.getInt("kw"));
				vei.setPrezzo(rs.getInt("prezzo"));
				vei.setAccessori(rs.getString("accessori"));
				vei.setCustom(rs.getString("custom"));
				vei.setPhoto(rs.getString("photo"));

				products5.add(vei);
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
		return products5;
	}

}
