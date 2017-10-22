package org.service.repositories;

import java.io.Serializable;

import org.models.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Serializable>  {

}
