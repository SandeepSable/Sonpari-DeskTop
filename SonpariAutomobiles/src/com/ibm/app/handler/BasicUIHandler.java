package com.ibm.app.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibm.app.bo.ProductBO;
import com.ibm.app.bo.StockBO;
import com.ibm.app.exceptions.ProductException;
import com.ibm.app.services.ProductService;
import com.ibm.app.services.StockService;
import com.ibm.app.validators.BasicValidator;
import com.ibm.app.validators.ProductValidator;
import com.ibm.app.vo.ProductVO;
import com.ibm.app.vo.StockVO;

public class BasicUIHandler
{
	final static Logger logger = Logger.getLogger(BasicUIHandler.class);
	
	public static Boolean saveProduct(ProductVO product, String addOrEdit)
	        throws Exception
	{
		Boolean returnResponse = false;
		logger.debug("BasicUIHandler.saveProduct()... start");
		ProductBO productBo = ProductValidator.validateProduct(product);
		if (addOrEdit == "ADD")
		{
			returnResponse = ProductService.addProduct(productBo);
		} else if (addOrEdit == "EDIT")
		{
			returnResponse = ProductService.updateProduct(productBo);
		}
		logger.debug("BasicUIHandler.saveProduct()...exiting");
		return returnResponse;
	}
	public static void saveStock(StockVO stock) throws Exception
	{
		logger.debug("BasicUIHandler.saveStock()... start");
		StockBO stockBo = ProductValidator.validateStock(stock);
		StockService service = new StockService();
		service.addStockService(stockBo);
		
		logger.debug("BasicUIHandler.saveStock()...exiting");
	}
	
	public static List<ProductVO> getAllproducts() throws Exception
	{
		List<ProductBO> list = ProductService.getAllProducts();
		List<ProductVO> productVOList = new ArrayList<ProductVO>();
		for (ProductBO bo : list)
		{
			ProductVO vo = new ProductVO();
			vo.setDescription(bo.getDescription());
			vo.setPrice(bo.getPrice().toString());
			vo.setProductId(bo.getProductId());
			vo.setProductName(bo.getProductName());
			vo.setRackNumber(bo.getRackNumber());
			vo.setManufacturer(bo.getManufacturer());
			vo.setEnablity(bo.getIsActiveProduct());
			vo.setGst(bo.getGst().toString());
			vo.setDiscount(bo.getDiscount().toString());
			productVOList.add(vo);
		}
		return productVOList;
	}
	public static Boolean handleDeleteProductRequest(String productId) throws Exception
	{
		logger.debug("BasicUIHandler.handleDeleteProductRequest()");
		Boolean isProductNull = BasicValidator.isNull(productId);
		Boolean isProductIdAvailable = ProductService.isValidProductId(productId);
		if (!isProductNull && isProductIdAvailable)
		{
			logger.debug("product id is valid :"+productId);
			return ProductService.deleteProduct(productId);
		}
		throw new ProductException();
	}
	
}
