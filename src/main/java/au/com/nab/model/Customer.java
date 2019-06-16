package au.com.nab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "customer")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8222793583395254730L;
	public Customer()
	{
		
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Customer(String pin, String custName) {
		super();
		this.pin = pin;
		this.custName = custName;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String pin;
	@NotNull
	private String custName;
	@OneToMany(mappedBy="customer", fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Contact.class)
	private List<Contact> contacts;
	public List<Contact> getContacts() {
		if(this.contacts== null)
		{
			this.contacts= new ArrayList<>();
		}
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContacts(Contact contact)
	{
		this.getContacts().add(contact);
		contact.setCustomer(this);
	}
	

}
