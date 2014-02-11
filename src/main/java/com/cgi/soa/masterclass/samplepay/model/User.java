package com.cgi.soa.masterclass.samplepay.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.cgi.soa.masterclass.samplepay.validator.annotation.AccountAvailable;
import com.cgi.soa.masterclass.samplepay.validator.annotation.AccountOwner;

@Entity
@Table(name="USERS")
@AccountOwner(accountFirstName="accountFirstName", accountLastName="accountLastName", accountNumber="accountNumber")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Basic
	@NotNull
	private String firstName;

	@Basic
	@NotNull
	private String lastName;
	
	@Basic
	@NotNull
	@Email
	private String email;

	@Basic
	@NotNull
	@AccountAvailable
	private Integer accountNumber;

	@Basic
	@NotNull
	private String accountFirstName;

	@Basic
	@NotNull
	private String accountLastName;

	@Basic
	@NotNull
	private BigDecimal balance;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountFirstName() {
		return accountFirstName;
	}

	public void setAccountFirstName(String accountFirstName) {
		this.accountFirstName = accountFirstName;
	}

	public String getAccountLastName() {
		return accountLastName;
	}

	public void setAccountLastName(String accountLastName) {
		this.accountLastName = accountLastName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
