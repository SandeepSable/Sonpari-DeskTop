package com.ibm.app;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.ibm.app.ui.Home;
import com.ibm.app.utilities.DBConnection;
import com.ibm.app.utilities.QueryUtility;

public class Application 
{
	final static Logger logger = Logger.getLogger(Application.class);
	public static ApplicationContext applicationContext = null;
	public static SpringConfiguration springConfig = new SpringConfiguration();
	private static PreparedStatement pst;

	public Application() throws SQLException 
	{
		logger.debug("Inside Application class.. execution is starting...");
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Home home = new Home(new JFrame());
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});

	}

	public static void databaseInitialization() throws SQLException 
	{
		logger.debug("Starting database table creation...");
		try 
		{
			Connection connection = DBConnection.getConnection();
			List<String> queries = QueryUtility.getMasterTableAndSeqNames();
			for (String queryString : queries) 
			{
				try 
				{
					pst = connection.prepareStatement(queryString);
					pst.executeUpdate();
				} catch (Exception ex) 
				{
					logger.error("---->"+ex.getMessage());
					logger.debug("TABLE OR SEQUENCE is already available : " + queryString);
				//	continue;
				}
			}
			DBConnection.releaseConnection(connection, pst, null);
		}
		catch (Exception exception) 
		{
			logger.warn("Table structure is already available. Skipping the structure creation...");
		}
	}
}
