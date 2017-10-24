package org.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.models.Customer;
import org.models.CustomerResponse;
import org.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * rest controller customer reports
 * @author pavansachi
 *
 */

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	final static Logger logger = Logger.getLogger(CustomerController.class);
	
	@RequestMapping("/")
	@ResponseBody
	public String info() {

		return "works";
	}

	@RequestMapping("/config")
	@ResponseBody
	public ResponseEntity<CustomerResponse> getCustomerList() {

		List<Integer> list = customerService.getConfig();
		
		logger.info("Number of customers returned: " + list.size());
		
		return new ResponseEntity<CustomerResponse>(
				new CustomerResponse(list),
				HttpStatus.OK);
	}
	
	@RequestMapping("/customer")
	@ResponseBody
	public ResponseEntity<CustomerResponse> getTransactions(@RequestParam final String id,
			@RequestParam final String mon,
			@RequestParam final String y) {

		ResponseEntity<CustomerResponse> entity = null;

		if (StringUtils.isEmpty(id)) {

			logger.error("Customer id is required");
			
			CustomerResponse res = new CustomerResponse();
			res.addError("C001", "Customer id is a required parameter");

			entity = new ResponseEntity<CustomerResponse>(
					res,
					HttpStatus.BAD_REQUEST);

			return entity;
		}

		if (StringUtils.isEmpty(mon)) {

			logger.error("month is required");
			
			CustomerResponse res = new CustomerResponse();
			res.addError("C002", "month is a required parameter");

			entity = new ResponseEntity<CustomerResponse>(
					res,
					HttpStatus.BAD_REQUEST);

			return entity;
		}
		
		if (StringUtils.isEmpty(y)) {

			logger.error("year is required");
			
			CustomerResponse res = new CustomerResponse();
			res.addError("C003", "year is a required parameter");

			entity = new ResponseEntity<CustomerResponse>(
					res,
					HttpStatus.BAD_REQUEST);

			return entity;
		}

		long customerID = Long.parseLong(id);
		int month = Integer.parseInt(mon);
		int year = Integer.parseInt(y);
		
		logger.info(String.format("customer %s, month %s, year %s", customerID, month, year));
		
		Customer c = customerService.getCustomerReport(customerID, month, year);
		
		logger.info(c);
		
		return new ResponseEntity<CustomerResponse>(
				new CustomerResponse(c),
				HttpStatus.OK);
	}
}