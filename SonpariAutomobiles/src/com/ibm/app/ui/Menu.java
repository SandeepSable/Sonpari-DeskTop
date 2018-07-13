package com.ibm.app.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.ibm.app.Application;
import com.ibm.app.controller.BasicUIController;

public class Menu {

	private JFrame frame = null;
	private static final long serialVersionUID = -7982600358909371499L;
	final static Logger logger = Logger.getLogger(Menu.class);
	final static InputStream is = Menu.class.getResourceAsStream(Messages.getString("Menu.Marathi-Saras.ttf")); //$NON-NLS-1$
	static Font marathiFont = null;
	public static JMenuBar menuBar = null;
	static {
		try {
			marathiFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			logger.error(e.getMessage());
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
	}

	public Menu(JFrame frame) {
		this.frame = frame;
		this.frame.getContentPane().setBackground(new Color(0, 128, 128));
		this.frame.getContentPane().setEnabled(false);
		logger.debug(Messages.getString("Menu.1")); //$NON-NLS-1$
		initialize();
		logger.debug("Menu initialization is completed. exiting from constructor..."); //$NON-NLS-1$
	}

	private void initialize() {
		logger.debug("Menu initialization started..."); //$NON-NLS-1$
		this.frame.setVisible(true);
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setMargin(new Insets(1111, 1110, 1110, 1110));
		menuBar.setBackground(new Color(220, 20, 60));
		this.frame.setJMenuBar(menuBar);

		JMenu mnHome = new JMenu(Messages.getString("Menu.home-menu-name")); //$NON-NLS-1$
		mnHome.setBackground(new Color(0, 0, 128));
		mnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Application();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnHome.setHorizontalAlignment(SwingConstants.CENTER);
		mnHome.setForeground(new Color(255, 255, 255));
		mnHome.setFont(new Font("Shivaji01", Font.PLAIN, 30)); //$NON-NLS-1$
		menuBar.add(mnHome);

		JMenu mnProduct = new JMenu(Messages.getString("Menu.product-menu-name"));
		mnProduct.setBackground(new Color(0, 0, 128));
		mnProduct.setHorizontalAlignment(SwingConstants.CENTER);
		mnProduct.setFont(new Font("Shivaji01", Font.PLAIN, 30)); //$NON-NLS-1$
		mnProduct.setForeground(new Color(255, 255, 255));
		menuBar.add(mnProduct);

		JMenuItem addProductMenu = new JMenuItem(Messages.getString("Menu.addproduct-menu-name")); //$NON-NLS-1$
		addProductMenu.setForeground(new Color(255, 255, 255));
		addProductMenu.setBackground(new Color(25, 25, 112));
		addProductMenu.setFont(new Font("Shivaji01", Font.PLAIN, 24));
		addProductMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					BasicUIController.controllAddProductAction(event);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		mnProduct.add(addProductMenu);

		JMenuItem viewProductMenu = new JMenuItem(Messages.getString("Menu.viewproduct-menu-name")); //$NON-NLS-1$
		viewProductMenu.setForeground(new Color(255, 255, 255));
		viewProductMenu.setBackground(new Color(25, 25, 112));
		viewProductMenu.setFont(new Font("Shivaji01", Font.PLAIN, 24));
		viewProductMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BasicUIController.handleProductsViewRequest(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JMenuItem mntmBdlKara = new JMenuItem(Messages.getString("Menu.mntmBdlKara.text")); //$NON-NLS-1$
		mntmBdlKara.setForeground(new Color(255, 255, 255));
		mntmBdlKara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BasicUIController.handleProductEditRequest(e);
			}
		});
		mntmBdlKara.setFont(new Font("Shivaji01", Font.PLAIN, 24));
		mntmBdlKara.setBackground(new Color(25, 25, 112));
		mnProduct.add(mntmBdlKara);
		mnProduct.add(viewProductMenu);

		JMenu mnStock = new JMenu(Messages.getString("Menu.stock-menu-name"));
		mnStock.setBackground(new Color(0, 0, 128));
		mnStock.setHorizontalAlignment(SwingConstants.CENTER);
		mnStock.setFont(new Font("Shivaji01", Font.PLAIN, 30)); //$NON-NLS-1$
		mnStock.setForeground(new Color(255, 255, 255));
		menuBar.add(mnStock);

		JMenuItem addStock = new JMenuItem(Messages.getString("Menu.mntmJamaKra.text")); //$NON-NLS-1$
		addStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BasicUIController.handleAddStockRequest(e);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
		addStock.setForeground(Color.WHITE);
		addStock.setFont(new Font("Shivaji01", Font.PLAIN, 24));
		addStock.setBackground(new Color(25, 25, 112));
		mnStock.add(addStock);

		JMenuItem viewStock = new JMenuItem("pha");
		viewStock.setForeground(Color.WHITE);
		viewStock.setFont(new Font("Shivaji01", Font.PLAIN, 24));
		viewStock.setBackground(new Color(25, 25, 112));
		mnStock.add(viewStock);

		JMenu mnData = new JMenu(Messages.getString("Menu.fomat-menu-name"));
		mnData.setBackground(new Color(0, 0, 128));
		mnData.setHorizontalAlignment(SwingConstants.CENTER);
		mnData.setFont(new Font("Shivaji01", Font.PLAIN, 30)); //$NON-NLS-1$
		mnData.setForeground(new Color(255, 255, 255));
		menuBar.add(mnData);

		JMenu mnBackup = new JMenu(Messages.getString("Menu.backup-menu-name"));
		mnBackup.setBackground(new Color(0, 0, 128));
		mnBackup.setHorizontalAlignment(SwingConstants.CENTER);
		mnBackup.setFont(new Font("Shivaji01", Font.PLAIN, 30)); //$NON-NLS-1$
		mnBackup.setForeground(new Color(255, 255, 255));
		menuBar.add(mnBackup);

		JMenuItem mntmBakup = new JMenuItem(Messages.getString("Menu.mntmBakup.text")); //$NON-NLS-1$
		mntmBakup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BasicUIController.handleDataBackupRequest(e);
			}
		});
		mntmBakup.setForeground(Color.WHITE);
		mntmBakup.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		mntmBakup.setBackground(new Color(25, 25, 112));
		mnBackup.add(mntmBakup);

		JMenuItem mntmPrt = new JMenuItem(Messages.getString("Menu.mntmPrt.text")); //$NON-NLS-1$
		mntmPrt.setForeground(Color.WHITE);
		mntmPrt.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		mntmPrt.setBackground(new Color(25, 25, 112));
		mnBackup.add(mntmPrt);

		JMenu mnHelp = new JMenu(Messages.getString("Menu.help-menu-name"));
		mnHelp.setBackground(new Color(0, 0, 128));
		mnHelp.setHorizontalAlignment(SwingConstants.CENTER);
		mnHelp.setFont(new Font("Shivaji01", Font.PLAIN, 30));
		mnHelp.setForeground(new Color(255, 255, 255));
		menuBar.add(mnHelp);
		logger.debug("Menu initialization is completed."); //$NON-NLS-1$
	}

}
