package org.service;

import java.util.List;
import java.util.stream.Collectors;

import org.models.Customer;
import org.models.Transaction;
import org.models.domain.CustomerRecord;
import org.service.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer getCustomerReport(long customerId) {

		return null;
	}

	@Override
	public Customer getCustomerReport(long customerId, int month, int year) {

		List<CustomerRecord> records = repository.findByCustomerIdAndMonthAndYearOrderByDateAscAmountAsc(customerId, month, year);

		Customer c = new Customer();

		c.setCustomerId(customerId);
		
		List<String> classifications = CustomerRules.applyRules(records);
		
		c.setClassification(classifications);
		
		List<Transaction> transactions = records.stream().map(r -> new Transaction(r.getAmount(), r.getDescription(), r.getDate()))
				.collect(Collectors.toList());

		c.setTransactions(transactions);

		return c;
	}

	@Override
	public Customer getCustomerReport(long customerId, int year) {

		return null;
	}
}
