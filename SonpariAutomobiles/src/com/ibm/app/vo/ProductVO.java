package com.ibm.app.vo;

import org.springframework.stereotype.Component;

@Component
public class ProductVO
{
	private String	productId;
	private String	productName;
	private String	manufacturer;
	private String	description;
	private String	price;
	private String	rackNumber;
	private String	gst;
	private String	discount;
	private String	enablity;
	
	public String getGst()
	{
		return gst;
	}
	
	public void setGst(String gst)
	{
		this.gst = gst;
	}
	
	public String getDiscount()
	{
		return discount;
	}
	
	public void setDiscount(String discount)
	{
		this.discount = discount;
	}
	
	public String getProductId()
	{
		return productId;
	}
	
	public void setProductId(String productId)
	{
		this.productId = productId;
	}
	
	public String getProductName()
	{
		return productName;
	}
	
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
	public String getManufacturer()
	{
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public void setPrice(String price)
	{
		this.price = price;
	}
	
	public String getRackNumber()
	{
		return rackNumber;
	}
	
	public void setRackNumber(String rackNumber)
	{
		this.rackNumber = rackNumber;
	}
	
	public String getEnablity()
	{
		return enablity;
	}

	public void setEnablity(String enablity)
	{
		this.enablity = enablity;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ProductVO [productId=").append(productId)
		        .append(", productName=").append(productName)
		        .append(", manufacturer=").append(manufacturer)
		        .append(", description=").append(description).append(", price=")
		        .append(price).append(", rackNumber=").append(rackNumber)
		        .append(", gst=").append(gst).append(", discount=")
		        .append(discount).append(", enablity=").append(enablity)
		        .append("]");
		return builder.toString();
	}

	
	
}
