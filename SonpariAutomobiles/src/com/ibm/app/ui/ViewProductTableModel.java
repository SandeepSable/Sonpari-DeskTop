package com.ibm.app.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.ibm.app.vo.ProductVO;

public class ViewProductTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = -1078446375301961469L;

	private final List<ProductVO> employeeList;
    
	
	
	DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
	    Font font = new Font(Messages.getString("ViewProduct.table-font-name"),Font.BOLD,20);
	    @Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
	                row, column);
	        setFont(font);
	        return this;
	    }

	};
	
    private final String[] columnNames = new String[] {
            "PRODUCT_ID", "PRODUCT_NAME", "DESCRIPTION", "MANUFACTURER","PRICE","RACK"
    };
    private final Class[] columnClass = new Class[] {
        String.class, String.class, String.class, String.class,String.class,String.class
    };

    public ViewProductTableModel(List<ProductVO> employeeList)
    {
        this.employeeList = employeeList;
    }
    
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return employeeList.size();
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ProductVO row = employeeList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getProductId();
        }
        else if(1 == columnIndex) {
            return row.getProductName();
        }
        else if(2 == columnIndex) {
            return row.getDescription();
        }
        else if(3 == columnIndex) {
            return row.getManufacturer();
        }
        else if(4 == columnIndex) {
            return row.getPrice();
        }
        else if(5 == columnIndex) {
            return row.getRackNumber();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        ProductVO row = employeeList.get(rowIndex);
        if(0 == columnIndex) {
            row.setProductId((String) aValue);
        }
        else if(1 == columnIndex) {
            row.setProductName((String) aValue);
        }
        else if(2 == columnIndex) {
            row.setDescription((String) aValue);
        }
        else if(3 == columnIndex) {
            row.setManufacturer((String) aValue);
        }
        else if(4 == columnIndex) {
            row.setPrice((String) aValue);
        }
        else if(5 == columnIndex) {
            row.setRackNumber((String) aValue);
        }
    }
}
