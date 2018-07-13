package com.ibm.test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.ibm.app.ui.ViewProductTableModel;
import com.ibm.app.vo.ProductVO;

public class EditableTableExample extends JFrame
{
	public EditableTableExample() {


		// build the list
		List<ProductVO> boList = new ArrayList<ProductVO>();
		boList.add(vo);

		// create the model
		ViewProductTableModel model = new ViewProductTableModel(boList);
		// create the table
		JTable table = new JTable(model);

		// add the table to the frame
		this.add(new JScrollPane(table));

		this.setTitle("Editable Table Example");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new EditableTableExample();
			}
		});
	}
}
