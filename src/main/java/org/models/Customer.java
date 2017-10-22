package org.models;

import java.util.Date;

public class Customer {

	private long customerId;
	private Date date;
	private double amount;
	private String desc;
	
	public Customer() {
		
	}
	
	public Customer(long customerId, Date date, double amount, String desc) {
		super();
		this.customerId = customerId;
		this.date = date;
		this.amount = amount;
		this.desc = desc;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
//	@Override
//	public int compareTo(Customer o) {
//		
//		int res = -1;
//		
//		// sort by customer id
//		long id1 = this.customerId;
//		long id2 = o.customerId;
//		
//		res = (int)(id1 - id2);
//		
//		// sort by date
//		
//		if (res == 0) {
//			return this.getDate().compareTo(o.getDate());
//		}
//		
//		// sort by amount
//		
//		if (res == 0) {
//			return (int)(this.getAmount() - o.getAmount());
//		}
//		
//		return res;
//	}
	
}
