package com.ibm.app.ui;


import javax.swing.JInternalFrame;

public class DefaultInternalFrame extends JInternalFrame
{
	private static final long serialVersionUID = 3739639728664472286L;
	static final int xPosition = 30, yPosition = 30;

	public DefaultInternalFrame() {
		super(
		        "Home Page",
		        true, // resizable
		        true, // closable
		        true, // maximizable
		        true  // iconifiable
		);// 
		
		setSize(getToolkit().getScreenSize());
	}
}
