package com.ibm.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibm.app.bo.ProductBO;
import com.ibm.app.exceptions.ProductNotFoundException;
import com.ibm.app.utilities.DBConnection;
import com.ibm.app.utilities.QueryUtility;

@Component
@Service
public class ProductDao
{
	final static Logger					logger		= Logger.getLogger(ProductDao.class);
	private static PreparedStatement	pst;
	private static Connection			connection	= DBConnection.getConnection();
	
	public ProductDao()
	{
		logger.debug("Inside the product dao");
	}
	
	public static Boolean saveProduct(ProductBO productBo) throws Exception
	{
		logger.debug("in product dao saveProduct method" + productBo);
		
		PreparedStatement pst = connection.prepareStatement(QueryUtility.SP_PRODUCT_INSERT_QUERY);
		pst.setString(1, productBo.getProductId());
		pst.setString(2, productBo.getProductName());
		pst.setString(3, productBo.getManufacturer());
		pst.setString(4, productBo.getDescription());
		pst.setDouble(5, productBo.getPrice());
		pst.setString(6, productBo.getRackNumber());
		pst.setDouble(7, productBo.getGst());
		pst.setDouble(8, productBo.getDiscount());
		pst.setString(9, productBo.getIsActiveProduct());
		
		int count = pst.executeUpdate();
		
		DBConnection.releaseConnection(connection, pst, null);
		if (count > 0)
			return true;
		else
			return false;
	}
	
	public static List<ProductBO> fetchAllProducts() throws SQLException
	{
		List<ProductBO> list = new ArrayList<ProductBO>();
		PreparedStatement pst = connection
		        .prepareStatement(QueryUtility.SP_PRODUCT_SELECT_QUERY);
		ResultSet rs = pst.executeQuery();
		if (rs.next())
		{
			do
			{
				ProductBO bo = new ProductBO();
				bo.setProductId(rs.getString(1));
				bo.setProductName(rs.getString(2));
				bo.setDescription(rs.getString(3));
				bo.setManufacturer(rs.getString(4));
				bo.setPrice(rs.getDouble(5));
				bo.setRackNumber(rs.getString(6));
				bo.setGst(rs.getDouble(7));
				bo.setDiscount(rs.getDouble(8));
				bo.setIsActiveProduct(rs.getString(9));
				
				list.add(bo);
			} while (rs.next());
		}
		DBConnection.releaseConnection(connection, pst, rs);
		return list;
	}
	
	public static Boolean updateProduct(ProductBO productBo) throws Exception
	{
		if (connection == null)
		{
			connection = DBConnection.getConnection();
		}
		pst = connection.prepareStatement(QueryUtility.SP_PRODUCT_UPDATE_QUERY);
		
		pst.setString(1, productBo.getProductName());
		pst.setString(2, productBo.getManufacturer());
		pst.setString(3, productBo.getDescription());
		pst.setDouble(4, productBo.getPrice());
		pst.setString(5, productBo.getRackNumber());
		pst.setDouble(6, productBo.getGst());
		pst.setDouble(7, productBo.getDiscount());
		pst.setString(8, productBo.getIsActiveProduct().toString());
		pst.setString(9, productBo.getProductId());
		int count = pst.executeUpdate();
		
		DBConnection.releaseConnection(connection, pst, null);
		
		if (count > 0)
			return true;
		else
			return false;
		
	}
	
	public static Boolean deleteProduct(String productId) throws SQLException
	{
		
		logger.debug("ProductDao.deleteProduct()");
		pst = connection.prepareStatement(QueryUtility.SP_PRODUCT_DELETE_A_RECORD_QUERY);
		pst.setString(1, productId);
		int result = pst.executeUpdate();
		
		if (result > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public static Boolean validateProductId(String productId) throws ProductNotFoundException, SQLException
	{
		 pst = connection.prepareStatement(QueryUtility.SP_PRODUCT_TABLE_FIND_PRODUCT_WITH_ID);
		 pst.setString(1, productId);
		 ResultSet rs  = pst.executeQuery();
		 if(rs.next())
		 {
			 return true;
		 }
		 else
		 {
			 throw new ProductNotFoundException("Product "+productId+" Not found");
		 }
	}
}
