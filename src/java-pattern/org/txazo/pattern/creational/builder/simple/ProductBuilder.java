package org.txazo.pattern.creational.builder.simple;

public class ProductBuilder {

	private Integer productId;
	private String name;
	private double price;
	private String personName;
	private String companyName;

	public ProductBuilder(Integer productId, String name, double price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	public Product build() {
		return new Product(this);
	}

	public ProductBuilder setPersonName(String personName) {
		this.personName = personName;
		return this;
	}

	public ProductBuilder setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public Integer getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getPersonName() {
		return personName;
	}

	public String getCompanyName() {
		return companyName;
	}

}
