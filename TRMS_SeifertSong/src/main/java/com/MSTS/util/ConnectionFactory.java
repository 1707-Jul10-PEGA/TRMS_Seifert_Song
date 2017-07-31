package com.MSTS.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static ConnectionFactory cf=null;
	private static Boolean build = true;
	
	private ConnectionFactory()
	{
		
	}
	
	public static synchronized ConnectionFactory getInstance()
	{
		if(build)
		{
			cf = new ConnectionFactory();
		}
		return cf;
	}

	public Connection getConnection()
	{
		Connection conn = null;
		
		Properties prop = new Properties();

		try {
			prop.load(new FileReader("C:/Users/Matthew Seifert/Documents/workspace-sts-3.8.4.RELEASE/TRMS_SeifertSong/datasource.properties"));
			//System.out.println(prop);
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}