package it.molinari.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

	Connection connection = null;

	private static final String drive = "com.mysql.cj.jdbc.Driver";

	private static final String url = "jdbc:mysql://151.30.251.67:3306/GestioneScuola?serverTimezone=UTC";
	private static final String login = "alex";
	private static final String paswd = "Tmax2011!";

	public Connection getConnection() {
		try {
			Class.forName(drive);
			connection = DriverManager.getConnection(url, login, paswd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
