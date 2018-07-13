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
import com.ibm.app.vo.StockVO;

public class AddStock extends DefaultInternalFrame 
{
	final static Logger logger = Logger.getLogger(AddStock.class);
	
	private static final long serialVersionUID = 1L;

	private JTextField stockId;
	private JTextField productName;
	private JTextField quantity;

	public AddStock() throws PropertyVetoException {
		setLocation(6, -31);
		Home.lblSs.setVisible(false);
		logger.debug("AddStock constructor.... start...");
		this.setTitle("Add Stock");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(Messages.getString("AddProduct.productid-label-name"));
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblNewLabel.setBounds(367, 97, 167, 34);
		panel.add(lblNewLabel);

		JLabel lblQuantity = new JLabel(Messages.getString("AddStock.stock-quantity-label-name"));
		lblQuantity.setForeground(new Color(0, 139, 139));
		lblQuantity.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblQuantity.setBounds(367, 225, 167, 26);
		panel.add(lblQuantity);

		JLabel lblProductName = new JLabel(Messages.getString("AddStock.product-name-label-name"));
		lblProductName.setForeground(new Color(0, 139, 139));
		lblProductName.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblProductName.setBounds(367, 158, 167, 32);
		panel.add(lblProductName);

		JLabel lblAddStockForm = new JLabel(Messages.getString("AddStock.lblAddStockForm.text")); //$NON-NLS-1$
		lblAddStockForm.setForeground(new Color(65, 105, 225));
		lblAddStockForm.setFont(new Font("Shivaji01", Font.PLAIN, 35));
		lblAddStockForm.setBounds(557, 13, 191, 48);
		panel.add(lblAddStockForm);

		stockId = new JTextField();
		stockId.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		stockId.setBounds(553, 98, 300, 34);
		panel.add(stockId);
		stockId.setColumns(10);

		productName = new JTextField();
		productName.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		productName.setColumns(10);
		productName.setBounds(553, 160, 300, 33);
		panel.add(productName);

		quantity = new JTextField();
		quantity.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		quantity.setColumns(10);
		quantity.setBounds(553, 217, 300, 34);
		panel.add(quantity);

		JButton btnNewButton = new JButton(Messages.getString("AddStock.save-btn-name"));
		btnNewButton.setBackground(new Color(210, 105, 30));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String errorMessage = "";				
				StockVO stock = new StockVO();
				stock.setStockId(stockId.getText());
				stock.setProductName(productName.getText());
				stock.setQuantity(quantity.getText());
				stock.setAddedDate(addedDate.getText());
				stock.setAddedBy(addedBy.getText());
				try 
				{
					if (stock.getStockId() == null || stock.getStockId().length() < 3) {
						stockId.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Id should be more than 3 character long.\n";
					}
					if (stock.getProductName() == null || stock.getProductName().length() < 3) 
					{
						productName.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Product name is be atleast 3 character large.\n";
					}

					logger.debug("inside try block of AddStock--> saveProduct btn action clicked.");
					logger.debug(errorMessage);
					if (errorMessage != null && errorMessage != "") {
						JOptionPane.showMessageDialog(getRootPane(), errorMessage);
						return;
					}
					boolean flag = BasicUIController.saveStock(e, stock);
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
		btnNewButton.setBounds(566, 384, 110, 34);
		panel.add(btnNewButton);

		JButton btnCancel = new JButton(Messages.getString("AddProduct.cancle-btn-name"));
		btnCancel.setBackground(new Color(210, 105, 30));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockId.setText("");
				productName.setText("");
				quantity.setText("");
				addedDate.setText("");
				addedBy.setText("");

				
				stockId.setBackground(Color.WHITE);
				productName.setBackground(Color.WHITE);
				quantity.setBackground(Color.WHITE);
				addedDate.setBackground(Color.WHITE);
				addedBy.setBackground(Color.WHITE);
			}
		});
		btnCancel.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnCancel.setBounds(718, 384, 110, 34);
		panel.add(btnCancel);
	}
}