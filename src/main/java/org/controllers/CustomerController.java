package org.controllers;

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
	
	@RequestMapping("/")
	@ResponseBody
	public String info() {

		return "works";
	}

	@RequestMapping("/customer")
	@ResponseBody
	public ResponseEntity<CustomerResponse> getTransactions(@RequestParam final String id,
			@RequestParam final String mon,
			@RequestParam final String y) {

		ResponseEntity<CustomerResponse> entity = null;

		if (StringUtils.isEmpty(id)) {

			CustomerResponse res = new CustomerResponse();
			res.addError("C001", "Customer id is a required parameter");

			entity = new ResponseEntity<CustomerResponse>(
					res,
					HttpStatus.BAD_REQUEST);

			return entity;
		}

		if (StringUtils.isEmpty(mon)) {

			CustomerResponse res = new CustomerResponse();
			res.addError("C002", "month is a required parameter");

			entity = new ResponseEntity<CustomerResponse>(
					res,
					HttpStatus.BAD_REQUEST);

			return entity;
		}
		
		if (StringUtils.isEmpty(y)) {

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
		
		Customer c = customerService.getCustomerReport(customerID, month, year);
		
		return new ResponseEntity<CustomerResponse>(
				new CustomerResponse(c),
				HttpStatus.OK);
	}
}