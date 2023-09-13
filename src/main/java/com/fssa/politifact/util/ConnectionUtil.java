package com.fssa.politifact.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	/**
	 * data base connection .
	 * 
	 * @return
	 */

//	public static Connection getConnection() {
//
//		Connection con = null;
//
//		String url, userName, passWord;
//
//		url = System.getenv("DATABASE_HOST");
//		userName = System.getenv("DATABASE_USERNAME");
//		passWord = System.getenv("DATABASE_PASSWORD");
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			con = DriverManager.getConnection(url, userName, passWord);
//			
//
//		} catch (Exception e) {
//
//			throw new RuntimeException("Unable to connect to the database");
//		}
//
//		System.out.println("successfully");
//		return con;
//	}

	public static Connection getConnection() {

		Connection connection = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/politifact";

			connection = DriverManager.getConnection(url, "root", "123456");

			System.out.println("Connection");

		} catch (Exception e) {

			e.printStackTrace();

			throw new RuntimeException(e);

		}
		System.out.println("hello");
		return connection;
	}

	public static void main(String[] args) {

		getConnection();
	}
}
