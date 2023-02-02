package br.com.intern.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;

	public static Connection getConnection() {

			Connection connection = null;

			try {
				Class.forName("");
				connection = DriverManager.getConnection("");

				if(connection != null) {
					System.out.println("Connection OK");
				}else {
					System.out.println("Connection FAILED");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;
	}

	public static void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
