package com.ibm.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;


public class Splash extends JFrame
{
	
	private static final long	serialVersionUID	= 9094570649146381921L;
	
	final static Logger			logger				= Logger.getLogger(Splash.class);
	
	private JLabel				imglabel;
	private ImageIcon			img;
	private static JProgressBar	processBar;
	Thread						t					= null;
	
	public Splash()
	{
		super("Splash");
		setTitle("Loading Application");
		setSize(404, 310);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Splash.class
		        .getResource("/com/ibm/app/resources/sonpari.png")));
		imglabel = new JLabel(img);
		getContentPane().add(imglabel);
		getContentPane().setLayout(null);
		processBar = new JProgressBar();
		processBar.setMinimum(0);
		processBar.setMaximum(100);
		processBar.setStringPainted(true);
		processBar.setForeground(new Color(0, 128, 0));
		imglabel.setBounds(0, 0, 404, 310);
		getContentPane().add(processBar);
		processBar.setPreferredSize(new Dimension(310, 30));
		processBar.setBounds(0, 290, 404, 20);
		
		JLabel label = new JLabel("maaihtI laaoD krt Aahaot ...");
		imglabel.setLabelFor(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Shivaji01", Font.PLAIN, 18));
		label.setBounds(0, 192, 392, 76);
		getContentPane().add(label);
		Thread t = new Thread()
		{
			
			public void run()
			{
				int i = 0;
				while (i <= 100)
				{
					processBar.setValue(i);
					try
					{
						sleep(90);
					} catch (InterruptedException ex)
					{
					}
					i++;
				}
			}
		};
		t.start();
		try {
			Application.databaseInitialization();
		}
		catch (SQLException e) 
		{

			logger.debug("Problem while loading the database table.");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws Exception {
        logger.debug("*************************************** Progam execution has started **************************************");
		Splash splash = new Splash();
		splash.setVisible(true);
		Thread t = Thread.currentThread();
		t.sleep(10000);
		splash.dispose();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					logger.debug("Going to load class Application....");
					Application app = new Application();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
	}
	
}