package com.ibm.app.controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.ibm.app.exceptions.ProductException;
import com.ibm.app.exceptions.ProductNotFoundException;
import com.ibm.app.handler.BasicUIHandler;
import com.ibm.app.ui.AddProduct;
import com.ibm.app.ui.AddStock;
import com.ibm.app.ui.EditProduct;
import com.ibm.app.ui.Home;
import com.ibm.app.ui.ViewProduct;
import com.ibm.app.vo.ProductVO;
import com.ibm.app.vo.StockVO;

public class BasicUIController
{
	final static Logger logger = Logger.getLogger(BasicUIController.class);
	static
	{
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e)
		{
			logger.debug(e, e);
		}
		
	}
	
	public static void controllAddProductAction(ActionEvent event) throws PropertyVetoException
	{
		logger.debug("BasicUIController.controllAddProductAction()...Starting");
		AddProduct product = new AddProduct();
		Home.createFrame(product);
		// Home.jdpDesktop.setSelectedFrame(product);
		logger.debug("BasicUIController.controllAddProductAction()...Exiting");
	}
	
	public static List<ProductVO> getAllProducts() throws Exception
	{
		logger.debug("BasicUIController.getAllProducts()...Starting");
		List<ProductVO> list = BasicUIHandler.getAllproducts();
		logger.debug("BasicUIController.getAllProducts()...Exiting");
		return list;
		
	}
	
	public static void handleProductsViewRequest(ActionEvent e) throws Exception
	{
		logger.debug(
		        "BasicUIController.controlViewAllProductsAction()...Starting");
		ViewProduct product = new ViewProduct();
		Home.createFrame(product);
		logger.debug(
		        "BasicUIController.controlcontrolViewAllProductsActionlAddProductAction()...Exiting");
		
	}
	
	public static boolean saveProduct(ActionEvent e, ProductVO productVo,String addOrEdit)
	{
		logger.debug(productVo);
		try
		{
			BasicUIHandler.saveProduct(productVo,addOrEdit);
		}
		catch (Exception exception)
		{
			logger.debug(exception);
			return false;
		}
		return true;
	}
	public static boolean saveStock(ActionEvent e, StockVO stockVo)
	{
		logger.debug(stockVo);
		try
		{
			BasicUIHandler.saveStock(stockVo);
		} catch (Exception exception)
		{
			logger.debug(exception);
			return false;
		}
		return true;
	}
	
	public static void handleProductEditRequest(ActionEvent e)
	{
		EditProduct product = new EditProduct();
		Home.createFrame(product);
	}
	
	public static void handleDataBackupRequest(ActionEvent e)
	{
	}
	
	public static void handleAddStockRequest(ActionEvent e) throws PropertyVetoException
	{
		logger.debug(e);
		AddStock stock = new AddStock();
		Home.createFrame(stock);
	}

	public static Boolean handleDeleteProductRequest(String productId) throws Exception
	{
		logger.debug("BasicUIController.handleDeleteProductRequest()");
       		return BasicUIHandler.handleDeleteProductRequest(productId);
	}
	
}
