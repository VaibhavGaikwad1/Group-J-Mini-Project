package com.min.project.java.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	Connection connection = null;

	public Connection getConnectionDetails() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MiniProject", "root", "Vaibhav@242");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
