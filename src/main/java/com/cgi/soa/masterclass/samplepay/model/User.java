package com.cgi.soa.masterclass.samplepay.model;

import java.io.Serializable;
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

import com.cgi.soa.masterclass.samplepay.validator.annotation.AccountAvailable;

@Entity
@Table(name="USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 126754866666765979L;

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
