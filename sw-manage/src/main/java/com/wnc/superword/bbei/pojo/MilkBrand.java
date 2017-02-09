package com.wnc.superword.bbei.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "milk_brand")
public class MilkBrand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select max(id)+1 from milk_brand")
	private int id;
	private String pic;
	private String name;
	private String url;

	public MilkBrand(String pic, String name, String url) {
		super();
		this.pic = pic;
		this.name = name;
		this.url = url;
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
		return "MilkBrand [pic=" + pic + ", name=" + name + ", url=" + url + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
