package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
