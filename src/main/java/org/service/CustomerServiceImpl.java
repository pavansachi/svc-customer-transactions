package org.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.models.Customer;
import org.models.Transaction;
import org.models.domain.CustomerRecord;
import org.service.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * customer service
 * @author pavansachi
 *
 */

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	final static Logger logger = Logger.getLogger(CustomerServiceImpl.class);
	
	@Override
	public Customer getCustomerReport(long customerId) {

		return null;
	}

	@Override
	public Customer getCustomerReport(long customerId, int month, int year) {

		List<CustomerRecord> records = repository.findByCustomerIdAndMonthAndYearOrderByDateAscAmountAsc(customerId, month, year);
		
		double balance = repository.sumByAmountLessThanEqual(customerId);

		Customer c = new Customer();

		c.setCustomerId(customerId);
		
		List<String> classifications = CustomerRules.applyRules(records);
		
		c.setClassification(classifications);
		
		logger.debug("Classifications: " + classifications);
		
		boolean loanCustomer = classifications.containsAll(Arrays.asList("BIG_SPENDER", "FAST_SPENDER"));
		
		c.setLoanCustomer(loanCustomer);
		
		logger.info("loan customer: " + loanCustomer);
		
		List<Transaction> transactions = records.stream().map(r -> new Transaction(r.getAmount(), r.getDescription(), r.getDate()))
				.collect(Collectors.toList());

		c.setTransactions(transactions);

		String sAmt = String.format("%.2f", balance);
		
		balance = Double.parseDouble(sAmt);
		
		c.setAmount(balance);
		
		logger.debug("Balance as of now: " + balance);
		
		return c;
	}

	@Override
	public Customer getCustomerReport(long customerId, int year) {

		return null;
	}

	@Override
	public List<Integer> getConfig() {

		return repository.findAllDistinctCustomers();
	}
}
