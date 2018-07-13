package com.ibm.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

public class SonpariFrame extends Menu 
{
	private static final long serialVersionUID = -8142712025217113280L;
	final static Logger logger = Logger.getLogger(SonpariFrame.class);
	private JPanel footerPanel;
	private JLabel lblSoft;
	private JPanel mainPanel;
	private JLabel label;
	private JFrame frame = null;

	public SonpariFrame(JFrame frame) {
		super(frame);
		this.frame = frame;
		this.frame.getContentPane().setBackground(new Color(46, 139, 87));
		this.frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SonpariFrame.class.getResource(Messages.getString("SonpariFrame.main-frame-logo"))) //$NON-NLS-1$
		);
		this.frame.getContentPane().add(getFooterPanel(), BorderLayout.SOUTH);
		this.frame.getContentPane().add(getMainPanel(), BorderLayout.CENTER);
		logger.debug("Starting Home class constructor"); //$NON-NLS-1$
		initialize();
		logger.debug("Exiting from Home class constructor"); //$NON-NLS-1$
	}

	private void initialize() {
		logger.debug("Entering into Home init method..."); //$NON-NLS-1$
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.frame.setTitle(Messages.getString("SonpariFrame.main-frame-title")); //$NON-NLS-1$
		logger.debug("Exiting from Home init method..."); //$NON-NLS-1$
	}

	private JPanel getFooterPanel() {
		if (footerPanel == null) {
			footerPanel = new JPanel();
			footerPanel.setBackground(new Color(0, 0, 128));
			footerPanel.setLayout(new BorderLayout(0, 0));
			footerPanel.add(getLblSoft());
		}
		return footerPanel;
	}

	private JLabel getLblSoft() {
		if (lblSoft == null) {
			lblSoft = new JLabel(Messages.getString("SonpariFrame.footer-info-bar") //$NON-NLS-1$
			);
			lblSoft.setIcon(new ImageIcon(SonpariFrame.class.getResource("/com/ibm/app/resources/users.gif")));
			lblSoft.setHorizontalAlignment(SwingConstants.CENTER);
			lblSoft.setForeground(new Color(240, 255, 255));
			lblSoft.setFont(new Font(Messages.getString("SonpariFrame.footer-font"), Font.PLAIN, 14)); //$NON-NLS-1$
		}
		return lblSoft;
	}

	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout(0, 0));
			mainPanel.add(getLabel());
		}
		return mainPanel;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(SonpariFrame.class.getResource("/com/ibm/app/resources/win_wallpaper.jpg")));
		}
		return label;

	}
}
