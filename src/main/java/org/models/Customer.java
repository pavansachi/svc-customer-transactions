package org.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private long customerId;
	private double amount;
	private List<String> classification;
	public List<String> getClassification() {
		return classification;
	}

	public void setClassification(List<String> classification) {
		this.classification = classification;
	}

	public boolean isLoanCustomer() {
		return loanCustomer;
	}

	public void setLoanCustomer(boolean loanCustomer) {
		this.loanCustomer = loanCustomer;
	}

	private boolean loanCustomer;
	
	private List<Transaction> transactions;
	
	public Customer() {
		classification = new ArrayList<>();
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", amount=" + amount + ", classification=" + classification
				+ ", loanCustomer=" + loanCustomer + ", transactions=" + transactions + "]";
	}
	
}
