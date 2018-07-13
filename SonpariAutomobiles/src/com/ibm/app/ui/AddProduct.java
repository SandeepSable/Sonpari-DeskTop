package com.ibm.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.ibm.app.controller.BasicUIController;
import com.ibm.app.utilities.ImageFileUtility;
import com.ibm.app.validators.BasicValidator;
import com.ibm.app.vo.ProductVO;

public class AddProduct extends DefaultInternalFrame {

	final static Logger logger = Logger.getLogger(AddProduct.class);

	private static final long serialVersionUID = 1L;
	private JTextField productIdTextField;
	private JTextField productNameTextField;
	private JTextField manufacturerNameTextField;
	private JTextField descriptionTextField;
	private JTextField priceTextfield;
	private JTextField rackNumberTextField;
	private JButton browseBtn;
	private Panel imagePanel;
	private JLabel imageLabel;
	private JTextField discountTextField;
	private JTextField gstTaxtTextField;

	private JCheckBox enablity;

	public AddProduct() throws PropertyVetoException {
		Home.lblSs.setVisible(false);
		logger.debug("AddProduct constructor.... start..."); 
		this.setTitle(Messages.getString("AddProduct.this.title")); 

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(Messages.getString("AddProduct.productid-label-name")); 
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Shivaji01", Font.PLAIN, 25)); 
		lblNewLabel.setBounds(176, 110, 167, 34);
		panel.add(lblNewLabel);

		JLabel lblManufacturer = new JLabel(Messages.getString("AddProduct.manufactucturer-label-name")); 
		lblManufacturer.setForeground(new Color(0, 139, 139));
		lblManufacturer.setFont(new Font("Shivaji01", Font.PLAIN, 25)); 
		lblManufacturer.setBounds(176, 255, 167, 26);
		panel.add(lblManufacturer);

		JLabel lblProductName = new JLabel(Messages.getString("AddProduct.product-name-label-name")); 
		lblProductName.setForeground(new Color(0, 139, 139));
		lblProductName.setFont(new Font("Shivaji01", Font.PLAIN, 25)); 
		lblProductName.setBounds(176, 161, 167, 32);
		panel.add(lblProductName);

		JLabel lblDescription = new JLabel(Messages.getString("AddProduct.description-label-name")); 
		lblDescription.setForeground(new Color(0, 139, 139));
		lblDescription.setFont(new Font("Shivaji01", Font.PLAIN, 25)); 
		lblDescription.setBounds(176, 206, 167, 28);
		panel.add(lblDescription);

		JLabel lblPrice = new JLabel(Messages.getString("AddProduct.price-label-name")); 
		lblPrice.setForeground(new Color(0, 139, 139));
		lblPrice.setFont(new Font("Shivaji01", Font.PLAIN, 25)); 
		lblPrice.setBounds(179, 303, 167, 26);
		panel.add(lblPrice);

		JLabel lblRackNumber = new JLabel(Messages.getString("AddProduct.rack-label-name")); 
		lblRackNumber.setForeground(new Color(0, 139, 139));
		lblRackNumber.setFont(new Font("Shivaji01", Font.PLAIN, 25)); 
		lblRackNumber.setBounds(176, 354, 167, 26);
		panel.add(lblRackNumber);

		JLabel lblAddProductForm = new JLabel(Messages.getString("AddProduct.add-product-form-title")); 
		lblAddProductForm.setForeground(new Color(65, 105, 225));
		lblAddProductForm.setFont(new Font("Shivaji01", Font.PLAIN, 35)); 
		lblAddProductForm.setBounds(517, 13, 518, 48);
		panel.add(lblAddProductForm);

		productIdTextField = new JTextField();
		productIdTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		productIdTextField.setBounds(362, 111, 300, 34);
		panel.add(productIdTextField);
		productIdTextField.setColumns(10);

		productNameTextField = new JTextField();
		productNameTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		productNameTextField.setColumns(10);
		productNameTextField.setBounds(362, 158, 300, 33);
		panel.add(productNameTextField);

		manufacturerNameTextField = new JTextField();
		manufacturerNameTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		manufacturerNameTextField.setColumns(10);
		manufacturerNameTextField.setBounds(362, 200, 300, 34);
		panel.add(manufacturerNameTextField);

		descriptionTextField = new JTextField();
		descriptionTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		descriptionTextField.setColumns(10);
		descriptionTextField.setBounds(362, 252, 300, 34);
		panel.add(descriptionTextField);

		priceTextfield = new JTextField();
		priceTextfield.setForeground(new Color(0, 0, 0));
		priceTextfield.addKeyListener(new KeyAdapter() {
			Boolean dot = false;
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println("char is "+c);
		        if (!(Character.isDigit(c) ||
		         	(c == KeyEvent.VK_BACK_SPACE) ||
		            (c == KeyEvent.VK_DELETE))) {
		             e.consume();
		              //JOptionPane.showMessageDialog(null, "Only numbers are allowed!","WARNING!!",JOptionPane.WARNING_MESSAGE);
		           }
			}
		});
		priceTextfield.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		priceTextfield.setColumns(10);
		priceTextfield.setBounds(363, 300, 299, 33);
		panel.add(priceTextfield);

