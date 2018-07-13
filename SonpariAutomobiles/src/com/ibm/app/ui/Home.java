package com.ibm.app.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Home extends SonpariFrame implements WindowListener
{
	private static final long	serialVersionUID	= 3127330155612168440L;
	public static JDesktopPane	jdpDesktop			= new JDesktopPane();
	static int					openFrameCount		= 0;
	public static JLabel		lblSs;
	private JFrame				frame				= null;
	
	public Home(JFrame frame)
	{
		super(frame);
		this.frame = frame;
		this.frame.setTitle("Sonpari Automobiles");
		WindowListener listener = new WindowAdapter()
		{
			public void windowClosing(WindowEvent w)
			{
				int response = JOptionPane.showConfirmDialog(null,
				        " Are you sure you want isuue the ticket?",
				        "Confirmation", JOptionPane.YES_NO_OPTION,
				        JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		};
		this.frame.addWindowListener(listener);
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(inset, inset, screenSize.width - inset * 2,
		// screenSize.height -
		// inset * 2);
		frame.setContentPane(jdpDesktop);
		
		lblSs = new JLabel("");
		lblSs.setBounds(0, -78, 1920, 1193);
		jdpDesktop.add(lblSs);
		jdpDesktop.setLayer(lblSs, 1);
		lblSs.setIcon(new ImageIcon(Home.class.getResource("/com/ibm/app/resources/win_wallpaper.jpg")));
		jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
	}
	
	public static void createFrame(JInternalFrame frame)
	{
		frame.setVisible(true);
		jdpDesktop.add(frame);
		try
		{
			JInternalFrame frames[] = jdpDesktop.getAllFrames();
			for (JInternalFrame f : frames)
			{
				if (!f.isIcon())
					f.setIcon(true);
			}
			frame.setMaximum(true);
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e)
		{
			
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e)
	{
		logger.debug("window opened.");
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		int dialogResult = JOptionPane.showConfirmDialog(null,
		        "Do you realy want to close ?", "Warning",
		        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if (dialogResult == JOptionPane.OK_OPTION)
		{
			this.frame.dispose();
		} else
		{
			frame.pack();
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e)
	{
		logger.debug("window is closed");
	}
	
	@Override
	public void windowIconified(WindowEvent e)
	{
		logger.debug("window is iconed");
	}
	
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		
		logger.debug("WINDOW DE ICONED");
		
	}
	
	@Override
	public void windowActivated(WindowEvent e)
	{
		logger.debug("WINDOW IS ACTIVATED");
	}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		logger.debug("WINDOW IS GOING TO BE DIACTIVATED");
	}
}