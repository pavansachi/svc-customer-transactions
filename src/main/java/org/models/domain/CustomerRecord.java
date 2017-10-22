package org.models.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CustomerRecord {

	@Id
	@GeneratedValue
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private long customerId;
	private double amount;
	private Date date;
	private int month;
	private int year;
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description;
	
	public CustomerRecord() {
		
	}
	
	public CustomerRecord(long customerId, Date date, double amount, String desc) {
		super();
		this.customerId = customerId;
		this.amount = amount;
		this.date = date;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		setMonth(cal.get(Calendar.MONTH));
		setYear(cal.get(Calendar.YEAR));
		
		this.description = desc;
	}

	@Override
	public String toString() {
		return "CustomerRecord [id=" + id + ", customerId=" + customerId + ", amount=" + amount + ", date=" + date
				+ ", month=" + month + ", year=" + year + ", description=" + description + "]";
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		
		this.date = date;
	}

}
