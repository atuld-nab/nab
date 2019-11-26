package au.com.anz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SUPPLIER")
public class Supplier {
	protected Supplier() {};
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Supplier(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
