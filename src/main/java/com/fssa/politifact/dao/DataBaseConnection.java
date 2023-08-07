package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

	public static Connection getConnection() {

		Connection connection = null; 
		String url = "jdbc:mysql://localhost:3306/politifact";
		String userName="root";
		String password= "123456";
		
		
		try{

			connection = DriverManager.getConnection(url, userName, password);
			

		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new RuntimeException("Unable to connect to the database");
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		
		getConnection(); 
		
	}

}
