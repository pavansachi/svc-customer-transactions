package org.models;

import java.util.Date;

public class Transaction {

	private double amount;
	private String description;
	private Date date;

	public Transaction() {

	}
	
	public Transaction(double amount, String description, Date date) {
		super();
		this.amount = amount;
		this.description = description;
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", description=" + description + ", date=" + date + "]";
	}

}
