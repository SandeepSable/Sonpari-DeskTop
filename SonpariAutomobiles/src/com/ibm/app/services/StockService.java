package com.ibm.app.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ibm.app.bo.StockBO;
import com.ibm.app.dao.StockDao;

@Component
public class StockService
{
	final static Logger logger = Logger.getLogger(ProductService.class);

	public void addStockService(StockBO stockBO) throws Exception
	{
		logger.debug("Starting to addStock ...");		
		StockDao stockDao=new StockDao();
		stockDao.saveStock(stockBO);
		logger.debug("Exiting from addProductService...");
	}

	/*public List<StockBO> getAllProducts() throws Exception
	{
		ProductDao dao = new ProductDao();
		List<ProductBO> productBoList = dao.fetchAllProducts();
		return productBoList;
	}
*/
}
