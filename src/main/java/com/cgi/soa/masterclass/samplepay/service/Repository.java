package com.cgi.soa.masterclass.samplepay.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.soa.masterclass.samplepay.model.Fee;
import com.cgi.soa.masterclass.samplepay.model.Transaction;
import com.cgi.soa.masterclass.samplepay.model.User;

@Stateless
public class Repository {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private BankWs bankWs;

	private static final BigDecimal FEE_FACTOR = BigDecimal.valueOf(0.03);

	private static final Integer ACCOUNT_NUMBER = 8;

	public List<User> getUsers() {
		return (List<User>) entityManager.createQuery(
				"SELECT user FROM " + User.class.getName() + " user ",
				User.class).getResultList();
	}

	public List<Fee> getFees() {
		return (List<Fee>) entityManager.createQuery(
				"SELECT fee FROM " + Fee.class.getName() + " fee ", Fee.class)
				.getResultList();
	}

	public BigDecimal getFeeBalance() {
		return entityManager.createQuery(
				"SELECT SUM(fee.fee) FROM " + Fee.class.getName() + " fee ",
				BigDecimal.class).getSingleResult();
	}

	public User findById(Integer userId) {
		User user = entityManager.find(User.class, userId);
		return user;
	}

	public User findByEmail(String email) {
		User user = (User) entityManager
				.createQuery(
						"SELECT user FROM " + User.class.getName()
								+ " user WHERE user.email = :email ")
				.setParameter("email", email).getSingleResult();
		return user;
	}

	public void createUser(User user) {
		user.setBalance(BigDecimal.ZERO);
		entityManager.persist(user);
		entityManager.flush();
	}

	public User merge(User user) {
		entityManager.merge(user);
		entityManager.flush();
		return entityManager.find(User.class, user.getId());
	}

	public User pay(Transaction transaction) {
		Date time = Calendar.getInstance().getTime();
		BigDecimal amount = transaction.getAmount();
		BigDecimal fee = amount.multiply(FEE_FACTOR);
		Fee feeObj = new Fee();
		feeObj.setFee(fee);
		feeObj.setTransaction(transaction);
		transaction.setDate(time);
		transaction.setAmount(transaction.getAmount().multiply(
				BigDecimal.valueOf(-1)));
		transaction.setFee(feeObj);
		transaction.getUser().setBalance(
				transaction
						.getUser()
						.getBalance()
						.add(transaction.getAmount())
						.add(transaction.getFee().getFee()
								.multiply(BigDecimal.valueOf(-1))));
		transaction.getUser().getTransactions().add(transaction);
		Transaction recipient = new Transaction();
		recipient.setUser(transaction.getRecipient());
		recipient.setAmount(amount);
		Fee recipientFee = new Fee();
		recipientFee.setFee(BigDecimal.ZERO);
		recipient.setFee(recipientFee);
		recipient.setDate(time);
		recipient.setPurpose(transaction.getPurpose());
		recipient.setRecipient(transaction.getUser());
		transaction.getRecipient().getTransactions().add(recipient);
		transaction.getRecipient().setBalance(
				transaction.getRecipient().getBalance().add(amount));
		entityManager.merge(transaction.getUser());
		entityManager.merge(transaction.getRecipient());
		entityManager.flush();
		return entityManager.find(User.class, transaction.getUser().getId());
	}

	public User deposit(Transaction transaction) {
		if (bankWs.getService().isBalanceCovered(
				transaction.getUser().getAccountNumber(),
				transaction.getAmount())) {
			Date time = Calendar.getInstance().getTime();
			Fee feeObj = new Fee();
			feeObj.setTransaction(transaction);
			feeObj.setFee(BigDecimal.ZERO);
			transaction.setDate(time);
			transaction.setAmount(transaction.getAmount());
			transaction.setFee(feeObj);
			transaction.getUser().setBalance(
					transaction.getUser().getBalance()
							.add(transaction.getAmount()));
			transaction.getUser().getTransactions().add(transaction);
			transaction.setRecipient(transaction.getUser());
			bankWs.getService().transfer(
					transaction.getUser().getAccountNumber(), ACCOUNT_NUMBER,
					transaction.getPurpose(), transaction.getAmount());
			entityManager.merge(transaction.getUser());
			entityManager.flush();
			return entityManager
					.find(User.class, transaction.getUser().getId());
		} else {
			throw new RuntimeException("Balance not covered.");
		}
	}

	public User clearing(Transaction transaction) {
		Date time = Calendar.getInstance().getTime();
		BigDecimal amount = transaction.getAmount();
		BigDecimal fee = amount.multiply(FEE_FACTOR);
		Fee feeObj = new Fee();
		feeObj.setTransaction(transaction);
		feeObj.setFee(fee);
		transaction.setDate(time);
		transaction.setAmount(transaction.getAmount().multiply(
				BigDecimal.valueOf(-1)));
		transaction.setFee(feeObj);
		transaction.getUser().setBalance(
				transaction
						.getUser()
						.getBalance()
						.add(transaction.getAmount())
						.add(transaction.getFee().getFee()
								.multiply(BigDecimal.valueOf(-1))));
		transaction.getUser().getTransactions().add(transaction);
		transaction.setRecipient(transaction.getUser());
		bankWs.getService().transfer(ACCOUNT_NUMBER,
				transaction.getUser().getAccountNumber(),
				transaction.getPurpose(), amount);
		entityManager.merge(transaction.getUser());
		entityManager.flush();
		return entityManager.find(User.class, transaction.getUser().getId());
	}
}
