package com.ibm.app.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class SPDefaultTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 2462928549086810808L;

	public Component getTableCellRendererComponent(JTable table, Object oValue, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = super.getTableCellRendererComponent(table, oValue, isSelected, hasFocus, row, column);
		String value = (String) oValue;

		JButton btn = new JButton(value);
		btn.setForeground(Color.RED);

		return btn;
	}
}
