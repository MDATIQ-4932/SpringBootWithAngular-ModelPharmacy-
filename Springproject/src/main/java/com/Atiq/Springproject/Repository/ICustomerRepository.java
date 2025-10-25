package com.Atiq.Springproject.Repository;


import com.Atiq.Springproject.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface ICustomerRepository extends JpaRepository<Customer,Integer> {


}
