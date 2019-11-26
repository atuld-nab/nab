package au.com.anz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="PRODUCT")
public class Product implements Serializable{

	public Product() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2157577923857796101L;
	public Product(Long sku, String name, String color, String size, Double price, Long category_id, Long brand_id,
			Long supplier_id) {
		super();
		this.sku = sku;
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.category_id = category_id;
		this.brand_id = brand_id;
		this.supplier_id = supplier_id;
	}
	@Id
	@Column(name = "sku", nullable = false)
	private Long sku;
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	@NotNull
	@Column(name = "color", nullable = false)
	private String color;
	@Column(name = "size", nullable = false)
	private String size;
	@Column(name = "price", nullable = false)
	private Double price;
	@Column(name = "category_id", nullable = false)
	private Long category_id;
	@Column(name = "brand_id", nullable = false)
	private Long brand_id;
	@Column(name = "supplier_id", nullable = false)
	private Long supplier_id;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public Long getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}
	public Long getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}

}
