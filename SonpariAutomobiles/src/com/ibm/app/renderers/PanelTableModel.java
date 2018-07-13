package com.ibm.app.renderers;

import javax.swing.table.DefaultTableModel;

public class PanelTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	@Override
	public int getColumnCount() {
		return 1;
	}

	public void addRow() {
		super.addRow(new Object[] { new Comp(0, 0, "", "") });
	}
}
