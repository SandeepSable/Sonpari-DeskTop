/*package com.ibm.app.ui;

import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.ibm.app.controller.BasicUIController;
import com.ibm.app.vo.ProductVO;

public class ViewProduct2 extends SonpariFrame
{
	private static final long serialVersionUID = 2167061816260634112L;

	public ViewProduct2() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		List<ProductVO> boList = null;
		try {

			boList = BasicUIController.getAllProducts();
		}
		catch (Exception e) {
			logger.debug("Some problem at loading the data...");
			logger.debug(e);
			JOptionPane.showMessageDialog(getRootPane(), Messages.getString("ViewProduct.table-data-load-problem")); //$NON-NLS-1$
			return;
		}
		ViewProductTableModel model = new ViewProductTableModel(boList);
		JTable productTable = new JTable(model);
		productTable.setFont(new Font(Messages.getString("ViewProduct.table-font-name"), Font.PLAIN, 13)); //$NON-NLS-1$
		productTable.setAutoCreateRowSorter(true);
		productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		productTable.setAutoscrolls(true);
		productTable.sizeColumnsToFit(true);
		
		getContentPane().add(new JScrollPane(productTable));

		this.setTitle(Messages.getString("ViewProduct.table-tittle")); //$NON-NLS-1$
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
*/