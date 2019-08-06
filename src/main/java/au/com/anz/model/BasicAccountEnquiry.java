package au.com.anz.model;

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
@Table(name = "basicAttribute")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")

public class BasicAccountEnquiry implements Serializable {
	private static final long serialVersionUID = -7688086163096128364L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotNull
	private String accountNumber;
	private String accountName;
	private String type;
	private String balanceDate;
	private String currency;
	private Double openingBalance;
	@OneToMany(mappedBy="enquiry", fetch = FetchType.LAZY,cascade = CascadeType.ALL, targetEntity = AccountTransaction.class)
	private List<AccountTransaction>transactions;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}
	/**
	 * 
	 */
	public List<AccountTransaction> getTransactions() {
		if(this.transactions== null)
		{
			this.transactions= new ArrayList<>();
		}
		return transactions;
	}
	public void setTransactions(List<AccountTransaction> transactions) {
		this.transactions = transactions;
	}
	
	
	public void addTransaction(AccountTransaction transaction)
	{
		this.getTransactions().add(transaction);
		transaction.setEnquiry(this);
	}
	
}
