package org.service.repositories;

import java.io.Serializable;
import java.util.List;

import org.models.domain.CustomerRecord;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerRecord, Serializable>  {

	List<CustomerRecord> findByCustomerIdOrderByDateAscAmountAsc(long customerId);
	List<CustomerRecord> findByCustomerIdAndMonthAndYearOrderByDateAscAmountAsc(long customerId, int month, int year);
	List<CustomerRecord> findByCustomerIdAndYearOrderByDateAscAmountAsc(long customerId, int year);
	
}
