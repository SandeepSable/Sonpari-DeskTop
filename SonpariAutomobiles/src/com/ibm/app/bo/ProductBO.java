package com.ibm.app.bo;

import org.springframework.stereotype.Component;

@Component
public class ProductBO
{
	private String	productId;
	private String	productName;
	private String	manufacturer;
	private String	description;
	private Double	price;
	private String	rackNumber;
	private Double	gst;
	private Double	discount;
	private String  isActiveProduct;
	
	public String getIsActiveProduct()
	{
		return isActiveProduct;
	}

	public void setIsActiveProduct(String isActiveProduct)
	{
		this.isActiveProduct = isActiveProduct;
	}

	public ProductBO()
	{
	}
	
	public Double getGst()
	{
		return gst;
	}

	public void setGst(Double gst)
	{
		this.gst = gst;
	}

	public Double getDiscount()
	{
		return discount;
	}

	public void setDiscount(Double discount)
	{
		this.discount = discount;
	}

	public void setPrice(double f)
	{
		this.price = f;
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
	
	public Double getPrice()
	{
		return price;
	}
	
	public String getRackNumber()
	{
		return rackNumber;
	}
	
	public void setRackNumber(String rackNumber)
	{
		this.rackNumber = rackNumber;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ProductBO [productId=").append(productId)
		        .append(", productName=").append(productName)
		        .append(", manufacturer=").append(manufacturer)
		        .append(", description=").append(description).append(", price=")
		        .append(price).append(", rackNumber=").append(rackNumber)
		        .append(", gst=").append(gst).append(", discount=")
		        .append(discount).append(", isActiveProduct=")
		        .append(isActiveProduct).append("]");
		return builder.toString();
	}
	
}
