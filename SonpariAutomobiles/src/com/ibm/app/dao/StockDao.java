package com.ibm.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ibm.app.bo.ProductBO;
import com.ibm.app.bo.StockBO;
import com.ibm.app.utilities.DBConnection;

@Component
public class StockDao
{
	final static Logger logger = Logger.getLogger(ProductDao.class);

	public StockDao() {
		logger.debug(Messages.getString("ProductDao.0")); //$NON-NLS-1$
	}

	public boolean saveStock(StockBO stockBo) throws Exception
	{

		logger.debug(Messages.getString("StockDao.1") + stockBo); //$NON-NLS-1$

		Connection connection = DBConnection.getConnection();
		PreparedStatement pst = connection.prepareStatement(Messages.getString("StockDao.insert-into-stock-table-query")); //$NON-NLS-1$
		pst.setString(1, stockBo.getStockId());
		pst.setString(2, stockBo.getProductName());
		pst.setString(3, stockBo.getQuantity());
		pst.setString(4, stockBo.getAddedDate());
		pst.setString(5, stockBo.getAddedBy());
		
		int count = pst.executeUpdate();

		DBConnection.releaseConnection(connection, pst, null);
		if (count > 0)
			return true;
		else
			return false;
	}

	public List<ProductBO> fetchAllProducts() throws SQLException
	{
		List<ProductBO> list = new ArrayList<ProductBO>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst = connection.prepareStatement(Messages.getString("ProductDao.select-all-product-data-query")); //$NON-NLS-1$
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			do {
				ProductBO bo = new ProductBO();
				bo.setProductId(rs.getString(1));
				bo.setProductName(rs.getString(2));
				bo.setDescription(rs.getString(3));
				bo.setManufacturer(rs.getString(4));
				bo.setPrice(rs.getFloat(5));
				bo.setRackNumber(rs.getString(6));
				list.add(bo);
			}
			while (rs.next());
		}
		DBConnection.releaseConnection(connection, pst, rs);
		return list;
	}
}
