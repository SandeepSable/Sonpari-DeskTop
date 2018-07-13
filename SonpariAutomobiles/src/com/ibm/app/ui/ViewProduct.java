package com.ibm.app.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.ibm.app.controller.BasicUIController;
import com.ibm.app.vo.ProductVO;

public class ViewProduct extends DefaultInternalFrame {
	private static final long serialVersionUID = 1L;
	Vector originalTableModel;
	DocumentListener documentListener;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JTextField searchTextField;
	private JButton searchButton;
	private JCheckBox searchOnType;
	private JTable table;

	public ViewProduct() throws Exception {
		getContentPane().setForeground(new Color(75, 0, 130));
		getContentPane().setFont(new Font("Shivaji01", Font.PLAIN, 20));
		Home.lblSs.setVisible(false);
		initComponents();
		originalTableModel = (Vector) ((DefaultTableModel) table.getModel()).getDataVector().clone();
		addDocumentListener();
		this.setTitle("View Products");
	}

	private void addDocumentListener() {
		documentListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent documentEvent) {
				search();
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				search();
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				search();
			}

			private void search() {
				searchTableContents(searchTextField.getText());
			}
		};
		searchOnType.setSelected(true);
	}

	private void initComponents() throws Exception {
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setForeground(new Color(139, 0, 0));
		table.setSurrendersFocusOnKeystroke(true);
		table.setFont(new Font("Shivaji01", Font.PLAIN, 23));
		table.getTableHeader().setFont(new Font("Shivaji01", Font.PLAIN, 20));
		jPanel1 = new JPanel();
		searchOnType = new JCheckBox();
		searchOnType.setText("Sabd saMvaodniSalata");
		searchOnType.setFont(new Font("Shivaji01", Font.PLAIN, 18));
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Shivaji01", Font.ITALIC, 17));
		searchTextField.setForeground(new Color(0, 128, 0));
		searchTextField.setBackground(new Color(255, 255, 255));
		searchButton = new JButton();
		searchButton.setBackground(new Color(0, 0, 128));
		searchButton.setForeground(new Color(255, 255, 255));
		table.setAutoCreateRowSorter(true);
		List<ProductVO> boList = BasicUIController.getAllProducts();

		Object[] columnNames = { "Anau k`maaMk", "vastucao naava", "saivastr maaihtI", "kMpnaIcao naava", "ikMmat",
				"zovalaolao izkaNa","savalat","Saasanacaa kr","daKavaa ka", "kaduna Taka" };
		DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
		for (ProductVO vo : boList) {
			Object[] object = new Object[11];
			object[0] = vo.getProductId();
			object[1] = vo.getProductName();
			object[2] = vo.getDescription();
			object[3] = vo.getManufacturer();
			object[4] = vo.getPrice();
			object[5] = vo.getRackNumber();
			object[6] = vo.getDiscount();
			object[7] = vo.getGst();
			object[8] = vo.getEnablity();
			object[9] = "" ;
			model.addRow(object);
		}
		table.setModel(model);
		jScrollPane1.setViewportView(table);

		getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
		searchOnType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				searchOnTypeItemStateChanged(evt);
			}
		});

		searchButton.setText("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				searchButtonActionPerformed(evt);
			}
		});
		
		JButton btnPrint = new JButton("ip`MT kra");
		btnPrint.setFont(new Font("Shivaji01", Font.PLAIN, 20));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try
			{
				table.print();
			} catch (PrinterException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGap(79)
					.addComponent(btnPrint)
					.addPreferredGap(ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
					.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(searchOnType)
					.addGap(102))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGap(18)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchOnType)
						.addComponent(btnPrint))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		jPanel1.setLayout(jPanel1Layout);

		getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

		pack();
	}

	private void searchButtonActionPerformed(ActionEvent evt) {
		searchTableContents(searchTextField.getText());
	}

	private void searchOnTypeItemStateChanged(ItemEvent evt) {
		if (searchOnType.isSelected()) {
			searchTextField.getDocument().addDocumentListener(documentListener);
		} else {
			searchTextField.getDocument().addDocumentListener(null);
		}
	}

	public void searchTableContents(String searchString) {
		DefaultTableModel currtableModel = (DefaultTableModel) table.getModel();
		currtableModel.setRowCount(0);
		for (Object rows : originalTableModel) {
			Vector rowVector = (Vector) rows;
			for (Object column : rowVector) {
				if (column.toString().contains(searchString)) {
					currtableModel.addRow(rowVector);
					break;
				}
			}
		}
	}
}
