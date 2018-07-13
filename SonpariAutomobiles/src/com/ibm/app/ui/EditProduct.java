package com.ibm.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.ibm.app.controller.BasicUIController;
import com.ibm.app.exceptions.ProductNotFoundException;
import com.ibm.app.utilities.ImageFileUtility;
import com.ibm.app.validators.BasicValidator;
import com.ibm.app.vo.ProductVO;
import javax.swing.SwingConstants;

public class EditProduct extends DefaultInternalFrame {

	private static final long serialVersionUID = 4600631405737582524L;
	final static Logger logger = Logger.getLogger(EditProduct.class);
	private JTextField productId;
	private JTextField manufacturerName;
	private JTextField description;
	private JTextField price;
	private JTextField rackNumber;
	private JButton browseBtn;
	private JComboBox<String> productNameCmbBx;
	private List<ProductVO> productsList = null;
	private HashMap<String, ProductVO> productTable;
	private JTextField gstTaxTextField;
	private JTextField discountTextField;
	private JCheckBox isEnabledCheckbox;
	private JLabel imageLabel;
	private HashSet<String> productsNameSet;

	public EditProduct() {
		Home.lblSs.setVisible(false);
		logger.debug("EditProduct constructor.... start...");
		this.setTitle("Edit Product");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(Messages.getString("EditProduct.productid-label-name"));
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblNewLabel.setBounds(176, 109, 176, 34);
		panel.add(lblNewLabel);

		JLabel lblManufacturer = new JLabel(Messages.getString("EditProduct.manufactucturer-label-name"));
		lblManufacturer.setForeground(new Color(0, 139, 139));
		lblManufacturer.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblManufacturer.setBounds(176, 255, 167, 26);
		panel.add(lblManufacturer);

		JLabel lblProductName = new JLabel(Messages.getString("EditProduct.product-name-label-name"));
		lblProductName.setForeground(new Color(0, 139, 139));
		lblProductName.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblProductName.setBounds(176, 156, 186, 32);
		panel.add(lblProductName);

		JLabel lblDescription = new JLabel(Messages.getString("EditProduct.description-label-name"));
		lblDescription.setForeground(new Color(0, 139, 139));
		lblDescription.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblDescription.setBounds(176, 201, 167, 35);
		panel.add(lblDescription);

		JLabel lblPrice = new JLabel(Messages.getString("EditProduct.price-label-name"));
		lblPrice.setForeground(new Color(0, 139, 139));
		lblPrice.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblPrice.setBounds(176, 302, 167, 26);

		panel.add(lblPrice);

		JLabel lblRackNumber = new JLabel(Messages.getString("EditProduct.rack-label-name"));
		lblRackNumber.setForeground(new Color(0, 139, 139));
		lblRackNumber.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		lblRackNumber.setBounds(176, 355, 167, 26);
		panel.add(lblRackNumber);

		JLabel lblEditProductForm = new JLabel(Messages.getString("EditProduct.add-product-form-title"));
		lblEditProductForm.setForeground(new Color(65, 105, 225));
		lblEditProductForm.setFont(new Font("Shivaji01", Font.PLAIN, 36));
		lblEditProductForm.setBounds(460, 13, 630, 48);
		panel.add(lblEditProductForm);

		productId = new JTextField();
		productId.setEnabled(false);
		productId.setEditable(false);
		productId.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		productId.setBounds(362, 111, 300, 34);
		panel.add(productId);
		productId.setColumns(10);

		manufacturerName = new JTextField();
		manufacturerName.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		manufacturerName.setColumns(10);
		manufacturerName.setBounds(362, 252, 300, 34);
		panel.add(manufacturerName);

		description = new JTextField();
		description.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		description.setColumns(10);
		description.setBounds(362, 202, 300, 34);
		panel.add(description);

		price = new JTextField();
		price.setForeground(new Color(0, 0, 0));
		price.addKeyListener(new KeyAdapter() {
			Boolean dot = false;

			@Override
			public void keyPressed(KeyEvent e) {

				char c = e.getKeyChar();
				System.out.println("char is "+c);
		        if (!(Character.isDigit(c) ||!(c == KeyEvent.VK_BACK_SPACE) || !(c == KeyEvent.VK_DELETE))) {
		             e.consume();
		              //JOptionPane.showMessageDialog(null, "Only numbers are allowed!","WARNING!!",JOptionPane.WARNING_MESSAGE);
		           }
			}
		});
		price.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		price.setColumns(10);
		price.setBounds(363, 300, 299, 33);
		panel.add(price);

		rackNumber = new JTextField();
		rackNumber.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		rackNumber.setColumns(10);
		rackNumber.setBounds(363, 346, 300, 35);
		panel.add(rackNumber);

		JButton btnNewButton = new JButton(Messages.getString("EditProduct.save-btn-name"));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(210, 105, 30));
		btnNewButton.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double value = 0.0;
				String errorMessage = "";
				try {
					value = Double.parseDouble(price.getText());
				} catch (Exception exception) {
					logger.debug(exception);
					price.setBackground(Color.YELLOW);
					errorMessage = errorMessage + "Please check the price.\n";
				}
				ProductVO product = new ProductVO();
				product.setProductId(productId.getText());
				product.setPrice(value.toString());
				product.setProductName(productNameCmbBx.getSelectedItem().toString());
				product.setDiscount(discountTextField.getText());
				product.setGst(gstTaxTextField.getText());
				product.setEnablity(isEnabledCheckbox.getText());
				product.setDescription(description.getText());
				product.setManufacturer(manufacturerName.getText());
				product.setRackNumber(rackNumber.getText());
				try {
					if (product.getProductId() == null || product.getProductId().length() < 3) {
						productId.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Id should be more than 3 character long.\n";
					}
					try {
						String p = product.getPrice();
						Double.parseDouble(p);
					} catch (Exception ee) {

						ee.printStackTrace();
						logger.debug(ee);
						price.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Invalid Price Value.\n";

					}

					try {
						logger.debug("before==================>");
						logger.debug("Now=============>" + product.getDiscount());
						String discountString = product.getDiscount().trim().toString();
						System.out.println("discountString:::" + discountString);
						Double.parseDouble(discountString);
					} catch (Exception ee) {
						ee.printStackTrace();
						logger.debug(ee);
						discountTextField.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Invalid discount amount Value. Only number allowed\n";
					}
					try {
						String priceString = product.getPrice().trim().toString();
						Double.parseDouble(priceString);
					} catch (Exception ee) {
						logger.debug(ee);
						price.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Invalid Price Value. Only number allowed\n";
					}

					try {
						String gstPercentage = product.getGst().trim().toString();
						Double.parseDouble(gstPercentage);
					} catch (Exception ee) {
						logger.debug(ee);
						gstTaxTextField.setBackground(Color.YELLOW);
						errorMessage = errorMessage + "Invalid Tax Value. Only number allowed\n";
					}

					logger.debug("inside try block of EditProduct--> saveProduct btn action clicked.");
					logger.debug(errorMessage);
					if (errorMessage != null && errorMessage != "") {
						JOptionPane.showMessageDialog(getRootPane(), errorMessage);
						return;
					}
					boolean flag = BasicUIController.saveProduct(e, product, "EDIT");
					if (flag) {
						JOptionPane.showMessageDialog(getRootPane(), "Product Updated Successfully");
						Home.jdpDesktop.getSelectedFrame().doDefaultCloseAction();
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getRootPane(), "Failed to update the product");
					logger.error("Exception occured:" + e1);
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(322, 545, 110, 34);
		panel.add(btnNewButton);

		JButton btnCancel = new JButton(Messages.getString("EditProduct.cancle-btn-name"));
		btnCancel.setBackground(new Color(210, 105, 30));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productNameCmbBx.setSelectedIndex(0);

				productId.setText("");
				productId.setBackground(Color.WHITE);

				discountTextField.setText("");
				discountTextField.setBackground(Color.WHITE);

				price.setText("");
				price.setBackground(Color.WHITE);

				gstTaxTextField.setText("");
				gstTaxTextField.setBackground(Color.WHITE);

				rackNumber.setText("");
				rackNumber.setBackground(Color.WHITE);

				isEnabledCheckbox.setSelected(false);

				productNameCmbBx.setSelectedIndex(0);

				description.setText("");
				description.setBackground(Color.WHITE);

				manufacturerName.setText("");
				manufacturerName.setBackground(Color.WHITE);

				imageLabel.setIcon(new ImageIcon(
						EditProduct.class.getResource(Messages.getString("EditProduct.default-preview-image"))));

			}
		});
		btnCancel.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnCancel.setBounds(444, 545, 110, 34);
		panel.add(btnCancel);
		panel.add(getBrowseBtn());
		panel.add(getComboBox());

		gstTaxTextField = new JTextField();
		gstTaxTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println("char is " + c);
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					// JOptionPane.showMessageDialog(null, "Only numbers are
					// allowed!","WARNING!!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		gstTaxTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		gstTaxTextField.setColumns(10);
		gstTaxTextField.setBounds(361, 448, 300, 35);
		panel.add(gstTaxTextField);

		JLabel label = new JLabel("savalat");
		label.setForeground(new Color(0, 139, 139));
		label.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		label.setBounds(179, 405, 167, 26);
		panel.add(label);

		JLabel label_1 = new JLabel("Saasanaacaa  kr ");
		label_1.setForeground(new Color(0, 139, 139));
		label_1.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		label_1.setBounds(176, 453, 167, 26);
		panel.add(label_1);

		discountTextField = new JTextField();
		discountTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println("char is " + c);
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
					// JOptionPane.showMessageDialog(null, "Only numbers are
					// allowed!","WARNING!!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		discountTextField.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		discountTextField.setColumns(10);
		discountTextField.setBounds(362, 398, 300, 35);
		panel.add(discountTextField);

		JLabel label_2 = new JLabel("Akarayart");
		label_2.setFont(new Font("Shivaji01", Font.PLAIN, 18));
		label_2.setBounds(388, 496, 134, 20);
		panel.add(label_2);

		isEnabledCheckbox = new JCheckBox("");
		isEnabledCheckbox.setBounds(549, 494, 145, 25);
		panel.add(isEnabledCheckbox);

		JButton btnKadunTaka = new JButton(Messages.getString("EditProduct.btnKadunTaka.text"));
		btnKadunTaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pid = productId.getText();
					logger.debug("started to delete product" + pid);
					if (pid != null || pid.length() > 3) {
						BasicUIController.handleDeleteProductRequest(pid);
						JOptionPane.showMessageDialog(getRootPane(), "Product deleted successfully:" + pid);
						// Home.jdpDesktop.getSelectedFrame().doDefaultCloseAction();

					} else {
						JOptionPane.showMessageDialog(getRootPane(), "Please check the product id.");
					}
					logger.debug("success in deleting product.");

				} catch (ProductNotFoundException ex) {
					JOptionPane.showMessageDialog(getRootPane(), "Invalid product id");
				} catch (Exception ex) {
					logger.debug(ex.getMessage());
					JOptionPane.showMessageDialog(getRootPane(), "Failed to deleted the Product");
				}

			}
		});
		btnKadunTaka.setForeground(Color.WHITE);
		btnKadunTaka.setFont(new Font("Shivaji01", Font.PLAIN, 25));
		btnKadunTaka.setBackground(new Color(210, 105, 30));
		btnKadunTaka.setBounds(566, 545, 120, 34);
		panel.add(btnKadunTaka);

		Panel panel_1 = new Panel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(220, 20, 60));
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(729, 117, 524, 366);
		panel.add(panel_1);

		imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(EditProduct.class.getResource("/com/ibm/app/resources/sonpari.png")));
		imageLabel.setBounds(0, 0, 524, 366);
		panel_1.add(imageLabel);
	}

	private JButton getBrowseBtn() {
		if (browseBtn == null) {
			browseBtn = new JButton(Messages.getString("EditProduct.browse-image-btn-name"));
			browseBtn.setForeground(new Color(255, 255, 255));
			browseBtn.setBackground(new Color(210, 105, 30));
			browseBtn.setFont(new Font("Shivaji01", Font.PLAIN, 25));
			browseBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle(Messages.getString("EditProduct.image-chooser-title"));
					fileChooser.setApproveButtonText(Messages.getString("EditProduct.image-select-btn-tittle"));
					fileChooser.setCurrentDirectory(new File(
							System.getProperty(Messages.getString("EditProduct.default-image-chooser-location"))));
					fileChooser.setVisible(true);
					fileChooser.setMultiSelectionEnabled(false);
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

					FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "gif", "jpeg", "jpg");
					fileChooser.setFileFilter(filter);

					int returnVal = fileChooser.showOpenDialog(getRootPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File uploadedFile = fileChooser.getSelectedFile();
						if (!BasicValidator.isNull(uploadedFile) && uploadedFile.canRead() && uploadedFile.canWrite()) {
							if (uploadedFile.isFile()) {
								File file = ImageFileUtility.copyAndSaveProductImage(uploadedFile, productId.getText(),
										500, 500);
								imageLabel.setIcon(new ImageIcon(file.toString()));
							} else {
								JOptionPane.showMessageDialog(getRootPane(), "Select image file only.");
							}
						}

					}
				}
			});
			browseBtn.setBounds(920, 545, 159, 34);
		}
		return browseBtn;
	}

	private JComboBox<String> getComboBox() {
		if (productNameCmbBx == null) {
			productNameCmbBx = new JComboBox<String>();
			productNameCmbBx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					{
						String selectedItem = (String) productNameCmbBx.getSelectedItem();
						ProductVO productVo = productTable.get(selectedItem);
						productId.setText(productVo.getProductId());
						description.setText(productVo.getDescription());
						gstTaxTextField.setText(productVo.getGst());
						discountTextField.setText(productVo.getDiscount());
						manufacturerName.setText(productVo.getManufacturer());
						price.setText(productVo.getPrice());
						rackNumber.setText(productVo.getRackNumber());
						String isActiveVal = productVo.getEnablity();

						if (isActiveVal == "" || isActiveVal == null || isActiveVal.equalsIgnoreCase("false")
								|| isActiveVal.trim() == "") {
							isEnabledCheckbox.setSelected(false);
						} else {
							isEnabledCheckbox.setSelected(true);
						}

						try {
							imageLabel.setIcon(new ImageIcon(EditProduct.class
									.getResource(Messages.getString("EditProduct.default-preview-image"))));
							File file = new File("c://sonpari//images//" + productVo.getProductId());
							if (file.isDirectory()) {
								logger.debug("Is a file");
								File[] listOfFiles = file.listFiles();
								for (int i = 0; i < listOfFiles.length; i++) {
									if (listOfFiles[i].isFile()) {
										String fileStr = listOfFiles[i].getPath();
										logger.debug("fileStr::::::" + fileStr);
										if (fileStr != null && fileStr.contains("small_")) {
											imageLabel.setIcon(new ImageIcon(fileStr));
										}
										logger.debug("found image...");
										break;
									}
								}
							} else {
								logger.debug("is a file.");
							}

						} catch (Exception ex) {
							imageLabel.setIcon(new ImageIcon(EditProduct.class
									.getResource(Messages.getString("EditProduct.default-preview-image"))));
							ex.printStackTrace();
							logger.debug("Problem in selecting the file");
						}

					}
				}
			});
			productNameCmbBx.setEditable(true);
			productNameCmbBx.setFont(new Font("Shivaji01", Font.PLAIN, 20));
			productTable = new HashMap<String, ProductVO>();
			productsNameSet = new HashSet<String>();
			try {
				if (productsList == null || productsList.isEmpty()) {
					productsList = BasicUIController.getAllProducts();
				}
				for (ProductVO vo : productsList) {
					String productName = vo.getProductName();
					productsNameSet.add(productName);
					productTable.put(productName, vo);
				}
			} catch (Exception e) {
				logger.debug(e);
				JOptionPane.showMessageDialog(getRootPane(), "Problem while loading the Products List.");
			}
			Vector<String> vectorData = new Vector();
			vectorData.addAll(productsNameSet);
			productNameCmbBx.setModel(new DefaultComboBoxModel<String>(vectorData));
			productNameCmbBx.setBounds(362, 156, 299, 34);
		}
		return productNameCmbBx;
	}
}