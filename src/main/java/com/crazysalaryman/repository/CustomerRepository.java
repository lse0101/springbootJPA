package com.crazysalaryman.repository;

import com.crazysalaryman.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lse0101 on 2017-01-23.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    @Query("SELECT x from Customer x order by x.firstName, x.lastName")
    List<Customer> findAllOrderByName();

    @Query("SELECT x from Customer x order by x.firstName, x.lastName")
    Page<Customer> findAllOrderByName(Pageable pageable);

}
