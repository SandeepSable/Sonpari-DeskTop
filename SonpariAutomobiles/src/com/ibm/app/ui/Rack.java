package com.ibm.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.ibm.app.controller.BasicUIController;
import com.ibm.app.vo.ProductVO;

public class Rack extends DefaultInternalFrame {
	final static Logger logger = Logger.getLogger(Rack.class);

	private static final long serialVersionUID = 1L;
	private JTextField rackId;
	private JTextField rackName;
	private JTextField rackDetail;

	public Rack() throws PropertyVetoException {
		Home.lblSs.setVisible(false);
		logger.debug("AddStock constructor.... start...");
		this.setTitle("Add Stock");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("dkfd");
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblNewLabel.setBounds(176, 110, 167, 34);
		panel.add(lblNewLabel);

		JLabel lblManufacturer = new JLabel("ssssss");
		lblManufacturer.setForeground(new Color(0, 139, 139));
		lblManufacturer.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblManufacturer.setBounds(176, 211, 167, 26);
		panel.add(lblManufacturer);

		JLabel lblProductName = new JLabel("dfd");
		lblProductName.setForeground(new Color(0, 139, 139));
		lblProductName.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblProductName.setBounds(176, 156, 167, 32);
		panel.add(lblProductName);

		JLabel lblDescription = new JLabel("dfd");
		lblDescription.setForeground(new Color(0, 139, 139));
		lblDescription.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblDescription.setBounds(176, 254, 167, 35);
		panel.add(lblDescription);

		JLabel lblPrice = new JLabel("sdfdfd");
		lblPrice.setForeground(new Color(0, 139, 139));
		lblPrice.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblPrice.setBounds(176, 302, 167, 26);
		panel.add(lblPrice);

		JLabel lblRackNumber = new JLabel("dfdfd");
		lblRackNumber.setForeground(new Color(0, 139, 139));
		lblRackNumber.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblRackNumber.setBounds(176, 355, 167, 26);
		panel.add(lblRackNumber);

		JLabel lblAddStockForm = new JLabel("]plabadta vaaZvaa");
		lblAddStockForm.setForeground(new Color(65, 105, 225));
		lblAddStockForm.setFont(new Font("Shivaji01", Font.PLAIN, 35));
		lblAddStockForm.setBounds(517, 13, 518, 48);
		panel.add(lblAddStockForm);

		rackId = new JTextField();
		rackId.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		rackId.setBounds(362, 111, 300, 34);
		panel.add(rackId);
		rackId.setColumns(10);

		rackName = new JTextField();
		rackName.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		rackName.setColumns(10);
		rackName.setBounds(362, 158, 300, 33);
		panel.add(rackName);

		rackDetail = new JTextField();
		rackDetail.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		rackDetail.setColumns(10);
		rackDetail.setBounds(362, 203, 300, 34);
		panel.add(rackDetail);

		JButton btnNewButton = new JButton("sdfdsfdf");
		btnNewButton.setBackground(new Color(210, 105, 30));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double value = 0.0;
				String errorMessage = "";
				ProductVO product = new ProductVO();
				product.setProductId(rackId.getText());
				product.setPrice(value.toString());
				product.setProductName(rackName.getText());

				product.setManufacturer(rackDetail.getText());
				try {
					if (product.getProductId() == null || product.getProductId().length() < 3) {
						rackId.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Id should be more than 3 character long.\n";
					}
					if (product.getProductName() == null || product.getProductName().length() < 3) {
						rackName.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Product name is be atleast 3 character large.\n";

					}
					try {
						String p = product.getPrice();
						Double.parseDouble(p);
					} catch (Exception ee) {
						errorMessage = errorMessage + "Invalid Price Value.\n";

					}

					logger.debug("inside try block of AddStock--> saveProduct btn action clicked.");
					logger.debug(errorMessage);
					if (errorMessage != null && errorMessage != "") {
						JOptionPane.showMessageDialog(getRootPane(), errorMessage);
						return;
					}
					boolean flag = BasicUIController.saveProduct(e, product);
					if (flag) {
						JOptionPane.showMessageDialog(getRootPane(), "Stock added successfully");
					} else {
						JOptionPane.showMessageDialog(getRootPane(), "Failed to add Stock.");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getRootPane(), "Error");
					logger.error("Exception occured:" + e1);
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(381, 422, 110, 34);
		panel.add(btnNewButton);

		JButton btnCancel = new JButton("dfdf");
		btnCancel.setBackground(new Color(210, 105, 30));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rackId.setText("");
				rackName.setText("");
				rackDetail.setText("");
				rackId.setBackground(Color.WHITE);
				rackName.setBackground(Color.WHITE);
			}
		});
		btnCancel.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnCancel.setBounds(529, 422, 110, 34);
		panel.add(btnCancel);
	}
}