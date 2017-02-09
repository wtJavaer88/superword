package com.wnc.superword.bbei.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "milk")
public class Milk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select max(id)+1 from milk")
	private Integer id;
	private Integer brandId;
	private String pic;
	private String name;
	private String url;
	private double price;

	public Milk() {

	}

	public Milk(String pic, String name, String url, double price) {
		super();
		this.pic = pic;
		this.name = name;
		this.url = url;
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Milk [brand=" + brandId + ", pic=" + pic + ", name=" + name + ", url=" + url + ", price=" + price + "]";
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBrand() {
		return brandId;
	}

	public void setBrand(int brandId) {
		this.brandId = brandId;
	}
}
