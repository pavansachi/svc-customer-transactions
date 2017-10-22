package org.service;

import java.util.List;

import org.models.domain.CustomerRecord;

@FunctionalInterface
public interface ICustomerRule {
	
	public boolean apply(List<CustomerRecord> list);
}
