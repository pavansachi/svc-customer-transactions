package org.service;

import org.models.Customer;

public interface CustomerService {

	public Customer getCustomerReport(long customerId);
	
	public Customer getCustomerReport(long customerId, int month, int year);
	
	public Customer getCustomerReport(long customerId, int year);
	
}
