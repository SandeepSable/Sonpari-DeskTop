package com.ibm.test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Changeadd {
	JFrame f;
	JPanel p1, p2, p3;
	JTabbedPane tp;
	ImageIcon btnimg1, btnimg2;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;
	JScrollPane sp1;
	JButton savebtn, resetbtn, editbtn1, editbtn2, deletebtn;

	Changeadd() {
		f = new JFrame("Form");
		p1 = new JPanel(new GridLayout(5, 2));
		p2 = new JPanel(new GridLayout(5, 2));
		p3 = new JPanel(new GridLayout(2, 2));
		tp = new JTabbedPane();
		l1 = new JLabel("MenuID");
		l2 = new JLabel("Name");
		l3 = new JLabel("Info");
		l4 = new JLabel("Price");
		l5 = new JLabel("Enter Menu ID to delete");

		l7 = new JLabel("MenuID");
		l8 = new JLabel("Name");
		l9 = new JLabel("Info");
		l10 = new JLabel("Price");
		tf1 = new JTextField(12);
		tf2 = new JTextField(12);
		tf3 = new JTextField(12);
		tf4 = new JTextField(12);
		tf5 = new JTextField(12);
		tf6 = new JTextField(12);
		tf7 = new JTextField(12);
		tf8 = new JTextField(12);
		tf9 = new JTextField(12);
		tf10 = new JTextField(12);
		savebtn = new JButton(" Add ");
		resetbtn = new JButton(" Reset");
		editbtn1 = new JButton(" Edit ");
		editbtn2 = new JButton(" Save");
		deletebtn = new JButton("Delete");
		p1.add(l1);
		p1.add(tf1);
		p1.add(l2);
		p1.add(tf2);
		p1.add(l3);
		p1.add(tf3);
		p1.add(l4);
		p1.add(tf4);
		p1.add(savebtn);
		p1.add(resetbtn);

		p2.add(l7);
		p2.add(tf7);
		p2.add(l8);
		p2.add(tf8);
		p2.add(l9);
		p2.add(tf9);
		p2.add(l10);
		p2.add(tf10);
		p2.add(editbtn1);
		p2.add(editbtn2);

		p3.add(l5);
		p3.add(tf5);
		p3.add(deletebtn);
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
			}
		});
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String value1 = tf1.getText();
				String value2 = tf2.getText();
				String value3 = tf3.getText();
				String value4 = tf4.getText();
				Connection con = null;
				String url = "jdbc:mysql://localhost:3306/mydb";
				String db = "myDB";
				String driver = "com.mysql.jdbc.Driver";
				String user = "root";
				String pass = "root";
				System.out.println(value1 + value2 + value3 + value4);
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url + db, user, pass);
					PreparedStatement st = con.prepareStatement(
							"INSERT INTO menu(menu.menuID,menu.name,menu.info,menu.price) values(?,?,?,?)");
					st.setString(1, value1);
					st.setString(2, value2);
					st.setString(3, value3);
					st.setString(4, value4);
					st.executeUpdate();
					JOptionPane.showMessageDialog(p1, "Data is successfully inserted into database.");
					con.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(p1, "Error in submitting data!");
				}
			}
		});

		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String value1 = tf5.getText();
				Connection con = null;
				String url = "jdbc:mysql://localhost:3306/mydb";
				String db = "myDB";
				String driver = "com.mysql.jdbc.Driver";
				String user = "root";
				String pass = "root";
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url + db, user, pass);
					PreparedStatement st = con.prepareStatement("DELETE FROM menu WHERE menu.menuID = ?");
					st.setString(1, value1);
					st.executeUpdate();
					JOptionPane.showMessageDialog(p3, "Record is deleted successfully.");
					con.close();
				} catch (Exception exp3) {
					JOptionPane.showMessageDialog(p3, "Error in deleting record.");
				}
			}
		});
		editbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String value = tf7.getText();
				Connection con = null;
				String url = "jdbc:mysql://localhost:3306/mydb";
				String db = "myDB";
				String driver = "com.mysql.jdbc.Driver";
				String user = "root";
				String pass = "root";
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url + db, user, pass);
					PreparedStatement st = con.prepareStatement("SELECT FROM menu WHERE menu.menuID=?");
					st.setString(1, value);
					ResultSet res = st.executeQuery();
					res.next();
					tf7.setText(Integer.toString(res.getInt(1)));
					tf8.setText(res.getString(2));
					tf9.setText(res.getString(3));
					tf10.setText(Integer.toString(res.getInt(4)));
					con.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(p2, "Can not edit data");
				}
			}
		});
		editbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Connection con = null;
				String url = "jdbc:mysql://localhost:3306/mydb";
				String db = "myDB";
				String driver = "com.mysql.jdbc.Driver";
				String user = "root";
				String pass = "root";
				try {
					int x = JOptionPane.showConfirmDialog(p2, "Confirm edit? All data will be replaced");
					if (x == 0) {
						try {
							String value1 = tf7.getText();
							String value2 = tf8.getText();
							String value3 = tf9.getText();
							String value4 = tf10.getText();

							Class.forName(driver);
							con = DriverManager.getConnection(url + db, user, pass);
							;
							Statement st = con.createStatement();
							st.executeUpdate("UPDATE menu set menu.name'" + value2 + "', menu.info'" + value3
									+ "', menu.price'" + value4 + "' where menu.menuID='" + value1 + "'");
							JOptionPane.showMessageDialog(p2, "Updated successfully");
							con.close();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(p2, "Error in updating edit fields");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(p2, "Error");
				}
			}
		});
	}

	void dis() {
		f.getContentPane().add(tp);
		tp.addTab("Add Record", p1);
		tp.addTab("Edit Record", p2);
		tp.addTab("Delete Record", p3);

		f.setSize(350, 180);
		f.setVisible(true);
		f.setResizable(true);
	}

	public static void main(String z[]) {
		Changeadd pro = new Changeadd();
		pro.dis();
	}

}