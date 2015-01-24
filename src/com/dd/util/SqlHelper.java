package com.dd.util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class SqlHelper {
	private static final String propertyFilePath = "D:\\Code\\Project\\Webproject\\src\\com\\dd\\mysql.property";
	private static String username;
	private static String password;
	private static String driver;
	private static String url;

	static {
		try {
			Properties properties = new Properties();
			System.out.println("---> path: " + propertyFilePath);
			InputStream inputStream = new FileInputStream(new File(propertyFilePath));
			properties.load(inputStream);

			username = properties.getProperty("db.username");
			password = properties.getProperty("db.password");
			driver = properties.getProperty("db.driver");
			url = properties.getProperty("db.url");
			System.out.println("---> driver: " + driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean executeQuery(String sql, String[] params) {
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setString(i + 1, params[i]);
			}

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
