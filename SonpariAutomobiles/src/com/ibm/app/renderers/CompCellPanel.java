package com.ibm.app.renderers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CompCellPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelWith = new JLabel("With ");
	private JComboBox typeCombo = new JComboBox(new Object[] { "height", "length", "volume" });
	private JComboBox relationCombo = new JComboBox(new Object[] { "above", "below", "between" });
	private JTextField lowerField = new JTextField();
	private JLabel labelAnd = new JLabel(" and ");
	private JTextField upperField = new JTextField();
	private JButton removeButton = new JButton("remove");

	public CompCellPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		relationCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableUpper(relationCombo.getSelectedIndex() == 2);
			}
		});
		enableUpper(false);
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, (Component) e.getSource());
				int row = table.getEditingRow();
				table.getCellEditor().stopCellEditing();
				((DefaultTableModel) table.getModel()).removeRow(row);
			}
		});
		add(labelWith);
		add(typeCombo);
		add(relationCombo);
		add(lowerField);
		add(labelAnd);
		add(upperField);
		add(Box.createHorizontalStrut(100));
		add(removeButton);
	}

	private void enableUpper(boolean enable) {
		labelAnd.setEnabled(enable);
		upperField.setEnabled(enable);
	}

	public void setComp(Comp Comp) {
		typeCombo.setSelectedIndex(Comp.type);
		relationCombo.setSelectedIndex(Comp.relation);
		lowerField.setText(Comp.lower);
		upperField.setText(Comp.upper);
		enableUpper(Comp.relation == 2);
	}

	public Comp getComp() {
		return new Comp(typeCombo.getSelectedIndex(), relationCombo.getSelectedIndex(), lowerField.getText(),
				upperField.getText());
	}
}