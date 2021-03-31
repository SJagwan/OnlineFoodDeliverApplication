package com.cg.fds.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fds.entities.Address;

public interface IAddressRepository extends JpaRepository<Address,String> {
	
	Address findAddressByArea(String location);

}
