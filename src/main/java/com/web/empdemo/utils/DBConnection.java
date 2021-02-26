package com.web.empdemo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	
	static Connection connection;
	public static Connection getConnectionFront() throws ClassNotFoundException
	{
		Connection connection = null;
		try
		{		
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sequrehelth","root","root");
				
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		return connection;
	}
	
	
}
