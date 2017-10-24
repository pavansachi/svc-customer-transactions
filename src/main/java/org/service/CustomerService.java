package org.service;

import java.util.List;

import org.models.Customer;

public interface CustomerService {

	public Customer getCustomerReport(long customerId);
	
	public Customer getCustomerReport(long customerId, int month, int year);
	
	public Customer getCustomerReport(long customerId, int year);
	
	public List<Integer> getConfig();
	
}
