package au.com.anz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "transaction")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")

public class AccountTransaction implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 8012165984222812962L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotNull
private String accountNumber;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "enquiry_accountNumber")
private BasicAccountEnquiry enquiry;

public BasicAccountEnquiry getEnquiry() {
	return enquiry;
}
public void setEnquiry(BasicAccountEnquiry enquiry) {
	this.enquiry = enquiry;
}
private String accountName;
private String valueDate;
private String currency;
private Double debit;
private Double credit;
private String transactionType;
private String narrative;

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
public String getValueDate() {
	return valueDate;
}
public void setValueDate(String valueDate) {
	this.valueDate = valueDate;
}
public String getCurrency() {
	return currency;
}
public void setCurrency(String currency) {
	this.currency = currency;
}
public Double getDebit() {
	return debit;
}
public void setDebit(Double debit) {
	this.debit = debit;
}
public Double getCredit() {
	return credit;
}
public void setCredit(Double credit) {
	this.credit = credit;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public String getNarrative() {
	return narrative;
}
public void setNarrative(String narrative) {
	this.narrative = narrative;
}

}
