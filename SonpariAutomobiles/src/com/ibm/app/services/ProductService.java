package com.ibm.app.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ibm.app.bo.ProductBO;
import com.ibm.app.dao.ProductDao;
import com.ibm.app.exceptions.DatabaseException;
import com.ibm.app.exceptions.ProductNotFoundException;

@Component
public class ProductService
{
	final static Logger logger = Logger.getLogger(ProductService.class);
	
	public static Boolean addProduct(ProductBO productBo) throws Exception
	{
		Boolean flag = false;
		logger.debug("Starting to addProduct ...");
		flag = ProductDao.saveProduct(productBo);
		logger.debug("Exiting from addProductService...");
		return flag;
	}
	
	public static List<ProductBO> getAllProducts() throws Exception
	{
		List<ProductBO> productBoList = ProductDao.fetchAllProducts();
		return productBoList;
	}
	
	public static Boolean updateProduct(final ProductBO productBo)
	        throws Exception
	{
		logger.debug("starting to update the product");
		Boolean flag = ProductDao.updateProduct(productBo);
		logger.debug("exiting from product service.");
		return flag;
		
	}
	
	public static Boolean deleteProduct(String productId) throws Exception
	{
		
		logger.debug("ProductService.deleteProduct()");
		return ProductDao.deleteProduct(productId);
		
	}

	public static Boolean isValidProductId(String productId) throws ProductNotFoundException, SQLException
	{
			return ProductDao.validateProductId(productId);
	}
	
}
