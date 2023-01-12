package it.unisa.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.model.bean.ProductCliente;
import it.unisa.utils.Utility;

public class login {

	private DataSource ds = null;

	public login(DataSource ds) {
		this.ds = ds;
	}
	
	public login() {
		
	}

	public String login(String nome, String password) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String cf = null;

		ProductCliente bean = new ProductCliente();

		String selectSQL1 = "SELECT * FROM cliente WHERE username = ? and password = ?";

		try {

			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL1);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, password);

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setCodice_fiscale(cf = rs.getString("codice_fiscale"));
				
				return cf;
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

		return "error";
	}

}
