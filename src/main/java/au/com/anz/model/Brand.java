package au.com.anz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BRAND")
public class Brand implements Serializable{

	/**
	 * 
	 */
	protected Brand() {};
	private static final long serialVersionUID = -5249835487402913694L;
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
	public Brand(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

}
