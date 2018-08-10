package org.txazo.pattern.creational.builder.simple;

public class Product {

	private Integer productId;
	private String name;
	private double price;
	private String personName;
	private String companyName;

	protected Product(ProductBuilder builder) {
		this.productId = builder.getProductId();
		this.name = builder.getName();
		this.price = builder.getPrice();
		this.personName = builder.getPersonName();
		this.companyName = builder.getCompanyName();
	}

	public void product() {
		System.out.println("Create " + this);
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", personName=" + personName + ", companyName="
				+ companyName + "]";
	}

}
