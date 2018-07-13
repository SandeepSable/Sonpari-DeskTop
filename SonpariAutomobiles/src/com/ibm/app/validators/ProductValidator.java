package com.ibm.app.validators;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ibm.app.bo.ProductBO;
import com.ibm.app.bo.StockBO;
import com.ibm.app.exceptions.ProductException;
import com.ibm.app.vo.ProductVO;
import com.ibm.app.vo.StockVO;

@Component
public class ProductValidator 
{
	final static Logger logger = Logger.getLogger(ProductValidator.class);

	public static ProductBO validateProduct(ProductVO product) throws ProductException 
	{
		logger.debug("controll inside validateProduct method...");
		ProductBO productBo = new ProductBO();
		productBo.setDescription(product.getDescription());
		productBo.setManufacturer(product.getManufacturer());
		productBo.setPrice(Double.parseDouble(product.getPrice()));
		productBo.setProductId(product.getProductId());
		productBo.setProductName(product.getProductName());
		productBo.setRackNumber(product.getRackNumber());
		productBo.setGst((Double.parseDouble(product.getGst())));
		productBo.setDiscount(Double.parseDouble(product.getDiscount()));
		Boolean isEnabledProductFlag = new Boolean(product.getEnablity());
		if(isEnabledProductFlag)
		{
		 productBo.setIsActiveProduct("T");
		}
		else
		{
			productBo.setIsActiveProduct("F");	
		}
		logger.debug("about to return from validateProduct method...");
		return productBo;
	}
	public static StockBO validateStock(StockVO stock) throws ProductException 
	{
		logger.debug("controll inside validateStock method...");
		StockBO stockBO = new StockBO();

		stockBO.setStockId(stock.getStockId());		
		stockBO.setProductName(stock.getProductName());
		stockBO.setQuantity(stock.getQuantity());
		stockBO.setAddedDate(stock.getAddedDate());
		stockBO.setAddedBy(stock.getAddedBy());
		logger.debug("about to return from validateProduct method...");
		return stockBO;
	}


}
