package org.service.repositories;

import java.io.Serializable;
import java.util.List;

import org.models.domain.CustomerRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerRecord, Serializable>  {

	List<CustomerRecord> findByCustomerIdOrderByDateAscAmountAsc(long customerId);
	List<CustomerRecord> findByCustomerIdAndMonthAndYearOrderByDateAscAmountAsc(long customerId, int month, int year);
	List<CustomerRecord> findByCustomerIdAndYearOrderByDateAscAmountAsc(long customerId, int year);
	
	@Query(value="select distinct c.customerId from CustomerRecord c")
	List<Integer> findAllDistinctCustomers();
	
	@Query(value="select sum(c.amount) from CustomerRecord c where date <= CURRENT_DATE and customerId = ?1")
	double sumByAmountLessThanEqual(long customerId);
	
}
