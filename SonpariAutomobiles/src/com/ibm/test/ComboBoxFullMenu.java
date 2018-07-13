package com.ibm.test;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class ComboBoxFullMenu<E> extends JComboBox<E>
{
	
	public ComboBoxFullMenu(E[] items)
	{
		super(items);
		addActionListener(this);
	}
	
	public ComboBoxFullMenu(Vector<E> items)
	{
		super(items);
		addActionListener(this);
	}
	
	public ComboBoxFullMenu(ComboBoxModel<E> aModel)
	{
		super(aModel);
		addActionListener(this);
	}
	
	/**
	 * Small hack to get pop up menu size bigger enough to show items even
	 * though
	 * the combo box size could be smaller
	 */
	private boolean layingOut = false;
	
	@Override
	public void doLayout()
	{
		try
		{
			layingOut = true;
			super.doLayout();
		} finally
		{
			layingOut = false;
		}
	}
	
	@Override
	public Dimension getSize()
	{
		Dimension dim = super.getSize();
		if (!layingOut)
		{
			dim.width = Math.max(dim.width, getPreferredSize().width);
		}
		return dim;
	}
}