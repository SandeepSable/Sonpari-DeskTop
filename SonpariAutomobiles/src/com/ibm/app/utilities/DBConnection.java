package com.ibm.app.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class DBConnection
{
	final static Logger			logger		= Logger.getLogger(DBConnection.class);
	public static Connection	connection	= null;

	public static Connection getConnection()
	{
		logger.debug("Started to create connection...");
		if (connection == null) {
			try {
				Class.forName("org.h2.Driver");
				logger.debug("class loaded successfully...");
				connection = DriverManager.getConnection("jdbc:h2:file:C:\\sonpari\\db;AUTO_SERVER=TRUE", "sa", "");
				logger.debug("connection is create successfully...");
			}
			catch (Exception e) 
			{
				logger.error("Connection exception:::" + e.getCause());
			}
		}
		return connection;
	}

	public static void releaseConnection(Connection conn, PreparedStatement pst, ResultSet rs)
	{
		logger.debug("Releasing the resources..");
		try {
/*			if (conn != null)
			    conn.close();
			if (pst != null)
			    pst.close();
			if (rs != null) {
				rs.close();
			}
*/		}
		catch (Exception ex) {
			logger.error("Failed to release the resources...");
		}
	}
}