		rackNumberTextField = new JTextField();
		rackNumberTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		rackNumberTextField.setColumns(10);
		rackNumberTextField.setBounds(362, 346, 300, 35);
		rackNumberTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
		        if (!(Character.isDigit(c) ||
		         	(c == KeyEvent.VK_BACK_SPACE) ||
		            (c == KeyEvent.VK_DELETE))) {
		             e.consume();
		              //JOptionPane.showMessageDialog(null, "Only numbers are allowed!","WARNING!!",JOptionPane.WARNING_MESSAGE);
		           }
			}
		});

		panel.add(rackNumberTextField);

		JButton btnNewButton = new JButton(Messages.getString("AddProduct.save-btn-name")); 
		btnNewButton.setBackground(new Color(210, 105, 30));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double value = 0.0;
				String errorMessage = "";
				try {
					value = Double.parseDouble(priceTextfield.getText());
				} catch (Exception exception) {
					priceTextfield.setBackground(Color.YELLOW);
					errorMessage = errorMessage + "Please check the price.\n";
				}
				ProductVO product = new ProductVO();
				product.setProductId(productIdTextField.getText());
				product.setPrice(value.toString());
				product.setProductName(productNameTextField.getText());

				product.setDescription(descriptionTextField.getText());
				product.setManufacturer(manufacturerNameTextField.getText());
				product.setRackNumber(rackNumberTextField.getText());
				product.setGst(gstTaxtTextField.getText());
				product.setDiscount(discountTextField.getText());
				if (enablity.isSelected()) {
					product.setEnablity("true");
				} else {
					product.setEnablity("false");
				}

				try {
					if (product.getProductId() == null || product.getProductId().length() < 3) {
						productIdTextField.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Id should be more than 3 character long.\n";
					}
					if (product.getProductName() == null || product.getProductName().length() < 3) {
						productNameTextField.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Product name is be atleast 3 character large.\n";

					}
					try {
						String discountString = product.getDiscount();
						Double.parseDouble(discountString);
					} catch (Exception ee) {
						discountTextField.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Invalid discount amount Value. Only number allowed\n";
					}
					try {
						String priceString = product.getPrice();
						Double.parseDouble(priceString);
					} catch (Exception ee) {
						priceTextfield.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Invalid Price Value. Only number allowed\n";
					}

					try {
						String gstPercentage = product.getGst();
						Double.parseDouble(gstPercentage);
					} catch (Exception ee) {
						gstTaxtTextField.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Invalid Tax Value. Only number allowed\n";
					}

					logger.debug("inside try block of AddProduct--> saveProduct btn action clicked."); 
					logger.debug(errorMessage);
					if (errorMessage != null && errorMessage != "") {
						JOptionPane.showMessageDialog(getRootPane(), errorMessage);
						return;
					}
					boolean flag = BasicUIController.saveProduct(e, product, "ADD");
					if (flag) {
						JOptionPane.showMessageDialog(getRootPane(), "Product Registered Successfully");
						resetBackGroundColor();
						resetTextFields();
						// Home.jdpDesktop.getSelectedFrame().doDefaultCloseAction();
					} else {
						JOptionPane.showMessageDialog(getRootPane(), "Failed to Create Product.");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getRootPane(), "Problem occured while Adding New Product"); 
					logger.error("Exception occured:" + e1);
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(384, 524, 110, 34);
		panel.add(btnNewButton);

		JButton btnCancel = new JButton(Messages.getString("AddProduct.cancle-btn-name")); 
		btnCancel.setBackground(new Color(210, 105, 30));
		btnCancel.setForeground(new Color(255, 255, 255));

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resetBackGroundColor();
				resetTextFields();
			}
		});
		btnCancel.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnCancel.setBounds(523, 524, 110, 34);
		panel.add(btnCancel);
		panel.add(getBrowseBtn());
		panel.add(getImagePanel());

		discountTextField = new JTextField();
		discountTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
		        if (!(Character.isDigit(c) ||
		         	(c == KeyEvent.VK_BACK_SPACE) ||
		            (c == KeyEvent.VK_DELETE))) {
		             e.consume();
		              //JOptionPane.showMessageDialog(null, "Only numbers are allowed!","WARNING!!",JOptionPane.WARNING_MESSAGE);
		           }
			}
		});
		discountTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		discountTextField.setColumns(10);
		discountTextField.setBounds(362, 397, 300, 35);
		panel.add(discountTextField);

		JLabel lblSavlat = new JLabel(Messages.getString("AddProduct.lblSavlat.text")); 
		lblSavlat.setForeground(new Color(0, 139, 139));
		lblSavlat.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblSavlat.setBounds(175, 404, 167, 26);
		panel.add(lblSavlat);

		JLabel lblKar = new JLabel(Messages.getString("AddProduct.lblKar.text")); 
		lblKar.setForeground(new Color(0, 139, 139));
		lblKar.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblKar.setBounds(172, 452, 167, 26);
		panel.add(lblKar);

		gstTaxtTextField = new JTextField();
		gstTaxtTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println("char is "+c);
		        if (!(Character.isDigit(c) ||
		         	(c == KeyEvent.VK_BACK_SPACE) ||
		            (c == KeyEvent.VK_DELETE))) {
		             e.consume();
		              //JOptionPane.showMessageDialog(null, "Only numbers are allowed!","WARNING!!",JOptionPane.WARNING_MESSAGE);
		           }
			}
		});
		gstTaxtTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		gstTaxtTextField.setColumns(10);
		gstTaxtTextField.setBounds(362, 447, 300, 35);
		panel.add(gstTaxtTextField);

		JLabel lblActive = new JLabel(Messages.getString("AddProduct.lblActive.text")); 
		lblActive.setFont(new Font("Shivaji01", Font.PLAIN, 18));
		lblActive.setBounds(384, 495, 146, 16);
		panel.add(lblActive);

		enablity = new JCheckBox(Messages.getString("AddProduct.checkBox.text")); 
		enablity.setBounds(538, 490, 113, 25);
		panel.add(enablity);
	}

	private JButton getBrowseBtn() {
		if (browseBtn == null) {
			browseBtn = new JButton(Messages.getString("AddProduct.browse-image-btn-name")); 
			browseBtn.setBackground(new Color(210, 105, 30));
			browseBtn.setForeground(new Color(255, 255, 255));
			browseBtn.setFont(new Font("Shivaji01", Font.PLAIN, 25));
			browseBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle(Messages.getString("AddProduct.image-chooser-title")); 
					fileChooser.setApproveButtonText(Messages.getString("AddProduct.image-select-btn-tittle")); 
					fileChooser.setCurrentDirectory(new File(
							System.getProperty(Messages.getString("AddProduct.default-image-chooser-location")))); 
					fileChooser.setVisible(true);
					fileChooser.setMultiSelectionEnabled(true);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "gif", "jpeg", "jpg");
					fileChooser.setFileFilter(filter);

					int returnVal = fileChooser.showOpenDialog(getRootPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File uploadedFile = fileChooser.getSelectedFile();
						if (!BasicValidator.isNull(uploadedFile) && uploadedFile.canRead() && uploadedFile.canWrite()) {
							if (uploadedFile.isFile()) {
								File file = ImageFileUtility.copyAndSaveProductImage(uploadedFile,
										productIdTextField.getText(), 500, 380);
								imageLabel.setIcon(new ImageIcon(file.toString()));
							} else {
								JOptionPane.showMessageDialog(getRootPane(),
										Messages.getString("AddProduct.image-only-msg")); 
							}
						}

					}
				}
			});
			browseBtn.setBounds(866, 524, 200, 34);
		}
		return browseBtn;
	}

	private Panel getImagePanel() {
		if (imagePanel == null) {
			imagePanel = new Panel();
			imagePanel.setBackground(new Color(230, 230, 250));
			imagePanel.setForeground(new Color(220, 20, 60));
			imagePanel.setBounds(736, 112, 524, 366);
			imagePanel.setLayout(null);
			imagePanel.add(getImageLabel());
		}
		return imagePanel;
	}

	private JLabel getImageLabel() {
		if (imageLabel == null) {
			imageLabel = new JLabel(""); 
			imageLabel.setBounds(0, 0, 524, 366);
			imageLabel.setIcon(new ImageIcon(AddProduct.class.getResource(Messages.getString("AddProduct.default-preview-image")))); 
		}
		return imageLabel;
	}

	public void resetBackGroundColor() {
		productIdTextField.setText("");
		productNameTextField.setText("");
		descriptionTextField.setText("");
		manufacturerNameTextField.setText("");
		priceTextfield.setText("");
		discountTextField.setText("");
		gstTaxtTextField.setText("");
		rackNumberTextField.setText("");
	}

	public void resetTextFields() {
		productIdTextField.setBackground(Color.WHITE);
		priceTextfield.setBackground(Color.WHITE);
		productNameTextField.setBackground(Color.WHITE);
		rackNumberTextField.setBackground(Color.WHITE);
		discountTextField.setBackground(Color.WHITE);
		gstTaxtTextField.setBackground(Color.white);
		enablity.setSelected(false);
		imageLabel.setIcon(new ImageIcon(AddProduct.class.getResource(Messages.getString("AddProduct.default-preview-image")))); 
	
		

	}
}
