package com.ibm.app.bo;

import org.springframework.stereotype.Component;

@Component
public class StockBO {

	private String stockId;
	private String productName;
	private String quantity;
	private String addedDate;
	private String addedBy;

	public StockBO() {
	}

	public StockBO(String stockId, String productName, String quantity, String addedDate, String addedBy) {

		this.stockId = stockId;
		this.productName = productName;
		this.quantity = quantity;
		this.addedDate = addedDate;
		this.addedBy = addedBy;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductVO [productId=");
		builder.append(stockId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", addedDate=");
		builder.append(addedDate);
		builder.append(", addedBy=");
		builder.append(addedBy);
		builder.append("]");
		return builder.toString();
	}

}
