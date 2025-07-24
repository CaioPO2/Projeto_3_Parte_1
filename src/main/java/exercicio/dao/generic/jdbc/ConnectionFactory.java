package exercicio.dao.generic.jdbc;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static Connection connection;
	
	private ConnectionFactory(Connection connection) {
		
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
		} else if (connection != null && connection.isClosed()) {
			connection = initConnection();
		}
		return connection;
	}
	
	private static Connection initConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/vendas_online", "postgres", "Admin");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
